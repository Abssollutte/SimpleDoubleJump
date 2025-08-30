package ru.absolute.simpleDoubleJump.methods;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ThrowPlayerDirectly {
    public static void throwPlayerUp(Player player, float power) {
        player.setVelocity(player.getLocation().getDirection().multiply(2.0D).setY(power));
    }
}
