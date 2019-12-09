//跳转到search.jsp然后 执行 searchGoods()
function turnToSearchJsp() {
    // if(window.localStorage){
    //     alert('This browser supports localStorage');
    // }else{
    //     alert('This browser does NOT support localStorage');
    // }
    //跳转
    //存数据
    // window.localStorage.setItem(“data”, “kevin”); 或者window.sessionStorage.setItem(“data”, “kevin”);
    //取数据 用getItem来取数据，参入要取数据的 名字就好
    // window.localStorage.getItem(“data”); 或者  window.sessionStorage.getItem(“data”);
    // 当然如果要存储的数据是一个对象的话，就需要将对象转换为字符串，在取数据的在将字符串转为对象就可以了
    // 对象转字符串 JSON.stringify( ” 对象 ” ) 字符串转对象 JSON.parse( ” 字符串 ” )
    var searchKey=document.getElementById("searchInput").value ;
    window.localStorage.setItem("searchKey",searchKey);
    window.localStorage.removeItem('nowPage');
    window.location.href="http://localhost:8082/superMarket/search.html";
}
function goToMyCar() {
    window.location.href="http://localhost:8082/superMarket/myCart.html";// 跳转到我的购物车页面
}