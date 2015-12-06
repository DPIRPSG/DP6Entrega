<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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
	name="warehouses" requestURI="${requestURI}" id="row">
	<!-- Action links -->

	<!-- Attributes -->
	<spring:message code="warehouse.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="warehouse.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}"
		sortable="true" />

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="storage/administrator/list.do?warehouseId=${row.id}"> <spring:message
					code="warehouse.storage" />
			</a>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('CLERK')">
		<display:column>
			<a href="storage/clerk/list.do?warehouseId=${row.id}&itemId="> <spring:message
					code="warehouse.storage" />
			</a>
		</display:column>
	</security:authorize>


</display:table>
<!-- Action links -->
