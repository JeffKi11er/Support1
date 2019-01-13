package com.t3h.model;

import java.awt.*;
import java.util.ArrayList;

public abstract class Entity {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    protected int x;
    protected int y;
    protected Image img;
    protected int orient;
    protected int speed = 1;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, x, y, null);
    }

    public void move(ArrayList<MapBoom> arr) {
        int xR = x;
        int yR = y;
        switch (orient) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
        boolean check = checkMap(arr);
        if (check == false){
            x = xR;
            y = yR;
        }
    }

    public boolean checkMap(ArrayList<MapBoom> arr){
        for (MapBoom map:arr) {
            if (map.getBit() == 0){
                continue;
            }
            Rectangle rect = getRect().intersection(map.getRect());
            if (rect.isEmpty() == false){
                return false;
            }
        }
        return true;
    }

    public Rectangle getRect(){
        if (img == null){
            return new Rectangle();
        }
        Rectangle rect = new Rectangle(x, y,
                img.getWidth(null),
                img.getHeight(null));
        return rect;
    }
}
