<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/general/template_top.jsp" />

<script type="text/javascript">
	$(document).ready(function() {
		$.post(
			'/trackandbug/proyectos/verproyecto/tareas/tablatareas.html',
			$('#formIdProyecto').serialize(),
			function(respuesta) {
				$('#divEleccion').html(respuesta);
			});

		$("#usuarioButton").click(
			function() {
				$.post(
					'/trackandbug/proyectos/verproyecto/usuarios/tablausuarios.html',
					$('#formIdProyecto').serialize(),
					function(respuesta) {
						$('#divEleccion').html(respuesta);
					});
			});

		$("#tareaButton").click(function() {
			$.post('/trackandbug/proyectos/verproyecto/tareas/tablatareas.html', $(
				'#formIdProyecto').serialize(),
			function(respuesta) {
				$('#divEleccion').html(respuesta);
			});

		});
	});
</script>

<form id="formIdProyecto">
	<input type="hidden" name="id" value="${proyecto.id}"></input>
</form>

<div class="grid">
	<div class='page-header row'>
		<div class='btn-toolbar pull-right'>
			<div class='btn-group'>
				<a href="/trackandbug/proyectos/listado/listar.html"
				class="btn btn-default">Volver</a>
			</div>
		</div>
		<h2>Proyecto: ${proyecto.nombre} - Horas Asignadas: ${proyecto.horasAsignadas} - Horas Disponibles: ${horasDisponibles}</h2>

		<table class="table table-striped table-bordered table-hover">
			<tr>
				<th class="text-center">Id</th>
				<th class="text-center">Descripcion</th>
				<th class="text-center">Responsable</th>
				<th class="text-center">Fecha Inicio</th>
				<th class="text-center">Fecha Fin</th>

			</tr>
			<tr>
				<td class="text-center" id="idProyecto">${proyecto.id}</td>
				<td class="text-center">${proyecto.descripcion}</td>
				<td class="text-center">${proyecto.usuarioPrincipal.nombreCompleto}</td>
				<td class="text-center"><fmt:formatDate value="${proyecto.fechaInicio}"
					pattern="yyyy-MM-dd" /></td>
				<td class="text-center"><fmt:formatDate value="${proyecto.fechaFinalizacion}"
						pattern="yyyy-MM-dd"/></td>
			</tr>
		</table>
	</div>

	<div class="row">
		<div class="form-inline">
			<button id="usuarioButton" class="btn btn-default " type="button">
				<span class="glyphicon glyphicon-user" aria-hidden="true"
				style="font-size: 20px;"> </span> &nbsp;&nbsp;Usuarios
			</button>
			<button id="tareaButton" class="btn btn-default " type="button">
				<span class="glyphicon glyphicon-list-alt" aria-hidden="true"
				style="font-size: 20px;"> </span> &nbsp;&nbsp;Tareas
			</button>
		</div>
	</div>

	<div id="divEleccion" class="row tablas"></div>

	<c:import url="/general/template_bottom.jsp" />

</div>