package top.tocchen.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.tocchen.entity.CVEntity;
import top.tocchen.entity.UserInfoEntity;
import top.tocchen.service.ICVService;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.PDFTemplateUtil;
import top.tocchen.utils.exceptions.ExecuteException;
import top.tocchen.utils.http.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/20 15:38
 * @since jdk 1.8
 **/
@RestController
@RequestMapping("/cv/file")
public class CVFileController {

    @Autowired
    private ICVService icvService;

    @GetMapping("/download/{id}")
    public Response<?> exportCVById(@PathVariable("id") String id, HttpServletResponse response){
        CVEntity result = icvService.queryCVById(id);
//        CVEntity result = new CVEntity();
//        result.setUserInfo(new UserInfoEntity("tocchen","123456","1","1","1",new Date(),"1","1","1"));
        // Create PDF File Name [YYYYMMDD_UserName_简历.pdf]
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = simpleDateFormat.format(date);
        String datetime = str.replace("-", "").replace(" ", "").replace(":","");
        String filename = datetime+"_"+result.getUserInfo().getName()+"_"+".pdf";
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            OutputStream out = response.getOutputStream();
            PDFTemplateUtil.cvEntity2PDF(result,out);
        }catch (Exception e){
            throw new ExecuteException();
        }
        return Response.success();
    }
}
