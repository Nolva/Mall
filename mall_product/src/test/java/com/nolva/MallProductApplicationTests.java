package com.nolva;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nolva.product.entity.BrandEntity;
import com.nolva.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {

//        Save
        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setDescript(" ");
//        brandEntity.setName("华为");
//        brandService.save(brandEntity);

//        Update
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("中华有为");
        brandService.updateById(brandEntity);

//        Query
        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
        list.forEach(System.out::println);
    }

}
