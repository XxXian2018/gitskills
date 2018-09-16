<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">

function a(){
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/requestJson.action',
		contentType:'application/json;charset=utf-8',
		//数据格式是json串，商品信息
		data:'{"name":"手机","price":999}',
		success:function(data){//返回json结果
			alert(data);
		}
		
	});
}

function b(){
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/responseJson.action',
		//请求是key/value这里不需要指定contentType，因为默认就 是key/value类型
		//contentType:'application/json;charset=utf-8',
		//数据格式是json串，商品信息
		data:'name=手机&price=999',
		success:function(data){//返回json结果
			alert(data.name);
		}
		
	});
}

</script>
</head>
<body>
<input type="button" value="请求json响应json" onclick="a()">
<input type="button" value="请求key/value响应json" onclick="b()">
</body>
</html>