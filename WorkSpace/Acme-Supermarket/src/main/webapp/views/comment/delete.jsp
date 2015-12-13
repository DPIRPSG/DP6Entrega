<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="comment.item"/> <jstl:out value="${item.name}" />
<!-- Form -->
<form:form action="comment/administrator/delete.do" modelAttribute="comment">
	<!-- Hidden Attributes -->
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="item"/>
	
	<!-- Shown Attributes -->
	<form:hidden path="userName"/>
	<spring:message code = "comment.userName"/>: <jstl:out value="${comment.userName}" />
	<br />
	
	<form:hidden path="title"/>
	<spring:message code = "comment.title"/>: <jstl:out value="${comment.title}" />
	<br />
				
	<form:hidden path="text"/>
	<spring:message code = "comment.text"/>: <jstl:out value="${comment.text}" />
	<br />
	
	<form:hidden path="rating"/>
	<spring:message code = "comment.rating"/>: <jstl:out value="${comment.rating}" />
	<br />
	
	<!-- Action buttons -->
	<input type="submit" name="delete"
			value="<spring:message code="comment.delete" />"
			onclick="return confirm('<spring:message code="comment.confirm.delete" />')" />
	&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="comment.delete.cancel" />"
		onclick="javascript: relativeRedir('/comment/list.do?itemId=${item.id}');" />
	
</form:form>