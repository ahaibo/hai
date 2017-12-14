package com.hai.spring.view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.hai.spring.view.override.MyAbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * RGB(255,23,140)是光的三原色，也即红绿蓝Red、Green、Blue,它们的最大值是255，相当于100%。
 * 白色：rgb(255,255,255)
 * 黑色：rgb(0,0,0)
 * 红色：rgb(255,0,0)
 * 绿色：rgb(0,255,0)
 * 蓝色：rgb(0,0,255)
 * 青色：rgb(0,255,255)
 * 紫色：rgb(255,0,255)
 * 调整相关数字，便可以得到深浅不一的各种颜色。
 */
public class MyPDFView extends MyAbstractPdfView {

    private List<String> titles;

    public MyPDFView() {
    }

    public MyPDFView(List<String> titles) {
        this.titles = titles;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取字体
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        BaseColor color = new BaseColor(255, 0, 0);//red BaseColor.RED
        Font font = new Font(baseFont, 16, Font.NORMAL, color);

        String header = (String) model.get("header");
        Paragraph headerParagraph = new Paragraph(header, font);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(headerParagraph);

        Font phraseFont = new Font(baseFont, 10, Font.NORMAL);
        List<Map<String, String>> list = (List<Map<String, String>>) model.get("content");

        PdfPTable table = new PdfPTable(titles.size());
        table.normalizeHeadersFooters();
        table.setWidthPercentage(80);//宽度比

        //创建表头 header
        for (String title : titles) {
            Phrase phrase = new Phrase(title, phraseFont);
            PdfPCell cell = new PdfPCell(phrase);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
//        table.completeRow();

        //创建 PDF Content
        for (Map<String, String> map : list) {
            Set<Map.Entry<String, String>> entrySet = map.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                String data = entry.getValue();
                Paragraph contentParagraph = new Paragraph(data, font);

                PdfPCell cell = new PdfPCell(contentParagraph);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
        }
        document.add(table);

        //copyright
        String copyright = (String) model.get("copyright");
        Paragraph copyrightParagraph = new Paragraph(copyright, font);
        copyrightParagraph.setAlignment(Paragraph.ALIGN_BOTTOM);
        document.add(copyrightParagraph);

    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
}