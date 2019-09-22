<html>
<head>
<title>Prenotazioni effettuate</title>
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
                  <h6 class="m-0 font-weight-bold text-primary">Prenotazioni effettuate</h6>
                </div>
                <div class="card-body">
					
                <!-- INSERIRE CONTENUTO -->
					<table>
						<tr>
							<th>Corso</th>
							<th>Professore</th>
							<th>Data</th>
						</tr>
						<c:forEach items="${prenotazioni}" var="p">
							<tr>
								<td>${p.dataAppello.corso.corso}</td>
								<td>${p.dataAppello.professore.cognome}
									${p.dataAppello.professore.nome}</td>
								<td>${p.dataAppello.dataAppello }
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