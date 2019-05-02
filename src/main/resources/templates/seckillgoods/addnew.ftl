<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/admin/seckillgoods/addnew">
                        <label>秒杀商品信息新增</label>
                        <div class="form-group">
                            <label>商品名称</label>
                            <input name="goodsName" readonly="readonly" type="text" class="form-control" value="${(goods.goodsName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>原售价</label>
                            <input name="goodsPrice" readonly="readonly" type="text" class="form-control" value="${(goods.goodsPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>秒杀价</label>
                            <input name="seckillPrice" type="text" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>秒杀数量</label>
                            <input name="stockCount" type="text" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="${(goods.goodsImg)!''}" alt="">
                        </div>
                        <div class="form-group">
                            <label>开始时间(为了全球统一，请输入GMT(零时区时间))</label>
                            <input name="startDate" type="text" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>结束时间(为了全球统一，请输入GMT(零时区时间))</label>
                            <input name="endDate" type="text" class="form-control"/>
                        </div>
                        <input hidden type="text" name="goodsId" value="${(goods.id)!''}">
                            <button type="submit" class="btn btn-default">新增</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>