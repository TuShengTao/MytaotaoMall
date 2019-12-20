
function turnToSearchJsp() {
    var searchKey=document.getElementById("searchInput").value ;
    window.localStorage.setItem("searchKey",searchKey);
    window.localStorage.removeItem('nowPage');
    window.location.href="http://localhost:8082/superMarket/search.html";
}
function goToMyCar() {
    window.location.href="http://localhost:8082/superMarket/myCart.html";// 跳转到我的购物车页面
}
function goTomyOrderCenter() {
    window.location.href="http://localhost:8082/superMarket/myOrderCenter.html";// 跳转到我的购物车页面
}
function goTomyOrderComment() {
    window.location.href="http://localhost:8082/superMarket/myOrderComment.html";// 跳转到我的购物车页面
}