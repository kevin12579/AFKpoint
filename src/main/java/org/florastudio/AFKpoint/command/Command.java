package org.florastudio.AFKpoint.command;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.florastudio.AFKpoint.AFKpoint;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.Command cmd, @NotNull String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("잠수포인트")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length < 1) {
                    if (sender.hasPermission("afkpoint.reload")) {
                        sender.sendMessage(" §e/잠수포인트 reload");
                    }
                } else {
                    if (sender.hasPermission("exafk.reload")) {
                        if (args[0].equalsIgnoreCase("reload")) {
                            AFKpoint.getInstance().reloadConfig();
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes("&".toCharArray()[0], "&a플러그인이 리로드되었습니다."));
                            sender.sendMessage(" §a플러그인 리로드 완료");
                        }
                    } else {
                        player.sendTitle("§f ", ChatColor.translateAlternateColorCodes('&', "&c권한이 없습니다."), 10, 40, 20);
                    }
                }
            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes("&".toCharArray()[0], "&c플레이어만 사용 가능합니다."));
            }
        }
        return false;
    }
}
