package com.hai.javase.servlet.upload.smartupload;

import com.hai.common.Constants;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/10/6.
 */
@WebServlet("/smart/upload")
public class Upload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SmartUpload smartUpload = new SmartUpload();
        smartUpload.initialize(getServletConfig(), req, resp);
        try {
            smartUpload.upload();
            smartUpload.save(Constants.FILE_UPLOAD_PATH);
            resp.getWriter().println("SmartUpload Success!");
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
