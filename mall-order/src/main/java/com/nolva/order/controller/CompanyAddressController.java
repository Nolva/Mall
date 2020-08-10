package com.nolva.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nolva.order.entity.CompanyAddressEntity;
import com.nolva.order.service.CompanyAddressService;
import com.nolva.common.utils.PageUtils;
import com.nolva.common.utils.R;



/**
 * 公司收发货地址表
 *
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-09 18:52:00
 */
@RestController
@RequestMapping("order/companyaddress")
public class CompanyAddressController {
    @Autowired
    private CompanyAddressService companyAddressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = companyAddressService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CompanyAddressEntity companyAddress = companyAddressService.getById(id);

        return R.ok().put("companyAddress", companyAddress);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CompanyAddressEntity companyAddress){
		companyAddressService.save(companyAddress);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CompanyAddressEntity companyAddress){
		companyAddressService.updateById(companyAddress);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		companyAddressService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
