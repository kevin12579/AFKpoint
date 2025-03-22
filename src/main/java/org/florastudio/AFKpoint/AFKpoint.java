package org.florastudio.AFKpoint;


import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.florastudio.AFKpoint.core.HexColor;
import org.florastudio.AFKpoint.core.placeholderAPI_Message;
import org.florastudio.AFKpoint.database.JsonManager;
import org.florastudio.AFKpoint.event.playerAfkEvent;
import org.florastudio.AFKpoint.event.playerJoinEvent;
import org.florastudio.AFKpoint.main.AddEffect;
import org.florastudio.AFKpoint.command.Command;
import org.florastudio.AFKpoint.main.AddPoint;

import java.util.Objects;

public final class AFKpoint extends JavaPlugin implements Listener {

        private static AFKpoint instance;
        private HexColor hexColor;
        private AddEffect addEffect;
        private placeholderAPI_Message placeholderAPIMessage;
        private AddPoint addPoint;
        private JsonManager jsonManager;
        @Override
        public void onEnable(){
            instance = this;
            hexColor = new HexColor();
            addEffect = new AddEffect();
            addPoint = new AddPoint();
            placeholderAPIMessage = new placeholderAPI_Message();
            jsonManager = new JsonManager();
            long startTime = System.currentTimeMillis();

            getServer().getPluginManager().registerEvents(this, this);
            getServer().getPluginManager().registerEvents(new playerAfkEvent(), this);
            getServer().getPluginManager().registerEvents(new playerJoinEvent(), this);
            saveDefaultConfig();

            boolean essentialsXAvailable = Bukkit.getPluginManager().isPluginEnabled("Essentials");
            boolean papiAvailable = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");

            Objects.requireNonNull(getCommand("잠수포인트")).setExecutor(new Command());
            
            if (essentialsXAvailable){
                getLogger().info("EssentialsX와 연결되었습니다.");
            } else {
                getLogger().severe("EssentialsX 플러그인 미확인. AFKpoint 플러그인 비활성화...");
                getServer().getPluginManager().disablePlugin(this);
            }

            if (papiAvailable){
                getLogger().info("PlaceholderAPI와 연결되었습니다.");
            } else {
                getLogger().severe("PlaceholderAPI 플러그인 미확인. AFKpoint 플러그인 비활성화...");
                getServer().getPluginManager().disablePlugin(this);
            }

            if (getServer().getPluginManager().isPluginEnabled(this)) {
                getLogger().info("잠수 포인트 플러그인 로드완료. 로드시간: " + (System.currentTimeMillis() - startTime) + "ms");
            }


        }

        public static AFKpoint getInstance() {
            return instance;
        }
        public HexColor getHexColor() {
            return hexColor;
        }
        public AddEffect getAddEffect() {
            return addEffect;
        }
        public placeholderAPI_Message getPlaceholderAPIMessage(){
            return placeholderAPIMessage;
        }
        public AddPoint getAddPoint() { return addPoint; }
        public JsonManager getJsonManager() {return jsonManager;}
    }