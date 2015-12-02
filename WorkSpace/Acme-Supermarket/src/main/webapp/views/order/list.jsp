<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="orders" requestURI="${requestURI}" id="row">
		
		<!-- Attributes -->
		<spring:message code="order.ticker" var="tickerHeader" />
		<display:column property="ticker" title="${tickerHeader}" sortable="false" />
		
		<spring:message code="order.placementMoment" var="placementMomentHeader" />
		<display:column property="placementMoment" title="${placementMomentHeader}" sortable="true" format="{0,date,yyyy/MM/dd }"/>
		
		<spring:message code="order.deliveryMoment" var="deliveryMomentHeader" />
		<display:column property="deliveryMoment" title="${deliveryMomentHeader}" sortable="true" format="{0,date,yyyy/MM/dd }"/>
		
		<spring:message code="order.cancelMoment" var="cancelMomentHeader" />
		<display:column property="cancelMoment" title="${cancelMomentHeader}" sortable="true" format="{0,date,yyyy/MM/dd }"/>
		
		<spring:message code="order.amount" var="amountHeader" />
		<display:column property="amount" title="${amountHeader}" sortable="true" />
		
		<spring:message code="order.clerk" var="clerkHeader" />
		<display:column property="clerk" title="${clerkHeader}" sortable="false" />
		
		<spring:message code="order.consumer " var="consumerHeader" />
		<display:column property="consumer" title="${consumerHeader}" sortable="false" />
		
	</display:table>
		
</security:authorize>