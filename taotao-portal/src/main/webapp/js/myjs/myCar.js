// 我的购物车页面 js代码 myCar.html
$(document).ready(function(){
    console.log("此页面加载完，输出购物车cookie  "+$.cookie("TT_CART"));
});
// 点击 我的购物车 触发
function goToMyCar() {
    window.location.href="http://localhost:8082/cart/myCart.html";// 跳转到我的购物车页面
}

// myCar.html加载完成 执行
$(document).ready(function () {
    var $goodsList=$.cookie("TT_CART");

});
//
// function addToCar($carUrl) {
//     // $select 是 添加购物车按钮的 id
//     var $href=$($carUrl).val();
//     var $num=parseInt($('#buy-num').val());
//     if ($href==null){
//         alert("carUrl为空")
//     }
//     else {
//         $.ajax({
//             url:$href,
//             type: "GET", //类型，POST或者GET
//             data:{
//                 num:$num
//             },
//             xhrFields: {    //设置 带上cookie
//                 withCredentials: true
//             },
//             dataType: "json",                //数据返回类型，可以是xml、json等
//             success: function (result) {      //成功，回调函数
//                 //成功以后 进行跳转 到成功页面
//                 alert("加入购物车成功！");
//                 console.log("输出购物车cookie"+$.cookie("TT_CART"));
//                 window.location.href="http://localhost:8082/cart/success.html";//跳转到成功页面
//             },
//             error: function () {        //失败，回调函数
//                 alert("加入购物车失败！服务器错误！");
//             }
//         });
//     }
//
// };
// // 修改cookie 里的商品数量 传入一个数量
// function modifyNum($goodsNum,$goodsId){
//     var $goodsInfo=$.cookie('TT_CART');// 从cookie
//     if ($goodsId!=null) {
//         var goodsJson = $.parseJSON($goodsInfo);
//         for (var i = 0; i < goodsJson.length; i++) {
//             if ($goodsId == goodsJson[i].id) {
//                 //修改商品数量
//                 goodsJson[i].num = $goodsNum;
//                 $.cookie('TT_CART', JSON.stringify(goodsJson));// 再写入cookie
//                 var $goodsAfter = $.cookie('TT_CART');
//                 var goodsAfterJson = $.parseJSON($goodsAfter);
//                 console.log("修改后的商品数量：  " + goodsAfterJson[i].num + "商品id是  " + goodsAfterJson[i].id);
//                 break; //修改完 退出循环
//             }
//         }
//     } else {
//         // 说明
//         //获取点击事件的元素 id
//         /*
//         html
//         <button id = "main" onclick="test(this.id)"></button>
//         js
//         function(id){
//             alert($(this).attr('id'))
//         }
//         */
//         alert("商品id为空！ ");
//     }
// };
// function getGoodsId($flag) {
//     // $flag 用来区分 是否在 item.html页面
//
//     // 在 item.html 页面 获取
//     // var $href= window.location.href;  //可以获取地址栏url
//     // var $id=$href.substring($href.lastIndexOf('/') + 1);
//     if ('item'==$flag){
//         $idNum=$id.substring(0,$id.indexOf('.'));
//         return $idNum;// 返回商品id
//     }
//     else {
//         alert("当前页面不在item.html,当前页面在购物车页面myCar.html，从隐藏表单域获取商品id");
//     }
//     // 在 myCart.jsp/html 中 在其购物车页面展示商品 是从cookie中取出来的 jquery动态生成dom 然后弄个弄隐藏域实现
//     // 在myCart 页面对某一个商品 进行数量的修改 隐藏域里面 要设置商品id
// }
// //  判断商品是否存在于购物车 和 购物车是否存在 如果任意一个都不存在 把商品数量存在隐藏域 不加入cookie
// function goodsIfExit() {
//     var $goodsInfo=$.cookie('TT_CART');// 从cookie
//     var $goodId=getGoodsId('item');
//     var $good_exit=0;// 0表示不存在于购物车
//     if ($goodsInfo !=null){
//         var goodsJson = $.parseJSON($goodsInfo);
//         for (var i = 0; i < goodsJson.length; i++) {
//             if ($goodId == goodsJson[i].id) {
//                 $good_exit=1;
//                 break;
//             }
//         }
//         if ($good_exit==1){
//             return true;// 存在就 可以不必向服务器再次请求 返回 true
//         } else {
//             return false;
//         }
//     }else {
//         return false;
//     }
// };
//
// function reduceNum($InputId) {
//     var $nowNum=$($InputId).val();
//     if ($nowNum ==1){
//         var $GoodsNum=1;
//     }else {
//         var $GoodsNum=parseInt($($InputId).val())-1;
//     }
//     $($InputId).val($GoodsNum);
//     // var $flag='item';
//     // var $goodsId=getGoodsId($flag);
//     // if (goodsIfExit()){
//     //     modifyNum($GoodsNum,$goodsId);
//     // } else {
//     //     $($InputId).val($GoodsNum);// 把数量添加进隐藏域
//     // }
//
// };
function addNum($InputId) {
    var $GoodsNum=parseInt($($InputId).val())+1;
    $($InputId).val($GoodsNum);
    // var $flag='item';
    // var $goodsId=getGoodsId($flag);
    // if (goodsIfExit()){
    //
    //     modifyNum($GoodsNum,$goodsId);
    // } else {
    //     $($InputId).val($GoodsNum);// 把数量添加进隐藏域
    // }
};
// 在item.html手动输入修改数量 值改变时触发 目前不做 放弃 2019/12/5
// $('#buy-num').on('input', function () {
//     // 捕捉事件并响应
//     // ^[0-9]*[1-9][0-9]*$  正整数
//     var $pattern=/^[0-9]*[1-9][0-9]*$/;
//     var $goodsNum=$('#buy-num').val();
//     if ($pattern.test($goodsNum) && $goodsNum !=null && $goodsNum >0 ){
//         // 如果是数字 修改cookie 修改时 商品库存数量比较是一个问题 利用ajax 给商品弄一个状态 与用户加入数量
//         // 进行比较 然后修改状态 有货  或者库存不足  或者无货 （暂定）
//         alert("数量正确");
//         $('#buy-num').val($goodsNum);
//         var $GoodsNum=$('#buy-num').val();
//         $flag='item';
//         var $goodsId=getGoodsId($flag);
//         modifyNum($GoodsNum,$goodsId);
//
//     } else {
//        // 如果不是数字
//        alert("请输入整数数字！,不可为空！");
//         $('#buy-num').val(1);//设置为默认数量 1
//     }
// });

