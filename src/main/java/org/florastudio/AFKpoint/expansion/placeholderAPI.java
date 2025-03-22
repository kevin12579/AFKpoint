package org.florastudio.AFKpoint.expansion;

import org.bukkit.OfflinePlayer;
import org.florastudio.AFKpoint.AFKpoint;


import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import java.util.UUID;

public class placeholderAPI extends PlaceholderExpansion {
    static AFKpoint plugin = AFKpoint.getInstance();

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "AFKpoint";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (player == null)
            return null;

        if (params.equalsIgnoreCase("Point")) {
            return String.valueOf(AFKpoint.getInstance().getJsonManager().getAfkPoint(player.getUniqueId()));
        }

        return null;
    }

}
