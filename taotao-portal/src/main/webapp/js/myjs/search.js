var GlobalCurPage=1;
//解决页面刷新bug 比如：已经在第五页 然后刷新网页 会释放全局变量 会回到第一页的bug
var nowPage=window.localStorage.getItem("nowPage");
if ( nowPage!=null && nowPage!=GlobalCurPage){
    GlobalCurPage=window.localStorage.getItem("nowPage");
}

//从首页跳转成功到search.html 页面加载完成就执行ajax请求到后端 接收返回的数据
$(document).ready(function () {
    var searchKey=window.localStorage.getItem("searchKey");
    if (searchKey==""){
        alert("请输入搜索的关键字！");
    } else {
        GoSearch(GlobalCurPage,searchKey);//执行 Ajax 请求
    }
});
//封装的ajax函数
function GoSearch(curPage,searchKey){
    window.localStorage.setItem("nowPage",curPage);
        $.ajax({
            url: "http://localhost:8082/searchByAjax.html",
            data: {
                "queryString":searchKey,
                "curPage":curPage
            },
            type: "POST",                     //类型，POST或者GET
            dataType: "json",                //数据返回类型，可以是xml、json等
            success: function (result) {      //成功，回调函数
                //每次搜索请求成功之后 searchKey,curPage 要被浏览器记住 以便于 用户发起下一页请求或上一页请求
                alert("搜索成功！当前的页数是第"+curPage+"页");
                var itemList=result.itemList;
                console.log(result.query);
                console.log(result.page);
                GlobalCurPage=parseInt(result.page);//设置 更新全局的变量 表示当前页数
                console.log(result.pages);
                console.log(result.totalPages);
                TotalPages=result.totalPages;//设置全局变量 总的页数
                console.log(itemList);
                console.log(itemList.length);
                console.log(itemList[0].sell_point);
                console.log(itemList[0].image);//在前端处理图片字符串即可 ，以 ，分割 生成图片url数组 2019/11/26 目前没采取 后面再重构
                console.log(itemList[0].images[0]);
                console.log(itemList[0].images[1]);
                console.log(itemList[0].id);
                //循环 生成 每个商品展示
                //首先 循环 生成 li
                for (var i = 0; i <itemList.length; i++) {
                    var $itemImage=itemList[i].images[0];
                    var $itemImage1=$itemImage.substring(1);
                    var $itemId=itemList[i].id;
                    var $itemIdArr=$itemId.split("\"");
                    var $itemTitle=itemList[i].title;
                    var $itemPrice=itemList[i].price/100;
                    var $liId='itemList'+i;
                    var $liId2='#'+$liId;
                    var $li=' <li class="item-book" bookid="11078102" id='+$liId+'></li>';
                    var $p_img=' <div class="p-img"> <a   target="_blank" href="/superMarket/item/'+$itemIdArr[1]+'.html"> <img width="160" height="160"  src='+$itemImage1+'></a> </div>';
                    var $p_name=' <div class="p-name"> <a target="_blank" href="/superMarket/item/'+$itemIdArr[1]+'.html">'+$itemTitle+'</a> </div>';
                    var $p_price='<div class="p-price"> <i>限时优惠价：</i> <strong>￥'+$itemPrice+'</strong> </div>';
                    var $p_service='<div class="service">惠淘淘 发货</div>';
                    var $p_extra=' <div class="extra"> <span class="star"><span class="star-white"><span class="star-yellow h5">&nbsp;</span></span></span> </div>';
                    $('#searchItemList').append($li);
                    $($liId2).append($p_img);
                    $($liId2).append($p_name);
                    $($liId2).append($p_price);
                    $($liId2).append($p_service);
                    $($liId2).append($p_extra);
                }
            },
            error: function () {        //失败，回调函数
                alert("搜索失败！服务器错误！");
            }
        });
};

// search.html 的前一页
function PreviousPage() {
    var searchKey=window.localStorage.getItem("searchKey");
    if (GlobalCurPage>1){
        $('#searchItemList').remove();
        var $searcIitemList= '<ul class="list-h clearfix" tpl="2" id="searchItemList"></ul>';
        $('#plist').append($searcIitemList);
        GlobalCurPage=GlobalCurPage-1;
        GoSearch(GlobalCurPage,searchKey);
    }else {
        alert("当前已经是第一页");
    }
};
// search.html 的下一页
function NextPage() {
    var searchKey=window.localStorage.getItem("searchKey");
    if (GlobalCurPage<TotalPages) {
        $('#searchItemList').remove();
        var $searcIitemList= '<ul class="list-h clearfix" tpl="2" id="searchItemList"></ul>';
        $('#plist').append($searcIitemList);
        GlobalCurPage=GlobalCurPage+1;
        GoSearch(GlobalCurPage,searchKey);
    }else {
        alert("当前已经是最后一页！")
    }
};
    //点击数字直接搜索 如 第5页 第6页
    function NubPage(curPage) {
        GlobalCurPage=curPage;//更新全局变量 当前页面数
        var searchKey=window.localStorage.getItem("searchKey");
        $('#searchItemList').remove();
        var $searcIitemList= '<ul class="list-h clearfix" tpl="2" id="searchItemList"></ul>';
        $('#plist').append($searcIitemList);
        GoSearch(curPage,searchKey);
    };
    $("#pagePage1").click(function(){
        if (GlobalCurPage==1){
            alert("当前已经是第一页！");
        }
        else {
            NubPage(1);
        }
    });
    $("#pagePage2").click(function(){
        var $curPage=parseInt($("#pagePage2").children().val());
        NubPage($curPage);
    });

    $("#pagePage3").click(function(){
        var $curPage=parseInt($("#pagePage3").children().val());
        NubPage($curPage);
    });

    $("#pagePage4").click(function(){
        var $curPage=parseInt($("#pagePage4").children().val());
        NubPage($curPage);
    });

    $("#pagePage5").click(function(){
        var $curPage=parseInt($("#pagePage5").children().val());
        NubPage($curPage);
    });

    $("#pagePage6").click(function(){
        var $curPage=parseInt($("#pagePage6").children().val());
        NubPage($curPage);
    });







