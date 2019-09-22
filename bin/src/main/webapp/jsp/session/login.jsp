<%@ include file="/jsp/templates/header.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>  
  <title>Login</title>
</head>

<body class="bg-gradient-primary">

  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
              <div class="col-lg-6">
                <div class="p-5">
                
                	<c:if test="${param.error != null}">
                		Username o Password incorretti
                	</c:if>
                	
					<form:form action="login" method="post">
						<label for="username">Username: </label><br>
						<input type="text" name="username"/><br>
						
						<label for="password">Password: </label><br>
						<input type="password" name="password"/><br>
					
						<input class="btn btn-success btn-icon-split" type="submit" value="Accedi"/>
					</form:form>
					
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="templates/dashboard/vendor/jquery/jquery.min.js"></script>
  <script src="templates/dashboard/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="templates/dashboard/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="templates/dashboard/js/sb-admin-2.min.js"></script>

</body>

</html>




