<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

security !!!!!
<!-- Form -->
<form:form action="wareHouse/administrator/edit.do" modelAttribute="wareHouse">
	<!-- Hidden Attributes -->
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="name">
		<spring:message code="wareHouse.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="address">
		<spring:message code="wareHouse.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	
	<!-- Action buttons -->
	<input type="submit" name="save"
		value="<spring:message code="wareHouse.save" />" />&nbsp; 
	<jstl:if test="${wareHouse.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="wareHouse.delete" />"
			onclick="return confirm('<spring:message code="wareHouse.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="wareHouse.cancel" />"
		onclick="javascript: relativeRedir('wareHouse/administrator/list.do');" />
	<br />

</form:form>