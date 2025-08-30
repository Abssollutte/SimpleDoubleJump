package ru.absolute.simpleDoubleJump.methods;

import org.bukkit.entity.Player;

import org.bukkit.util.Vector;

public class ThrowPlayerUp {
    public static void ThrowUp(Player player, float power) {
        int throwPower = (int) power;
        Vector upwardVelocity = new Vector(0, throwPower, 0);
        player.setVelocity(upwardVelocity);

    }
}
