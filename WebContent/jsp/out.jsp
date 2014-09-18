<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>${method}</title></head>
<body>
<h2>Parcing ${method}</h2>
<table>
<c:if test="${not empty dataWorker }">
<tr>Workers</tr>
<c:forEach var="elem" items="${dataWorker}" varStatus="status">

<tr>
<td>Fullname : <c:out value="${ elem.firstName} ${ elem.surName }" /></td>
</tr>
<tr>
<td>city :<c:out value="${ elem.city }" /></td>
</tr>
<tr>
<td>password :<c:out value="${ elem.password }" /></td>
<tr>
<td>ID :<c:out value="${ elem.ID }" /></td>
</tr>
<tr>
<td>Number of items :<c:out value="${ elem.numberOfItem }" /></td>
</tr>
</c:forEach>
</c:if>
</table>
<table>
<br>
<c:if test="${not empty dataSellers }">
<tr>Sellers</tr>
<c:forEach var="elem" items="${dataSellers}" varStatus="status">
<tr>
<td>Fullname : <c:out value="${elem.firstName} ${elem.surName }" /></td>
</tr>
<tr>
<td>city :<c:out value="${ elem.city }" /></td>
</tr>
<tr>
<td>password :<c:out value="${ elem.password }" /></td>
<tr>
<td>ID :<c:out value="${ elem.ID }" /></td>
</tr>
<tr>
<td>Number of items :<c:out value="${ elem.numberOfClient }" /></td>
</tr>
</c:forEach>
</c:if>
</table>
<a href="index.jsp"> Go to index.jsp </a>
</body></html>