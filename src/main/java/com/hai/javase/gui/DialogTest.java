package com.hai.javase.gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DialogTest {
    Frame f = new Frame("测试");
    Dialog d1 = new Dialog(f, "Modal Dialog Box", true);
    Dialog d2 = new Dialog(f, "Modeless Dialog Box", false);
    Button b1 = new Button("Open Modal Dialog Box");
    Button b2 = new Button("Open Modeless Dialog Box");

    public void init() {
        d1.setBounds(20, 30, 300, 400);
        d2.setBounds(20, 30, 300, 400);

        d1.addWindowListener(MyWindowAdapter.newInstance(true));
        d2.addWindowListener(MyWindowAdapter.newInstance(true));

        b1.addActionListener(e -> d1.setVisible(true));
        b2.addActionListener(e -> d2.setVisible(true));

        f.add(b1);
        f.add(b2, BorderLayout.SOUTH);

        f.pack();
        f.setVisible(true);
        f.addWindowListener(MyWindowAdapter.newInstance(false));
    }

    public static void main(String[] args) {
        new DialogTest().init();
    }

    static class MyWindowAdapter extends WindowAdapter {

        boolean toHidden;

        @Override
        public void windowClosing(WindowEvent e) {
            if (toHidden) {
                e.getComponent().setVisible(false);
            } else {
                System.exit(-1);
            }
        }

        public static MyWindowAdapter newInstance(boolean toHidden) {
            MyWindowAdapter myWindowAdapter = new MyWindowAdapter();
            myWindowAdapter.toHidden = toHidden;
            return myWindowAdapter;
        }
    }
}  