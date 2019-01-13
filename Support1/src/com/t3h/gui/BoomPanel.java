package com.t3h.gui;

import com.t3h.manager.GameManager;
import com.t3h.model.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BoomPanel extends JPanel implements Runnable, KeyListener {
    private GameManager manager = new GameManager();
    private boolean[] flag = new boolean[256];

    public BoomPanel() {
        setBackground(Color.white);
        manager.initGame();
        Thread t = new Thread(this);
        // bắt đâu thực hiện. chỉ đc gọi 1 lần/1 thread
        t.start();
        // đăng ký sự kiện bàn phím
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
//        g2d.setColor(Color.RED);
//        g2d.setStroke(new BasicStroke(20));
//        g2d.drawLine(0, 0, 200, 200);
//
//        g2d.drawOval(200, 200, 100, 50);
//        g2d.drawRect(300, 300, 100, 100);
//        g2d.setFont(new Font(null, Font.ITALIC, 20));
//        g2d.drawString("Hello world", 400, 200);
        manager.draw(g2d);
    }

    @Override
    public void run() {
        while (true) {
            manager.AI();
            if (flag[KeyEvent.VK_LEFT]) {
                manager.movePlayer(Entity.LEFT);
            } else if (flag[KeyEvent.VK_RIGHT]) {
                manager.movePlayer(Entity.RIGHT);
            }
            if (flag[KeyEvent.VK_SPACE]) {
                manager.jumpPlayer();
            }
            // cập nhập lại giao diện
            repaint();
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        flag[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        flag[e.getKeyCode()] = false;
    }
}
