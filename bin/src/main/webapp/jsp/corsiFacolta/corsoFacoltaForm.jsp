<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Inserimento\Rimozione Facoltà</title>
</head>
<%@ include file="/jsp/templates/header.jspf" %>

<!-- Contenuto pagina centrale -->
<div class="container-fluid">

	<!-- Content Row -->
	<div class="row">
	
	<!-- Content Column -->
		<div class="col-lg-12 mb-4">
			<!-- Approach -->

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
					<form:form action="${pageContext.request.contextPath }/inserimentoFacolta" method="post" modelAttribute="newFacolta">
						<label path="facolta">Facoltà: </label><br> 
						<form:input path="facolta"/><br>
							<input class="btn btn-success btn-icon-split"
							type="submit" value="Inserisci"/>
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
					<form:form action="${pageContext.request.contextPath }/corso-facolta" method="post" >
					
						<label for="facoltaId">Facolta: </label>
  						<select name="facoltaId">
  						<c:forEach items="${facolta}" var="f">
  						<option value="${f.id}"> 
  						${f.facolta}
  						 </option>
  			
  						</c:forEach>
  						</select>
  						<label for="corsoId">Corso</label>
  						<select name="corsoId">
						<c:forEach items="${corsi}" var="c">
  						<option value="${c.id}"> 
  						${c.corso}
  						 </option>
  			
  						</c:forEach>
  						</select> <br>
  						
						 <input class="btn btn-success btn-icon-split"
							type="submit" value="Aggiungi"/>
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
					
				</div>
			</div>
		</div>
		<!-- End Content Column -->


	<!-- /.container-fluid -->

<!-- Fine Contenuto pagina centrale -->


<%@ include file="/jsp/templates/footer.jspf" %>