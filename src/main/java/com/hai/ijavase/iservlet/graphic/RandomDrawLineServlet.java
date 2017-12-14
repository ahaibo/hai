package com.hai.ijavase.iservlet.graphic;

import com.hai.ijavase.iservlet.common.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Administrator on 2017/10/6.
 */
@WebServlet("/graphic/line/random")
public class RandomDrawLineServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedImage image = new BufferedImage(ServletHelper.IMG_WIDTH, ServletHelper.IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Random random = new Random();

        Graphics graphics = image.getGraphics();
        graphics.setColor(new Color(random.nextInt(ServletHelper.IMG_WIDTH), random.nextInt(ServletHelper.IMG_WIDTH), random.nextInt(ServletHelper.IMG_WIDTH)));
//        graphics.fill3DRect(0, 0, ServletHelper.IMG_WIDTH, ServletHelper.IMG_HEIGHT, true);
        graphics.fillRect(0, 0, ServletHelper.IMG_WIDTH, ServletHelper.IMG_HEIGHT);
        graphics.setColor(Color.BLACK);

        //最少20条直线
        for (int i = 0, num = random.nextInt(20) + 20; i < num; i++) {
            //绘图
            graphics.drawLine(random.nextInt(ServletHelper.IMG_WIDTH), random.nextInt(ServletHelper.IMG_WIDTH), random.nextInt(ServletHelper.IMG_WIDTH), random.nextInt(ServletHelper.IMG_WIDTH));
        }
//        graphics.drawLine(70, 50, 180, 50);
//        graphics.drawLine(70, 80, 180, 80);
//        graphics.drawLine(70, 10, 180, 180);

        //释放资源
        graphics.dispose();

        try {
            ServletHelper.processJpegGraphic(req, resp, null, image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
