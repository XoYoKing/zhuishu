<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <script type="text/javascript" src="lib/PIE_IE678.js"></script>
    <![endif]-->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <!--[if IE 6]>
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>建材列表</title>
</head>
<body class="pos-r">
<div style="margin-left:0px">
    <div class="pd-20">
        <div class="text-c">
            <input type="text" name="bookAuthor" id="bookAuthor" placeholder=" 书籍名称" style="width:250px" class="input-text">
            <button name="" id="booksearch" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜书
            </button>
            <%--TODO 搜书功能--%>

        </div>
        <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="l"> <a class="btn btn-primary radius"
                                                              onclick="product_add('添加产品','product-add.html')"
                                                              href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加产品</a></span>
            <span class="r">共有数据：<strong id="booknumber">0</strong> 条</span></div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover ">
                <thead>
                <tr class="text-c">
                    <%--<th width="40"><input name="" type="checkbox" value=""></th>--%>
                    <th width="60">ID</th>
                    <%--<th width="40">onlineId</th>--%>
                    <th width="90">书名</th>
                    <th width="100">作者</th>
                    <th width="60">封面</th>
                    <th>简介</th>
                    <th width="180">最新章节</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody id="productlist">

                </tbody>
            </table>
        </div>

        <%--分页--%>
        <div class="col-md-12 column">
            <ul class="pagination pull-right">

                        <li><a href="javascript: --page;showList();">上一页</a></li></#else>

                        <li><a href="javascript: ++page;showList();window.scrollTo(0,0);">下一页</a></li></#else>

            </ul>
        </div>


    </div>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
   var size = 15 ;
   var page = 1;
   var pages=1;
    function showList(bookId) {
        //获取书籍列表
        var url;
        if (bookId == null || bookId ==""){
        url = "bookdetail/list/?majorCateName=${param.majorCateName}&minorCateName=${param.minorCateName}&page=" + page+ "&size= "+size;
    }else{
        url = "bookdetail/" + bookId;
   }
        $.getJSON(url,
            function (_data) {
                if (_data && _data.status == 200 && _data.data) {
                    var books = _data.data.rows;
                    pages = _data.data.total / books.length ;
                    $("#booknumber").html(_data.data.total);
                    $("#productlist").html("");
                    for (var i = 0; i < books.length; i++) {
                        var pd = books[i];
                        var html = "<tr class=\"text-c va-m\">";
                        html += "<td>" + pd.bookDetailId + "</td>";
                        html += "<td>" + pd.bookTitle + "</td>";
                        html += "<td>" + pd.bookAuthor + "</td>";
                        html += "<td width=\"60px\" height=\"70px\"><img src=" + pd.bookCover + "  alt=\"小说封面\" width=\"60px\" height=\"70px\"></td>";
                        html += "<td>" + pd.bookIntro + "</td>";
                        html += "<td>" + pd.lastChapter + "</td>";
                        html += "<td class=\"td-manage\"><a style=\"text-decoration:none\" onClick=\"product_stop(this,'10001')\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"product_edit('产品编辑','product-add.html','10001')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"product_del(this,'10001')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td>\n";
                        html += "</tr>";
                        $("#productlist").append(html);
                    }
                }
            });
    }

    $(document).ready(function () {

            showList();

        }
    );


    /*图片-添加*/
    function product_add(title, url) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*图片-查看*/
    function product_show(title, url, id) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*图片-审核*/
    function product_shenhe(obj, id) {
        layer.confirm('审核文章？', {
                btn: ['通过', '不通过'],
                shade: false
            },
            function () {
                $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
                $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
                $(obj).remove();
                layer.msg('已发布', {icon: 6, time: 1000});
            },
            function () {
                $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
                $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
                $(obj).remove();
                layer.msg('未通过', {icon: 5, time: 1000});
            });
    }

    /*图片-下架*/
    function product_stop(obj, id) {
        layer.confirm('确认要下架吗？', function (index) {
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
            $(obj).remove();
            layer.msg('已下架!', {icon: 5, time: 1000});
        });
    }

    /*图片-发布*/
    function product_start(obj, id) {
        layer.confirm('确认要发布吗？', function (index) {
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
            $(obj).remove();
            layer.msg('已发布!', {icon: 6, time: 1000});
        });
    }

    /*图片-申请上线*/
    function product_shenqing(obj, id) {
        $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
        $(obj).parents("tr").find(".td-manage").html("");
        layer.msg('已提交申请，耐心等待审核!', {icon: 1, time: 2000});
    }

    /*图片-编辑*/
    function product_edit(title, url, id) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*图片-删除*/
    function product_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $(obj).parents("tr").remove();
            layer.msg('已删除!', {icon: 1, time: 1000});
        });
    }
</script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
</body>
</html>