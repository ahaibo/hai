package com.hai.javase.gui.notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2017/9/22.
 */
public class MyNotepad extends JFrame implements ActionListener, WindowListener {

    //    private JFrame this;
    private JScrollPane scrollPane;

    private JMenuBar menuBar;

    private JMenu fileMenu;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem exit;

    private JMenu editMenu;
    private JMenuItem cut;
    private JMenuItem copy;
    private JMenuItem paste;
    private JMenuItem delete;

    private JMenu formatMenu;
    private JMenuItem newline;
    private JMenuItem font;

    private JMenu viewMenu;
    private JMenuItem stateBar;

    private JMenu helpMenu;
    private JMenuItem look;
    private JMenuItem about;

    private JTextArea textArea;

    private JButton ok;
    private JButton cancel;


    public MyNotepad() {
        this.init();
    }

    private void init() {
//        this = new JFrame("My Notepad!");
        this.setTitle("My Notepad!");

        textArea = new JTextArea();
        textArea.setVisible(true);
        textArea.setEnabled(true);
//        textArea.setBounds(0, 0, 625, 334);
        textArea.setFont(new Font("宋体", Font.BOLD, 12));
        textArea.setBackground(Color.green);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(Color.WHITE);
//        scrollPane.add(textArea);
//        scrollPane.setVisible(true);
        scrollPane.setBounds(5, 30, 625, 334);

        this.initMenuBar();
        this.initButtons();

        this.setLayout(null);
        this.setResizable(false);
        this.addWindowListener(this);
        this.setBounds(250, 200, 640, 450);
        this.add(scrollPane);
        this.setVisible(true);
    }

    private void initMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setVisible(true);
        menuBar.setBounds(5, 0, 624, 30);
        this.initFileMenu();
        this.initEditMenu();
        this.initFormatMenu();
        this.initViewMenu();
        this.initHelpMenu();
        this.add(menuBar);
    }

    private void initFileMenu() {
        fileMenu = new JMenu("文件");
        open = new JMenuItem("打开");
        save = new JMenuItem("保存");
        exit = new JMenuItem("退出");

        open.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);

        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(exit);
        fileMenu.addActionListener(this);
        fileMenu.setVisible(true);
        menuBar.add(fileMenu);
    }

    private void initEditMenu() {
        editMenu = new JMenu("编辑");
        cut = new JMenuItem("剪切");
        copy = new JMenuItem("复制");
        paste = new JMenuItem("粘贴");
        delete = new JMenuItem("删除");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        delete.addActionListener(this);

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);
        editMenu.add(delete);
        editMenu.addActionListener(this);
        editMenu.setVisible(true);
        menuBar.add(editMenu);
    }

    private void initFormatMenu() {
        formatMenu = new JMenu("格式化");
        newline = new JMenuItem("换行");
        font = new JMenuItem("字体");

        newline.addActionListener(this);
        font.addActionListener(this);

        formatMenu.add(newline);
        formatMenu.add(font);
        formatMenu.addActionListener(this);
        formatMenu.setVisible(true);
        menuBar.add(formatMenu);
    }


    private void initViewMenu() {
        viewMenu = new JMenu("查看");
        stateBar = new JMenuItem("状态栏");

        stateBar.addActionListener(this);

        viewMenu.add(stateBar);
        viewMenu.addActionListener(this);
        viewMenu.setVisible(true);
        menuBar.add(viewMenu);
    }

    private void initHelpMenu() {
        helpMenu = new JMenu("帮助");
        look = new JMenuItem("查看帮助");
        about = new JMenuItem("关于");

        look.addActionListener(this);
        about.addActionListener(this);

        helpMenu.add(look);
        helpMenu.add(about);
        helpMenu.addActionListener(this);
        helpMenu.setVisible(true);
        menuBar.add(helpMenu);
    }


    private void initButtons() {
        ok = new JButton("确定");
        cancel = new JButton("取消");

        ok.addActionListener(this);
        ok.setBounds(435, 370, 90, 35);

        cancel.addActionListener(this);
        cancel.setBounds(539, 370, 90, 35);

        this.add(ok);
        this.add(cancel);
    }

    public static void main(String[] args) {
        MyNotepad myNotepad = new MyNotepad();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == open) {

            System.out.println("file open...");

            FileDialog dialog = new FileDialog(this, "保存文件", FileDialog.LOAD);
            dialog.setVisible(true);
            String path = dialog.getDirectory() + dialog.getFile();
            System.out.println("open path: " + path);

            FileReader reader = null;
            try {
                File file = new File(path);
                if (!file.exists() || file.length() == 0) {
                    System.out.println("file not exists or is empty...");
                    return;
                }
                reader = new FileReader(file);
                char[] buffer = new char[1024];
                int len;
                String text = textArea.getText();
                if (null != text && text.trim().length() > 0) {
                    textArea.append("\r\n");
                }
                while ((len = reader.read(buffer)) != -1) {
                    textArea.append(new String(buffer, 0, len));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        } else if (source == save) {

            System.out.println("file save...");
            ok.doClick();

        } else if (source == exit) {

            System.out.println("file exit...");
            this.dispose();

        } else if (source == ok) {

            System.out.println("button ok...");
            String content = textArea.getText();

            if (null == content || content.trim().length() == 0) {
                System.out.println("content is empty...");
                JDialog dialog = new JDialog(this, "提示", true);
                return;
            }

            FileDialog dialog = new FileDialog(this, "保存文件", FileDialog.SAVE);
            dialog.setVisible(true);
            String path = dialog.getDirectory() + dialog.getFile();
            System.out.println("save path: " + path);

            FileWriter writer = null;
            try {
                writer = new FileWriter(path, true);
                writer.append(content.trim());
                textArea.setText("");
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                try {
                    writer.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        } else if (source == cancel) {
            System.out.println("button cancel...");
            this.dispose();
        } else {
            JDialog dialog = new JDialog(this, "提示");
//            this.setBounds(200, 200, 650, 450);
            dialog.setBounds(500, 370, 150, 100);

            JTextPane textPane = new JTextPane();
            textPane.setText("未实现。。。");
            dialog.add(textPane);

            /*JButton ok = new JButton("确定");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });
            ok.setBounds(80, 60, 30, 25);

            dialog.add(ok);*/
            dialog.setVisible(true);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("windowOpened...");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("windowOpened...");
        System.exit(-1);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("windowClosed...");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("windowIconified...");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("windowDeiconified...");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("windowActivated...");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("windowDeactivated...");
    }

}
