package me.trae.champions.utility;

import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;


public class UtilChampions {

    public static List<PotionEffectType> getNegativePotionEffects() {
        return Arrays.asList(PotionEffectType.SLOW, PotionEffectType.SLOW_DIGGING, PotionEffectType.HARM, PotionEffectType.CONFUSION, PotionEffectType.BLINDNESS, PotionEffectType.HUNGER, PotionEffectType.WEAKNESS, PotionEffectType.POISON, PotionEffectType.WITHER);
    }
}