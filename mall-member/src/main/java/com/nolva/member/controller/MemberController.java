package com.nolva.member.controller;

import com.nolva.common.utils.PageUtils;
import com.nolva.common.utils.R;
import com.nolva.member.entity.MemberEntity;
import com.nolva.member.feign.CouponFeignService;
import com.nolva.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;


/**
 * 会员
 *
 * @author Nolva
 * @email nolvafighting@gmail.com
 * @date 2020-08-09 18:39:41
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    private final MemberService memberService;
    private final CouponFeignService couponFeignService;

    @Autowired
    public MemberController(MemberService memberService, CouponFeignService couponFeignService) {
        this.memberService = memberService;
        this.couponFeignService = couponFeignService;
    }

    @RequestMapping("/coupons")
    public R testDiscovery(){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickname("Nolva");

        R memberCoupons = couponFeignService.memberCoupons();
//        return Objects.requireNonNull(R.ok().put("member", memberEntity)).put("coupon", memberCoupons.get("coupons"));
        return Objects.requireNonNull(R.ok().put("member", memberEntity)).put("coupons", memberCoupons.get("coupons"));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
