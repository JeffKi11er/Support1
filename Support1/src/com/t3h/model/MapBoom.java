package com.t3h.model;

import com.t3h.gui.BoomFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapBoom {
    private int x;
    private int y;
    private int bit;

    private Image[] images = {
            new ImageIcon(getClass().getResource("/images/snow.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/Tree.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/snowman1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/Stone.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/home.png")).getImage(),
    };

    public MapBoom(int x, int y, int bit) {
        this.x = x;
        this.y = y;
        this.bit = bit;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(images[bit], x, y, null);
    }

    public int getBit() {
        return bit;
    }

    public Rectangle getRect() {
        Rectangle rect = new Rectangle(x, y,
                images[bit].getWidth(null),
                images[bit].getHeight(null));
        return rect;
    }

    /*
     * Kiểm tra map đến vị trí giới hạn bắt đầu
     * @return true nếu đến đầu tiên, false nếu chưa đến
     * */
    public boolean boundStart() {
        return x >= 0;
    }

    /*
     * Kiểm tra map đến vị trí giới hạn cuối cùng
     * @return true nếu đến cuối cùng, false nếu chưa đến
     * */
    public boolean boundEnd() {
        return x <= BoomFrame.W;
    }

    public void move(int orient) {
        switch (orient) {
            case Entity.LEFT:
                x++;
                break;
            case Entity.RIGHT:
                x--;
                break;
        }
    }
}
