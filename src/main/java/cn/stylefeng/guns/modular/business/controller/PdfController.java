package cn.stylefeng.guns.modular.business.controller;

import cn.stylefeng.guns.modular.business.pojo.PdfRequest;
import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/**
 * pdf下载
 *
 * @author sunhao
 * @date 2021/7/6 10:57
 * 1、安装
 *
 */

@Controller
@ApiResource(name = "示例")
@RequestMapping("/patient/pdf")
public class PdfController {

    /**
     * post方式 生成PDF
     */
    @ResponseBody
    @PostMapping(name = "post方式创建受试者PDF", path = "/create/{patientId}")
    public void createPDF(
            @PathVariable("patientId") String id,
            @ModelAttribute PdfRequest pdfRequest,
            HttpServletResponse response
    ) {

        //1、创建PDF实例，添加基本参数
        Pdf pdf = new Pdf();
        pdf.addParam(
                new Param("--title", pdfRequest.getTitle()),
                new Param("--page-size", "A4"),
                new Param("--encoding", "utf-8"),
                new Param("--no-footer-line"),
                new Param("--header-html", pdfRequest.getHeader()),
                new Param("--orientation", pdfRequest.getOrientation()), //  Portrait | Landscape 横向 | 纵向
                new Param("cover", pdfRequest.getCover())
        );
        //2、添加目录
        pdf.addToc();
        pdf.addTocParam(
                new Param("--disable-dotted-lines"),
                new Param("--toc-header-text", "eCRF目录")
        );

        //3、遍历pages，添加内容
        List<MultipartFile> pages = pdfRequest.getPages();
        Iterator<MultipartFile> iterator = pages.iterator();
        while (iterator.hasNext()) {
            MultipartFile page =  iterator.next();
            try {
                byte[] bytes = page.getBytes();
                String content = new String(bytes);
                if(content.trim().startsWith("http")) {
                    pdf.addPageFromUrl(content);
                }else{
                    pdf.addPageFromString(content);
                }

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        //4、返回文件
        try {
            HttpHeaders headers = new HttpHeaders();
            byte[] buffer = pdf.getPDF();
            OutputStream outputStream = response.getOutputStream();
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName="+"a.pdf");
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();
            pdf.cleanAllTempFiles();
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @GetResource(name = "测试页面", path = "/index")
    public String index() {
        return "demo.html";
    }


}
