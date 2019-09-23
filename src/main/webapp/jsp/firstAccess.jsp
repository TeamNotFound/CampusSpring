<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="it">

<head>
<%@ include file="/jsp/templates/header.jspf" %>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Primo Accesso al Portale</title>

  <!-- Custom fonts for this template-->
  <link href="/jsp/templates/dashboard/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="/jsp/templates/dashboard/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body class="bg-gradient-primary">
  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Primo Accesso? Registrati come rettore!</h1>
              </div>

				<form:form class="user" action="FirstAccess" method="post" modelAttribute="professore"> 
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <form:input  class="form-control form-control-user" path="nome" id="nome" placeholder="Nome Professore"/>
                    <form:errors path="nome"/>
                  </div>
                  <div class="col-sm-6">
                    <form:input class="form-control form-control-user" path="cognome" id="cognome" placeholder="Cognome"/>
                    <form:errors path="cognome"/>
                  </div>
                </div>

                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <form:input type="date"  id="nascita-data" path="dataNascita" class="form-control form-control-user" placeholder="Data Nascita"/>

                  </div>
                  <div class="col-sm-6">
                    <form:input class="form-control form-control-user" path="luogoNascita" id="nascita-luogo" placeholder="Luogo Nascita"/>
                  	<form:errors path="luogoNascita"/>
                  </div>
                </div>

                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <form:input  class="form-control form-control-user" id="fiscale" path="codiceFiscale" placeholder="Codice Fiscale"/>
                 	<form:errors path="codiceFiscale"/>
                  </div>
                  
                </div>

                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0"> <label for="sesso">Sesso:</label><br>

                  <form:radiobutton id="uomo" path="uomo" value="true" checked="checked"/>
                  <label for="uomo">Uomo</label>

                  <form:radiobutton id="sesso" path="uomo" value="false"/>
                  <label for="donna">Donna</label><br>
                   </div>
                  
                </div>



                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <form:input class="form-control form-control-user" id="titoli" path="titoliDiStudio" placeholder="Titoli di studio"/>
                  	<form:errors path="titoliDiStudio"/>
                  	<form:hidden path="rettore" value="true"/>
                  </div>
                  
                </div>

                 <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <form:input class="form-control form-control-user" id="username" path="account.username" placeholder="Username"/>
                  	<form:errors path="account.username"/>
                  </div>
                  <div class="col-sm-6">
                    <form:password  class="form-control form-control-user" id="password" path="account.password" placeholder="Password"/>
                  	<form:errors path="account.password"/>
                  </div>
                  
                </div>
              <input class="btn btn-success btn-icon-split" type="submit" value="Registrati"/>

              </form:form>
              
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="../Campus/views/templates/dashboard/vendor/jquery/jquery.min.js"></script>
  <script src="../Campus/views/templates/dashboard/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="../Campus/views/templates/dashboard/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="../Campus/views/templates/dashboard/js/sb-admin-2.min.js"></script>
<%@ include file="/jsp/templates/footer.jspf" %>
