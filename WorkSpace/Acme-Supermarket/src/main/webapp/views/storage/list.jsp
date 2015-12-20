<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
	
<!-- Listing grid -->
<display:table pagesize="5" class="displaytag"
	name="storages" requestURI="${requestURI}" id="row">
	<!-- Action links -->
	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="storage.edit" var="editHeader" />
		<display:column>
			<a href="storage/administrator/edit.do?storageId=${row.id}"> <spring:message
					code="storage.edit" />
			</a>
		</display:column>
	</security:authorize>
	<!-- Attributes -->
	
	<spring:message code="storage.units" var="unitsHeader" />
	<display:column property="units" title="${unitsHeader}" sortable="false" />

	<jstl:if test="${byWarehouse}">

		<spring:message code="storage.item.name" var="nameHeader" />
		<display:column property="item.name" title="${nameHeader}"
			sortable="true" />

		<spring:message code="storage.item.description"
			var="descriptionHeader" />
		<display:column property="item.description"
			title="${descriptionHeader}" sortable="false" />

		<spring:message code="storage.item.price" var="priceHeader" />
		<display:column property="item.price" title="${priceHeader}"
			sortable="true" />

	</jstl:if>

	<jstl:if test="${byItem}">

		<spring:message code="storage.warehouse.name" var="nameHeader" />
		<display:column property="wareHouse.name" title="${nameHeader}"
			sortable="true" />
			
		<spring:message code="storage.warehouse.address" var="addressHeader" />
		<display:column property="wareHouse.address" title="${addressHeader}"
			sortable="true" />

	</jstl:if>

</display:table>

<!-- Action links -->

<security:authorize access="hasRole('ADMIN')">
	<div>
		<b><a
			href="storage/administrator/create.do?warehouseId=${warehouseId}">
				<spring:message code="storage.create" />
		</a></b>
	</div>
</security:authorize>
