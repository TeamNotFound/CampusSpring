<html>
<head>
<title>Inserimento Facoltà</title>
</head>
<%@ include file="/jsp/templates/header.jspf" %>

<!-- Contenuto pagina centrale -->
<div class="container-fluid">

	<!-- Content Row -->
	<div class="row">

		<!-- Content Column -->
		<div class="col-lg-12 mb-4">
			<!-- Approach -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Inserimento
						Facoltà</h6>
				</div>
				<div class="card-body">

					<!-- INSERIRE CONTENUTO -->
					<form:form action="${pageContext.request.contextPath }/inserimentoFacolta" method="post">
						<label path="facolta">Facoltà: </label><br> 
						<form:input path="facolta"  /><br>
							<form:input class="btn btn-success btn-icon-split"
							type="submit" />
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
					<h6 class="m-0 font-weight-bold text-primary">Inserimento Corsi</h6>
				</div>
				<div class="card-body">

					<!-- INSERIRE CONTENUTO -->
					<form:form  action="${pageContext.request.contextPath }/inserimentoCorso" method="post">
						<form:label path="corso">Corso: </form:label><br> 
						<form:input path="corso"/><br>
						<form:errors path="corso" />
						
							<input class="btn btn-success btn-icon-split" type="submit" />
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
					<h6 class="m-0 font-weight-bold text-primary">Aggiungi corsi a
						Facoltà</h6>
				</div>
				<div class="card-body">

					<!-- INSERIRE CONTENUTO -->
					<form:form action="CorsiFacolta" method="post">
					
						<form:label path="facolta">Facolta: </form:label>
  						<form:select path="facolta">
  						<form:options items="${facolta}"/>
  						</form:select>
						
							<form:label path="corsi">Corsi: </form:label>
  						<form:select path="corsi">
  						<form:options items="${corsi}"/>
  						</form:select>
  						
						 <input class="btn btn-success btn-icon-split"
							type="submit" />
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
					<h6 class="m-0 font-weight-bold text-primary">Rimozione Corsi o Facoltà</h6>
				</div>
				<div class="card-body">

					<!-- INSERIRE CONTENUTO -->

					<table>
						<tr>
							<th>Facolta</th>
							<th>Elimina</th>
						</tr>
						<c:forEach items="${facolta}" var="fac">
							<tr>
								<td><a href="Facolta/${fac.id}">${fac.facolta}</a></td>
								<td><a href="rimuoviFacolta/${fac.id}">Rimuovi</a></td>
							</tr>
						</c:forEach>
					</table>
					
					<br>

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



	</div>
	<!-- /.container-fluid -->

</div>
<!-- Fine Contenuto pagina centrale -->


<%@ include file="/jsp/templates/footer.jspf" %>