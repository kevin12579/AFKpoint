package org.florastudio.AFKpoint.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class playerJoinEvent implements Listener {
    @EventHandler
    private void RemoveAFKEffect(PlayerJoinEvent e) {
        PotionEffect afkEffect = e.getPlayer().getPotionEffect(PotionEffectType.BLINDNESS);
        if ( afkEffect != null ) {
            e.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
        }
    }
}
