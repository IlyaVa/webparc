<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>Choice page</title></head>
<body>
<h2>Parcing Data</h2>
<form method="POST" action="adminController">
	<fieldset>
		<legend>Choice method</legend>
	parc-file:<select name = "parcmethod">
				<option value = "dom">DOMparcer</option>
				<option value = "sax">SAXparcer</option>
				<option value = "stax">StAXparcer</option>
			</select>
	</fieldset>
<input type="submit" value="Run!!!">
</form>
</body></html>