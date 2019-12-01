$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8082/indexAd.html",
        type: "GET",                     //类型，POST或者GET
        dataType: "json",                //数据返回类型，可以是xml、json等
        success: function (result) {      //成功，回调函数
            console.log(result);
            lunboad(result);
        },
        error: function () {        //失败，回调函数
            alert("广告展示失败！服务器错误！");
        }
    });
});


//首页轮播图广告展示
function lunboad(adData) {
    var $adbignav=document.getElementById("adbignav");
    var $adbiglunbo=document.getElementById("adbiglunbo");
    for (var i = 0; i<adData.length; i++) {
        var $src=adData[i].src;
        var $adbiglunboli1='<li style="display:block"><img src='+$src+' alt="" /></li>';
        var $adbiglunboli2='<li><img src='+$src+' alt="" /></li>';
        if (i==0){
            $($adbiglunbo).append($adbiglunboli1);
        }else {
            $($adbiglunbo).append($adbiglunboli2);
        }
    }
    for (var j = 0; j<adData.length; j++) {
        var $index=j+1;
        var $adbignavli1='<li class="on">'+$index+'</li>';
        var $adbignavli2='<li>'+$index+'</li>';
        if (j==0){
            $($adbignav).append($adbignavli1);
        }else {
            $($adbignav).append($adbignavli2);
        }
    }
}