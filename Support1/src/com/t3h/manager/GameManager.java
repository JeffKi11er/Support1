package com.t3h.manager;

import com.t3h.model.Entity;
import com.t3h.model.MapBoom;
import com.t3h.model.Player;

import java.awt.*;
import java.util.ArrayList;

public class GameManager {
    private Player player;
    private MapManager mapManager = new MapManager();

    public void initGame() {
        mapManager.readMap("mapGame1.txt");
        player = new Player(200, 200);
    }

    public void draw(Graphics2D g2d) {
        for (MapBoom map : mapManager.getArrMap()) {
            map.draw(g2d);
        }
        player.draw(g2d);

    }

    public void movePlayer(int newOrient) {
        player.changeOrient(newOrient);
        ArrayList<MapBoom> arr = mapManager.getArrMap();
        player.move(arr);
        // kiểm tra nhân vật đã ra giữa chưa
        // nếu chưa thì cho nhân vật di chuyển vị trí
        if (player.isCenter() == false){
            player.movePosition(arr);
            // nếu nhân vật di chuyển vị trí thì k cho di chuyển map nữa
            return;
        }

        // là giá trị kiểm tra có thể di chuyển
        boolean check = false;
        // nếu hướng hiện mà di chuyển sang trái thì kiểm tra thằng đầu tiên
        if (newOrient == Entity.LEFT){
            check = arr.get(0).boundStart();
        }else if (newOrient == Entity.RIGHT){
            // sang phải thì kiểm tra thằng cuối cùng
            check = arr.get(arr.size() -1).boundEnd();
        }
        // nếu check == true là đã  đến giới hạn k cho di chuyển
        if (check){
            // nếu đến giới hạn thì cho nhân vật di chuyển
            player.movePosition(arr);
            return;
        }
        for (int i = 0; i< arr.size(); i++) {
            mapManager.getArrMap().get(i).move(newOrient);
        }
    }

    public void jumpPlayer(){
        player.jump();
    }

    public void AI() {
        player.fallen(mapManager.getArrMap());
    }
}
