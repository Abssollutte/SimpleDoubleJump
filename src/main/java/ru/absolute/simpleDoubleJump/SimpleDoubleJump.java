package ru.absolute.simpleDoubleJump;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.kyori.adventure.util.TriState;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import ru.absolute.simpleDoubleJump.methods.Levitation;
import ru.absolute.simpleDoubleJump.methods.ThrowPlayerDirectly;
import ru.absolute.simpleDoubleJump.methods.ThrowPlayerUp;

import java.util.HashMap;
import java.util.Map;

public final class SimpleDoubleJump extends JavaPlugin implements Listener {

    private FileConfiguration config = getConfig();
    public String doubleJumpMessage = this.config.getString("DoubleJump.message");
    public String type = this.config.getString("DoubleJump.type");
    public float power = (float) this.config.getDouble("DoubleJump.power");
    public boolean enableFallDamage = this.config.getBoolean("Global.enableFallDamage");
    public String particles = this.config.getString("DoubleJump.particles");
    public int particleCount = this.config.getInt("DoubleJump.particlesCount");
    public String sound = this.config.getString("DoubleJump.sound");
    public double soundVolume = this.config.getDouble("DoubleJump.soundVolume");
    public double soundPitch = this.config.getDouble("DoubleJump.soundVolume");




    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Enabled");
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
            player.setAllowFlight(true);
            player.setFlying(false);

        }

    }



    @EventHandler
    public void onPlayerTouchGround(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.isOnGround() && player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
            if (enableFallDamage) {
                player.setFlyingFallDamage(TriState.TRUE);
            }
            player.setAllowFlight(true);
            player.setFlying(false);

        }
    }



    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
            if (!doubleJumpMessage.isEmpty() && !doubleJumpMessage.isBlank()) {
                player.sendMessage(doubleJumpMessage);
            }
            player.getWorld().playSound(location, Sound.valueOf(sound), (float) soundVolume, (float) soundPitch);
            player.getWorld().spawnParticle(Particle.valueOf(particles), location, particleCount, 0.1, 0.1, 0.1);
            if (type.equalsIgnoreCase("dash")) {
                ThrowPlayerDirectly.throwPlayerUp(player, power);
            }
            else if (type.equalsIgnoreCase("up")) {
                ThrowPlayerUp.ThrowUp(player, power);
            }
            else if (type.equalsIgnoreCase("levitation")) {
                Levitation.levitation(player, power);
            }

            player.setAllowFlight(false);
            player.setFlying(false);

        }

    }





    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
