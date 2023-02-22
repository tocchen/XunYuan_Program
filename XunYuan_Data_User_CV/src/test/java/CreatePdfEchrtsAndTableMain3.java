import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author tocchen
 * @date 2023/2/20 15:47
 * @since jdk 1.8
 **/
public class CreatePdfEchrtsAndTableMain3  {
    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter pdfWriter = PdfWriter.getInstance(document,new FileOutputStream("D:\\Desktops\\tmp\\tocchen.pdf"));
        PdfPTable table = createTable(pdfWriter);
        document.addTitle("简历");
        document.addAuthor("tocchen.top@XunYuan");
        document.addSubject("product sheet.");
        document.addKeywords("product.");
        document.setMargins(10, 10, 20, 20);
        document.open();
        document.add(table);
        document.close();

        make("D:\\Desktops\\tmp\\tocchen.pdf");
    }

    public static PdfPTable createTable(PdfWriter writer) throws DocumentException, IOException {

        PdfPTable table = new PdfPTable(5);//生成一个两列的表格
        Font font = new Font(BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
                BaseFont.NOT_EMBEDDED));
        PdfPCell cell;

        int size = 30;
        cell = new PdfPCell(new Phrase("基本信息",font));
        cell.setColspan(1);//设置所占列数
        cell.setRowspan(4);//合并行
        cell.setFixedHeight(size*3);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("姓名:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("当前求职状态:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("性别:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("我的身份:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("生日:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("电话:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("微信号:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("邮箱:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("个人优势",font));
        cell.setColspan(1);
        cell.setRowspan(3);//合并行
        cell.setFixedHeight(size*3);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("",font));
        cell.setColspan(4);
        cell.setRowspan(3);//合并行
        cell.setFixedHeight(size*3);//设置高度

        table.addCell(cell);


        cell = new PdfPCell(new Phrase("工作经历",font));
        cell.setColspan(1);//设置所占列数
        cell.setRowspan(8);//合并行
        cell.setFixedHeight(size*3);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("==========经历1=========",font));
        cell.setFixedHeight(size);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("公司名称:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("所属行业:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("所属部门:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("职位名称:",font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("在职时间:",font));
        cell.setColspan(4);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("工作内容:",font));
        cell.setColspan(4);
        cell.setRowspan(2);
        cell.setFixedHeight(size*4);//设置高度
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("工作业绩:",font));
        cell.setColspan(4);
        cell.setRowspan(2);
        cell.setFixedHeight(size*4);//设置高度
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("项目经历",font));
        cell.setColspan(1);//设置所占列数
        cell.setRowspan(8);//合并行
        cell.setFixedHeight(size*3);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("==========项目-1=========",font));
        cell.setFixedHeight(size);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("项目名称:",font));
        cell.setColspan(2);
        cell.setFixedHeight(size);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("项目角色:",font));
        cell.setColspan(2);
        cell.setFixedHeight(size);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("项目链接:",font));
        cell.setFixedHeight(size);
        cell.setColspan(4);//设置所占列数
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("在职时间:",font));
        cell.setFixedHeight(size);
        cell.setColspan(4);//设置所占列数
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("项目描述:",font));
        cell.setColspan(4);//设置所占列数
        cell.setRowspan(2);
        cell.setFixedHeight(size*4);//设置高度
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("项目业绩:",font));
        cell.setColspan(4);//设置所占列数
        cell.setRowspan(2);
        cell.setFixedHeight(size*4);//设置高度
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("教育经历",font));

        cell.setColspan(1);//设置所占列数
        cell.setRowspan(8);//合并行
        cell.setFixedHeight(size*3);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("==============经历-1==============",font));
        cell.setFixedHeight(size);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("学校名称:",font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        cell.setColspan(4);//设置所占列数
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("学历:",font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("专业:",font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        cell.setColspan(4);//设置所占列数
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("时间段:",font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        cell.setColspan(4);//设置所占列数
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("在校经历:",font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setColspan(4);//设置所占列数
        cell.setRowspan(2);
        cell.setFixedHeight(size*4);//设置高度
        table.addCell(cell);

        return table;
    }


    public static void make(String path)  {
        try{
        // 待加水印的文件
        PdfReader reader = new PdfReader(path);
        // 加完水印的文件
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("D:\\Desktops\\tmp\\demo-2-result.pdf"));
        int total = reader.getNumberOfPages() + 1;
        PdfContentByte content;

        // 设置透明度
        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.1f);
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
            content.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 50, 0, 45);
            content.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 50, 200, 45);
            content.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 50, 400, 45);
            content.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 50, 600, 45);
            content.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 50, 800, 45);
            content.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 350, 0, 45);
            content.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 350, 200, 45);
            content.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 350, 400, 45);
            content.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 350, 600, 45);
            content.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 350, 800, 45);
            content.endText();
        }
        stamper.close();
        reader.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
