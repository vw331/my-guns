package cn.stylefeng.guns.modular.business.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PdfRequest {

    private String  title;

    private String orientation;

    private String Cover;

    private String header;

    @NotNull
    @JSONField(serialize = false)
    private List<MultipartFile> pages;

}
