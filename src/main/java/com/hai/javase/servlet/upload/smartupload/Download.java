//package com.hai.javase.servlet.upload.smartupload;
//
//import com.hai.common.Constants;
//import com.jspsmart.upload.SmartUpload;
//import com.jspsmart.upload.SmartUploadException;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//
///**
// * Created by Administrator on 2017/10/6.
// */
//@WebServlet("/smart/download")
//public class Download extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        resp.setCharacterEncoding("UTF-8");
//
//        String fileName = req.getParameter("file");
////        File file = new File(getServletContext().getRealPath(Constants.FILE_IMAGES_PATH + fileName));
//        File file = new File(getServletContext().getRealPath(Constants.FILE_UPLOAD_PATH + fileName));
//        ServletOutputStream out = resp.getOutputStream();
//        if (null == file || !file.exists()) {
//            out.println("file not exists...");
//        } else {
//            //设置响应头信息
//            resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//            resp.setHeader("Content-Length", file.length() + "");
//            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
//            resp.setContentType("application/octet-stream");
////            resp.setContentType("multipart/form-data");
////            resp.setContentType("image/jpeg");
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream((int) file.length());
//            byte[] buffer = new byte[1024];
//            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
//            int len;
//            while ((len = bis.read(buffer)) != -1) {
//                baos.write(buffer, 0, len);
//            }
//            bis.close();
////            baos.toByteArray();
//            out.write(baos.toByteArray(), 0, baos.size());
//        }
//        out.close();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.doGet(req, resp);
//    }
//
//    public void downloadWithSmart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SmartUploadException {
//        SmartUpload smartUpload = new SmartUpload();
//        smartUpload.initialize(getServletConfig(), req, resp);
//        smartUpload.setContentDisposition(null);//设置ContentDisposition=null；禁止浏览器自动打开文件
//        smartUpload.downloadFile(req.getParameter("file"));
//    }
//}
