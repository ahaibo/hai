package com.hai.common.util;

import jxl.biff.EmptyCell;
import jxl.write.*;
import jxl.write.Boolean;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/7.
 */
public class JXLExportUtil {
    @Test
    public void test() {
        export2excell("D:\\Files", "jxl-export2excell-1.xlsx");
    }

    public static jxl.write.WritableWorkbook export2excell(String filePath, String fileName) {
        try {
            jxl.write.WritableWorkbook book = jxl.Workbook.createWorkbook(new File(filePath, fileName));
            jxl.write.WritableSheet sheet = book.createSheet("第一页", 0);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            //Label: c: column index, start from 0; r: row index, start from 0, cont: content string.
            jxl.write.WritableCell cell = new jxl.write.Label(0, 0, "First Cell(Label)");
            sheet.addCell(cell);

            Blank blank = new Blank(0, 0, new WritableCellFormat());
            sheet.addCell(blank);

            Boolean bt = new Boolean(1, 0, true);
            Boolean bf = new Boolean(2, 0, false);
            sheet.addCell(bt);
            sheet.addCell(bf);

            DateTime dateTime = new DateTime(3, 0, new Date());
            sheet.addCell(dateTime);

            EmptyCell empty = new EmptyCell(4, 0);
            sheet.addCell(empty);

            Formula formula = new Formula(4, 0, "a^2 + b^2 = c^2");
            sheet.addCell(formula);

            Number number = new Number(0, 1, 123.456);

            Label l1 = new Label(1, 1, "12.12");
            Label l2 = new Label(2, 1, "false");
            Label l3 = new Label(3, 1, new Date().toString());
            Label l4 = new Label(4, 1, format.format(new Date()));

            sheet.addCell(number);
            sheet.addCell(l1);
            sheet.addCell(l2);
            sheet.addCell(l3);

            book.write();
            book.close();
            return book;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
