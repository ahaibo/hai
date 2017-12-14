package com.hai.ijavase.igui.multcopy.v1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Administrator on 2017/9/22.
 */
public class CopyWindow extends JFrame implements ActionListener {

    private static final int[] position = {400, 200, 517, 350};
    private static final int[] label = {10, 10, 80, 30};
    private static final int[] text = {100, 10, 400, 30};

    private JTextField srcFileText;
    private JTextField destFileText;
    private JTextField countThreadText;

    private JButton startBtn;
    private JProgressBar progressBar;
    private JLabel copyStateLabel;

    public CopyWindow() {
        JLabel srcFileLabel = new JLabel("    源文件：");
        srcFileLabel.setBounds(label[0], label[1], label[2], label[3]);

        srcFileText = new JTextField();
        srcFileText.setText("C:\\Media\\Transformers.The.Last.Knight.2017.1080p.WEB-DL.DD5.1.H264-FGT_2017914153028.mkv");
        srcFileText.setBounds(text[0], text[1], text[2], text[3]);

        this.add(srcFileLabel);
        this.add(srcFileText);

        JLabel destFileLabel = new JLabel("目标文件：");
        destFileLabel.setBounds(label[0], label[1] * 2 + text[3], label[2], label[3]);

        destFileText = new JTextField();
        destFileText.setText("C:\\Media\\Transformers.The.Last.Knight.2017.1080p.WEB-DL.DD5.1.H264-FGT_2017914153028-02.mkv");
        destFileText.setBounds(text[0], label[1] * 2 + text[3], text[2], text[3]);

        this.add(destFileLabel);
        this.add(destFileText);

        JLabel countThreadLabel = new JLabel("    线程数：");
        countThreadLabel.setBounds(label[0], label[1] * 3 + text[3] * 2, label[2], label[3]);

        countThreadText = new JTextField();
        countThreadText.setText(8 + "");
        countThreadText.setBounds(text[0], label[1] * 3 + text[3] * 2, text[2], text[3]);

        this.add(countThreadLabel);
        this.add(countThreadText);

        startBtn = new JButton("开始复制");
        startBtn.setBounds(label[0], label[1] * 4 + text[3] * 3, label[2], label[3]);
        startBtn.addActionListener(this);
        this.add(startBtn);

        progressBar = new JProgressBar();
        progressBar.setBounds(label[0], label[1] * 5 + text[3] * 4, position[2] - label[0] * 3, label[3]);
        progressBar.setVisible(true);
        this.add(progressBar);

        copyStateLabel = new JLabel("复制完成！");
        copyStateLabel.setVisible(false);
        copyStateLabel.setBounds(label[0], label[1] * 6 + text[3] * 5, label[2], label[3]);
        this.add(copyStateLabel);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(-1);
            }
        });
        this.setTitle("My Multi Copier!");
        this.setLayout(null);
        this.setBounds(position[0], position[1], position[2], position[3]);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startBtn) {
            System.out.println("main multi copy...");
            String srcPath = srcFileText.getText();
            String destPath = destFileText.getText();
            String threadCountPath = countThreadText.getText();
            if (isEmpty(srcPath) || isEmpty(destPath) || isEmpty(threadCountPath)) {
                System.out.println("info is not complete...");
                return;
            }
            new Copier(this, Integer.parseInt(threadCountPath), srcPath, destPath);
        }
    }

    public void updateProgressBar(int length) {
        synchronized (CopyWindow.class) {
            this.progressBar.setValue(this.progressBar.getValue() + length);
        }
    }

    public void setProgressBarMaxValue(int length) {
        this.progressBar.setMaximum(length);
    }

    public void visibleCompleteLabel() {
        this.copyStateLabel.setVisible(true);
    }

    public boolean isEmpty(String s) {
        return null == s || s.trim().length() == 0;
    }
}
