<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form action="${requestURI}">
	<input type="hidden" name="shoppingCartId" value="${shoppingCartId}"/>
	<select name="exchangeRateId">
		<jstl:forEach var="exchangeRateSel" items="${moneyList}">
			<jstl:if test="${exchangeRateSel.id == exchangeRate.id}">
				<option value="${exchangeRateSel.id}" selected="selected">${exchangeRateSel.name}</option>
			</jstl:if>
			<jstl:if test="${exchangeRateSel.id != exchangeRate.id}">
				<option value="${exchangeRateSel.id}">${exchangeRateSel.name}</option>
			</jstl:if>
		</jstl:forEach>
	</select> <input type="submit" value="<spring:message code="content.change" />" />&nbsp;
</form>

<br/>

<spring:message code="content.exchangeRate" var="message"/>
<jstl:out value="${message}: ${exchangeRate.name} [${exchangeRate.currency}]"/>
<br/>

<security:authorize access="hasRole('CONSUMER')">
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="contents" requestURI="${requestURI}" id="row">
	
	<spring:message code="content.edit" var="editHeader" />
	<display:column title="${editHeader}">
		<a href="content/consumer/edit.do?contentId=${row.id}"> <spring:message
			code="content.edit.url" />
		</a>
	</display:column>

	<!-- Attributes -->
	<spring:message code="content.item.name" var="nameHeader" />
	<display:column title="${nameHeader}"
		sortable="true" >
		<jstl:out value="${row.item.name}"/>
	</display:column>
		
	<spring:message code="content.units" var="unitsHeader" />
	<display:column title="${unitsHeader}" 
		sortable="false" >
		<jstl:out value="${row.units}"/>
	</display:column>
	
	<spring:message code="content.item.description"
		var="descriptionHeader" />
	<display:column title="${descriptionHeader}"
		sortable="false" >
		<jstl:out value="${row.item.description}"/>
	</display:column>

	<spring:message code="content.item.price" var="priceHeader" />
	<display:column title="${priceHeader}"
		sortable="true" >
		<jstl:out value="${row.item.price * exchangeRate.rate}"/>
	</display:column>
	
	</display:table>
	
	<!-- Action Links -->
	<div>
		<b><a
			href="order/consumer/create.do" >
				<spring:message code="content.checkout" />
		</a></b>
	</div>

	
</security:authorize>