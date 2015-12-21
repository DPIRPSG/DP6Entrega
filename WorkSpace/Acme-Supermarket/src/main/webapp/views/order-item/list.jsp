<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="orders-item" requestURI="${requestURI}" id="row_OItem">
	
	<!-- Action links -->
	<display:column>
			<a href="order-item/clerk/serve.do?orderItemId=${row_OItem.id}"> <spring:message
					code="orderItem.serve" />
			</a>
	</display:column>

	<!-- Attributes -->
	<spring:message code="orderItem.sku" var="skuHeader" />
	<display:column property="sku" title="${skuHeader}" sortable="true" />

	<spring:message code="orderItem.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
	<spring:message code="orderItem.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" sortable="true" />
	
	<spring:message code="orderItem.units" var="unitsHeader" />
	<display:column property="units" title="${unitsHeader}" sortable="true" />
	
	<spring:message code="orderItem.unitsServed" var="unitsServedHeader" />
	<display:column property="unitsServed" title="${unitsServedHeader}" sortable="true" />

</display:table>

<input type="button" name="cancel"
		value="<spring:message code="orderItem.return" />"
		onclick="javascript: relativeRedir('order/clerk/list.do');" />
	<br />