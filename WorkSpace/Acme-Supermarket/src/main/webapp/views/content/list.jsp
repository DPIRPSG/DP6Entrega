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
		name="shoppingCarts" requestURI="${requestURI}" id="row">

	<!-- Attributes -->
	<spring:message code="content.units" var="unitsHeader" />
	<display:column property="units" title="${unitsHeader}" sortable="false" />
	
	<jstl:if test="${byShoppingCart}">

		<spring:message code="content.item.name" var="nameHeader" />
		<display:column property="item.name" title="${nameHeader}"
			sortable="true" />

		<spring:message code="content.item.description"
			var="descriptionHeader" />
		<display:column property="item.description"
			title="${descriptionHeader}" sortable="false" />

		<spring:message code="content.item.price" var="priceHeader" />
		<display:column property="item.price" title="${priceHeader}"
			sortable="true" />

	</jstl:if>
	
	<jstl:if test="${byItem}">

		<spring:message code="content.shoppingCart.comment" var="commentHeader" />
		<display:column property="shoppingCart.comment" title="${commentHeader}"
			sortable="false" />

	</jstl:if>

	
	</display:table>
	
</security:authorize>