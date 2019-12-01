<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
    <form id="itemAddForm" class="itemForm" method="post">
        <table cellpadding="5">
            <tr>
                <td>商品类目:</td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
                    <input type="hidden" name="cid" style="width: 280px;"></input>
                </td>
            </tr>
            <tr>
                <td>商品标题:</td>
                <td><input class="easyui-textbox" type="text" name="title" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>商品卖点:</td>
                <td><input class="easyui-textbox" name="sellPoint"
                           data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 280px;"></input></td>
            </tr>
            <tr>
                <td>商品价格:</td>
                <td><input class="easyui-numberbox" type="text" name="priceView"
                           data-options="min:1,max:99999999,precision:2,required:true"/>
                    <input type="hidden" name="price"/>
                </td>
            </tr>
            <tr>
                <td>库存数量:</td>
                <td><input class="easyui-numberbox" type="text" name="num"
                           data-options="min:1,max:99999999,precision:0,required:true"/></td>
            </tr>
            <tr>
                <td>条形码:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="barcode" data-options="validType:'length[1,30]'"/>
                </td>
            </tr>
            <tr>
                <td>商品图片:</td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
                    <input type="hidden" name="image"/>
                </td>
            </tr>
            <tr>
                <td>商品描述:</td>
                <td>
                    <textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>
                </td>
            </tr>
            <tr class="params hide">
                <td>商品规格:</td>
                <td>

                </td>
            </tr>
        </table>
        <input type="hidden" name="itemParams"/>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
    </div>
</div>
<script type="text/javascript">
    var itemAddEditor;
    //页面初始化完毕后执行此方法 jQuery入口函数
    $(function () {
        //创建富文本编辑器 html需要 textarea 标签
        //itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
        //绑定到textarea标签 传入 url （TT.kingEditorParams）等参数
        itemAddEditor = KindEditor.create("#itemAddForm [name=desc]", TT.kingEditorParams);
        //初始化类目选择和图片上传器
        TAOTAO.init({
            //fun 函数作为一个参数传进去，然后先选中分类 如手机，然后获取到手机这个节点的node
            //之后 node值再传入 fun函数 再调用 TAOTAO.changeItemParam(node, "itemAddForm") 生成规格参数模板
            fun: function (node) {
                console.log("输出item-add.jsp 的node： "+node);
                //根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
                TAOTAO.changeItemParam(node, "itemAddForm");
                node=JSON.stringify(node);
                console.log("输出node之后 "+node);
            }
        });
    });

    //提交表单
    function submitForm() {
        // EasyUI的Form表单通过data-options属性的required子属性设置是否必填，
        // validType子属性来设置内容验证规则
        if (!$('#itemAddForm').form('validate')) {
            $.messager.alert('提示', '表单还未填写完成!');
            return;
        }
        //取商品价格，单位为“分”
        $("#itemAddForm [name=price]").val(eval($("#itemAddForm [name=priceView]").val()) * 100);
        //同步文本域中的商品描述
        itemAddEditor.sync();
        //取商品的规格
        var paramJson = [];
        $("#itemAddForm .params li").each(function (i, e) {
            var trs = $(e).find("tr");
            var group = trs.eq(0).text();
            var ps = [];
            for (var i = 1; i < trs.length; i++) {
                var tr = trs.eq(i);
                ps.push({
                    "k": $.trim(tr.find("td").eq(0).find("span").text()),
                    "v": $.trim(tr.find("input").val())
                });
            }
            paramJson.push({
                "group": group,
                "params": ps
            });
        });
        //把json对象转换成字符串
        paramJson = JSON.stringify(paramJson);
        $("#itemAddForm [name=itemParams]").val(paramJson);

        //ajax的post方式提交表单 单表操作 可以使用mybatis逆向生成的代码
        //$("#itemAddForm").serialize()将表单序列化为key-value形式的字符串
        $.post("/item/save", $("#itemAddForm").serialize(), function (data) {
            if (data.status == 200) {
                $.messager.alert('提示', '新增商品成功!');
            }
        });
    }
    // 重置/清除表单
    function clearForm() {
        $('#itemAddForm').form('reset');
        itemAddEditor.html('');
    }
</script>
