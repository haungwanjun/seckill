package com.mobilephone.seckill.controller;

import com.mobilephone.seckill.domain.Goods;
import com.mobilephone.seckill.exception.GlobalException;
import com.mobilephone.seckill.result.CodeMsg;
import com.mobilephone.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/goods")
public class AdminGoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<Goods> goodsList = goodsService.listAllGoods();
        map.put("goodsList", goodsList);
        return new ModelAndView("goods/list", map);
    }

    @GetMapping("/update")
    public ModelAndView update(@RequestParam(value = "goodsId", required = false) Long goodsId,
                               Map<String, Object> map) {
        if (goodsId != null) {
            Goods goods = goodsService.getGoodsById(goodsId);
            map.put("goods", goods);
        }
        return new ModelAndView("goods/update", map);
    }
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "goodsId") Long goodsId,
                               Map<String, Object> map) {
        if (goodsId != null) {
            goodsService.deleteGoodsById(goodsId);
        }
        map.put("url", "/admin/goods/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/startseckill")
    public ModelAndView startseckill(@RequestParam(value = "goodsId") Long goodsId,
                               Map<String, Object> map) {
        if (goodsId != null) {
            Goods goods = goodsService.getGoodsById(goodsId);
            map.put("goods", goods);
        }
        return new ModelAndView("seckillgoods/addnew", map);
    }


    @PostMapping("/save")
    public ModelAndView save(@Valid Goods goods,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
//        if (bindingResult.hasErrors()) {
//            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
//            map.put("url", "/admin/goods/update");
//            return new ModelAndView("common/error", map);
//        }
       // Goods good = new Goods();
        try {
            if (goods.getId() != null) {
                goodsService.setGoodsById(goods);
            } else {
                goodsService.insertGoods(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("url", "/admin/goods/list");
        return new ModelAndView("common/success", map);
    }


}
