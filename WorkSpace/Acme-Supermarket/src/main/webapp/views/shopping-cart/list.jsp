<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CONSUMER')">
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="shoppingCarts" requestURI="${requestURI}" id="row">

	<!-- Attributes -->
	<spring:message code="shoppingCart.comments" var="commentsHeader" />
	<display:column property="comments" title="${commentsHeader}" sortable="false" />
	
	<display:column>
			<a href="content/consumer/list.do?shoppingCartId=${row.id}&itemId="> <spring:message
					code="shoppingCart.contents" />
			</a>
	</display:column>
	
	</display:table>
	
	<!-- Action links -->
	
</security:authorize>