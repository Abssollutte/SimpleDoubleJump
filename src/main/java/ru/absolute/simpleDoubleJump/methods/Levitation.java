package ru.absolute.simpleDoubleJump.methods;

import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Levitation {
    public static void levitation(Player player, float power) {
        PotionEffectType effectType = PotionEffectType.getByName("levitation");
        PotionEffect effect = new PotionEffect(effectType, 3, (int) power);
        player.addPotionEffect(effect);
    }
}
