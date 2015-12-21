<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
	S
<h3><spring:message code="comment.itemPlural"/> <jstl:out value="${item.name}(${item.sku})" /></h3>
<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="comments" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="comment/administrator/delete.do?commentId=${row.id}">
				<spring:message	code="comment.delete" />
			</a>
		</display:column>		
	</security:authorize>
	
	<!-- Attributes -->
	<jstl:choose>
  		<jstl:when test="${row.userName != 'Anonymous'}">
			<spring:message code="comment.userName" var="userNameHeader" />
			<display:column title="${userNameHeader}"
				sortable="true" >
				<jstl:out value="${row.userName}"/>
			</display:column>
		</jstl:when>
  		<jstl:otherwise>
		<display:column title="${userNameHeader}" sortable="true"><spring:message code="comment.anonymous"/></display:column>
		</jstl:otherwise>
	</jstl:choose>
	
	<spring:message code="comment.title" var="titleHeader" />
	<display:column title="${titleHeader}"
		sortable="false" >
		<jstl:out value="${row.title}"/>
	</display:column>

	<spring:message code="comment.text" var="textHeader" />
	<display:column title="${textHeader}" 
		sortable="false" >
		<jstl:out value="${row.text}"/>
	</display:column>

	<spring:message code="comment.rating" var="ratingHeader" />
	<display:column title="${ratingHeader}" 
		sortable="true" >
		<jstl:out value="${row.rating}"/>
	</display:column>
		
</display:table>

<!-- Action links -->
<div>
	<a href="comment/create.do?itemId=${item.id}"> <spring:message
			code="comment.create" />
	</a>
</div>