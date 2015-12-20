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
	name="consumers" requestURI="${requestURI}" id="row">

	<!-- Attributes -->
	<spring:message code="consumer.username" var="userNameHeader" />
	<display:column property="userAccount.username"
		title="${userNameHeader}" sortable="true" />

	<spring:message code="consumer.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="consumer.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}"
		sortable="true" />

	<spring:message code="consumer.phone" var="phoneHeader" />
	<display:column property="phone" title="${phoneHeader}" sortable="true" />

	<spring:message code="consumer.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" sortable="true" />

</display:table>

