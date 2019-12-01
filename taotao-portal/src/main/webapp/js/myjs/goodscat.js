
 //jsonp 跨域访问
 $(document).ready(function () {
    $.ajax({
        url: "http://localhost:8081/rest/itemcat/list",
        type: "GET",
        dataType: "jsonp", //指定服务器返回的数据类型
        success: function (data) {
            console.log(data);
            //json数据  jsondata
            //展示商品分类 需要三层循环
            //步骤1 一级分类添加 第一层循环
            // ul  catul 下面需要添加14个li；每个li  <li class="li1"> 下面 需要添加2个盒子
            //1 div class="mulu" mulu要添加三个 <a>:一级分类 、<div class="jian"></div>、<div class="bai"></div>
            var $mulu2div = '<div class="jian"></div><div class="bai"></div>';
            //2 div class="hidemenu"(需要判断第三层的个数超过7个就 动态修改 class="hidemenu1")
            // div class="hidemenu" 下面需要添加2个盒子 <div class="hideleft">、<div class="hideright">
            //盒子 <div class="hideleft"> 下面 需要添加
            // <div class="hideleft"> 下面 添加 <dl class="dl1">, 三级分类为两行时 二级分类一行展示7个
            // <dl class="dl1">  class需要动态修改为dl2 ,
            // <dl>下面添加 <dt><a href="">电子书</a></dt> :二级分类
            // <dl> 下面添加 <dd><a href="">小说</a></dd> 三级分类
            //<div class="hideright"> 暂时不做 就是商品分类弹出框 右侧的部分

            for (var i = 0; i < data.data.length; i++) {
                var $a = data.data[i].n;//完整的一级商品分类a标签
                var $liname = 'li';
                var count = i + 1;//生成li 的class序号 如 class=li1 、li2、li3...li14
                var $li = $('<li>').addClass($liname + count).appendTo('#catul');// li 添加完成
                var $muluId = 'muluId' + count;
                var $hidemenuId = 'hidemenuId' + count;
                //var $hidemenuclass='hidemenu'+count;
                var $hidemenuclass = 'hidemenu';
                var $lidiv = '<div class="mulu" id=' + $muluId + '></div><div class=' + $hidemenuclass + ' id=' + $hidemenuId + '></div>';
                var $muluId2 = '#' + $muluId;

                $li.append($lidiv);
                $($muluId2).append($a);//一级分类添加完成
                $($muluId2).append($mulu2div);//hidemenue 区域添加完成 在hidemenue 里添加二级分类

                var $hideleftId = $hidemenuId + 'left';//<div class="hideleft" >的id
                var $hidemenuleft = '<div class="hideleft" id=' + $hideleftId + '> </div>';
                var $hidemenuId2 = '#' + $hidemenuId;
                $($hidemenuId2).append($hidemenuleft);//给hidemenu 添加左侧盒子

                for (var j = 0; j < data.data[i].i.length; j++) {
                    var count2 = j + 1;
                    var $dlId = $hideleftId + 'dl' + count2;//dl的id 每一个二级分类  dl包含一个<dt> 若干个<dd>, <dt>和<dd>分别有一个<a>

                    //动态修改 dl的class  jsontest.data[i].i[j].i.length三级分类个数是否大于7 大于7就class=dl2 大于14就class=dl3
                    var $dlclass = 'dl1';//默认为1
                    if (data.data[i].i[j].i.length >= 12 && data.data[i].i[j].i.length <= 14) {
                        $dlclass = 'dl2';
                    } else if (data.data[i].i[j].i.length > 14 && data.data[i].i[j].i.length <= 24) {
                        $dlclass = 'dl3';
                    } else if (data.data[i].i[j].i.length > 24) {
                        $dlclass = 'dl4';
                    } else {
                        $dlclass = 'dl1';
                    }
                    var $dl = '<dl class=' + $dlclass + ' id=' + $dlId + '></dl>';
                    var $dtId = $dlId + 'dtId'
                    var $dt = "<dt id=" + $dtId + "> </dt>";
                    var $hideleftId2 = '#' + $hideleftId;
                    $($hideleftId2).append($dl);//hideleft添加 dl 此处dl 要根据三级分类个数 动态修改 dl的class=dl1、dl2、dl3.....

                    var $dlId2 = '#' + $dlId;
                    $($dlId2).append($dt);//dl添加dt
                    var $dtId2 = '#' + $dtId;
                    $('<a>').attr({
                        href: data.data[i].i[j].u //设置二级分类的链接
                    }).text(data.data[i].i[j].n).appendTo($dtId2);//添加二级分类

                    //添加三级分类  在dl下面 循环添加<dd> 并且要根据返回的json数据 对三级分类文本进行分割 ‘/products/3.html|电子书’
                    for (var k = 0; k < data.data[i].i[j].i.length; k++) {
                        var count3 = k + 1;
                        var sanjiJson = data.data[i].i[j].i[k];//json数据 例子：‘/products/3.html|电子书’
                        //字符串分割
                        var arr = new Array();
                        arr = sanjiJson.split('|');//arr[0]是链接 arr[1]是三级分类名称
                        var $ddId = $dlId + 'ddId' + count3;
                        var $dd = '<dd id=' + $ddId + '></dd>';
                        $($dlId2).append($dd);//dl循环添加dd
                        var $ddId2 = '#' + $ddId;
                        $('<a>').attr({
                            href: arr[0] //设置三级分类的链接
                        }).text(arr[1]).appendTo($ddId2);//添加三级分类
                    }

                }
            }
        }
    });
});


