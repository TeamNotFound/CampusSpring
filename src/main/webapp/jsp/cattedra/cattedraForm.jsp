<html>
<head>
<title>Assegnazione Professore</title>
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
                  <h6 class="m-0 font-weight-bold text-primary">Assegnazione Professore</h6>
                </div>
                <div class="card-body">
					<form:form action="${pageContext.request.contextPath }/inserimentoCattedra" method="post">
  					
  						<form:label path="professore">Professore: </form:label>
  						<form:select path="professore">
  						<form:options items="${professori}"/>
  						</form:select>
  						
  							<form:label path="facolta">Facolta: </form:label>
  						<form:select path="facolta">
  						<form:options items="${facolta}"/>
  						</form:select>
  						
  							<form:label path="corso">Corso: </form:label>
  						<form:select path="corso">
  						<form:options items="${corsi}"/>
  						</form:select>
  						
  					
						
						<br> <input class="btn btn-success btn-icon-split"
							type="submit" />

					</form:form>
				</div>
              </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- Fine Contenuto pagina centrale -->
      
      
<%@ include file="/jsp/templates/footer.jspf" %>