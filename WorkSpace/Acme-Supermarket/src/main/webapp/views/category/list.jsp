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
	name="categories" requestURI="${requestURI}" id="row">
		<!-- Action links -->
		<display:column>
			<a href="category/edit.do?categoryId=${row.id}">
				<spring:message	code="category.list.edit" />
			</a>
		</display:column>
		<!-- Attributes -->
		<spring:message code="category.list.name" var="nameHeader" />
		<display:column property="name" title="${nameHeader}" sortable="true" />
		
		<spring:message code="category.list.description" var="descriptionHeader" />
		<display:column property="description" title="${descriptionHeader}" sortable="true" />
		
		<spring:message code="category.list.picture" var="pictureHeader" />
		<display:column>
			<img src="${pictureHeader}" />
		</display:column>
		
		<spring:message code="category.list.taxName" var="taxNameHeader" />
		<display:column property="taxName" title="${taxNameHeader}" sortable="true" />
		
		<spring:message code="category.list.taxValue" var="taxValueHeader" />
		<display:column property="taxValue" title="${taxValueHeader}" sortable="true" />
		
</display:table>
<!-- Action links -->
<div>
	<a href="item/administrator/create.do"> <spring:message
			code="category.list.create" />
	</a>
</div>
	
</security:authorize>	
	



