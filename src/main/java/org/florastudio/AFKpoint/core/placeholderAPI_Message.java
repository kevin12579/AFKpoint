package org.florastudio.AFKpoint.core;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class placeholderAPI_Message {
    public String puplaceholderAPISupportConvertMessage(Player player, List <String> string) {
        List<String> listTitle = string;
        Random goneAFKTitle = new Random();
        int randomMessageTitle = goneAFKTitle.nextInt(listTitle.size());
        String newMessageTitle = (String) PlaceholderAPI.setPlaceholders(player, listTitle.get(randomMessageTitle));
        return newMessageTitle;
    }
}
