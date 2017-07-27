<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/general/template_top.jsp" />

<script type="text/javascript">
	function buscarNombre() {
		$.post('/trackandbug/proyectos/listado/buscarproyectos.html', {
			nombreBuscado : $("#buscador2").val()
		}, function(data) {
			$("#tablaProyectos").html(data);
		});
	}

	$(document).ready(function() {
		$.get('/trackandbug/proyectos/listado/listarproyectos.html',
				function(respuesta) {
					$('#tablaProyectos').html(respuesta);
				});
		$("#buscador2").keyup(function(e) {
			buscarNombre();
		});
	});
</script>

<div class='page-header'>

	<c:choose>
		<c:when test="${not empty todosUsuarios}">
			<c:choose>
				<c:when test="${not empty usuariosPrincipales}">
					<c:choose>
						<c:when test="${not empty usuarios}">
							<div class='btn-toolbar pull-right'>
								<div class='btn-group'>
									<a href="/trackandbug/proyectos/nuevoproyecto.html"
										class="btn btn-default">Nuevo Proyecto</a>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<h4 class="pull-right">No hay usuarios Comunes disponibles
								para agregar a otro Proyecto.</h4>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<h4 class="pull-right">No hay usuarios Principales disponibles
						para agregar a otro Proyecto.</h4>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<h4 class="pull-right">No hay usuarios disponibles para agregar
				a otro Proyecto.</h4>

		</c:otherwise>
	</c:choose>

	<h2>Listado de proyectos</h2>
</div>
<div class="grid">
	<c:choose>
		<c:when test="${not empty proyectos}">
			<div class="row">
				<div class="col-md-3">
					<input type="text" class="form-control" placeholder="Buscar..."
						id="buscador2" />
				</div>
			</div>

			<div id="tablaProyectos" class="tablas"></div>
			<button type="button" class="btn btn-primary"
				onclick="location.href='/trackandbug/proyectos/ListadoProyectosExcel.html?output=excel'">Descargar
				Listado de Proyectos</button>

		</c:when>
		<c:otherwise>
			<h4>No hay proyectos disponibles.</h4>
		</c:otherwise>
	</c:choose>
</div>

<c:import url="/general/template_bottom.jsp" />