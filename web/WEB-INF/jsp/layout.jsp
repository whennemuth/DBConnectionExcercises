<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div style="min-width:450px; max-width:900%;">

	<div style="background: #bbb;">
		<tiles:insertAttribute name="banner" />
	</div>
    
	<div style="float: left; background: #fff; width:250px;">
	    <tiles:insertAttribute name="navigation" />
	</div>
    
	<div style="float: right; background: #eee; width:650px;">
	    <tiles:insertAttribute name="body" />
	</div>
    
	<div style="clear: both; background: #aaa">
	    <tiles:insertAttribute name="footer" />
	</div>
</div>

</body>
</html>