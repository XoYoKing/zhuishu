<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
	<title>淘淘商城后台管理系统</title>
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/icon.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.net/Public/js/easyui/jquery.easyui.min.js"></script>
	
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true"
		style="width: 180px;">
		<ul id="menu" class="easyui-tree"
			style="margin-top: 10px; margin-left: 5px;">
			<li><span>商品管理</span>
				<ul>
					<li data-options="attributes:{'url':'item-add'}">新增商品</li>
					<li data-options="attributes:{'url':'item-list'}">查询商品</li>
					<li data-options="attributes:{'url':'item-param-list'}">规格参数</li>
				</ul></li>
			<li>
			<span>网站内容管理</span>
				<ul>
					<li data-options="attributes:{'url':'content-category'}">内容分类管理</li>
					<li data-options="attributes:{'url':'content'}">内容管理</li>
				</ul>
			</li>
			<li>
			<span>权限管理</span>
				<ul>
					<li data-options="attributes:{'url':'content-category'}">角色管理</li>
					<li data-options="attributes:{'url':'content'}">权限管理</li>
				</ul>
			</li>
		</ul>
	</div>
	<div data-options="region:'center',title:''">
		<div id="tabs" class="easyui-tabs">
			<div title="首页" style="padding: 20px;"></div>
		</div>
	</div>

	<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
</script>
</body>
</html>