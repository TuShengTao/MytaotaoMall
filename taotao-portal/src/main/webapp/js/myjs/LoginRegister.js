
    function  checkLogin (){
        //取 cookie 值
        var _ticket = $.cookie('TT_TOKEN');//$.cookie 使用此函数 需要引入 jquery.cookie.js
        console.log("输出登录成功后的cookie: "+_ticket);
        if(!_ticket){
            return ;
        }
        $.ajax({
            url : "http://localhost:8084/user/token/" + _ticket,
            dataType : "jsonp",
            type : "GET",
            success : function(data){
                if(data.status == 200){
                    var username = data.data.username;
                    localStorage.removeItem("TAOTAO_USERID");
                    // 存入 用户id
                    localStorage.setItem("TAOTAO_USERID",data.data.id);
                    console.log("检测是否登录后测试返回数据"+JSON.stringify(data));
                    $('#SpanUsername').html(username);
                   //退出功能 还未完成 2019/12/2
                    $("#LSUrl").html(' ');
                }
            }
        });
    };
//页面加载完成执行
$(document).ready(function () {
    checkLogin();
});
