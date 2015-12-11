<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Form -->
<form:form action="order/consumer/edit.do" modelAttribute="order">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="ticker">
		<spring:message code="order.ticker" />:
	</form:label>
	<form:label path="ticker" />
	<br />
	
	<form:label path="placementMoment">
		<spring:message code="order.placementMoment" />:
	</form:label>
	<form:label path="placementMoment" />
	<br />
	
	<form:label path="deliveryMoment">
		<spring:message code="order.deliveryMoment" />:
	</form:label>
	<form:label path="deliveryMoment" />
	<br />
	
	<form:label path="cancelMoment">
		<spring:message code="order.cancelMoment" />:
	</form:label>
	<form:label path="cancelMoment" />
	<br />
	
	<form:label path="amount">
		<spring:message code="order.amount" />:
	</form:label>
	<form:label path="amount" />
	<br />
	
	<form:label path="clerk">
		<spring:message code="order.clerk" />:
	</form:label>
	<form:label path="clerk" />
	<br />
	
	<form:label path="consumer">
		<spring:message code="order.consumer" />:
	</form:label>
	<form:label path="consumer" />
	<br />
	
	<!-- Action buttons -->
	<input type="submit" name="delete"
		value="<spring:message code="order.delete" />" />&nbsp; 
	<jstl:if test="${order.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="item.delete" />"
			onclick="return confirm('<spring:message code="item.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="order.cancel" />"
		onclick="javascript: relativeRedir('order/consumer/list.do');" />
	<br />

</form:form>