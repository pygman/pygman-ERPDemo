<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
</script>
<script type="text/javascript">
	function intToFloat(val){
		return new Number(val).toFixed(2);
	}
	$(function(){
		//修改供应商(暂定)
		$("#supplier").change(function(){
			$(".goodsType").empty();
			$(".goods").empty();
			//根据选中的供应商uuid发送ajax请求，获取对应的商品类别+第一个类别的具体商品+第一个商品的进价信息
			var supplierUuid = $(this).val();
			//发送ajax请求
			$.post("order_ajaxGetGtmAndGm.action",{"supplierUuid":supplierUuid},function(data){
				//动态加载类别数据
				var gtmList = data.gtmList;
				for(var i = 0;i<gtmList.length;i++){
					var gtm = gtmList[i];
					$op = $("<option value='"+gtm.uuid+"'>"+gtm.name+"</option>");
					$(".goodsType").append($op);
				}
				//动态加载商品数据
				var gmList = data.gmList;
				for(var i = 0;i<gmList.length;i++){
					var gm = gmList[i];
					$op = $("<option value='"+gm.uuid+"'>"+gm.name+"</option>");
					$(".goods").append($op);
				}
				//动态加载单价数据
				var inPrice = data.gm.inPriceView;
				$(".prices").val(inPrice);
				//动态加载合计数据
				$(".total").html(inPrice+" 元");
				//求总计：
				totalAll();
			});
		});
		//修改商品类别
		//$(".goodsType").change(function(){
		$(".goodsType").live("change",function(){
			//必须设置修改的内容为当前行
			//$(this)当前select->父td->下一个兄弟td->子select
			/*
			$goods = $(this).parent().next().children("select");
			$num = $(this).parent().next().next().children("input");
			$price = $(this).parent().next().next().next().children("input");
			$total = $(this).parent().next().next().next().next();
			*/
			$nowTr = $(this).parent().parent();
			$goods = $nowTr.children("td:eq(1)").children("select");
			$num = $nowTr.children("td:eq(2)").children("input");
			$price = $nowTr.children("td:eq(3)").children("input");
			$total = $nowTr.children("td:eq(4)");
			
			var goodsArr = $(".goods");		//取出一个数组对象
			var used = "";
			for(var i = 0;i<goodsArr.length;i++){
				used = used + goodsArr[i].value;
				used = used + ",";
			}
			
			$goods.empty();
			//根据商品类别uuid获取商品信息
			var goodsTypeUuid = $(this).val();
			//发送ajax请求
			$.post("order_ajaxGetGm",{"goodsTypeUuid":goodsTypeUuid,"used":used},function(data){
				//动态加载商品数据
				var gmList = data.gmList;
				for(var i = 0;i<gmList.length;i++){
					var gm = gmList[i];
					$op = $("<option value='"+gm.uuid+"'>"+gm.name+"</option>");
					$goods.append($op);
				}
				//设置采购数量为1
				$num.val(1);
				//动态加载单价数据
				var inPrice = data.gm.inPriceView;
				$price.val(inPrice);
				//动态加载合计数据
				$total.html(inPrice+" 元");
				//求总计：
				totalAll();
			});
		});
		//修改商品
		$(".goods").live("change",function(){
			$nowTr = $(this).parent().parent();
			$num = $nowTr.children("td:eq(2)").children("input");
			$price = $nowTr.children("td:eq(3)").children("input");
			$total = $nowTr.children("td:eq(4)");
		
			//获取商品uuid
			var goodsUuid = $(this).val();
			//发送ajax请求
			$.post("order_ajaxGetOne",{"goodsUuid":goodsUuid},function(data){
				//动态加载单价数据
				var inPrice = data.inPriceView;
				//设置采购数量为1
				$num.val(1);
				//动态加载单价数据
				$price.val(inPrice);
				//动态加载合计数据
				$total.html(inPrice+" 元");
				//求总计：
				totalAll();
			})
		});
		//添加订单子项
		$("#add").click(function(){
			//添加订单子项时，已经确认供应商，因此供应商不能再做修改
			$("#supplier").attr("disabled",true);
			//设置class=goodsType的组件不能修改
			$(".goodsType").attr("disabled",true);
			//设置class=goods的组件不能修改
			$(".goods").attr("disabled",true);
			
			//动态添加行，行数据来源于后台数据
			//ajax获取数据，根据数据组织行对象，添加到对应位置
			//获取的是什么数据？类别数据、商品数据、第一个商品单价(数据必须进行过滤，已经使用过的数据不能再出现了)
			var supplierUuid = $("#supplier").val();
			
			//由于获取的数据不能包含已经使用过的商品，因此需要将已经使用过的商品数据传递到后台，进行过滤
			//将使用过的商品的uuid组织成一个字符串，发送到后台
			
			var goodsArr = $(".goods");		//取出一个数组对象
			var used = "";
			for(var i = 0;i<goodsArr.length;i++){
				used = used + goodsArr[i].value;
				used = used + ",";
			}
			
			$.post("order_ajaxGetGtmAndGm2.action",{"supplierUuid":supplierUuid,"used":used},function(data){
				$tr = $('<tr align="center" bgcolor="#FFFFFF"></tr>');
			
				//商品类别
				var gtmList = data.gtmList;
				$td1 = $("<td></td>");
				$gtmSelect = $("<select class='goodsType' style='width:200px' ></select>");
				for(var i = 0;i<gtmList.length;i++){
					var gtm = gtmList[i];
					$op = $("<option value='"+gtm.uuid+"'>"+gtm.name+"</option>")
					$gtmSelect.append($op);
				}
				$td1.append($gtmSelect);
				$tr.append($td1);
				
				//商品
				var gmList = data.gmList;
				$td2 = $("<td></td>");
				$gmSelect = $("<select name='goodsUuids' class='goods' style='width:200px' ></select>");
				for(var i = 0;i<gmList.length;i++){
					var gm = gmList[i];
					$op = $("<option value='"+gm.uuid+"'>"+gm.name+"</option>")
					$gmSelect.append($op);
				}
				$td2.append($gmSelect);
				$tr.append($td2);
				
				//数量
				$td3 = $('<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>');
				$tr.append($td3);
			
				var inPrice = data.gm.inPriceView;	
				//单价
				$td4 = $('<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="'+inPrice+'"/> 元</td>');
				$tr.append($td4);
				
				//合计
				$td5 = $('<td class="total" align="right">'+inPrice+'&nbsp;元</td>');
				$tr.append($td5);
				
				//删除按钮
				$td6 = $('<td><a class="deleteBtn delete xiu"><img src="images/icon_04.gif" />删除</a></td>');
				$tr.append($td6);
				
				//最后一个行对象，将新的tr添加到该对象的上方
				$("#finalTr").before($tr);
				
				//如果已经只有一个商品了，添加按钮消失
				//此次请求时，如果gtmList.size=1 && gmList.size=1
				if(gtmList.length == 1 && gmList.length == 1){
					//设置添加按钮为不可显示
					$("#add").css("display","none");
				}
				totalAll();
			});
		});
		//删除
		//jquery的运行速度要比js快一些
		//jquery将页面组件分为两大类：加载页面时候的内容，当前内容
		//如何为动态添加的组件绑定事件？
		//格式：$(selector).live(event,data,function)
		$(".deleteBtn").live("click",function(){
			//最后一个不能删除
			if($(".deleteBtn").length == 1){
				return ;
			}
			//删除当前行对象
			//使用相对获取的方式来获取要操作的对象
			//获取当前this->获取父td->获取父tr
			//测试元素选中是否正确，可以为其添加临时属性，然后通过现实属性值测试是否是你要的组件
			var $nowTr = $(this).parent().parent();
			$nowTr.remove();
			
			//设置添加按钮显示
			$("#add").css("display","inline");
			
			totalAll();
		});
		
		//为数量绑定事件
		$(".num").live("keyup",function(){
			//此处只能输入0-9的数字
			//先把非数字的都替换掉，除了数字和. 
			$(this).val($(this).val().replace(/[^\d]/g,""));
	        
			$nowTr = $(this).parent().parent();
			$num = $nowTr.children("td:eq(2)").children("input");
			$price = $nowTr.children("td:eq(3)").children("input");
			$total = $nowTr.children("td:eq(4)");
			//数量
			var num = $num.val();
			//单价
			var price = $price.val();
			//计算合计
			var total = num * price;
			//修改合计
			//需要对数字进行格式化，转换成两位小数
			$total.html(intToFloat(total)+" 元");
			//求总计：
			totalAll();
		});
		//为单价绑定事件
		$(".prices").live("keyup",function(event){
			//先把非数字的都替换掉，除了数字和. 
			$(this).val($(this).val().replace(/[^\d.]/g,""));
	        //必须保证第一个为数字而不是. 
	        $(this).val($(this).val().replace(/^\./g,"0."));
	        //保证只有出现一个.而没有多个. 
	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	        //保证.只出现一次，而不能出现两次以上
	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$",".")); 
	        
			$nowTr = $(this).parent().parent();
			$num = $nowTr.children("td:eq(2)").children("input");
			$price = $nowTr.children("td:eq(3)").children("input");
			$total = $nowTr.children("td:eq(4)");
			//数量
			var num = $num.val();
			//单价
			var price = $price.val();
			//计算合计
			var total = num * price;
			//修改合计
			//需要对数字进行格式化，转换成两位小数
			$total.html(intToFloat(total)+" 元");
			//求总计：
			totalAll();
		});
		//总计：
		function totalAll(){
			//获取所有的数量
			var numArr = $(".num");
			//获取所有的单价
			var priceArr = $(".prices");
			//两个的乘积求和
			var sum = 0;
			for(var i = 0;i<numArr.length;i++){
				var num = numArr[i].value;
				var price = priceArr[i].value;
				var total = num * price;
				sum += total;
			}
			//设置到总计处
			$(".all").html(intToFloat(sum)+" 元");
		}
		$("#submitOrder").click(function(){
			//由于不可编辑的组件不参与表单提交，因此在提交前，必须将其状态恢复为可用状态
			$("#supplier").removeAttr("disabled");
			$(".goods").removeAttr("disabled");
			$("form:first").submit();
		});
		
	});
	function intToFloat(val){
		return new Number(val).toFixed(2);
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="order_buySave" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68px" height="30">供应商：</td>
						<td width="648px">
							<s:select name="om.sm.uuid" id="supplier" list="supplierList" listKey="uuid" listValue="name" cssStyle="width:190px"></s:select>
						</td>
						<td height="30">
							<a id="add"><img src="images/can_b_02.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<tr align="center" bgcolor="#FFFFFF">
						<td>
							<s:select cssClass="goodsType" list="gtmList" listKey="uuid" listValue="name" cssStyle="width:200px"></s:select>
						</td>
						<td>
							<s:select name="goodsUuids" cssClass="goods" list="gmList" listKey="uuid" listValue="name" cssStyle="width:200px"></s:select>
						</td>
						<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>
						<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="${gmList.get(0).inPriceView}"/> 元</td>
						<td class="total" align="right">${gmList[0].inPriceView}&nbsp;元</td>
						<td>
							<a class="deleteBtn delete xiu"><img src="images/icon_04.gif" />删除</a>
						</td>
					</tr>
					<tr id="finalTr" align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" width="16%" align="right">${gmList[0].inPriceView}&nbsp;元</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" id="submitOrder"><img src="images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</div>
		</s:form>
	</div>
	
	<div class="content-bbg"></div>
</div>
