<%@ page contentType='text/html' pageEncoding='UTF-8' session='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='Wereldwijnen > overzicht'/>
<body>
	<header>
		<v:menu/>
	</header>	
	<c:if test="${not empty id}">
		<h1>Je mandje is bevestigd als bestelbon ${id}</h1>
	</c:if>
</body>
</html>