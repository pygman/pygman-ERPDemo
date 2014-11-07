<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	
	/*
	$(function() {
		//初始化仓库数据
		var storeUuidArr = new Array();
		var storeNameArr = new Array();
			
			storeUuidArr[0] = 11;
			storeNameArr[0] = "1号仓库";
			storeUuidArr[1] = 22;
			storeNameArr[1] = "2号仓库";
			storeUuidArr[2] = 33;
			storeNameArr[2] = "3号仓库";
		var omUuid = 123;
		
		$(".oper").click(function() {
			var $myTr = $(this).parent().parent();
			var $nextTr = $myTr.next();
			if($nextTr.attr("class") == "in"){
				return;
			}
			if($(".in").length>0){
				$(".in").remove();
			}
			var $newTr = $("<tr class='in'></tr>");
			var $td1 = $("<td align='right'>仓库：</td>");
			$newTr.append($td1);	
				var storeSelectStr = "<select style='width:200px'>";
				for(var i = 0;i<storeUuidArr.length;i++){
					storeSelectStr+="<option value='";
					storeSelectStr+=storeUuidArr[i];
					storeSelectStr+="'>";
					storeSelectStr+=storeNameArr[i];
					storeSelectStr+="</option>";
				}
				storeSelectStr += "</select>";
			var $td2 = $("<td height='30'>"+storeSelectStr+"</td>");
			$newTr.append($td2);	
			//2.3入库多少
			var $td3 = $("<td align='right'>入库量：</td>");
			$newTr.append($td3);	
			//获取当前入库数据总量
			var totalNum = $(this).parent().prev().text();
			var $td4 = $("<td><input id='inNum' type='text' value='"+totalNum+"'/></td>");
			$newTr.append($td4);	
			var $td5 = $("<td align='center'><a href='javascript:void(0)' class='ajaxIn xiu'><img src='images/icon_3.gif' />确定</a></td>");
			$newTr.append($td5);
			//3.将新的行对象添加到当前按钮所在的行对象后面
			$myTr.after($newTr);
		});
		$(".ajaxIn").live("click",function(){
			//0.页面校验输入是否合法（省略）
			//1.组织ajax提交的数据
			jsonParam ={};
			//主单编号
			jsonParam["odm.order.uuid"] = omUuid;
			//获取当前链接所在行的上一行中隐藏的第一个子节点和第二个子节点的值
			//子单编号
			jsonParam["odm.uuid"] = $(this).parent().parent().prev().children("input:eq(0)").val();
			//货物编号
			jsonParam["odm.goods.uuid"] = $(this).parent().parent().prev().children("input:eq(1)").val();
			//入库数量
			jsonParam["som.num"] = $(this).parent().prev().children("input:eq(0)").val();
			//仓库编号
			jsonParam["som.sdm.sm.uuid"] = $(this).parent().parent().children("td:eq(1)").children("select").val();
			
			//为ajax提交操作后的操作对象进行初始化
			var $upTr = $(this).parent().parent().prev();
			var $upCenter =  $upTr.children("td:eq(2)");
			var $upRight = $upTr.children("td:eq(3)");
			var $myTr = $(this).parent().parent();
				if(false){
					//显示返回按钮
					$("#inOrderTitle").remove();
					$("#inOrder").remove();
					$("#allInTitle").css("display","block");
					$("#return").css("display","block");
					return;
				}
				
				if(0 == 0){
					$upTr.remove();
					$myTr.remove();
				}else{
					$upCenter.text(20);
					$upRight.text(10);	
				}
			});
		
	});
	*/
	$(function(){
		/*
		<s:iterator value="storeList" status="idx">
			//idx描述的是当前循环过程中，每次循环出的数据对应的行对象
			alert(${idx.count-1});
		</s:iterator>
		*/
		//如果将orderList中的数据转换成数组
		
		var uuidArr = new Array();
		var nameArr = new Array();
		var i = 0;
		<s:iterator value="storeList" status="idx">
			uuidArr[${idx.count-1}] = ${uuid};
			nameArr[i] = "${name}";
			i++;
		</s:iterator>
		
		//为入库链接绑定事件
		$(".oper").click(function(){
			//删除所有目前现有的动态行
			$(".in").remove();
		
			//动态添加行
			//行数据来自于那里？行结果什么样？行添加的位置
			$nowTr = $(this).parent().parent();
			
			$tr =$("<tr class='in'></tr>");
			
			$td1 = $("<td align='right'>仓库：</td>");
			$tr.append($td1);
			
			$td2 = $("<td></td>");
			//创建select,用于显示仓库信息
			$select = $("<select id='storeUuid'></select>")
			for(var i = 0;i<uuidArr.length;i++){
				$op = $("<option value='"+uuidArr[i]+"'>"+nameArr[i]+"</option>")
				$select.append($op);
			}
			$td2.append($select);
			$tr.append($td2);
			
			$td3 = $("<td align='right'>入库量：</td>");
			$tr.append($td3);
			//发送 ajax请求，获取当前商品的总数量与剩余量,参数是：
			var orderDetaiUuid = $(this).attr("odm");
			
			$.post("orderDetail_ajaxGetNumAndSurplusByOdmUuid.action",{"om.uuid":orderDetaiUuid},function(data){
				//请求返回的数据：{"num":200,"surplus":200}
				var num = data.num;
				var surplus = data.surplus;
				
				$td4 = $('<td><input id="inNum" type="text" value="'+(surplus)+'"/></td>');
				$tr.append($td4);
				
				$td5 = $('<td align="center"><a class="ajaxIn xiu" href="javascript:void(0)"><img src="images/icon_3.gif">确定</a></td>');
				$tr.append($td5);
	
				$nowTr.after($tr);
			});
		});
		//确认入库
		$(".ajaxIn").live("click",function(){
			//点击确认：将指定订单明细中的商品对应的数量进行入库
			//1.订单明细中的剩余数量要修改
			//2.仓库中要不要记录入库了某些商品（某年某月某日具体时间，谁，将哪个订单中的哪个货物，入到了哪个仓库中，入了多少）仓库入库记录表
			//3.仓库中的货物总量要更新
			//需要发送ajax请求，传输的参数有哪些？
			/*
			哪个订单明细？关联货物
			数量？
			仓库？
			操作人？登陆人
			*/
			$nowTr = $(this).parent().parent();
			$upTr = $nowTr.prev();
			$num = $nowTr.children("td:eq(3)").children("input");
			$alreadyTr = $upTr.children("td:eq(2)");
			$surplusTr = $upTr.children("td:eq(3)");
			
			var num = $("#inNum").val();
			var storeUuid = $("#storeUuid").val();
			var odmUuid = $nowTr.prev().children("td:eq(4)").children("a").attr("odm");
			
			
			$.post("orderDetail_ajaxInGoods.action",{"num":num,"storeUuid":storeUuid,"odmUuid":odmUuid},function(data){
				//需求数据：num surplus
				var num = data.num;
				var surplus = data.surplus;
				
				//如果全部入库完毕，将当前页面的数据拿掉，换成一个返回按钮
				if($(".ins").length == 1 && surplus == 0){
					$("#inOrderTitle").css("display","none");
					$("#inOrder").css("display","none");
					$("#allInTitle").css("display","block");
					$("#return").css("display","block");
				}
				
				//如果当前商品全部入库，则将当前商品对应的项删除掉
				if(surplus == 0){
					$upTr.remove();
					$nowTr.remove();
					return;
				}
				
				//修改下次入库的数量为全部
				$num.val(surplus);
				//修改已经入库数量和剩余入库数量
				$alreadyTr.html(num-surplus);
				$surplusTr.html(surplus);
			});
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>订 单 号:</td>
						<td class="order_show_msg">${om.orderNum}</td>
						<td>商品总量:</td>
						<td class="order_show_msg">${om.totalNum}</td>
					</tr>
				</table>
			</div>
			
			<!--"square-o-top"end-->
			<div class="square-order">
				<center id="inOrderTitle" style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;据&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table id="inOrder" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品名称</td>
						<td width="30%">总数量</td>
						<td width="10%">已入库数量</td>
						<td width="30%">剩余数量</td>
						<td width="10%">入库</td>
					</tr>
					<s:iterator value="om.odms">
						<s:if test="surplus != 0">
							<tr class="ins" align="center" bgcolor="#FFFFFF">
								<td height="30">${gm.name}</td>
								<td>${num}</td>
								<td>${num-surplus}</td>
								<td>${surplus}</td>
								<td><a odm="${uuid}" href="javascript:void(0)" class="oper xiu"><img src="images/icon_3.gif" />入库</a></td>
							</tr>
						</s:if>
					</s:iterator>
				</table>
				
				<center id="allInTitle" style="display:none;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;全&nbsp;&nbsp;部&nbsp;&nbsp;入&nbsp;&nbsp;库&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<table id="return" style="display:none" >
					<tr>
						<td>&nbsp;</td>
						<td width="100%" align="center">
							<a action="list.jsp" style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(images/btn_bg.jpg)">
								返回
							</a>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
