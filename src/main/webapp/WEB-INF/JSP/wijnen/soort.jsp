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
	<c:if test="${empty soort}">
		<h2>Geen correcte soort gekozen</h2>
	</c:if>
	<c:if test="${not empty soort}">
		<h2>Soorten uit ${soort.land.naam}</h2>
		<ul class="zonderbolletjes">
			<c:forEach var="soort" items="${soort.land.soorten}">
				<c:url value="/wijnen/soort.htm" var = "url">
					<c:param name="id" value="${soort.id}"/>
				</c:url>			
				<li><a href="${url}">${soort.naam}</a></li>
			</c:forEach>
		</ul>		
		<ul>
			<c:forEach var="wijn" items="${soort.wijnen}">
				<c:url var = "wijnUrl" value = "/wijn/toevoegen.htm">
					<c:param name="id" value="${wijn.id}"/>					
				</c:url>					
				<li>
					<a href="${wijnUrl}">${wijn.jaar} <c:forEach end="${wijn.beoordeling}" begin="0">&#9733;</c:forEach></a>
				</li>					
			</c:forEach>
		</ul>
	</c:if>		
</body>
</html>