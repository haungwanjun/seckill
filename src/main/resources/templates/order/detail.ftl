<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">


    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>订单金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderInfo.id}</td>
                            <td>${orderInfo.goodsCount * orderInfo.goodsPrice}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            <#--订单详情表数据-->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>用户姓名</th>
                            <th>手机号</th>
                            <th>收货地址</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderInfo.userName}</td>
                            <td>${orderInfo.userId}</td>
                            <td>${orderInfo.deliveryAddress}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <#--订单详情表数据-->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总额</th>
                            <th>订单创建时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderInfo.goodsId}</td>
                            <td>${orderInfo.goodsName}</td>
                            <td>${orderInfo.goodsPrice}</td>
                            <td>${orderInfo.goodsCount}</td>
                            <td>${orderInfo.goodsCount * orderInfo.goodsPrice}</td>
                            <td>${(orderInfo.createDate)?string("yyyy-MM-dd HH:mm:ss")}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            <#--操作-->
                <div class="col-md-12 column" align="center">
                <#if orderInfo.status == 0>
                    <a href="" type="button" class="btn btn-default btn-primary">完结订单</a>
                    <a href="" type="button" class="btn btn-default btn-danger">取消订单</a>
                </#if>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>