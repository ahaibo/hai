package com.hai.common.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;

import java.io.*;

/**
 * 文件导出工具类
 * Created by Administrator on 2017/10/7.
 */
public class POIExportUtil {

    /**
     * 利用 POI 组件到处 office 文件
     *
     * @param fileName
     * @param content
     * @param out
     */
    public static void export2doc(String fileName, String content, OutputStream out) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(content.getBytes());
            POIFSFileSystem fileSystem = new POIFSFileSystem();
            DirectoryEntry directory = fileSystem.getRoot();
            directory.createDocument(fileName, bais);
            fileSystem.writeFilesystem(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(bais, out);
        }
    }

    @Test
    public void testCreateExcelFile() {
        createExcelFile("D:\\Files", "POICreateExcelFile.xls");
    }

    public static HSSFWorkbook createExcelFile(String filePath, String fileName) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("第一页");
//        HSSFRow row = sheet.createRow(0);
//        HSSFCell cell = row.createCell(0, CellType.STRING);
//        cell.setCellValue(12.34);

        for (int i = 0; i < 30; i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 6; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(RandomUtil.randomString(20));
            }
        }

        File xls = new File(filePath, fileName);
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(xls));
            workbook.write(bos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(bos, workbook);
        }
        return workbook;
    }

    private static void close(OutputStream os, HSSFWorkbook workbook) {
        close(os);
        close(workbook);
    }


    private static void close(InputStream in, OutputStream out) {
        close(in);
        close(out);
    }

    private static void close(HSSFWorkbook workbook) {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(InputStream in) {
        if (null != in) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void close(OutputStream out) {
        if (null != out) {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
