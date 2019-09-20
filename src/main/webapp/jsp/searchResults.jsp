<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title>Risultati Facolta</title>
</head>

<%@ include file="/jsp/templates/header.jspf" %>
	<c:forEach items="${searchResults}" var="facolta">
		<a href="${pageContext.request.contextPath}/Facolta/${facolta.id}" >${facolta.facolta}</a>
	</c:forEach>
<%@ include file="/jsp/templates/footer.jspf" %>