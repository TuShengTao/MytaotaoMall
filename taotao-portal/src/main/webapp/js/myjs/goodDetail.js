//商品信息的展示 使用 ajax请求数据
var $descUrl="http://localhost:8082/item/desc/";
var $paramUrl= "http://localhost:8082/item/param/";
var $href= window.location.href;  //可以获取地址栏url
var $id=$href.substring($href.lastIndexOf('/') + 1);
$(document).ready(function(){
    $href="http://localhost:8082/item/"+$id;
    $.ajax({
		url:$href,
		type: "GET",                     //类型，POST或者GET
		dataType: "json",                //数据返回类型，可以是xml、json等
		success: function (item) {      //成功，回调函数
			console.log(item);
			var $title=item.title;//title 标题
			var $h1='<h1>'+$title+'</h1>';
			$('#name').append($h1);

			var $sellPoint=item.sellPoint;//卖点
			var $point='<strong >'+$sellPoint+'</strong>';
            $('#name').append($point);

			var $price=item.price/10; //价格
			var $Price='<strong class="p-price"  id="jd-price">￥'+$price+'<strong >';
			$('#goodPrice').append($Price);

			var $goodId=item.id;//商品id
			var $ID='<span >'+$goodId+'</span>';
           $('#goodId').append($ID);

			//添加商品详情图片
			for (var i=0;i<item.images.length;i++){
				var $src=item.images[i];
				var $img='<img src="'+$src+'" width="350" height="350" />';
				$('#showbox').append($img);
			}
			imgshow();//显示商品详情图片
		},
		error: function () {        //失败，回调函数
			alert("详情页展示失败！服务器错误！");
		}
    });
});
setTimeout(getDesc,1500);// 获取商品描述 延时1.5秒再加载商品描述

//目前CMS系统还未实现：添加商品的时候把规格参数也添加进去！
//在商品详情页点击 商品参数 时 去后台请求
function getParam() {
    $.get($descUrl+$id, function(data){
        //返回商品描述的html，直接显示到页面
        $("#item-desc").append(data);
    });
}
//获取商品描述
function getDesc() {
	alert("执行商品描述");
	alert($descUrl+$id);
    $.ajax({
        url:$descUrl+$id,
        type: "GET",                     //类型，POST或者GET
        dataType: "json",                //数据返回类型，可以是xml、json等
        success: function (data) {
        	alert("商品描述请求成功！");
        	console.log("输出返回的商品描述:");
        	console.log(data);
            $("#item-desc").append(data);
        },
        error: function () {        //失败，回调函数
            alert("商品描述失败！服务器错误！");
        }
    });
}

<!--商品详情 图片展示与放大效果-->
function imgshow(){
    var showproduct = {
        "boxid":"showbox",
        "sumid":"showsum",
        "boxw":350,//宽度,该版本中请把宽高填写成一样
        "boxh":350,//高度,该版本中请把宽高填写成一样
        "sumw":60,//列表每个宽度,该版本中请把宽高填写成一样
        "sumh":60,//列表每个高度,该版本中请把宽高填写成一样
        "sumi":7,//列表间隔
        "sums":5,//列表显示个数
        "sumsel":"sel",
        "sumborder":1,//列表边框，没有边框填写0，边框在css中修改
        "lastid":"showlast",
        "nextid":"shownext"
    };//参数定义
    $.ljsGlasses.pcGlasses(showproduct);//方法调用，务必在加载完后执行
}
//图片放大效果
jQuery.ljsGlasses = {
    pcGlasses:function(_obj){
		var _box = $("#"+_obj.boxid);
		var _sum = $("#"+_obj.sumid);
		var _last,_next;
		var _imgarr = _box.find("img");
		var _length = _imgarr.length;
		var _index = 0;
		var _arr = new Array();
		_sum.append("<p style='position:absolute;left:0;top:0;'></p>");
		var _sumbox = _sum.find("p");
		
		for(var i=0;i<_length;i++){
			_arr[i] = new Array();
			_arr[i][0] = _imgarr.eq(i).attr("src");
			_arr[i][1] = _imgarr.eq(i).attr("width");
			_arr[i][2] = _imgarr.eq(i).attr("height");
			var _scale = _arr[i][1]/_arr[i][2];
			if(_scale == 1){
				_arr[i][3] = _obj.boxw;//width
				_arr[i][4] = _obj.boxh;//height
				_arr[i][5] = 0;//top
				_arr[i][6] = 0;//left
				_arr[i][7] = _obj.boxw/2;
				_arr[i][8] = _obj.boxw*2;//width
				_arr[i][9] = _obj.boxh*2;//height
				_sumbox.append("<span><img src='"+_imgarr.eq(i).attr("src")+"' width='"+_obj.sumw+"' height='"+_obj.sumh+"' /></span>");
				}
			if(_scale > 1){
				_arr[i][3] = _obj.boxw;//width
				_arr[i][4] = _obj.boxw/_scale;
				_arr[i][5] = (_obj.boxh-_arr[i][4])/2;
				_arr[i][6] = 0;//left
				_arr[i][7] = _arr[i][4]/2;
				_arr[i][8] = _obj.boxh*2*_scale;//width
				_arr[i][9] = _obj.boxh*2;//height
				var _place = _obj.sumh - (_obj.sumw/_scale);
				_place = _place/2;
				_sumbox.append("<span><img src='"+_imgarr.eq(i).attr("src")+"' width='"+_obj.sumw+"' style='top:"+_place+"px;' /></span>");
				}
			if(_scale < 1){
				_arr[i][3] = _obj.boxh*_scale;//width
				_arr[i][4] = _obj.boxh;//height
				_arr[i][5] = 0;//top
				_arr[i][6] = (_obj.boxw-_arr[i][3])/2;
				_arr[i][7] = _arr[i][3]/2;
				_arr[i][8] = _obj.boxw*2;//width
				_arr[i][9] = _obj.boxw*2/_scale;
				var _place = _obj.sumw - (_obj.sumh*_scale);
				_place = _place/2;
				_sumbox.append("<span><img src='"+_imgarr.eq(i).attr("src")+"' height='"+_obj.sumh+"' style='left:"+_place+"px;' /></span>");
				}
		}
		_imgarr.remove();
		
		_sum.append("<div style='clear:both;width:100%;'></div>");
		var _sumarr = _sum.find("span");
		var _sumimg = _sum.find("img");
		_sumarr.eq(_index).addClass(_obj.sumsel);
		var _border = _obj.sumborder*2 + _obj.sumh;
		var _sumwidth = (_border+_obj.sumi)*_obj.sums;
		var _sumboxwidth = (_border+_obj.sumi)*_length;
		_sum.css({
			"overflow":"hidden",
			"height":_border+"px",
			"width":_sumwidth+"px",
			"position":"relative"
			});
		_sumbox.css({
			"width":_sumboxwidth+"px"
			});
		_sumarr.css({
			"float":"left",
			"margin-left":_obj.sumi+"px",
			"width":_obj.sumw+"px",
			"height":_obj.sumh+"px",
			"overflow":"hidden",
			"position":"relative"
			});
		_sumimg.css({
			"max-width":"100%",
			"max-height":"100%",
			"position":"relative"
			});
		
		_box.append("<div style='position:relative;'><b style='display:block;'><img style='display:block;' src='' /></b><span style='position:absolute;left:0;top:0;display:none;z-index:5;'></span></div><p style='position:absolute;overflow:hidden;top:0;display:none;'><img style='max-width:none;max-height:none;position:relative;left:0;top:0;' src='' /></p>");
		var _glass = _box.find("span");
		var _boximg = _box.find("b img");
		var _imgout = _box.find("div");
		var _showbox = _box.find("p");
		var _showimg = _box.find("p img");

		_box.css({
			"width":_obj.boxw+"px",
			"height":_obj.boxh+"px",
			"position":"relative"
			});
		var _showboxleft = _obj.boxw + 10;
		_showbox.css({
			"width":_obj.boxw+"px",
			"height":_obj.boxh+"px",
			"left":_showboxleft+"px"
			});
		
		var imgPlaces = function(){
			_showimg.attr("src",_arr[_index][0]);
			_boximg.attr("src",_arr[_index][0]);
			_boximg.css({
			    "width":_arr[_index][3]+"px",
			    "height":_arr[_index][4]+"px"
			});
			_imgout.css({
				"width":_arr[_index][3]+"px",
			    "height":_arr[_index][4]+"px",
			    "top":_arr[_index][5]+"px",
			    "left":_arr[_index][6]+"px",
			    "position":"relative"
			});
			_glass.css({
			    "width":_arr[_index][7]+"px",
			    "height":_arr[_index][7]+"px"
			});
			_showimg.css({
				"width":_arr[_index][8]+"px",
			    "height":_arr[_index][9]+"px"
			});
			
		};
		imgPlaces();
		
		_imgout.mousemove(function(e){
			var _gl_w = _glass.width()/2;
			var _maxX = _imgout.width() - _gl_w;
			var _maxY = _imgout.height() - _gl_w;
			var _moveX = 0,_moveY = 0;
			var _nowX = e.pageX - _imgout.offset().left;
		    var _nowY = e.pageY - _imgout.offset().top;
			var _moveX = _nowX-_gl_w,_moveY = _nowY-_gl_w;
			
			if(_nowX <= _gl_w){ _moveX = 0; }
			if(_nowX >= _maxX){ _moveX = _maxX-_gl_w; }
			if(_nowY <= _gl_w){ _moveY = 0;}
			if(_nowY >= _maxY){ _moveY = _maxY-_gl_w;}
			_glass.css({"left":_moveX+"px","top":_moveY+"px"});

			var _imgX = -_moveX*_showbox.width()/_glass.width();
			var _imgY = -_moveY*_showbox.width()/_glass.width();
			_showimg.css({"left":_imgX+"px","top":_imgY+"px"});
	
		});//mouse END
		
		_imgout.mouseenter(function(){
			_glass.css("display","block");
			_showbox.css("display","block");
			});
		_imgout.mouseleave(function(){
			_glass.css("display","none");
			_showbox.css("display","none");
			});
		
		//列表部分
		var _nextbtn = $("#"+_obj.nextid);
		var _lastbtn = $("#"+_obj.lastid);
		var _moveindex = 0;//索引移动
		
		var _sumListMove = function(){
			var _leftmove = -_moveindex*(_border+_obj.sumi);
			if(_sumbox.is(":animated")){_sumbox.stop(true,true);}
			_sumbox.animate({left:_leftmove+"px"},300);
			_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
			imgPlaces();
		};//fun END
		
		if(_length <= _obj.sums){
			var _place = (_obj.sums-_length)*_border/2;
			_sumbox.css("left",_place+"px");
			_nextbtn.click(function(){
				_index++;
				if(_index >= _length){ _index=_length-1;}
				_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
			    imgPlaces();
			});
			_lastbtn.click(function(){
				_index--;
				if(_index <= 0){ _index=0;}
				_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
			    imgPlaces();
			});
		}else{
			var _maxNum = _length-_obj.sums;
			_nextbtn.click(function(){
			   _moveindex++;
			   if(_moveindex >= _maxNum){ _moveindex=_maxNum; }
			   if(_index <= _moveindex){ _index=_moveindex;}
			   _sumListMove();
		    });
			_lastbtn.click(function(){
				_moveindex--;
				if(_moveindex <= 0){ _moveindex=0;}
				if(_index >= _moveindex+_obj.sums){ _index=_moveindex+_obj.sums-1;}
				_sumListMove();
			});
		}//if END

		_sumarr.hover(function(){
			_index = $(this).index();
			_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
			imgPlaces();
		});
	
  }//pcGlasses END
}//ljsGlasses END




