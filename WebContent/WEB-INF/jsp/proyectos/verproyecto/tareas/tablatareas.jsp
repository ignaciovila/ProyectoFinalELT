<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	$(document)
	.ready(
		function() {
			$.validator
			.addMethod("fechaFinTarea", function(value,
				element) {
				var startDate = $('.fechaInicioTarea')
				.val();

				return Date.parse(startDate) <= Date
				.parse(value)
				|| value == "";
			},
			"  La Fecha de Finalizacion debe ser > a la Fecha de Inicio ");

			$.post('/trackandbug/proyectos/verproyecto/tareas/listartareas.html',
				$('#idFormTareasBusqueda').serialize(), function(
					respuesta) {
					$('#tabla-tareas').html(respuesta);
				});

			$("#idTituloBuscado")
			.keyup(
				function() {
					$.post(
						'/trackandbug/proyectos/verproyecto/tareas/buscotareas.html',
						$(
							'#idFormTareasBusqueda')
						.serialize(),
						function(respuesta) {
							$(
								'#tabla-tareas')
							.html(
								respuesta);
						});
				});

			$('#tabla-tareas')
			.delegate(
				'#buscoTarea',
				'click',
				function() {
					var datos = $(this).parent()
					.parent().find('#idTarea')
					.html();
					var datos2 = $('#form' + datos)
					.serialize();
					$
					.post(
						'/trackandbug/proyectos/verproyecto/tareas/infotareas.html',
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
	<c:when test="${horasRestantes}">
		<c:choose>
			<c:when test="${not empty proyecto.usuarios}">
				<div class='btn-toolbar pull-right'>
					<div class='btn-group'>
						<a
							href="<c:url value='/tareas/nuevatarea.html?idProyecto=${proyecto.id}'/>"
							class="btn btn-default">Nueva tarea</a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<h4 class="pull-right etiqueta">No hay tareas en el proyecto</h4>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<h4 class="pull-right etiqueta">No hay horas disponibles</h4>
	</c:otherwise>
</c:choose>	
<c:choose>
	<c:when test="${not empty proyecto.tareas}">
		<form id="idFormTareasBusqueda">
			<div class="form-group row col-md-2">
				<input type="text" name="tituloBuscado" class="form-control"
					placeholder="Buscar Tarea" id="idTituloBuscado" />
				<input type="hidden" name="idProyecto"
					class="form-control" value="${proyecto.id}" />
			</div>
		</form>
		<div id="tabla-tareas"></div>
	</c:when>
	<c:otherwise>
		<h4>No hay tareas cargadas</h4>
	</c:otherwise>
</c:choose>