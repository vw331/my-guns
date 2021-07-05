package cn.stylefeng.guns.modular.business.entity;

import cn.stylefeng.roses.kernel.db.api.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @Author sun
 * @create 2021/7/1 23:40
 * Describe
 */
@Data
@TableName("car")
public class Car extends BaseEntity {

    @TableId("car_id")
    private Long carId;

    @TableField("car_name")
    private String carName;

    /**
     * 车辆种类： 1-轿车 2-货车
     */
    @TableField("car_type")
    private Integer carType;

    @TableField("car_color")
    private String carColor;

    @TableField("car_price")
    private BigDecimal carPrice;

    private String manufacturer;


}
