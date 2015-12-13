<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="welcome.greeting.prefix" />

	<security:authorize access="isAnonymous()">
		<spring:message code="welcome.greeting.middle" />
	</security:authorize>

	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal.username" />
	</security:authorize>
	<spring:message code="welcome.greeting.suffix" /></p>

<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 

<!-- Listing grid -->
<security:authorize access="hasRole('CONSUMER')">

	<h3><spring:message code="consumer.item.bestSelling" />:</h3>
	
	<p><spring:message code="consumer.item.category" />: <jstl:out value="${item.category.name}" /></p>
	<p><spring:message code="consumer.item.name" />: <jstl:out value="${item.name}" /></p>
	<p><spring:message code="consumer.item.price" />: <jstl:out value="${item.price}" /></p>
	<p><spring:message code="consumer.item.description" />: <jstl:out value="${item.description}" /></p>
	<p><spring:message code="consumer.item.tags" />: <jstl:out value="${item.tags}" /></p>
	<p><spring:message code="consumer.item.picture" />: <img src="${item.picture}" /></p>
	<p>
		<a href="comment/list.do?itemId=${item.id}">
			<spring:message code="consumer.item.comments" />
		</a>
	</p>
	
	<a href="item/consumer/add.do?itemId=${item.id}&?keyword=">
		<spring:message code="consumer.item.add" />
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
