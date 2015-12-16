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
	name="items" requestURI="${requestURI}" id="row">
	<!-- Action links -->
	<security:authorize access="hasRole('CONSUMER')">
		<display:column>
			<a href="item/consumer/add.do?itemId=${row.id}&?keyword="> <spring:message
					code="item.add" />
			</a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="item/administrator/edit.do?itemId=${row.id}"> <spring:message
					code="item.edit" />
			</a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('CLERK')">
		<display:column>
			<a href="storage/clerk/list.do?warehouseId=&itemId=${row.id}"> <spring:message
					code="item.storage" />
			</a>
		</display:column>
	</security:authorize>


	<!-- Attributes -->
	<spring:message code="item.category" var="categoryHeader" />
	<display:column property="category.name" title="${categoryHeader}"
		sortable="true" />

	<spring:message code="item.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="false" />

	<spring:message code="item.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}"
		sortable="false" />

	<spring:message code="item.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"
		sortable="false" />

	<spring:message code="item.tags" var="tagsHeader" />
	<display:column property="tags" title="${tagsHeader}" sortable="false" />

	<spring:message code="item.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader}" sortable="false" />

	<display:column>
		<a href="comment/list.do?itemId=${row.id}"> <spring:message
				code="item.comments" />
		</a>
	</display:column>

</display:table>


<form action="${requestURI}">
	<input type="text" name="keyword"> <input type="submit"
		value="<spring:message code="item.search" />" />&nbsp;
</form>


<!-- Action links -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="item/administrator/create.do"> <spring:message
				code="item.create" />
		</a>
	</div>
</security:authorize>
