<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>Gestione Professori</title>
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
                  <h6 class="m-0 font-weight-bold text-primary">Gestione Professori</h6>
                </div>
                <div class="card-body">
					
                <!-- INSERIRE CONTENUTO -->
					<table>
						<tr>
							<th>Professore</th>
							<th>Rimuovi</th>
							<th>Promuovi</th>
						</tr>

						<c:forEach items="${professori}" var="p">
							<tr>
								<td>${p.nome} ${p.cognome}</td>
								
								<td><c:if test="${p.account.ruolo.ruolo eq 'PROFESSORE'}">
										<a href="${pageContext.request.contextPath}/Professore/Rimuovi/${p.id}">Rimuovi</a>

									</c:if></td>
								<td><c:if test="${p.account.ruolo.ruolo eq 'PROFESSORE'}">

										<a href="${pageContext.request.contextPath}/Professore/Promuovi/${p.id}">Promuovi</a>

									</c:if></td>
								
							</tr>
						</c:forEach>
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