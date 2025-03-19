package org.florastudio.AFKpoint.event;

import net.ess3.api.events.AfkStatusChangeEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.florastudio.AFKpoint.AFKpoint;


public class playerAfkEvent implements Listener {

    private Player player;

    @EventHandler
    private void PlayerGoesAFK(AfkStatusChangeEvent e) {
        player = e.getAffected().getBase();
        AFKpoint.getInstance().getAddEffect().applyEffect(player, e.getValue());
    }
}
