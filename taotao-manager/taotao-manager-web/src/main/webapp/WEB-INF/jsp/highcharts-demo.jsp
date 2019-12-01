<%--
  Created by IntelliJ IDEA.
  User: tushengtao
  Date: 2019/10/15
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    案例
</head>
<body>
<script src="/js/highcharts-7.2.0/highcharts.js"type="text/javascript"></script>
<%--highcharts-demo实例--%>
<div id="highcharts-demo" style="width: 100%; height:400px"></div>
<%--highcharts-demo实例结束--%>

<div id="container2" style="min-width:400px;height:400px"></div>


<script type="text/javascript">

    //highcharts-demo 实例
    var chart1; // 全局变量
    $(document).ready(function() {
        chart1 = new Highcharts.Chart({
            chart : {
                renderTo: 'highcharts-demo',
                type : 'bar'
            },
            title : {
                text : 'Fruit Consumption'
            },
            xAxis : {
                categories: [ 'Apples', 'Bananas', 'Oranges' ]
            },
            yAxis : {
                title : {
                    text: 'Fruit eaten'
                }
            },
            series : [ {
                name : 'Jane',
                data : [1, 0, 4 ]
            }, {
                name : 'John',
                data : [5, 7, 3 ]
            } ]
        });
    });
    //highcharts-demo实例结束

    var chart = Highcharts.chart('container2',{
        chart: {
            type: 'area'
        },
        title: {
            text: '美苏核武器库存量'
        },
        subtitle: {
            text: '数据来源: &lt;a href=&quot;https://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf&quot;&gt;' +
                'thebulletin.metapress.com&lt;/a&gt;'
        },
        xAxis: {
            allowDecimals: false
        },
        yAxis: {
            title: {
                text: '核武库国家'
            },
            labels: {
                formatter: function () {
                    return this.value / 1000 + 'k';
                }
            }
        },
        tooltip: {
            pointFormat: '{series.name} 制造 &lt;b&gt;{point.y:,.0f}&lt;/b&gt;枚弹头'
        },
        plotOptions: {
            area: {
                pointStart: 1940,
                    marker: {
                    enabled: false,
                        symbol: 'circle',
                    radius: 2,
                        states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            }
        },
        series: [{
            name: '美国',
        data: [null, null, null, null, null, 6, 11, 32, 110, 235, 369, 640,
            1005, 1436, 2063, 3057, 4618, 6444, 9822, 15468, 20434, 24126,
            27387, 29459, 31056, 31982, 32040, 31233, 29224, 27342, 26662,
            26956, 27912, 28999, 28965, 27826, 25579, 25722, 24826, 24605,
            24304, 23464, 23708, 24099, 24357, 24237, 24401, 24344, 23586,
            22380, 21004, 17287, 14747, 13076, 12555, 12144, 11009, 10950,
            10871, 10824, 10577, 10527, 10475, 10421, 10358, 10295, 10104]
    }, {
            name:'苏联/俄罗斯',
            data: [null, null, null, null, null, null, null, null, null, null,
                5, 25, 50, 120, 150, 200, 426, 660, 869, 1060, 1605, 2471, 3322,
                4238, 5221, 6129, 7089, 8339, 9399, 10538, 11643, 13092, 14478,
                15915, 17385, 19055, 21205, 23044, 25393, 27935, 30062, 32049,
                33952, 35804, 37431, 39197, 45000, 43000, 41000, 39000, 37000,
                35000, 33000, 31000, 29000, 27000, 25000, 24000, 23000, 22000,
                21000, 20000, 19000, 18000, 18000, 17000, 16000]
        }]
    });
</script>
</body>
</html>
