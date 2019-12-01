Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};


var TT = TAOTAO = {
    // 配置富文本编辑器参数
    //文件上传
    kingEditorParams: {
        //指定上传文件参数名称
        filePostName: "uploadFile",
        //指定上传文件请求的url
        uploadJson: '/pic/upload',
        //上传类型，分别为image、flash、media、file
        dir: "image"
    },
    // 格式化时间
    formatDateTime: function (val, row) {
        var now = new Date(val);
        return now.format("yyyy-MM-dd hh:mm:ss");
    },
    // 格式化连接
    formatUrl: function (val, row) {
        if (val) {
            return "<a href='" + val + "' target='_blank'>查看</a>";
        }
        return "";
    },
    // 格式化价格
    formatPrice: function (val, row) {
        return (val / 1000).toFixed(2);
    },
    // 格式化商品的状态
    formatItemStatus: function formatStatus(val, row) {
        if (val == 1) {
            return '正常';
        } else if (val == 2) {
            return '<span style="color:red;">下架</span>';
        } else {
            return '未知';
        }
    },

    init: function (data) {
        // 初始化图片上传组件
        console.log("初始化图片上传组件");
        this.initPicUpload(data);
        // 初始化选择类目组件
        console.log("初始化选择类目组件");
        console.log("输出data："+JSON.stringify(data));
        console.log(data);
        this.initItemCat(data);
    },
    // 在item-add.jsp 页面加载完 开始 初始化图片上传组件
    initPicUpload: function (data) {
        $(".picFileUpload").each(function (i, e) {
            var _ele = $(e);
            _ele.siblings("div.pics").remove();
            _ele.after('\
    			<div class="pics">\
        			<ul></ul>\
        		</div>');
            // 回显图片
            if (data && data.pics) {
                var imgs = data.pics.split(",");
                for (var i in imgs) {
                    if ($.trim(imgs[i]).length > 0) {
                        _ele.siblings(".pics").find("ul").append("<li><a href='" + imgs[i] + "' target='_blank'><img src='" + imgs[i] + "' width='80' height='50' /></a></li>");
                    }
                }
            }
            //给“上传图片按钮”绑定click事件
            $(e).click(function () {
                var form = $(this).parentsUntil("form").parent("form");
                //打开图片上传窗口
                KindEditor.editor(TT.kingEditorParams).loadPlugin('multiimage', function () {
                    var editor = this;
                    editor.plugin.multiImageDialog({
                        clickFn: function (urlList) {
                            var imgArray = [];
                            KindEditor.each(urlList, function (i, data) {
                                imgArray.push(data.url);
                                form.find(".pics ul").append("<li><a href='" + data.url + "' target='_blank'><img src='" + data.url + "' width='80' height='50' /></a></li>");
                            });
                            form.find("[name=image]").val(imgArray.join(","));
                            editor.hideDialog();
                        }
                    });
                });
            });
        });
    },

    // 初始化选择类目组件
    initItemCat: function (data) {
        //slectItemCat 是类选择器 因为 类选择器不只一个 所以 用each函数遍历所有的
        $(".selectItemCat").each(function (i, e) {
            var _ele = $(e);
            if (data && data.cid) {
                _ele.after("<span style='margin-left:10px;'>" + data.cid + "</span>");
            } else {
                _ele.after("<span style='margin-left:10px;'></span>");
            }
            _ele.unbind('click').click(function () {
                $("<div>").css({padding: "5px"}).html("<ul>")
                    .window({
                        width: '500',
                        height: "450",
                        modal: true,
                        closed: true,
                        iconCls: 'icon-save',
                        title: '选择类目',
                        onOpen: function (){
                            var _win = this;
                            $("ul", _win).tree({
                                url: '/item/cat/list',
                                animate: true,
                                onClick: function (node) {//点击某个 分类的事件
                                    if ($(this).tree("isLeaf", node.target)) {	//判断是否为叶子节点
                                        // 填写到cid中 cid是一个隐藏的输入域 这一表单就可以提交了
                                        _ele.parent().find("[name=cid]").val(node.id);
                                        _ele.next().text(node.text).attr("cid", node.id);
                                        console.log("选择类目组件内部的node.text"+node.text);
                                        $(_win).window('close');
                                        if (data && data.fun) {
                                            //判断data 是否是回调方法 是就执行回调方法 传入node参数 传入 item-param-add.jsp的fun函数对象
                                            //之后fun判断该分类商品是否已经添加规格参数
                                            console.log("初始化选择类目组件内部");
                                            console.log(data);
                                            console.log("选择类目内部输出node"+JSON.stringify(node));
                                            data.fun.call(this, node);
                                        }
                                    }
                                }
                            });
                        },
                        onClose: function () {
                            $(this).window("destroy");
                        }
                    }).window('open');
            });
        });
    },
    /**
     *
     * 创建一个窗口，关闭窗口后销毁该窗口对象。<br/>
     *
     * 默认：<br/>
     * width : 80% <br/>
     * height : 80% <br/>
     * title : (空字符串) <br/>
     *
     * 参数：<br/>
     * width : <br/>
     * height : <br/>
     * title : <br/>
     * url : 必填参数 <br/>
     * onLoad : function 加载完窗口内容后执行<br/>
     */
    createWindow: function (params) {
        $("<div>").css({padding: "5px"}).window({
            width: params.width ? params.width : "80%",
            height: params.height ? params.height : "80%",
            modal: true,
            title: params.title ? params.title : " ",
            href: params.url,
            onClose: function () {
                $(this).window("destroy");
            },
            onLoad: function () {
                if (params.onLoad) {
                    params.onLoad.call(this);
                }
            }
        }).window("open");
    },

    closeCurrentWindow: function () {
        $(".panel-tool-close").click();
    },

    changeItemParam: function (node, formId) {
        $.getJSON("/item/param/query/itemcatid/" + node.id, function (data) {
            if (data.status == 200 && data.data) {
                $("#" + formId + " .params").show();
                var paramData = JSON.parse(data.data.paramData);//JSON.parse字符串转json
                //把商品规格模板由json转为表单
                var html = "<ul>";
                for (var i in paramData) {
                    var pd = paramData[i];
                    html += "<li><table>";
                    html += "<tr><td colspan=\"2\" class=\"group\">" + pd.group + "</td></tr>";

                    for (var j in pd.params) {
                        var ps = pd.params[j];
                        html += "<tr><td class=\"param\"><span>" + ps + "</span>: </td><td><input autocomplete=\"off\" type=\"text\"/></td></tr>";
                    }
                    html += "</li></table>";
                }
                html += "</ul>";
                $("#" + formId + " .params td").eq(1).html(html);
            } else {
                $("#" + formId + " .params").hide();
                $("#" + formId + " .params td").eq(1).empty();
            }
        });
    },
    //获取选中的id
    getSelectionsIds: function (select) {
        var list = $(select);
        var sels = list.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    },

    /**
     * 初始化单图片上传组件 <br/>
     * 选择器为：.onePicUpload <br/>
     * 上传完成后会设置input内容以及在input后面追加<img>
     */
    initOnePicUpload: function () {
        $(".onePicUpload").click(function () {
            var _self = $(this);
            KindEditor.editor(TT.kingEditorParams).loadPlugin('image', function () {
                this.plugin.imageDialog({
                    showRemote: false,
                    clickFn: function (url, title, width, height, border, align) {
                        var input = _self.siblings("input");
                        input.parent().find("img").remove();
                        input.val(url);
                        input.after("<a href='" + url + "' target='_blank'><img src='" + url + "' width='80' height='50'/></a>");
                        this.hideDialog();
                    }
                });
            });
        });
    }
};