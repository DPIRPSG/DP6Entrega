<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CONSUMER')">
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="contents" requestURI="${requestURI}" id="row_Content">
	
	<spring:message code="content.edit" var="editHeader" />
	<display:column title="${editHeader}">
		<a href="content/consumer/edit.do?contentId=${row_Content.id}"> <spring:message
			code="content.edit.url" />
		</a>
	</display:column>

	<!-- Attributes -->
	<spring:message code="content.item.name" var="nameHeader" />
	<display:column property="item.name" title="${nameHeader}"
		sortable="true" />
		
	<spring:message code="content.units" var="unitsHeader" />
	<display:column property="units" title="${unitsHeader}" sortable="false" />
	
	<spring:message code="content.item.description"
		var="descriptionHeader" />
	<display:column property="item.description"
		title="${descriptionHeader}" sortable="false" />

	<spring:message code="content.item.price" var="priceHeader" />
	<display:column property="item.price" title="${priceHeader}"
		sortable="true" />
	
	</display:table>
	
	<!-- Action Links -->
	<div>
		<b><a
			href="order/consumer/create.do" >
				<spring:message code="content.checkout" />
		</a></b>
	</div>

	
</security:authorize>