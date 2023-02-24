package top.tocchen.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import top.tocchen.entity.CVEntity;
import top.tocchen.utils.exceptions.ExecuteException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

/**
 * @author tocchen
 * @date 2023/2/20 15:46
 * @since jdk 1.8
 **/
public class PDFTemplateUtil {

    public static void cvEntity2PDF(CVEntity entity, OutputStream out){
        try {
            Document document = new Document(PageSize.A4,10, 10, 20, 20);
            document.addAuthor("tocchen.top@XunYuan");
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();
            document.add(createTable(writer,entity));
            int count = writer.getPageNumber() ;
            for(int n = 1; n<=count; n++){
                // 添加文字水印
                PdfContentByte cb = writer.getDirectContent();
                cb.beginText(); // 开始
                // 设置透明度
                PdfGState gs = new PdfGState();
                gs.setFillOpacity(0.05f);
                cb.setGState(gs);
                cb.setFontAndSize(base,32);
                cb.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 50, 0, 45);
                cb.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 50, 200, 45);
                cb.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 50, 400, 45);
                cb.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 50, 600, 45);
                cb.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 50, 800, 45);
                cb.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 350, 0, 45);
                cb.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 350, 200, 45);
                cb.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 350, 400, 45);
                cb.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 350, 600, 45);
                cb.showTextAligned(Element.ALIGN_MIDDLE, "寻猿网(tocchen.top@xunyuan)", 350, 800, 45);
                cb.endText(); // 结束
            }
            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * PDF 格式设置
     * @param writer PdfWriter
     * @param entity CVEntity
     * @return PdfPTable
     * @throws DocumentException 抛出异常
     * @throws IOException 抛出的异常
     */
    private static PdfPTable createTable(PdfWriter writer,CVEntity entity) throws DocumentException, IOException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        PdfPTable table = new PdfPTable(5);//生成一个两列的表格
        Font font = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED),12,Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell;

        int size = 30;
        cell = new PdfPCell(new Phrase("基本信息",font));
        cell.setColspan(1);//设置所占列数
        cell.setRowspan(4);//合并行
        cell.setFixedHeight(size*3);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("姓名:\t"+entity.getUserInfo().getName(),font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("当前求职状态:\t"+entity.getUserInfo().getJobStatus(),font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("性别:\t"+entity.getUserInfo().getGender(),font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("我的身份:\t"+entity.getUserInfo().getIdentity(),font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("生日:\t"+simpleDateFormat.format(entity.getUserInfo().getBirthday()),font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("电话:\t"+entity.getUserInfo().getPhone(),font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("微信号:\t"+entity.getUserInfo().getWechatId(),font));
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        cell.setFixedHeight(size);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("邮箱:\t"+entity.getUserInfo().getEmail(),font));
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
        if (entity.getUserAdvantage() == null || entity.getUserAdvantage().getInfo() == null){
            cell = new PdfPCell(new Phrase("",font));
        }else{
            cell = new PdfPCell(new Phrase(entity.getUserAdvantage().getInfo(),font));
        }
        cell.setColspan(4);
        cell.setRowspan(3);//合并行
        cell.setFixedHeight(size*3);//设置高度
        table.addCell(cell);

        // =================================================================
        int workHistoryCount;
        if (entity.getUserWorkHistory() == null){
            workHistoryCount = 0;
        }else {
            workHistoryCount = entity.getUserWorkHistory().size();
        }

        cell = new PdfPCell(new Phrase("工作经历",font));
        cell.setColspan(1);//设置所占列数
        if (workHistoryCount == 0){
            cell.setRowspan(8);//合并行
        }else {
            cell.setRowspan(8*workHistoryCount);
        }
        cell.setFixedHeight(size*3);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        if (workHistoryCount == 0){
            cell = new PdfPCell(new Phrase("没有工作经历",font));
            cell.setColspan(4);//设置所占列数
            cell.setRowspan(8);//合并行
            cell.setFixedHeight(size*3);//设置高度
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
            table.addCell(cell);
        }else{
            for (int i = 0; i < workHistoryCount ;i++){
                cell = new PdfPCell(new Phrase("==========经历-"+i+"=========",font));
                cell.setFixedHeight(size);
                cell.setColspan(4);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("公司名称:\t"+entity.getUserWorkHistory().get(i).getCompanyName(),font));
                cell.setColspan(4);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                cell.setFixedHeight(size);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("所属部门:\t"+entity.getUserWorkHistory().get(i).getDepartment(),font));
                cell.setColspan(2);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                cell.setFixedHeight(size);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("职位名称:\t"+entity.getUserWorkHistory().get(i).getPosition(),font));
                cell.setColspan(2);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                cell.setFixedHeight(size);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("在职时间:\t"+simpleDateFormat.format(entity.getUserWorkHistory().get(i).getStartDate())
                        +" - "+ simpleDateFormat.format(entity.getUserWorkHistory().get(i).getEndDate())
                        ,font));
                cell.setColspan(4);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                cell.setFixedHeight(size);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("工作内容:\t"+entity.getUserWorkHistory().get(i).getWorkContent(),font));
                cell.setColspan(4);
                cell.setRowspan(2);
                cell.setFixedHeight(size*4);//设置高度
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("工作业绩:\t"+entity.getUserWorkHistory().get(i).getPerformance(),font));
                cell.setColspan(4);
                cell.setRowspan(2);
                cell.setFixedHeight(size*4);//设置高度
                table.addCell(cell);
            }
        }


        // ================================================================================
        int projectCount;
        if (entity.getUserProjectHistory() == null){
            projectCount = 0;
        }else {
            projectCount = entity.getUserProjectHistory().size();
        }
        cell = new PdfPCell(new Phrase("项目经历",font));
        cell.setColspan(1);//设置所占列数
        if (projectCount == 0){
            cell.setRowspan(8);//合并行
        }else {
            cell.setRowspan(8*projectCount);
        }
        cell.setFixedHeight(size*3);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        if (projectCount == 0){
            cell = new PdfPCell(new Phrase("没有项目经历",font));
            cell.setColspan(4);//设置所占列数
            cell.setRowspan(8);//合并行
            cell.setFixedHeight(size*3);//设置高度
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
            table.addCell(cell);
        }else{
            for (int i = 0; i < projectCount; i++) {
                cell = new PdfPCell(new Phrase("==========项目-1=========",font));
                cell.setFixedHeight(size);
                cell.setColspan(4);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("项目名称:\t"+entity.getUserProjectHistory().get(i).getProjectName(),font));
                cell.setColspan(2);
                cell.setFixedHeight(size);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("项目角色:\t"+entity.getUserProjectHistory().get(i).getProjectRole(),font));
                cell.setColspan(2);
                cell.setFixedHeight(size);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("项目链接:\t"+entity.getUserProjectHistory().get(i).getProjectLink(),font));
                cell.setFixedHeight(size);
                cell.setColspan(4);//设置所占列数
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("在职时间:\t"+simpleDateFormat.format(entity.getUserProjectHistory().get(i).getStartProjectDate())
                        + " - " + simpleDateFormat.format(entity.getUserProjectHistory().get(i).getEndProjectDate())
                        ,font));
                cell.setFixedHeight(size);
                cell.setColspan(4);//设置所占列数
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("项目描述:\t"+entity.getUserProjectHistory().get(i).getProjectDescribe(),font));
                cell.setColspan(4);//设置所占列数
                cell.setRowspan(2);
                cell.setFixedHeight(size*4);//设置高度
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("项目业绩:\t"+entity.getUserProjectHistory().get(i).getProjectPerformance(),font));
                cell.setColspan(4);//设置所占列数
                cell.setRowspan(2);
                cell.setFixedHeight(size*4);//设置高度
                table.addCell(cell);
            }
        }


        int educationalCount;
        if (entity.getUserEducational() == null){
            educationalCount = 0;
        }else {
            educationalCount = entity.getUserEducational().size();
        }

        cell = new PdfPCell(new Phrase("教育经历",font));
        cell.setColspan(1);//设置所占列数
        if (educationalCount == 0){
            cell.setRowspan(8);//合并行
        }else {
            cell.setRowspan(8*educationalCount);
        }
        cell.setFixedHeight(size*3);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        if (educationalCount == 0){
            cell = new PdfPCell(new Phrase("没有教育经验",font));
            cell.setColspan(4);//设置所占列数
            cell.setRowspan(8);//合并行
            cell.setFixedHeight(size*3);//设置高度
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
            table.addCell(cell);
        }else{
            for (int i = 0; i < educationalCount; i++) {
                cell = new PdfPCell(new Phrase("==============教育经历-1==============",font));
                cell.setFixedHeight(size);
                cell.setColspan(4);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("学校名称:\t"+entity.getUserEducational().get(i).getSchoolName(),font));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                cell.setFixedHeight(size);
                cell.setColspan(4);//设置所占列数
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("学历:\t"+entity.getUserEducational().get(i).getEducational(),font));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                cell.setFixedHeight(size);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("专业:\t"+entity.getUserEducational().get(i).getMajor(),font));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                cell.setFixedHeight(size);
                cell.setColspan(4);//设置所占列数
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("时间段:\t"+simpleDateFormat.format(entity.getUserEducational().get(i).getStartDate())
                        +" - " + simpleDateFormat.format(entity.getUserEducational().get(i).getEndDate())
                        ,font));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                cell.setFixedHeight(size);
                cell.setColspan(4);//设置所占列数
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("在校经历:\t"+entity.getUserEducational().get(i).getOnSchoolContent(),font));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
                cell.setColspan(4);//设置所占列数
                cell.setRowspan(2);
                cell.setFixedHeight(size*4);//设置高度
                table.addCell(cell);
            }
        }
        return table;
    }


    private static void make(String path)  {
        try{
            // 待加水印的文件
            PdfReader reader = new PdfReader(path);
            // 加完水印的文件
            FileOutputStream out = new FileOutputStream("\\tmp\\demo-2-result.pdf");
            PdfStamper stamper = new PdfStamper(reader, out);
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
