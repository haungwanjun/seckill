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
                    <form role="form" method="post" action="/admin/goods/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="goodsName" type="text" class="form-control" value="${(goods.goodsName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="goodsPrice" type="text" class="form-control" value="${(goods.goodsPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="goodsStock" type="number" class="form-control" value="${(goods.goodsStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="goodsDetail" type="text" class="form-control" value="${(goods.goodsDetail)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="${(goods.goodsImg)!''}" alt="">
                            <input name="goodsImg" type="text" class="form-control" value="${(goods.goodsImg)!''}"/>
                        </div>
                        <input hidden type="text" name="id" value="${(goods.id)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>