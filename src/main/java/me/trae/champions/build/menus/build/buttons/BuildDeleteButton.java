package me.trae.champions.build.menus.build.buttons;

import me.trae.champions.build.data.RoleBuild;
import me.trae.champions.build.menus.build.BuildCustomizationMenu;
import me.trae.champions.build.menus.build.buttons.interfaces.IBuildButton;
import me.trae.core.client.Client;
import me.trae.core.menu.Button;
import me.trae.core.utility.objects.SoundCreator;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public abstract class BuildDeleteButton extends Button<BuildCustomizationMenu> implements IBuildButton {

    public BuildDeleteButton(final BuildCustomizationMenu menu, final int slot) {
        super(menu, slot, new ItemStack(Material.TNT));
    }

    @Override
    public String getDisplayName() {
        return String.format("<red><bold>Delete Custom Build #%s", this.getID());
    }

    @Override
    public String[] getLore() {
        return new String[]{
                "This will not come back!"
        };
    }

    @Override
    public void onClick(final Player player, final Client client, final ClickType clickType) {
        if (!(Arrays.asList(ClickType.LEFT, ClickType.RIGHT).contains(clickType))) {
            return;
        }

        final RoleBuild roleBuild = this.getRoleBuild();
        if (roleBuild == null) {
            return;
        }

        new SoundCreator(Sound.ITEM_BREAK).play(player);

        this.getMenu().getManager().removeRoleBuild(roleBuild);

        this.getMenu().getManager().getRepository().deleteData(roleBuild);

        this.getMenu().refresh();
    }
}