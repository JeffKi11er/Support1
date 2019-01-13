package com.t3h.gui;

import javax.swing.*;
import java.awt.*;

public class BoomFrame extends JFrame {
    public static final int W = 695;
    public static final int H = 640;

    public BoomFrame() throws HeadlessException {
        setTitle("Boom");
        setSize(W, H);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new BoomPanel());
    }
}
