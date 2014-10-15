<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:hidden name="pageNum"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="51%">&nbsp;</td>
		<td width="13%">共${dataTotal}条记录
		<td width="6%">
			<a id="fir" class="sye">首&nbsp;&nbsp;页</a>
		</td>
		<td width="6%">
			<a id="prev" class="sye">上一页</a>
		</td>
		<td width="6%">
			<a id="next" class="sye">下一页</a>
		</td>
		<td width="6%">
			<a id="last" class="sye">末&nbsp;&nbsp;页</a>
		</td>
		<td width="12%">当前第<span style="color:red;">${pageNum}</span>/${maxPageNum}页</td>
	</tr>
</table>
<script type="text/javascript">
	$(function(){
		var pageNum = ${pageNum};
		var maxPageNum = ${maxPageNum};
	
		if(maxPageNum == 1){
			//设置显示使用的是属性还是样式？display样式
			$("#fir").css("display","none");
			$("#prev").css("display","none");
			$("#next").css("display","none");
			$("#last").css("display","none");
		}else if(pageNum == 1){
			$("#fir").css("display","none");
			$("#prev").css("display","none");
			$("#next").css("display","inline");
			$("#last").css("display","inline");
		}else if(pageNum == maxPageNum){
			$("#fir").css("display","inline");
			$("#prev").css("display","inline");
			$("#next").css("display","none");
			$("#last").css("display","none");
		}else{
			$("#fir").css("display","inline");
			$("#prev").css("display","inline");
			$("#next").css("display","inline");
			$("#last").css("display","inline");
		}
	
		$("#fir").click(function(){
			var pageNum = $("[name=pageNum]").val();
			$("[name=pageNum]").val(1);
			$("form:first").submit();
		});
		$("#prev").click(function(){
			var pageNum = $("[name=pageNum]").val();
			$("[name=pageNum]").val( pageNum - 1 );
			$("form:first").submit();
		});
		$("#next").click(function(){
			//1.获取页码值:属性选择器
			var pageNum = $("[name=pageNum]").val();
			//2.设置页码值为下一页+1
			$("[name=pageNum]").val( pageNum* 1 + 1 );
			//3.提交form
			$("form:first").submit();
		});
		$("#last").click(function(){
			var pageNum = $("[name=pageNum]").val();
			$("[name=pageNum]").val(maxPageNum);
			$("form:first").submit();
		});
	});
</script>		