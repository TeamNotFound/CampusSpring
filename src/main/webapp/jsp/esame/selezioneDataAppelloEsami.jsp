<html>
<head>
<title>Prenotazione Esame</title>
</head>
<%@ include file="/jsp/templates/header.jspf" %>
<c:forEach items="${date}" var="data">
<a href="${pageContext.request.contextPath}/Esami/Data/${data.id}">${data.dataAppello}</a><br>
</c:forEach>
<%@ include file="/jsp/templates/footer.jspf" %>