<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  						
  						<label for="facolta">Facolta: </label>
  						<select name="facolta">
  						<c:forEach items="${facolta}" var="f">
  							<option value="${f.id}"> 
  							${f.facolta}
  						 </option>
  			
  						</c:forEach>
  						</select>
  						<label for="corso">Corso</label>
  						<select name="corso">
						<c:forEach items="${corsi}" var="c">
  						<option value="${c.id}"> 
  						${c.corso}
  						 </option>
  			
  						</c:forEach>
  						</select>
  						
  						</select>
  						<label for="professore">Professore</label>
  						<select name="professore">
						<c:forEach items="${professori}" var="p">
  						<option value="${p.id}"> 
  						${p.nome} ${p.cognome} 
  						 </option>
  			
  						</c:forEach>
  						</select>
  					
						
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