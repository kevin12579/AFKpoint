package org.florastudio.AFKpoint.handler;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.florastudio.AFKpoint.AFKpoint;

import java.util.List;

public class AddEffect {

    public void applyEffect(Player player, Boolean GoesAfk) {

        Integer titleFadein = 1*20;
        Integer titleFadeout = 2*20;
        Integer timeout = Integer.MAX_VALUE;

        if (GoesAfk) {
            List<String> listTitle = AFKpoint.getInstance().getConfig().getStringList("afk-messages.titles");
            List<String> listSub = AFKpoint.getInstance().getConfig().getStringList("afk-messages.subtitles");

            player.sendTitle(ChatColor.translateAlternateColorCodes('&',
                            AFKpoint.getInstance().getHexColor().hexFormat(AFKpoint.getInstance().getPlaceholderAPIMessage().puplaceholderAPISupportConvertMessage(player, listTitle))),
                    ChatColor.translateAlternateColorCodes('&',
                            AFKpoint.getInstance().getHexColor().hexFormat(AFKpoint.getInstance().getPlaceholderAPIMessage().puplaceholderAPISupportConvertMessage(player, listSub))),
                    titleFadein, timeout, titleFadeout);

            if (AFKpoint.getInstance().getConfig().getString("afk-effect.enabled").equalsIgnoreCase("true")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, timeout, 1));
            }

            if (AFKpoint.getInstance().getConfig().getString("afk-sounds.enabled").equalsIgnoreCase("true")) {
                player.getLocation().getWorld().playSound(player.getLocation(), Sound.valueOf(AFKpoint.getInstance().getConfig().getString("afk-sounds.afk")), 1.0F, 1.0F);
            }


        } else {
            List<String> wbListTitle = AFKpoint.getInstance().getConfig().getStringList("return-messages.titles");
            List<String> wbListSub = PlaceholderAPI.setPlaceholders(player, AFKpoint.getInstance().getConfig().getStringList("return-messages.subtitles"));

            Integer titleReturnDuration = 4 * 20;

            player.sendTitle(ChatColor.translateAlternateColorCodes('&',
                            AFKpoint.getInstance().getHexColor().hexFormat(AFKpoint.getInstance().getPlaceholderAPIMessage().puplaceholderAPISupportConvertMessage(player, wbListTitle))),
                    ChatColor.translateAlternateColorCodes('&',
                            AFKpoint.getInstance().getHexColor().hexFormat(AFKpoint.getInstance().getPlaceholderAPIMessage().puplaceholderAPISupportConvertMessage(player, wbListSub))) ,
                    titleFadein, titleReturnDuration, titleFadeout);

            if (AFKpoint.getInstance().getConfig().getString("afk-effect.enabled").equalsIgnoreCase("true")) {
                player.removePotionEffect(PotionEffectType.BLINDNESS);
            }

            if (AFKpoint.getInstance().getConfig().getString("afk-sounds.enabled").equalsIgnoreCase("true")) {
                player.getLocation().getWorld().playSound(player.getLocation(), Sound.valueOf(AFKpoint.getInstance().getConfig().getString("afk-sounds.return")), 1.0F, 1.0F);
            }

        }
    }
}
