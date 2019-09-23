<%@ include file="/jsp/templates/header.jspf"%>

<div class="container-fluid">
	<img src="${profilePic}" alt="Image not found" width="500px" />
	<form:form action="${pageContext.request.contextPath}/ProfilePic"
		method="post" enctype="multipart/form-data">
		<br><label for="image">Nuova immagine: </label><br>
		<input type="file" name="image" /><br>
		<input type="submit" class="btn btn-success btn-icon-split" />
	</form:form>

</div>

<%@ include file="/jsp/templates/footer.jspf"%>