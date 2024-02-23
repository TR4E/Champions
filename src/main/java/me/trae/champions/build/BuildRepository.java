package me.trae.champions.build;

import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.build.enums.RoleBuildProperty;
import me.trae.framework.shared.config.AbstractConfig;
import me.trae.framework.shared.database.query.Query;
import me.trae.framework.shared.database.query.types.DeleteQuery;
import me.trae.framework.shared.database.query.types.SaveQuery;
import me.trae.framework.shared.database.query.types.SingleCallbackQuery;
import me.trae.framework.shared.database.query.types.UpdateQuery;
import me.trae.framework.shared.database.query.types.interfaces.ICallbackQuery;
import me.trae.framework.shared.database.repository.Repository;
import me.trae.framework.shared.database.repository.types.SingleLoadRepository;
import me.trae.framework.shared.database.repository.types.UpdateRepository;
import me.trae.framework.shared.utility.objects.EnumData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BuildRepository extends Repository<BuildManager> implements UpdateRepository<RoleBuild, RoleBuildProperty>, SingleLoadRepository {

    public BuildRepository(final BuildManager manager) {
        super(manager, "Builds");
    }

    @Override
    public boolean isInform(final Query query) {
        return query instanceof ICallbackQuery<?>;
    }

    @Override
    public void saveData(final RoleBuild roleBuild) {
        final SaveQuery<RoleBuildProperty> query = new SaveQuery<RoleBuildProperty>(roleBuild.getUUID().toString()) {
            @Override
            public EnumData<RoleBuildProperty> getData() {
                return roleBuild.getData();
            }

            @Override
            public List<String> getType() {
                return Arrays.asList(roleBuild.getName(), String.valueOf(roleBuild.getID()));
            }
        };

        this.addQuery(query);
    }

    @Override
    public void updateData(final RoleBuild roleBuild, final RoleBuildProperty property) {
        final UpdateQuery<RoleBuildProperty> query = new UpdateQuery<RoleBuildProperty>(roleBuild.getUUID().toString(), property, roleBuild.getPropertyByValue(property)) {
            @Override
            public List<String> getType() {
                return Arrays.asList(roleBuild.getName(), String.valueOf(roleBuild.getID()));
            }
        };

        this.addQuery(query);
    }


    @Override
    public void deleteData(final RoleBuild roleBuild) {
        final DeleteQuery query = new DeleteQuery(roleBuild.getUUID().toString()) {
            @Override
            public List<String> getType() {
                return Arrays.asList(roleBuild.getName(), String.valueOf(roleBuild.getID()));
            }
        };

        this.addQuery(query);
    }

    @Override
    public void loadData(final UUID uuid) {
        final SingleCallbackQuery<RoleBuildProperty> query = new SingleCallbackQuery<RoleBuildProperty>(uuid.toString()) {
            @Override
            public void onCallback(final EnumData<RoleBuildProperty> data) {
                final RoleBuild roleBuild = new RoleBuild(data);

                getManager().addRoleBuild(roleBuild);

                patchData(roleBuild, data);
            }

            @Override
            public List<String> getTypes(final AbstractConfig<?> config) {
                final List<String> list = new ArrayList<>();

                for (final String name : config.getSection(this.getKey())) {
                    for (final String id : config.getSection(String.format("%s.%s", this.getKey(), name))) {
                        list.add(String.format("%s.%s", name, id));
                    }
                }

                return list;
            }
        };

        this.addQuery(query);
    }

    @Override
    public void wipeData() {
        this.getManager().getRoleBuilds().clear();
    }
}