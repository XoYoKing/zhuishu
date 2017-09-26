<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <script type="text/javascript" src="lib/PIE_IE678.js"></script>
    <![endif]-->
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <!--[if IE 6]>
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>产品分类</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span class="c-gray en">&gt;</span> 产品分类 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<table class="table">
    <tr>
        <td width="200" class="va-t"><ul id="treeDemo" class="ztree"></ul></td>
        <td class="va-t"><IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=390px SRC="product-category-add"></IFRAME></td>
    </tr>
</table>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript">
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: false,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            beforeClick: function(treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("tree");
                if (treeNode.isParent) {
                    zTree.expandNode(treeNode);
                    return false;
                } else {
                    demoIframe.attr("src","product-category-add?minorCateName=" + treeNode.name);
                    return true;
                }
            }
        }
    };

    var zNodes = new Array();

    // 参数：prop = 属性，val = 值
    function createJson(str ,prop, val) {
        // 如果 val 被忽略
        if(typeof val === "undefined") {
            // 删除属性
            delete str[prop];
        }
        else {
            // 添加 或 修改
            str[prop] = val;
        }
    }


    var code;

    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>"+str+"</li>");
    }

    $(document).ready(function(){
        var rows;
        var cates = new Array();
        var str = {"id": "main", "pId": "root" , "name" : "分类" ,"open" : true};
        zNodes.push(str);
        cates.push("分类");
        $.ajaxSettings.async = false;
        $.getJSON('minorcate/list',function (data){
            rows = data.data ;
            var str1 = {"id": "rr", "pId": "11" , "name" : "分类"};

            for (var i = 0 ; i < rows.length ; i ++){
                for (var  j = 0 ; j <  cates.length ; j++){
                    var status = false;
                    if(rows[i].majorCateName == cates[j]){
                        status = true;
                        break;
                    }
                }
                if (!status){

                    var str2 = {"id": "rr", "pId": "11" , "name" : "分类"};
                    createJson(str2 , "id" , rows[i].majorCateName);
                    createJson(str2, "pId" , "main");
                    createJson(str2 , "name" , rows[i].majorCateName);
                    zNodes.push(str2);
                    cates.push(rows[i].majorCateName);
                }
            }

            for (var i = 0 ; i < rows.length ; i ++){
                var str1 = {"id": "rr", "pId": "11" , "name" : "分类"};
                createJson(str1 , "id" , rows[i].minorCateName);
                createJson(str1 , "pId" , rows[i].majorCateName);
                createJson(str1 , "name" , rows[i].minorCateName);
                zNodes.push(str1);
            }
        });
        $.ajaxSettings.async = true;

        var t = $("#treeDemo");
        t = $.fn.zTree.init(t, setting, zNodes);
        demoIframe = $("#testIframe");
        demoIframe.bind("load", loadReady);
        var zTree = $.fn.zTree.getZTreeObj("tree");
        zTree.selectNode(zTree.getNodeByParam("id",'1'));
    });
</script>
</body>
</html>