<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/Calendar.js"></script>
<script type="text/javascript">
	/*
	$(function() {
		$("#all").click(function() {
			$("[name=resources]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=resources]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });

		});
	});
	*/
	$(function(){
		//全选
		$("#all").click(function(){
			//点击后做什么事情，将所有的name=resUuids的checkbox设置为选中状态
			//修改其选中状态为全部选中
			//$("[name=resUuids]").attr("checked",获取当前点击的组件对应的check属性值);
			//alert($(this).attr("checked"));
			//false "false" 0
			//true "true" "aa" "" "checked"
			$("[name=resUuids]").attr("checked",$(this).attr("checked") == "checked");
		});
		$("#reverse").click(function(){
			//将name=roleUuids的所有组件的状态改变成当前的反状态
			//$("[name=resUuids]").attr("checked")如果$("[name=resUuids]")取出来是一个对象，那么取值
			//如果是多个对象，那么取第一个对象的值
			//$("[name=resUuids]").attr("checked",$("[name=resUuids]").attr("checked") == "checked");
			//要求$("[name=resUuids]")中的所有的对象，分别取完成一件事情，获取自己的组件状态值，然后切换
			$("[name=resUuids]").each(function(){
				//获取当前迭代出的组件值
				//切换成相反状态
				$(this).attr("checked",!$(this).attr("checked"));
			});
			checkAll();
		});
		$("[name=resUuids]").click(function(){
			//每次点击后，判断当前所有名称相同的组件的状态，如果全部选中，则全选按钮为选中，否则全选按钮为不选中
			//当前有10个组件，此处就是将全选按钮的状态设置为所有组件的状态的&结果
			checkAll();
		});
		function checkAll(){
			var flag = true;
			$("[name=resUuids]").each(function(){
				flag = flag && ($(this).attr("checked") == "checked");
			});
			
			$("#all").attr("checked",flag);
		}
	});
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<s:form action="role_save" method="post">
			<!-- BUG -->
			<s:hidden name="rm.uuid"/>
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">角色名称</td>
				      <td width="32%">
				      	<s:textfield name="rm.name" size="25"/>
				      </td>
				      <td width="18%" align="center">角色编码</td>
				      <td width="32%">
						<s:textfield name="rm.code" size="25"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">资源名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3">
				      	<s:checkboxlist name="resUuids" list="resList" listKey="uuid" listValue="name"></s:checkboxlist>
				      </td>
				    </tr>
				     <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">菜单名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3">
				      	<s:checkboxlist name="menuUuids" list="menuList" listKey="uuid" listValue="name"></s:checkboxlist>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" onclick="document.forms[0].submit()"><img src="images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</s:form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="images/content_bbg.jpg" /></div>
</div>
