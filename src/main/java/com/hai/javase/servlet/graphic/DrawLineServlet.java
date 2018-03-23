package com.hai.javase.servlet.graphic;

import com.hai.javase.servlet.common.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Administrator on 2017/10/6.
 */
@WebServlet("/graphic/line")
public class DrawLineServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        BufferedImage image = (BufferedImage) session.getAttribute("lineImg");
        if (null == image) {
            image = getBufferedImage();
            session.setAttribute("lineImg", image);
        } else {
            System.out.println("read buffered image from cache in session...");
        }

        try {
            ServletHelper.processJpegGraphic(req, res, new HaiGraphicActionDefaultAdapter() {
                @Override
                protected Object processAction(ServletRequest request, ServletResponse response) throws Exception {
                    System.out.println("hi,process action...");
                    return null;
                }
            }, image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage getBufferedImage() {
        System.out.println(getClass().getName() + ".getBufferedImage...");
        BufferedImage image = new BufferedImage(ServletHelper.IMG_WIDTH, ServletHelper.IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        graphics.setColor(new Color(178, 186, 174));
//        graphics.fill3DRect(0, 0, ServletHelper.IMG_WIDTH, ServletHelper.IMG_HEIGHT, true);
        graphics.fillRect(0, 0, ServletHelper.IMG_WIDTH, ServletHelper.IMG_HEIGHT);
        graphics.setColor(Color.BLACK);

        //绘图
        graphics.drawLine(70, 50, 180, 50);
        graphics.drawLine(70, 80, 180, 80);
        graphics.drawLine(70, 10, 180, 180);

        //释放资源
        graphics.dispose();
        return image;
    }
}
