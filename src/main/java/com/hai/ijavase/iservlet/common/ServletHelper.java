/**
 *
 */
package com.hai.ijavase.iservlet.common;

import com.hai.ijavase.iservlet.graphic.HaiGraphicAction;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Administrator
 */
public class ServletHelper {

    public static final String IMG_TYPE_IPEG = "JPEG";
    public static final int IMG_WIDTH = 200;
    public static final int IMG_HEIGHT = 180;

    /**
     * 设置程序的编码方式
     *
     * @param response
     */
    public static void setContentTypeAndEncoding(ServletResponse response) {
        response.setContentType("type=text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
    }

    /**
     * 设置程序不缓存
     *
     * @param resp
     */
    public static void setNoCache(ServletResponse resp) {
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
    }

    /**
     * 设置程序的编码方式和浏览器不缓存模式
     *
     * @param response
     */
    public static void setContentTypeAndEncodingAndNoCache(ServletResponse response) {
        setNoCache(response);
        setContentTypeAndEncoding(response);
    }

    public static void setGraphicContentTypeAndEncodingAndNoCache(ServletResponse response) {
        setNoCache(response);
        response.setContentType("image/jpeg");
    }

    public static void writeImage(RenderedImage image, ServletResponse response, String format) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, format, outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static Object processJpegGraphic(ServletRequest request, ServletResponse response, HaiGraphicAction action, RenderedImage image) throws Exception {
        return processGraphic(request, response, action, image, IMG_TYPE_IPEG);
    }

    /**
     * 处理绘图的模板方法
     *
     * @param request
     * @param response
     * @param action   委托方法
     * @param image    输出图片
     * @param format   输出图片格式
     * @return
     * @throws Exception
     */
    public static Object processGraphic(ServletRequest request, ServletResponse response, HaiGraphicAction action, RenderedImage image, String format) throws Exception {
        Object result = null;
        setGraphicContentTypeAndEncodingAndNoCache(response);
        if (null != action) {
            result = action.action(request, response);
        }
        writeImage(image, response, format);
        return result;
    }
}
