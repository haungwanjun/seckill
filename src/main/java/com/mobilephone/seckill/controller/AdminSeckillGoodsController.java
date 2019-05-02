package com.mobilephone.seckill.controller;

import com.mobilephone.seckill.domain.Goods;
import com.mobilephone.seckill.domain.SeckillGoods;
import com.mobilephone.seckill.service.GoodsService;
import com.mobilephone.seckill.service.SeckillGoodsService;
import com.mobilephone.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.mobilephone.seckill.converter.StringToDateConverter;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/seckillgoods")
public class AdminSeckillGoodsController {
    @Autowired
    SeckillGoodsService seckillGoodsService;

    @Autowired
    GoodsService goodsService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<GoodsVo> goodsVoList = goodsService.listGoodsVo();
        map.put("goodsVoList", goodsVoList);
        return new ModelAndView("seckillgoods/list", map);
    }
    @GetMapping("/update")
    public ModelAndView update(@RequestParam(value = "goodsId", required = false) Long goodsId,
                               Map<String, Object> map) {
        if (goodsId != null) {
            GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
            map.put("goodsVo", goodsVo);
        }
        return new ModelAndView("seckillgoods/update", map);
    }

    @RequestMapping(value = "/addnew", method = RequestMethod.POST)
    public String addnew(@Valid Goods goods, HttpServletRequest request) {
//        if (bindingResult.hasErrors()) {
//            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
//            map.put("url", "/admin/goods/update");
//            return new ModelAndView("common/error", map);
//        }
        StringToDateConverter std = new StringToDateConverter();
        SeckillGoods  seckillGoods = new SeckillGoods();
        seckillGoods.setGoodsId(Long.valueOf(request.getParameter("goodsId")));
        seckillGoods.setStockCount(Integer.valueOf(request.getParameter("stockCount")));
        seckillGoods.setSeckillPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter("seckillPrice"))));
        seckillGoods.setStartDate(std.convert(request.getParameter("startDate")));
        seckillGoods.setEndDate(std.convert(request.getParameter("endDate")));
        try {
                goodsService.insertSeckillGoods(seckillGoods);
                goodsService.setIsSeckillFLagTureByGoodsId(seckillGoods.getGoodsId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/seckillgoods/list";
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid GoodsVo goodsVo,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
//        if (bindingResult.hasErrors()) {
//            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
//            map.put("url", "/admin/goods/update");
//            return new ModelAndView("common/error", map);
//        }
        SeckillGoods  seckillGoods = new SeckillGoods();
        SeckillGoods  sg = goodsService.getSeckillGoodsByGoodsId(goodsVo.getId());
        seckillGoods.setGoodsId(goodsVo.getId());
        seckillGoods.setStockCount(goodsVo.getStockCount());
        seckillGoods.setSeckillPrice(goodsVo.getSeckillPrice());
        seckillGoods.setStartDate(goodsVo.getStartDate());
        seckillGoods.setEndDate(goodsVo.getEndDate());
        try {
            if (goodsVo.getId() != null) {
                seckillGoods.setId(sg.getId());
                goodsService.setSeckillGoodsById(seckillGoods);
            } else {
                goodsService.insertSeckillGoods(seckillGoods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("url", "/admin/seckillgoods/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "goodsId") Long goodsId,
                               Map<String, Object> map) {
        if (goodsId != null) {
            goodsService.deleteSeckillGoodsByGoodsId(goodsId);
            goodsService.setIsSeckillFLagFalseByGoodsId(goodsId);
        }
        map.put("url", "/admin/seckillgoods/list");
        return new ModelAndView("common/success", map);
    }


}
