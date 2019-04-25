package com.mobilephone.seckill.service;

import com.mobilephone.seckill.dao.GoodsDao;
import com.mobilephone.seckill.domain.Goods;
import com.mobilephone.seckill.domain.SeckillGoods;
import com.mobilephone.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public boolean reduceStock(GoodsVo goods) {
        SeckillGoods g = new SeckillGoods();
        g.setGoodsId(goods.getId());
        int ret = goodsDao.reduceStock(g);
        return ret > 0;
    }

    public void resetStock(List<GoodsVo> goodsList) {
        for (GoodsVo goods : goodsList) {
            SeckillGoods g = new SeckillGoods();
            g.setGoodsId(goods.getId());
            g.setStockCount(goods.getStockCount());
            goodsDao.resetStock(g);
        }
    }

    public List<Goods> listAllGoods() {
        return goodsDao.listAllGoods();
    }

    public Goods getGoodsById(Long goodsId) {
        return goodsDao.getGoodsById(goodsId);
    }

    public void insertGoods(Goods goods) {
        goodsDao.insertGoods(goods);
    }

    public void setGoodsById(Goods goods) {
        goodsDao.setGoodsById(goods);
    }

    public void deleteGoodsById(Long goodsId) {
        goodsDao.deleteGoodsById(goodsId);
    }

    public SeckillGoods getSeckillGoodsByGoodsId(Long id) {
        return goodsDao.getSeckillGoodsByGoodsId(id);
    }

    public void setSeckillGoodsById(SeckillGoods seckillGoods) {
        goodsDao.setSeckillGoodsById(seckillGoods);
    }

    public void insertSeckillGoods(SeckillGoods seckillGoods) {
        goodsDao.insertSeckillGoods(seckillGoods);
    }

    public void deleteSeckillGoodsByGoodsId(Long goodsId) {
        goodsDao.deleteSeckillGoodsByGoodsId(goodsId);
    }



    public void setIsSeckillFLagFalseByGoodsId(Long goodsId) {
        goodsDao.setIsSeckillFLagFalseByGoodsId(goodsId);
    }

    public void setIsSeckillFLagTureByGoodsId(Long goodsId) {
        goodsDao.setIsSeckillFLagTureByGoodsId( goodsId);
    }
}
