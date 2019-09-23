<%@ include file="/jsp/templates/header.jspf"%>
<img src="${profilePic}" class="thumbnail" alt="Image not found"></img>

<div class="container-fluid">
	<form:form action="${pageContext.request.contextPath}/ProfilePic"
		method="post" enctype="multipart/form-data">
		<label for="image">Nuova immagine: </label><br>
		<input type="file" name="image" />
		<input type="submit" />
	</form:form>

</div>
<c:if test="${error!=null}">
<div style="color: red">
    <br>&nbsp;&nbsp;&nbsp;ERRORE! Carica una foto più piccola di 128KB!
</div>
</c:if>
<%@ include file="/jsp/templates/footer.jspf"%>