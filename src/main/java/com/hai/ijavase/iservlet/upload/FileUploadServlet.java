package com.hai.ijavase.iservlet.upload;

import com.alibaba.fastjson.JSONObject;
import com.hai.common.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/3.
 */
@WebServlet("/uploadServlet")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getServletContext().getRealPath(Constants.FILE_UPLOAD_PATH);
        Part part = req.getPart("file");

        part.write(path + "/" + part.getName() + System.nanoTime());
        resp.setContentType("application/json;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 200);
        jsonObject.put("message", "文件上传成功");
        resp.getWriter().write(jsonObject.toJSONString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
