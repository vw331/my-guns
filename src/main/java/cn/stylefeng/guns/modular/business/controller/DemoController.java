package cn.stylefeng.guns.modular.business.controller;

import cn.hutool.core.lang.Dict;
import cn.stylefeng.guns.modular.business.pojo.PdfRequest;
import cn.stylefeng.guns.modular.business.service.DemoService;
import cn.stylefeng.roses.kernel.rule.pojo.response.ErrorResponseData;
import cn.stylefeng.roses.kernel.rule.pojo.response.ResponseData;
import cn.stylefeng.roses.kernel.rule.pojo.response.SuccessResponseData;
import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.PostResource;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.configurations.WrapperConfig;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;
import org.apache.coyote.OutputBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 示例控制器
 *
 * @author fengshuonan
 * @date 2021/1/24 10:57
 */
@Controller
@ApiResource(name = "示例")
public class DemoController {

    @Resource
    private DemoService demoService;

    /**
     * 示例方法
     *
     * @author fengshuonan
     * @date 2021/1/24 10:59
     */
    @GetResource(name = "示例方法", path = "/json/success")
    @ResponseBody
    public ResponseData renderSuccess() {
        demoService.demoService();
        return new SuccessResponseData();
    }

    /**
     * 示例加密方法
     * <p>
     * requiredEncryption = true
     * </p>
     *
     * @author fengshuonan
     * @date 2021/1/24 10:59
     */
    @PostResource(name = "示例加密方法", path = "/encode", requiredPermission = false, requiredLogin = false, requiredEncryption = true)
    @ResponseBody
    public ResponseData encode(@RequestBody Dict dict) {
        return new SuccessResponseData(dict);
    }



    /**
     * 生成PDF
     */
    @ResponseBody
    @GetMapping(name = "创建pdf", path = "/pdf/create")
    public void createPDF(HttpServletResponse response) {
        //String wkhtmltopdfCommand = "C:/wkhtmltox-0.12.6-1.mxe-cross-win64/wkhtmltox/wkhtmltoimage.exe";
        //WrapperConfig wc = new WrapperConfig(wkhtmltopdfCommand);
        Pdf pdf = new Pdf();
        pdf.addParam(
                new Param("--title", "xxx项目病历表"),
                new Param("--page-size", "A4"),
                new Param("--encoding", "utf-8"),
                new Param("--no-footer-line"),
                new Param("--header-html", "file:///C:/demo/my-guns/header.html"),
                new Param("--orientation", "Landscape"), //  Portrait | Landscape 横向 | 纵向
                new Param("cover", "http://baidu.com")
        );
        pdf.addPageFromString("<h1 style='color: red'>第一页!</h1><p>第一页里的内容， 来自html</p>");
        pdf.addPageFromString("<h5 style='color: red'>第二页!</h5><p>第二页里的内容, 来自file</p>");

        pdf.addPageFromUrl("http://douban.com");
        pdf.addToc();
        pdf.addTocParam(
                new Param("--disable-dotted-lines"),
                new Param("--toc-header-text", "eCRF目录")
        );

        try {
            HttpHeaders headers = new HttpHeaders();
            byte[] buffer = pdf.getPDF();
            OutputStream outputStream = response.getOutputStream();
            response.reset();
            response.setHeader("Content-Disposition", "inline; filename=citiesreport.pdf");
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

    @GetMapping("/demo/index")
    public String index() {
        return "demo.html";
    }

    @GetMapping(path = "/pdf/cover/{patientId}")
    public String cover(@PathVariable("patientId") String id, Model model) {
        model.addAttribute("id", id);
        return "/modular/business/pdf/cover.html";
    }

    @GetMapping(path = "/pdf/cover/{patientId}/header")
    public String coverHeader(@PathVariable("patientId") String id, Model model) {
        model.addAttribute("id", id);
        return "/modular/business/pdf/header.html";
    }
}

