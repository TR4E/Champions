package me.trae.champions.build.menus.build;

import me.trae.champions.build.BuildManager;
import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.build.data.types.DefaultRoleBuild;
import me.trae.champions.build.menus.build.buttons.BuildArmourButton;
import me.trae.champions.build.menus.build.buttons.BuildDeleteButton;
import me.trae.champions.build.menus.build.buttons.BuildEditButton;
import me.trae.champions.build.menus.build.buttons.BuildEquipButton;
import me.trae.champions.build.menus.build.interfaces.IBuildCustomizationMenu;
import me.trae.champions.role.Role;
import me.trae.core.client.Client;
import me.trae.core.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class BuildCustomizationMenu extends Menu<BuildManager> implements IBuildCustomizationMenu {

    public BuildCustomizationMenu(final BuildManager manager, final Player player, final Role role) {
        super(manager, player, 45, String.format("<green><bold>%s Class Editor", role.getName()));
    }

    @Override
    public void fillPage(final Player player, final Client client) {
        this.addArmourButtons(new int[]{9, 18, 27, 36});

        for (int index = 0; index < 5; index++) {
            final RoleBuild roleBuild = this.getManager().getRoleBuildByID(player, this.getRole(), index);

            this.addEquipButtons(new int[]{0, 2, 4, 6, 8}[index], index, roleBuild);

            if (index > 0) {
                this.addEditButtons(new int[]{20, 22, 24, 26}[index - 1], index, roleBuild);
                this.addDeleteButtons(new int[]{38, 40, 42, 44}[index - 1], index, roleBuild);
            }
        }
    }

    private void addArmourButtons(final int[] slots) {
        for (int index = 0; index < 4; index++) {
            final Material material = this.getRole().getArmour().get(index);
            final int slot = slots[index];

            addButton(new BuildArmourButton(this, slot, new ItemStack(material)));
        }
    }

    private void addEquipButtons(final int slot, final int id, final RoleBuild roleBuild) {
        addButton(new BuildEquipButton(this, slot, id) {
            @Override
            public int getID() {
                return id;
            }

            @Override
            public RoleBuild getRoleBuild() {
                if (id == 0) {
                    return new DefaultRoleBuild(this.getMenu().getUUID(), this.getMenu().getRole()) {
                        @Override
                        public boolean isActive() {
                            return getManager().getRoleBuildsByRole(getPlayer(), getMenu().getRole()).values().stream().noneMatch(RoleBuild::isActive);
                        }
                    };
                }

                return roleBuild;
            }
        });
    }

    private void addEditButtons(final int slot, final int id, final RoleBuild roleBuild) {
        addButton(new BuildEditButton(this, slot) {
            @Override
            public int getID() {
                return id;
            }

            @Override
            public RoleBuild getRoleBuild() {
                if (roleBuild == null) {
                    return new RoleBuild(this.getMenu().getUUID(), this.getMenu().getRole(), id);
                }

                return roleBuild;
            }
        });
    }

    private void addDeleteButtons(final int slot, final int id, final RoleBuild roleBuild) {
        addButton(new BuildDeleteButton(this, slot) {
            @Override
            public int getID() {
                return id;
            }

            @Override
            public RoleBuild getRoleBuild() {
                return roleBuild;
            }
        });
    }
}