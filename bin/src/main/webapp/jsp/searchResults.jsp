<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title>Risultati Facolta</title>
</head>

<%@ include file="/jsp/templates/header.jspf"%>

<!-- Contenuto pagina centrale -->
<div class="container-fluid">

	<!-- Content Row -->
	<div class="row">

		<!-- Content Column -->
		<div class="col-lg-12 mb-4">
			<!-- Approach -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Risultati</h6>
				</div>
				<div class="card-body">

					<!-- INSERIRE CONTENUTO -->

					<c:forEach items="${searchResults}" var="facolta">
						<a href="${pageContext.request.contextPath}/Facolta/${facolta.id}"
							class="btn btn-light btn-icon-split"> <span
							class="icon text-gray-600"> <i class="fas fa-university"></i>
						</span> <span class="text">${facolta.facolta}</span>
						</a>
					</c:forEach>

					<!-- CONTENUTO -->

				</div>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->

</div>
<!-- Fine Contenuto pagina centrale -->

<%@ include file="/jsp/templates/footer.jspf"%>