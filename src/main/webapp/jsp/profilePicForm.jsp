<%@ include file="/jsp/templates/header.jspf"%>

<div class="container-fluid">
	<form:form action="${pageContext.request.contextPath}/ProfilePic"
		method="post" enctype="multipart/form-data">
		<label for="image">Nuova immagine: </label><br>
		<input type="file" name="image" />
		<input type="submit" />
	</form:form>

</div>

<%@ include file="/jsp/templates/footer.jspf"%>