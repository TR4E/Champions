package me.trae.champions.effect;

import me.trae.core.effect.Effect;
import me.trae.core.effect.EffectManager;
import me.trae.core.effect.data.EffectData;
import me.trae.core.effect.interfaces.types.NegativeEffect;
import me.trae.core.utility.UtilMessage;
import org.bukkit.entity.Player;

public class Silenced extends Effect<Player> implements NegativeEffect {

    public Silenced(final EffectManager manager) {
        super(manager);
    }

    @Override
    public void onRemove(final Player player, final EffectData data) {
        UtilMessage.message(player, "Condition", "You are no longer Silenced!");
    }
}