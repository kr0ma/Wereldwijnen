<%@ page contentType='text/html' pageEncoding='UTF-8' session='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<v:head title='Wereldwijnen > mandje'/>
<body>
	<header>
		<v:menu/>
	</header>	
	<h2>Mandje</h2>
	<a href='<c:url value = "/"/>'>Terug naar overzicht</a>
	<c:if test="${empty bestelbon}">
		<h2>Geen wijnen gevonden in mandje</h2>
	</c:if>
	<c:if test="${not empty bestelbon}">	
		<div>
			<table>
				<tr>
					<th>Wijn</th>
					<th>Prijs</th>
					<th>Aantal</th>
					<th>Te betalen</th>
				</tr>
				<c:forEach var="bestelbonlijn" items="${bestelbon.bestelbonlijnen}">
					<tr>								
						<td>${bestelbonlijn.wijn}</td>								
						<td class="number">€<fmt:formatNumber value='${bestelbonlijn.wijn.prijs}' minFractionDigits='2'/> </td>
						<td class="number">${bestelbonlijn.aantal}</td>
						<td class="number">€<fmt:formatNumber value='${bestelbonlijn.teBetalen}' minFractionDigits='2'/></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3"></td>
					<td>Totaal €<fmt:formatNumber value='${bestelbon.totaal}' minFractionDigits='2'/></td>
				</tr>
			</table>
		</div>	
		<div>
			<form method="post">
				<label>Naam<span>${fouten.naam}</span>
					<input name='naam' value='${param.naam}' required >
				</label>
				
				<label>Straat<span>${fouten.straat}</span>
					<input name='straat' value='${param.straat}' required >
				</label>
				
				<label>Huisnummer<span>${fouten.huisNr}</span>
					<input name='huisNr' value='${param.huisNr}' required >
				</label>
				
				<label>Postcode<span>${fouten.postCode}</span>
					<input name='postCode' value='${param.postCode}' required >
				</label>
				
				<label>Gemeente<span>${fouten.gemeente}</span>
					<input name='gemeente' value='${param.gemeente}' required >
				</label>
				
				<label>
					<input name="bestelwijze" value="0" type="radio" id="food"	${param.bestelwijze == "0" ? "checked" : "" }>
					Afhalen
				</label>
				<label>
					<input name="bestelwijze" value="1" type="radio" id="food"	${param.bestelwijze == "1" ? "checked" : "" }>
					Opsturen<span>${fouten.bestelwijze}</span>
				</label>
				<label><input type='submit' value='Als bestelbon bevestigen'><span>${fouten.bestelling}</span></label>
			
			</form>
		</div>				
	</c:if>
</body>
</html>