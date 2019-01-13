package com.t3h.manager;

import com.t3h.model.MapBoom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MapManager {
    private ArrayList<MapBoom> arrMap;
    private final int SIZE = 40;

    public void readMap(String name) {
        try {
            arrMap = new ArrayList<>();
            File f = new File("src/maps/" + name);
            FileReader fileReader = new FileReader(f);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                for (int j = 0; j < line.length(); j++) {
                    int x = j * SIZE;
                    int y = i * SIZE;
                    int bit = Integer.parseInt(line.charAt(j) + "");
                    arrMap.add(new MapBoom(x, y, bit));
                }
                i++;
                line = reader.readLine();
            }
            reader.close();
            fileReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<MapBoom> getArrMap() {
        return arrMap;
    }
}
