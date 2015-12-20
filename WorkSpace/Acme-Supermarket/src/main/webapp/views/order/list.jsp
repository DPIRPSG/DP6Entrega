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

<security:authorize access="hasAnyRole('ADMIN', 'CLERK', 'CONSUMER')">
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="orders" requestURI="${requestURI}" id="row">
		
		<security:authorize access="hasRole('CONSUMER')">
			<spring:message code="order.delete" var="deleteHeader" />
			<jstl:if test="${row.clerk == null && row.cancelMoment == null}">
				<display:column>
					<a href="order/consumer/cancel.do?orderId=${row.id}" onclick="return confirm('<spring:message code="order.cancel.advise" />')"> <spring:message
						code="order.cancel" />
					</a>
				</display:column>	
			</jstl:if>
			<jstl:if test="${row.clerk != null}">
				<display:column title="${deleteHeader}"
					sortable="false" />
			</jstl:if>
		</security:authorize>
		
		<!-- Attributes -->
		<spring:message code="order.ticker" var="tickerHeader" />
		<display:column property="ticker" title="${tickerHeader}"
			sortable="false" />

		<spring:message code="order.placementMoment"
			var="placementMomentHeader" />
		<display:column property="placementMoment"
			title="${placementMomentHeader}" sortable="true"
			format="{0,date,yyyy/MM/dd }" />

		<spring:message code="order.deliveryMoment" var="deliveryMomentHeader" />
		<display:column property="deliveryMoment"
			title="${deliveryMomentHeader}" sortable="true"
			format="{0,date,yyyy/MM/dd }" />

		<spring:message code="order.cancelMoment" var="cancelMomentHeader" />
		<display:column property="cancelMoment" title="${cancelMomentHeader}"
			sortable="true" format="{0,date,yyyy/MM/dd }" />

		<spring:message code="order.amount" var="amountHeader" />
		<display:column property="amount" title="${amountHeader}"
			sortable="true" />

		<security:authorize access="hasRole('CLERK')">
			<spring:message code="order.clerk" var="clerkHeader" />
			<jstl:if test="${row.clerk == null}">
				<display:column sortable = "true"  title="${clerkHeader}">
					<a href="order/clerk/self-assign.do?orderId=${row.id}"> <spring:message
							code="order.self-assign" />
					</a>
				</display:column>
			</jstl:if>
			<jstl:if test="${row.clerk != null}">
				<display:column property="clerk.userAccount.username" title="${clerkHeader}"
					sortable="true" />
			</jstl:if>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<spring:message code="order.clerk" var="clerkHeader" />
			<display:column property="clerk.userAccount.username" title="${clerkHeader}"
					sortable="false" />
		</security:authorize>

		<security:authorize access="!hasAnyRole('CONSUMER')">
			<spring:message code="order.consumer" var="consumerHeader" />
			<display:column property="consumer.userAccount.username" title="${consumerHeader}"
				sortable="false" />
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<spring:message code="order.creditCard.number.list" var="creditCardHeader"/>
			<display:column property="creditCard.number" title="${creditCardHeader}" sortable="false"/>
		</security:authorize>
		
		<security:authorize access="hasRole('CLERK')">
				<display:column>
					<a href="order-item/clerk/list.do?orderId=${row.id}"> <spring:message
						code="order.orderItems" />
					</a>
				</display:column>	
		</security:authorize>
	</display:table>

</security:authorize>