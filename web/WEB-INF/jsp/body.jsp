<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.text.*, java.util.Date" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div style="background-color: red; ">
<%
	SimpleDateFormat fmt = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
	out.println("The date and time is: ");
	out.println(fmt.format(new Date()));
%>
</div>