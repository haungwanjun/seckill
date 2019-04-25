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
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>售价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th colspan="2">商品状态</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list goodsList as goods>
                        <tr>
                            <td>${goods.id}</td>
                            <td>${goods.goodsName}</td>
                            <td><img height="100" width="100" src="${(goods.goodsImg)!''}" alt=""></td>
                            <td>${goods.goodsPrice}</td>
                            <td>${goods.goodsStock}</td>
                            <td>${goods.goodsDetail}</td>
                            <#if goods.isSeckill == 1>
                                <td>已设为秒杀</td>
                                <td><a href="/admin/seckillgoods/delete?goodsId=${goods.id}">停止秒杀</a></td>
                            <#else>
                                <td>未设为秒杀</td>
                                <td><a href="/admin/goods/startseckill?goodsId=${goods.id}">设为秒杀</a></td>
                            </#if>

                            <td><a href="/admin/goods/update?goodsId=${goods.id}">修改</a></td>
                            <td><a href="/admin/goods/delete?goodsId=${goods.id}">删除</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

</div>
</body>
</html>