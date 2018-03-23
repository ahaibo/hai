package com.hai.javase.servlet.upload.commonsupload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/6.
 */
public class Upload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("/") + "upload";
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        String msg = "文件上传成功";
        if (ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置内存中允许存储的字节数
            factory.setSizeThreshold(20 * 1024);
//            factory.setFileCleaningTracker(new FileCleaningTracker());
            //设置存放临时文件的目录
            factory.setRepository(factory.getRepository());

            ServletFileUpload upload = new ServletFileUpload(factory);
            //文件最大大小
            int maxSize = 5 * 1024 * 1024;
            Map<String, String> formFields = new HashMap<>();
            try {
                //定义文件过滤类型
                SuffixFileFilter suffix = new SuffixFileFilter(new String[]{".exe", ".bat", ".jsp"});
                //解析请求
                List<FileItem> fileItems = upload.parseRequest(req);
                for (FileItem fileItem : fileItems) {
                    if (fileItem.isFormField()) {
                        formFields.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
                    } else {
                        if (fileItem.getSize() > maxSize) {
                            msg = "文件过大";
                            break;
                        }

                        String fileName = fileItem.getName();
                        int index = fileName.lastIndexOf("\\");
                        fileName = index != -1 ? fileName.substring(index + 1) : fileName;
                        File uploadFile = new File(uploadPath, fileName);

                        if (suffix.accept(uploadFile)) {
                            msg = "文件类型不支持";
                            break;
                        }
                        //保存文件
                        fileItem.write(uploadFile);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        resp.getWriter().write(msg);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
