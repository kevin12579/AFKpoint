package org.florastudio.AFKpoint.main;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.florastudio.AFKpoint.AFKpoint;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AddPoint {
    private final Map<UUID, Long> afkplayer = new ConcurrentHashMap<>();

    public AddPoint() {
        runScheduler(); // 생성자에서 스케줄러 실행
    }

    public void addPlayerAfk(Player player) {
        if (!afkplayer.containsKey(player.getUniqueId())) {
            afkplayer.put(player.getUniqueId(), 0L);  // 초기값 0L로 설정
        }
    }
    public void removePlayerAfk(Player player) {
        afkplayer.remove(player.getUniqueId());
    }

    private void runScheduler(){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (UUID uuid : afkplayer.keySet()) {
                    afkplayer.put(uuid, afkplayer.get(uuid) + 1);  // AFK 포인트 증가
                }
            }
        }.runTaskTimerAsynchronously(AFKpoint.getInstance(), 20L, 20L);
        new BukkitRunnable() {
            @Override
            public void run() {
                AFKpoint.getInstance().getJsonManager().saveAfkPointData(afkplayer);
                afkplayer.clear(); // 저장 후 초기화
            }
        }.runTaskTimerAsynchronously(AFKpoint.getInstance(), 6000L, 6000L);
    }

}
