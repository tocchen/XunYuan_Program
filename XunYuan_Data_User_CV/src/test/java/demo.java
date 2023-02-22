import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author tocchen
 * @date 2023/2/20 16:53
 * @since jdk 1.8
 **/
public class demo {
    public static void main(String[] args) {
        System.out.println("===========start=============");
        try {
            Document doc = createPdf("D:\\Desktops\\tmp\\demo-1.pdf");
            //生成  合同文件
            createFile(doc);
            doc.close();
            // 待加水印的文件
            PdfReader reader = new PdfReader("D:\\Desktops\\tmp\\demo-1.pdf");
            // 加完水印的文件
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("D:\\Desktops\\tmp\\demo-1-result.pdf"));
            int total = reader.getNumberOfPages() + 1;
            PdfContentByte content;

            // 设置透明度
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.3f);
            // 设置字体 如果水印字体有中文，必须添加
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            // 循环对每页插入水印
            for (int i = 1; i < total; i++)
            {
                // 水印的起始
                content = stamper.getOverContent(i);
                content.setGState(gs);
                content.setFontAndSize(base, 32);
                // 开始
                content.beginText();
                // 设置颜色 默认为黑色
                content.setColorFill(BaseColor.BLACK);
                // 开始写入水印 ，参数二为水印文字，三为x坐标，四为y坐标，五为角度
                content.showTextAligned(Element.ALIGN_MIDDLE, "什么哈哈哈哈哈", 50, 0, 45);
                content.showTextAligned(Element.ALIGN_MIDDLE, "什么哈哈哈哈哈", 50, 200, 45);
                content.showTextAligned(Element.ALIGN_MIDDLE, "什么哈哈哈哈哈", 50, 400, 45);
                content.showTextAligned(Element.ALIGN_MIDDLE, "什么哈哈哈哈哈", 50, 600, 45);
                content.showTextAligned(Element.ALIGN_MIDDLE, "什么哈哈哈哈哈", 50, 800, 45);
                content.showTextAligned(Element.ALIGN_MIDDLE, "什么哈哈哈哈哈", 350, 0, 45);
                content.showTextAligned(Element.ALIGN_MIDDLE, "什么哈哈哈哈哈", 350, 200, 45);
                content.showTextAligned(Element.ALIGN_MIDDLE, "什么哈哈哈哈哈", 350, 400, 45);
                content.showTextAligned(Element.ALIGN_MIDDLE, "什么哈哈哈哈哈", 350, 600, 45);
                content.showTextAligned(Element.ALIGN_MIDDLE, "什么哈哈哈哈哈", 350, 800, 45);
                content.endText();
            }
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===========end=============");
    }

    /**
     * 创建一个pdf并打开
     * @param path  pdf路径
     */
    public static Document createPdf(String path) throws DocumentException, IOException {
        //页面大小
        //Rectangle rect = new Rectangle(PageSize.A4.rotate());//文档横方向
        Rectangle rect = new Rectangle(PageSize.A4);//文档竖方向
        File saveDir = new File(path);
        File dir = saveDir.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Document doc = new Document(rect);
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(path));
        //PDF版本(默认1.4)
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
        //文档属性
        doc.addTitle("Title@wpixel");
        doc.addAuthor("Author@wpixel");
        doc.addSubject("Subject@wpixel");
        doc.addKeywords("Keywords@wpixel");
        doc.addCreator("Creator@wpixel");
        //页边空白
        doc.setMargins(40, 40, 40, 40);
        //打开文档
        doc.open();
        return doc;
    }

    public static void createFile(Document doc) throws DocumentException {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        doc.add(PdfFontUtils.getFont(4, " "));
        doc.add(PdfFontUtils.getFont(1, "××××××调查表"));
        doc.add(PdfFontUtils.getFont(2, "（"+year+"年度）"));
        doc.add(PdfFontUtils.getFont(3, "×××全称（盖章）："+"哈哈哈哈哈哈哈哈哈"));
        doc.add(PdfFontUtils.getFont(3, "填报时间： "+year+" 年 "+month+" 月 "+day+" 日"));
        doc.add(PdfFontUtils.getFont(4, "××××××××××"));
    }

}
