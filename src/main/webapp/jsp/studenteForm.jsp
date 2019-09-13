<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Inserimento Studente</title>
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
                  <h6 class="m-0 font-weight-bold text-primary">Inserimento Studente</h6>
                </div>
                <div class="card-body">
					
                <!-- INSERIRE CONTENUTO -->
					<form:form modelAttribute="studente" action="Studente" method="post">
						<form:label path="nome">Nome:</form:label><br> 
						<form:input id="nome" path="nome"/><br> 
						<form:errors path="nome" cssStyle="color:red"/><br>
						
						<form:label path="cognome">Cognome:</form:label><br> 
						<form:input id="cognome" path="cognome"/><br>
						<form:errors path="cognome" cssStyle="color:red"/><br>

						<form:label path="dataNascita">Data Nascita</form:label><br> 
						<form:input type="date" id="nascita-data" path="dataNascita" /><br>
						<form:errors path="dataNascita" cssStyle="color:red"/><br>
						
						<form:label path="luogoNascita">Luogo Nascita</form:label><br> 
						<form:input id="nascita-luogo" path="luogoNascita" /><br> 
						<form:errors path="luogoNascita" cssStyle="color:red"/><br>
						
						<form:label path="codiceFiscale">Codice Fiscale</form:label><br> 
						<form:input id="fiscale" path="codiceFiscale" /><br> 
						<form:errors path="codiceFiscale" cssStyle="color:red"/><br>
						
						<form:label path="titoliDiStudio">Titoli Di Studio</form:label><br> 
						<form:input path="titoliDiStudio" /><br>
						<form:errors path="titoliDiStudio" cssStyle="color:red"/><br>
						
						<form:label path="uomo">Sesso:</form:label><br>
						<form:radiobutton id="uomo" path="uomo" value="true" label="Uomo"/> <br>
						<form:radiobutton id="sesso" path="uomo" value="false" label="Donna"/><br> 

						<label for="selFacolta">Facoltà: </label><br> 
						<select id="selFacolta" name="selFacolta">
							<c:forEach items="${facolta}" var="fac">
								<option value="${fac.id}">${fac.facolta}</option>
							</c:forEach>
						</select><br> 
						
						<form:label path="account.username">Username: </form:label><br> 
						<form:input id="username" path="account.username"/><br> 
						<form:errors path="account.username" cssStyle="color:red"/><br>
						
						<form:label path="account.password">Password: </form:label><br> 
						<form:password id="password" path="account.password"/><br>
						<form:errors path="account.password" cssStyle="color.red"/><br>

						<input class="btn btn-success btn-icon-split" type="submit" value="Registrati"/>
					</form:form>
					<!-- CONTENUTO -->

				</div>
              </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- Fine Contenuto pagina centrale -->
      
      
<%@ include file="/jsp/templates/footer.jspf" %>