<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Inserimento Professore</title>
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
                  <h6 class="m-0 font-weight-bold text-primary">Inserimento Professore</h6>
                </div>
                <div class="card-body">
					
                <!-- INSERIRE CONTENUTO -->
					<form:form modelAttribute="professore" action="InserimentoProfessore" method="post">

						<form:label path="nome">Nome Professore</form:label><br> 
						<form:input path="nome" /><br>
						<form:errors path="nome" cssStyle="color:red"/><br>
						 
						<form:label path="cognome">Cognome Professore</form:label><br> 
						<form:input path="cognome" /><br>
						<form:errors path="cognome" cssStyle="color:red"/><br>
						 
						<form:label path="dataNascita">Data Nascita</form:label><br> 
						<form:input type="date" path="dataNascita"/><br>
						<form:errors path="dataNascita" cssStyle="color:red"/><br> 
						
						<form:label path="luogoNascita">Luogo Nascita</form:label><br> 
						<form:input path="luogoNascita" /><br>
						<form:errors path="luogoNascita" cssStyle="color:red"/><br>
						 
						<form:label path="codiceFiscale">Codice Fiscale</form:label><br> 
						<form:input path="codiceFiscale" /><br>
						<form:errors path="codiceFiscale" cssStyle="color:red"/><br>
						
						<form:label path="sesso">Sesso:</form:label><br>
						<form:radiobutton path="sesso" value="true" label="Uomo" checked="true"/><br>
						<form:radiobutton path="sesso" value="false" label="Donna"/><br>

						<form:label path="titoliDiStudio">Titoli Di Studio</form:label><br> 
						<form:input path="titoliDiStudio" /><br>
						<form:errors path="titoliDiStudio" cssStyle="color:red"/><br>

						<hr>

						<form:label path="username">Username</form:label><br>
						<form:input path="username"/><br>
						<form:errors path="username" cssStyle="color:red"/><br>
						
						<form:label path="password">Password</form:label><br>
						<form:password path="password"/><br>
						<form:errors path="password"/><br>
						
						<input class="btn btn-success btn-icon-split" type="submit" />

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