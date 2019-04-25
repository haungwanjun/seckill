package com.mobilephone.seckill.dao;

import com.mobilephone.seckill.domain.Goods;
import com.mobilephone.seckill.domain.SeckillGoods;
import com.mobilephone.seckill.domain.SeckillOrder;
import com.mobilephone.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface GoodsDao {

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.seckill_price from seckill_goods mg left join goods g on mg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.seckill_price from seckill_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);



    @Update("update seckill_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
    public int reduceStock(SeckillGoods g);

    @Update("update seckill_goods set stock_count = #{stockCount} where goods_id = #{goodsId}")
    public int resetStock(SeckillGoods g);

    @Select("select * from goods")
    public List<Goods> listAllGoods();

    @Insert("insert into goods (goods_name, goods_title, goods_img,goods_detail, goods_price, goods_stock)values(#{goodsName}, #{goodsTitle}, #{goodsImg},#{goodsDetail}, #{goodsPrice}, #{goodsStock})")
    public int insertGoods(Goods goods);

    @Select("select * from goods where id = #{id}")
    public Goods getGoodsById(long id);

    @Update("update goods set goods_name=#{goodsName},goods_title=#{goodsTitle},goods_img=#{goodsImg},goods_detail=#{goodsDetail},goods_price=#{goodsPrice},goods_stock=#{goodsStock} where id= #{id}")
    void setGoodsById(Goods goods);

    @Delete("delete from goods where id= #{id}")
    void deleteGoodsById(long id);

    @Select("select * from seckill_goods where goods_id= #{id}")
    public SeckillGoods getSeckillGoodsByGoodsId(long id);

    @Update("update seckill_goods set seckill_price=#{seckillPrice},stock_count=#{stockCount},start_date=#{startDate},end_date=#{endDate} where goods_id=#{goodsId}")
    void setSeckillGoodsById(SeckillGoods seckillGoods);

    @Insert("insert into seckill_goods (goods_id, seckill_price,stock_count,start_date,end_date)values(#{goodsId},#{seckillPrice},#{stockCount},#{startDate},#{endDate})")
    void insertSeckillGoods(SeckillGoods seckillGoods);

    @Delete("delete from seckill_goods where goods_id= #{goodsId}")
    void deleteSeckillGoodsByGoodsId(Long goodsId);

    @Update("update goods set is_seckill = 0 where id = #{goodsId}")
    void setIsSeckillFLagFalseByGoodsId(Long goodsId);

    @Update("update goods set is_seckill = 1 where id = #{goodsId}")
    void setIsSeckillFLagTureByGoodsId(Long goodsId);
}
