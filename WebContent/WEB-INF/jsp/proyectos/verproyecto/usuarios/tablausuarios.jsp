<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function() {
			$.post('/trackandbug/proyectos/verproyecto/usuarios/listarusuarios.html',
				$('#idFormUsuariosBusqueda').serialize(), function(
					respuesta) {
					$('#tabla-usuarios').html(respuesta);
				});

			$("#idNombreBuscado").keyup(function() {
					$.post(
						'/trackandbug/proyectos/verproyecto/usuarios/buscarusuarios.html',
						$(
							'#idFormUsuariosBusqueda')
						.serialize(),
						function(respuesta) {
							$(
								'#tabla-usuarios')
							.html(
								respuesta);
						});
			});

		$("#tabla-usuarios").delegate("#buscoUsuario","click",
			function() {
				var datos = $(this).parent().parent().find("#idUsuario").html();
				
				var datos2 = $("#form" + datos).serialize();
				$.post('/trackandbug/proyectos/verproyecto/usuarios/infousuarios.html',
					datos2,
					function(respuesta) {
						$('#myModal')
						.html(
							respuesta);
					});

			});
	});
</script>

<c:choose>
	<c:when test="${not empty proyecto.usuarios}">
		<form id="idFormUsuariosBusqueda">
			<div class="form-group row col-md-2">
				<input type="text" name="nombreBuscado" class="form-control"
					placeholder="Buscar Usuario" id="idNombreBuscado" />
				<input type="hidden" name="idProyecto" class="form-control"
					value="${proyecto.id}" />
			</div>
		</form>
		<div id="tabla-usuarios"></div>
	</c:when>
	<c:otherwise>
		<h4>No hay usuarios asignados a este proyecto</h4>
	</c:otherwise>
</c:choose>