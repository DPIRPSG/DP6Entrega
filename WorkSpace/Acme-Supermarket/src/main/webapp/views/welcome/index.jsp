<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:if test="${messageStatus != Null && messageStatus != ''}">
	<spring:message code="${messageStatus}" var="showAlert" />
	<script>$(document).ready(function(){
	    alert("${showAlert}");
	  });
	</script>

</jstl:if>

<security:authorize access="hasRole('CONSUMER')">
	<form action="${requestURI}">
		<input type="hidden" name="keyword" value="${keyword}" /> <select
			name="exchangeRateId">
			<jstl:forEach var="exchangeRateSel" items="${moneyList}">
				<jstl:if test="${exchangeRateSel.id == exchangeRate.id}">
					<option value="${exchangeRateSel.id}" selected="selected">${exchangeRateSel.name}</option>
				</jstl:if>
				<jstl:if test="${exchangeRateSel.id != exchangeRate.id}">
					<option value="${exchangeRateSel.id}">${exchangeRateSel.name}</option>
				</jstl:if>
			</jstl:forEach>
		</select> <input type="submit" value="<spring:message code="welcome.change" />" />&nbsp;
	</form>

	<br />

	<spring:message code="welcome.exchangeRate" var="message" />
	<jstl:out
		value="${message}: ${exchangeRate.name} [${exchangeRate.currency}]" />
	<br />
</security:authorize>

<p>
	<spring:message code="welcome.greeting.prefix" />

	<security:authorize access="isAnonymous()">
		<spring:message code="welcome.greeting.middle" />!
	</security:authorize>

	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal.username" />!
	</security:authorize>
</p>

<p>
	<spring:message code="welcome.greeting.current.time" />
	${moment}
</p>

<!-- Listing grid -->
<security:authorize access="hasRole('CONSUMER')">

	<h3>
		<spring:message code="consumer.item.bestSelling" />
		:
	</h3>

	<p>
		<spring:message code="consumer.item.category" />
		:
		<jstl:out value="${item.category.name}" />
	</p>
	<p>
		<spring:message code="consumer.item.name" />
		:
		<jstl:out value="${item.name}" />
	</p>
	<p>
		<spring:message code="consumer.item.price" />
		:
		<fmt:formatNumber value="${item.price * exchangeRate.rate}" maxFractionDigits="2" minFractionDigits="2"/>
	</p>
	<p>
		<spring:message code="consumer.item.description" />
		:
		<jstl:out value="${item.description}" />
	</p>
	<p>
		<spring:message code="consumer.item.tags" />
		:
		<jstl:out value="${item.tags}" />
	</p>
	<p>
		<spring:message code="consumer.item.picture" />
		: <img src="${item.picture}" style="width: 204px; height: 128px;" />
	</p>
	<p>
		<a href="comment/list.do?itemId=${item.id}"> <spring:message
				code="consumer.item.comments" />
		</a>
	</p>

	<a href="item/consumer/add.do?itemId=${item.id}&?keyword="> <spring:message
			code="consumer.item.add" />
	</a>

	<!--
	<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="items" requestURI="${requestURI}" id="row">
	
		<display:column>
			<a href="item/consumer/add.do?itemId=${row.id}&?keyword="> <spring:message
					code="consumer.item.add" />
			</a>
		</display:column>
		
		<!- Attributes ->
		<spring:message code="consumer.item.category" var="categoryHeader" />
		<display:column property="category.name" title="${categoryHeader}"
			sortable="true" />
	
		<spring:message code="consumer.item.name" var="nameHeader" />
		<display:column property="name" title="${nameHeader}" sortable="false" />
	
		<spring:message code="consumer.item.price" var="priceHeader" />
		<display:column property="price" title="${priceHeader}"
			sortable="false" />
	
		<spring:message code="consumer.item.description" var="descriptionHeader" />
		<display:column property="description" title="${descriptionHeader}"
			sortable="false" />
	
		<spring:message code="consumer.item.tags" var="tagsHeader" />
		<display:column property="tags" title="${tagsHeader}" sortable="false" />
	
		<spring:message code="consumer.item.picture" var="pictureHeader" />
		<display:column>
			<img src="${pictureHeader}" />
		</display:column>
	
		<display:column>
			<a href="comment/list.do?itemId=${row.id}"> <spring:message
					code="consumer.item.comments" />
			</a>
		</display:column>
	
	</display:table>
-->
</security:authorize>
