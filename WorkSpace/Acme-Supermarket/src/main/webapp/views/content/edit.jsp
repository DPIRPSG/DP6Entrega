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
<form:form action="content/consumer/edit.do" modelAttribute="content">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="item"/>
	<form:hidden path="shoppingCart"/>
	
	<!-- Editable Attributes -->
	<form:label path="units">
		<spring:message code="content.units" />:
	</form:label>
	<form:input path="units" />
	<form:errors cssClass="error" path="units" />
	<br /> <br />
	
	<!-- Action buttons -->
	<input type="submit" name="save"
		value="<spring:message code="content.save" />" />&nbsp;
		
	<input type="submit" name="delete"
		value="<spring:message code="content.delete" />" />&nbsp;
		
	<input type="button" name="cancel"
		value="<spring:message code="item.cancel" />"
		onclick="javascript: relativeRedir('shopping-cart/consumer/list.do');" />
	<br />

</form:form>