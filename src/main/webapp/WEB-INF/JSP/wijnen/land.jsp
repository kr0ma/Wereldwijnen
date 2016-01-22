<%@ page contentType='text/html' pageEncoding='UTF-8' session='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='Wereldwijnen'/>
<body>
	<header>
		<v:menu/>
	</header>
	<c:if test="${empty land}">
		<h2>Geen correct land gekozen</h2>
	</c:if>
	<c:if test="${not empty land}">
		<h2>Soorten uit ${land.naam}</h2>
		<ul class="zonderbolletjes">
			<c:forEach var="soort" items="${land.soorten}">
				<c:url value="/wijnen/soort.htm" var = "url">
					<c:param name="id" value="${soort.id}"/>
				</c:url>			
				<li><a href="${url}">${soort.naam}</a></li>
			</c:forEach>
		</ul>		
	</c:if>		
</body>
</html>