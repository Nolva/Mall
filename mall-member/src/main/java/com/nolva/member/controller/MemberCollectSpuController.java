package com.nolva.member.controller;

import com.nolva.common.utils.PageUtils;
import com.nolva.common.utils.R;
import com.nolva.member.entity.MemberCollectSpuEntity;
import com.nolva.member.service.MemberCollectSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 会员收藏的商品
 *
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-09 18:39:41
 */
@RestController
@RequestMapping("member/membercollectspu")
public class MemberCollectSpuController {
    private final MemberCollectSpuService memberCollectSpuService;

    @Autowired
    public MemberCollectSpuController(MemberCollectSpuService memberCollectSpuService) {
        this.memberCollectSpuService = memberCollectSpuService;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberCollectSpuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberCollectSpuEntity memberCollectSpu = memberCollectSpuService.getById(id);

        return R.ok().put("memberCollectSpu", memberCollectSpu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberCollectSpuEntity memberCollectSpu){
		memberCollectSpuService.save(memberCollectSpu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberCollectSpuEntity memberCollectSpu){
		memberCollectSpuService.updateById(memberCollectSpu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		memberCollectSpuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
