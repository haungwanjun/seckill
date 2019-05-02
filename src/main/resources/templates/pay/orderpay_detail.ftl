<html>
<head>
    <meta charset="utf-8">
    <title>订单支付详情页</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <td>${orderPay.outTradeNo}</td>
                        </tr>
                        </thead>
                    </table>
                </div>

                <#--订单详情表数据-->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <tbody>
                        <tr>
                            <th>用户姓名</th>
                            <td>${user.nickname}</td>
                        </tr>
                        <tr>
                            <th>商品名称</th>
                            <td>${orderPay.subject}</td>
                        </tr>
                        <tr>
                            <th>支付平台</th>
                            <td>${payInfo.payPlatform}</td>
                        </tr>
                        <tr>
                            <th>支付形式</th>
                            <td>${payInfo.platformStatus}</td>
                        </tr>
                        <tr>
                            <th>订单创建时间</th>
                            <td>${(orderPay.gmtCreate)?string("yyyy-MM-dd HH:mm:ss")}</td>
                        </tr>
                        <tr>
                            <th>订单支付时间</th>
                            <td>${(orderPay.gmtPayment)?string("yyyy-MM-dd HH:mm:ss")}</td>
                        </tr>
                        <tr>
                            <th>交易状态</th>
                            <#if orderPay.tradeStatus?? && orderPay.tradeStatus == "WAIT_BUYER_PAY">
                                <td>待支付</td>
                            <#else >
                                <td>已支付</td>
                            </#if>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <#--操作-->
                <div class="col-md-12 column" align="center">
                        <a href="/order_detail.htm?orderId=#{orderPay.orderId}" type="button" class="btn btn-default btn-primary">订单详情</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>