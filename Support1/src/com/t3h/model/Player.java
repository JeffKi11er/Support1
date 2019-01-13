package com.t3h.model;

import com.t3h.gui.BoomFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity{
    private Image[] imLeft = {
            new ImageIcon(getClass().getResource("/santa_images/left0.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/left1.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/left2.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/left3.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/left4.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/left5.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/left6.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/left7.png")).getImage()
    };

    private Image[] imRight = {
            new ImageIcon(getClass().getResource("/santa_images/right0.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/right1.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/right2.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/right3.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/right4.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/right5.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/right6.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/right7.png")).getImage()
    };

    private Image[] imUp = {
            new ImageIcon(getClass().getResource("/santa_images/up0.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/up1.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/up2.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/up3.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/up4.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/up5.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/up6.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/up7.png")).getImage()
    };

    private Image[] imDown = {
            new ImageIcon(getClass().getResource("/santa_images/down0.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/down1.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/down2.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/down3.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/down4.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/down5.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/down6.png")).getImage(),
            new ImageIcon(getClass().getResource("/santa_images/down7.png")).getImage()
    };

    private Image[][] images = {imLeft, imRight, imUp, imDown};
    private int index = 0;
    private int count = 0;
    private long t;
    // giá trị nhẩy(<0) giá trị rơi(>0)
    private int instance = 1;
    // giá trị đã nhẩy được
    private int jumpValue = 0;
    public Player(int x, int y) {
        super(x, y);
        orient = RIGHT;
    }

    @Override
    public void draw(Graphics2D g2d) {
        img = images[orient][index];
        super.draw(g2d);
    }

    public void changeOrient(int newOrient){
        orient = newOrient;
    }

    @Override
    public void move(ArrayList<MapBoom> arr) {
        count++;
        if (count >= 5){
            index++;
            if (index >= images[orient].length){
                index = 0;
            }
            count = 0;
        }
    }
    /**
     * di chuyển vị trí nhân vật khi map đến giới hạn đầu hoặc cuối
     * */
    public void movePosition(ArrayList<MapBoom> arr){
        super.move(arr);
    }

    public void fallen(ArrayList<MapBoom> arr){
        // y tăng hoặc giảm tùy thuộc vào hành động rơi hoặc nhẩy
        y+= instance;
        // hành động nhẩy
        if (instance<0){
            jumpValue += instance;
        }
        // giá trị nhẩy đạt đến giới hạn của 1 lần nhẩy
        // 50 là giá trị quy định độ cao của 1 lần nhẩy có thể tùy biến
        if (jumpValue <= -50){
            // đổi chiều sang rơi
            instance = 1;
            //reset giá trị
            jumpValue = 0;
        }
        // kiểm tra chạm nền
        boolean check = checkMap(arr);
        if (check == false){
            y--;
        }
    }

    public void jump(){
        long T = System.currentTimeMillis();
        if (T - t < 300){
            return;
        }
        t = T;
        instance = -3;
    }
    /**
     * kiểm tra vị trí của nhân vật đã đến giữa màn hình hay chưa
     * @return true nếu ở giữa, false ngược lại
     * nếu trả về false sẽ cho nhân vật di chuyển
     * */
    public boolean isCenter(){
        return x == BoomFrame.W/3;
    }

    @Override
    public Rectangle getRect() {
        int w = images[orient][index].getWidth(null);
        int h = images[orient][index].getHeight(null);
        Rectangle rect = new Rectangle(x + 10, y + 30, w - 20, h - 45);
        return rect;
    }
}
