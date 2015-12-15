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

<security:authorize access="hasAnyRole('ADMIN', 'CLERK', 'CONSUMER')">
	<!-- Listing grid -->
	<display:table pagesize="5" class="displaytag" keepStatus="true"
		name="messa" requestURI="${requestURI}" id="row">

		<spring:message code="message.display" var="displayHeader" />
		<display:column>
			<a href="message/actor/display.do?messageId=${row.id}"> 
				<spring:message code="message.display" />
			</a>
		</display:column>
		
		<spring:message code="message.delete" var="deleteHeader" />
		<display:column>
			<a href="message/actor/delete.do?messageId=${row.id}&folderId=${folder.id}"> 
				<spring:message code="message.delete" />
			</a>
		</display:column>

		<!-- Attributes -->
		<spring:message code="message.moment" var="momentHeader" />
		<display:column property="moment" title="${momentHeader}"
			sortable="true" format="{0,date,yyyy/MM/dd }" />
		
		<spring:message code="message.subject" var="subjectHeader" />
		<display:column property="subject" title="${subjectHeader}"
			sortable="true" />
			
	</display:table>
	
	<!-- Action links -->
	<div>
		<b><a href="message/actor/create.do"> 
			<spring:message code="message.create" />
		</a></b>
	</div>

</security:authorize>