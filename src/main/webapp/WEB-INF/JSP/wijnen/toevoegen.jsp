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
	<c:if test="${not empty fouten.wijn}">
		<h2>${fouten.wijn}</h2>
	</c:if>
	<c:if test="${not empty wijn}">
		<h2>Wijn toevoegen aan mandje</h2>
		<c:url var= "backUrl" value= "/wijnen/soort.htm">
			<c:param name="id" value="${wijn.soort.id}"/>
		</c:url>	
		<a href="${backUrl}">Terug naar overzicht</a>
		<dl>		
			<dt>Land</dt>
			<dd>${wijn.soort.land.naam}</dd>
			
			<dt>Soort</dt>
			<dd>${wijn.soort.naam}</dd>
			
			<dt>Jaar</dt>
			<dd>${wijn.jaar}</dd>
			
			<dt>Beoordeling</dt>
			<dd><c:forEach end="${wijn.beoordeling}" begin="0">&#9733;</c:forEach></dd>
			
			<dt>Prijs</dt>
			<dd>${wijn.prijs}</dd>			
		</dl>
		<form method="post">
			<label>Aantal flessen<span>${fouten.aantal}</span>
				<input name='aantal' <c:if test="${not empty aantal}"> value='${aantal}'</c:if>  autofocus type="number" min="1" required>
			</label>
			<input type="hidden" name="wijnID" value="${wijn.id}">			
			<input type='submit' value='Toevoegen' id='toevoegenButton'>
		</form>
	</c:if>
</body>
</html>