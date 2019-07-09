package com.hai.javase.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 阿里云文件上传工具类
 */
public class AliOSSUtil {

    private static Logger logger = LoggerFactory.getLogger(AliOSSUtil.class);

    public static String ENDPOINT = "oss-cn-hongkong.aliyuncs.com";
    public static String ACCESS_KEY_ID = "LTAII6VhYzdhi15w";
    public static String ACCESS_KEY_SECRET = "UHhfKtLv37bVeWB3nbv5BE1CZINTwV";
    public static String BUCKET_NAME = "caipiao-file";
    public static String FILE_HOST = "http://static.zk01.cc/";
    public static String PROXYIP = "47.244.113.241";
    public static String PROXYPORT = "8888";

    public static OSSClient buildOSSClient() {
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 上传单个文件
     *
     * @param
     * @return
     */
    public static String upload(InputStream is, String prefix, String type, String folder) {
        long start = System.currentTimeMillis();
        logger.info("OSS文件上传开始,源文件名：{}", is.toString());

        // 获取年-月-日
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        // 创建OSSClient实例
        OSSClient ossClient = buildOSSClient();
        String url = "";
        try {
            // 容器不存在，就创建
            if (!ossClient.doesBucketExist(BUCKET_NAME)) {
                ossClient.createBucket(BUCKET_NAME);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(BUCKET_NAME);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }

            // 创建文件路径
            String filePath = type + "/" + folder + "/" + (dateStr + "/" + UUID.randomUUID().toString() + prefix);
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, filePath, is));
            // 设置权限 这里是公开读
            ossClient.setBucketAcl(BUCKET_NAME, CannedAccessControlList.PublicRead);
            // 上传文件结果
            if (null != result) {
                url = FILE_HOST + filePath;
            }
        } catch (Exception oe) {
            logger.error(oe.getMessage());
        } finally {
            logger.info("OSS文件上传成功,图片地址：{}.times:{} ms.", url, (System.currentTimeMillis() - start));
            // 关闭 OSS 服务
            ossClient.shutdown();
        }
        return url;
    }

    /**
     * 删除单个文件
     *
     * @param key
     */
    public static void deleteFile(String key) {
        key = getFileName(key);
        if (null == key) {
            return;
        }

        OSSClient ossClient = null;
        try {
            ossClient = buildOSSClient();
            ossClient.deleteObject(BUCKET_NAME, key);
            logger.info("deleteFile for:{} succeed.", key);
        } catch (Exception e) {
            logger.error("deleteFile for:{} occur error:{}", key, e);
        } finally {
            shutdown(ossClient);
        }
    }

    /**
     * 批量删除文件
     *
     * @param keys
     */
    public static void deleteMultiFiles(List<String> keys) {
        keys = getFileName(keys);
        if (null == keys) {
            return;
        }

        OSSClient ossClient = null;
        try {
            ossClient = buildOSSClient();
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(BUCKET_NAME).withKeys(keys));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            logger.info("deleteMultiFiles:{} succeed.", JSONObject.toJSONString(deletedObjects));
        } catch (Exception e) {
            logger.error("deleteMultiFiles:{} occur error:{}", JSONObject.toJSONString(keys), e);
        } finally {
            shutdown(ossClient);
        }
    }

    public static void main(String[] args) {
//        deleteFile("http://static.zk01.cc/image/lh-image/2019-06-21/1805cd1e-a4c6-4498-9554-8a28f2088fe2.jpg");
        List<String> list = new ArrayList<>();
        list.add("http://static.zk01.cc/image/lh-image/2019-06-21/8b7a20e3-e4a2-41b7-a5d0-d059d352eab6.jpg");
        list.add("http://static.zk01.cc/image/lh-image/2019-06-21/010ea99a-f082-4b46-bb1a-d3c0a4040607.jpg");
        deleteMultiFiles(list);
    }

    /**
     * 获取名称
     *
     * @param name
     * @return
     */
    public static String getFileName(String name) {
        if (null == name || "".equals(name.trim())) {
            return null;
        }
        if (name.startsWith(FILE_HOST)) {
            name = name.substring(FILE_HOST.length());
        }
        return name;
    }

    /**
     * 批量获取名称
     *
     * @param names
     * @return
     */
    public static List<String> getFileName(List<String> names) {
        if (null == names || names.size() == 0) {
            return null;
        }
        List<String> list = new ArrayList<>(names.size());
        for (String name : names) {
            String curr = getFileName(name);
            if (null != curr) {
                list.add(curr);
            }
        }
        return list.size() == 0 ? null : list;
    }

    // 关闭OSSClient
    private static void shutdown(OSSClient ossClient) {
        if (null != ossClient) {
            ossClient.shutdown();
        }
    }

}
