package cn.stylefeng.guns.modular.business.pojo;

import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author sun
 * @create 2021/7/2 00:12
 * Describe
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CarRequest extends BaseRequest {

    @NotNull(message = "车辆id不能为空", groups = {edit.class, delete.class, detail.class})
    private Long carId;

    @NotBlank(message = "车辆名称不能为空", groups = {add.class, edit.class})
    private String carName;

    @NotNull(message = "车辆种类不能为空", groups = {add.class, edit.class})
    private Integer carType;

    private String carColor;

    private BigDecimal carPrice;

    private String manufacturer;
}
