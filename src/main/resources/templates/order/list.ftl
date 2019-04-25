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
                            <th>订单id</th>
                            <th>商品名称</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>数量</th>
                            <th>总价</th>
                            <th>订单状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list orderInfoList as orderInfo>
                        <tr>
                            <td>${orderInfo.id}</td>
                            <td>${orderInfo.goodsName}</td>
                            <td>${orderInfo.userName}</td>
                            <td>${orderInfo.userId}</td>
                            <td>${orderInfo.deliveryAddress}</td>
                            <td>${orderInfo.goodsCount}</td>
                            <td>${orderInfo.goodsPrice}</td>
                            <td>${orderInfo.status}</td>
                            <#--不能将Date类型直接转化为String类型-->
                            <td>${(orderInfo.createDate)?string("yyyy-MM-dd hh:mm:ss")}</td>
                            <td><a href="/admin/order/detail?orderId=${orderInfo.id}">详情</a></td>
                            <td>
                                <#if orderInfo.status == 0>
                                    <a href="/admin/order/cancel?orderId=${orderInfo.id}">取消</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

</div>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>