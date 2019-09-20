<html>
<head>
<title>Aggiungi corsi a Facoltà</title>
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
                  <h6 class="m-0 font-weight-bold text-primary">Aggiungi corsi a Facoltà</h6>
                </div>
                <div class="card-body">
					
                <!-- INSERIRE CONTENUTO -->
					<h1>${facolta.facolta}</h1>
					<table>
						<tr>
							<th>Corso</th>
							<th>Professore</th>
							<th>Rimuovi corso</th>
							<th>Libera cattedra</th>
						</tr>
						<c:forEach items="${facolta.corsi}" var="cor">
							<tr>
								<td>${cor.corso }</td>
								<td>${cattedre.get(cor.id).professore.fullName}</td>
								<td><a
									href="${pageContext.request.contextPath}/rimuoviFacoltaCorso/${facolta.id}-${cor.id}">Rimuovi</a></td>
								<td><a
									href="${pageContext.request.contextPath}/rimuoviCattedra/${cattedre.get(cor.id).corso.id}-${cattedre.get(cor.id).professore.id}-${cattedre.get(cor.id).facolta.id}">Libera
										cattedra</a></td>
							</tr>
						</c:forEach>

						<form:form action="${pageContext.request.contextPath }/corso-facolta" method="post">

							<input type="hidden" name="facoltaId" value="${facolta.id}">

							<select name="corsoId">
								<c:forEach items="${corsi}" var="c">
									<option value="${c.id}">${c.corso}</option>
								</c:forEach>
							</select> 
							<input class="btn btn-success btn-icon-split" type="submit" />
						</form:form>
					</table>

					<!-- CONTENUTO -->

				</div>
              </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- Fine Contenuto pagina centrale -->
      
      
<%@ include file="/jsp/templates/footer.jspf" %>