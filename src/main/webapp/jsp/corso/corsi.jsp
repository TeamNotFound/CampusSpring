<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Inserimento\Rimozione Corsi</title>
</head>
<%@ include file="/jsp/templates/header.jspf" %>
<!-- Content Column -->
		<div class="col-lg-12 mb-4">
			<!-- Approach -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Inserimento Corsi</h6>
				</div>
				<div class="card-body">

					<!-- INSERIRE CONTENUTO -->
					<form:form  action="${pageContext.request.contextPath }/inserimentoCorso" method="post" modelAttribute="newCorso">
						<form:label path="corso">Corso: </form:label><br> 
						<form:input path="corso"/><br>
						<form:errors path="corso"/>
						
							<input class="btn btn-success btn-icon-split" type="submit" value="Inserisci"/>
					</form:form>
					<!-- CONTENUTO -->

				</div>
			</div>
		</div>
		<!-- End Content Column -->
<!-- Content Column -->
		<div class="col-lg-12 mb-4">
			<!-- Approach -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Rimozione Corsi</h6>
				</div>
				<div class="card-body">

					<!-- INSERIRE CONTENUTO -->

					<table>
						<tr>
							<th>Corsi</th>
							<th>Elimina</th>
						</tr>
						<c:forEach items="${corsi}" var="cor">
							<tr>
								<td>${cor.corso}</td>
								<td><a href="rimuoviCorso/${cor.id}">Rimuovi</a></td>
							</tr>
						</c:forEach>
					</table>


					<!-- CONTENUTO -->

				</div>
			</div>
		</div>
		<!-- End Content Column -->
		
<%@ include file="/jsp/templates/footer.jspf" %>