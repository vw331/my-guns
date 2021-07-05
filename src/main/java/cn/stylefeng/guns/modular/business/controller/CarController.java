package cn.stylefeng.guns.modular.business.controller;

import cn.stylefeng.guns.modular.business.pojo.CarRequest;
import cn.stylefeng.guns.modular.business.service.CarService;
import cn.stylefeng.roses.kernel.rule.pojo.response.ResponseData;
import cn.stylefeng.roses.kernel.rule.pojo.response.SuccessResponseData;
import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.PostResource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author sun


 * @create 2021/7/3 12:25
 * Describe
 */
@RestController
@ApiResource(name = "车辆管理 ")
public class CarController {

    @Resource
    private CarService carService;

    @PostResource(name = "添加车辆", path = "/car/add")
    public ResponseData add(@RequestBody @Validated(CarRequest.add.class) CarRequest carRequest) {
        carService.add(carRequest);
        return new SuccessResponseData();
    }

    @PostResource(name = "删除车辆", path = "/car/delete")
    public ResponseData delete(@RequestBody @Validated(CarRequest.delete.class) CarRequest carRequest) {
        carService.del(carRequest);
        return new SuccessResponseData();
    }

    @GetResource(name = "查看车辆", path = "/car/detail")
    public ResponseData edit(@Validated(CarRequest.delete.class) CarRequest carRequest) {
        return new SuccessResponseData(carService.detail(carRequest));
    }

    @GetResource(name = "车辆列表", path = "/car/findList")
    public ResponseData list(CarRequest carRequest) {
        return new SuccessResponseData(carService.findList(carRequest));
    }

    @GetResource(name = "分页查询", path = "/car/findPage")
    public ResponseData page(CarRequest carRequest) {
        return new SuccessResponseData(carService.findPage(carRequest));
    }

}
