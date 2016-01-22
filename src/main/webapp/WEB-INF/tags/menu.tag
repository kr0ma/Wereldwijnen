<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav>
	<h1><a href="<c:url value='/'/>">Wereldwijnen</a></h1>	
	<ul>
		<c:forEach var = "land" items="${landen}">
			<c:url value='/images/${land.id}.png' var='imgUrl'/>
			<c:url value='/wijnen/land.htm' var="url">
				<c:param name='id' value='${land.id}' />
			</c:url>
			<li><a href="${url}"><img alt="${land.naam}" src="${imgUrl}" title="${land.naam}"></a>
			</li>
		</c:forEach>		
	</ul>
	<c:if test="${not empty mandje}">
		<div>
			<a href='<c:url value="/mandje/bevestigen.htm"/>'>
				<img alt="mandje" src="<c:url value='/images/mandje.png'/>">
			</a>
		</div>
	</c:if>
	
</nav>
