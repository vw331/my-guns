package cn.stylefeng.guns.modular.business.service;


import cn.stylefeng.guns.modular.business.entity.Car;
import cn.stylefeng.guns.modular.business.pojo.CarRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author sun
 * @create 2021/7/2 00:10
 * Describe
 */
public interface CarService extends IService<Car> {

    void add(CarRequest carRequest);

    void del(CarRequest carRequest);

    void edit(CarRequest carRequest);

    Car detail(CarRequest carRequest);

    PageResult<Car> findPage(CarRequest carRequest);

    List<Car> findList(CarRequest carRequest);


}
