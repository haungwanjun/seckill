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
                        <tr >
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>原售价</th>
                            <th>秒杀价</th>
                            <th>库存</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list goodsVoList as goodsVo>
                        <tr>
                            <td>${goodsVo.id}</td>
                            <td>${goodsVo.goodsName}</td>
                            <td><img height="100" width="100" src="${(goodsVo.goodsImg)!''}" alt=""></td>
                            <td>${goodsVo.goodsPrice}</td>
                            <td>${goodsVo.seckillPrice}</td>
                            <td>${goodsVo.stockCount}</td>
                            <td>${(goodsVo.startDate)?string("yyyy-MM-dd hh:mm:ss")}</td>
                            <td>${(goodsVo.endDate)?string("yyyy-MM-dd hh:mm:ss")}</td>
                            <td><a href="/admin/seckillgoods/update?goodsId=${goodsVo.id}">修改</a></td>
                            <td><a href="/admin/seckillgoods/delete?goodsId=${goodsVo.id}">删除</a></td>
                        </tr>

                        </#list>
                        </tbody>
                    </table>
                </div>

</div>
</body>
</html>