package cn.stylefeng.guns.modular.business.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;

/**
 * @Author sun
 * @create 2021/7/1 00:08
 * Describe
 */
@Controller
@ApiResource(name = "车辆管理界面")
public class CarViewController {

    @GetResource(name = "车辆管理 首页", path = "/view/car")
    public String carIndex() {
        return "/modular/business/car/car.html";
    }

    @GetResource(name = "车辆管理-编辑", path = "/view/car/add")
    public String carAdd() {
        return "/modular/business/car/car_add.html";
    }

    @GetResource(name = "车辆管理", path = "/view/car/edit")
    public String carEdit() {
        return "/modular/business/car/car_edit.html";
    }
}
