package org.florastudio.AFKpoint.database;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class JsonManager {
    private static final String FILE_PATH = "plugins/AFKpoint/playerdata.json";

    // JSON에서 location 목록 불러오기
    public JSONArray getData() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            return new JSONArray();
        }
    }

    public void saveAfkPointData(Map<UUID, Long> afkplayer) {
        JSONArray data = getData();
        for (Map.Entry<UUID, Long> entry: afkplayer.entrySet()) {
            JSONObject playerData = new JSONObject();
            playerData.put("uuid", entry.getKey().toString());
            playerData.put("point", entry.getValue());
            data.add(playerData);
        }

        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(data.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getAfkPoint(UUID uuid) {
        JSONArray data = getData();
        for (Object obj : data) {
            JSONObject playerData = (JSONObject) obj;
            if (playerData.get("uuid").equals(uuid.toString())) {
                return (long) playerData.get("point");
            }
        }
        return 0; // 데이터가 없으면 기본값 0 반환
    }
}
