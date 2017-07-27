<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/general/template_top.jsp" />


<script type="text/javascript">
function buscarId(id){
	var datos = $("#form"+id).serialize();
	$.post('/trackandbug/proyectos/verproyecto/usuarios/infousuarios.html', datos, function(respuesta) {
		$('#myModal').html(respuesta);
	});
}

function nuevoComentario(id){
	var datos = $("#formIdTarea").serialize();
	$.post('/trackandbug/tareas/nuevocomentario.html', datos, function(respuesta) {
		$('#myModal').html(respuesta);
	});
}

$(document).ready(function() {
	$.post(
		'/trackandbug/tareas/vercomentarios.html',
		$('#formIdTarea').serialize(),
		function(respuesta) {
			$(
				'#divComentario')
			.html(
				respuesta);
		});
	
});
	
	
	
</script>


<div class='page-header'>
	<div class='btn-toolbar pull-right'>
		<div class='btn-group'>
			<button class="btn btn-default" type="button" data-toggle="modal"
				data-target="#myModal" onclick="nuevoComentario(${tarea.id})">Nuevo
				Comentario</button>
			<a
				href="/trackandbug/proyectos/verproyecto/verproyecto.html?id=${proyecto.id}"
				class="btn btn-default">Volver</a>
		</div>
	</div>
	<h2>Proyecto:${proyecto.nombre} - Tarea: ${tarea.titulo}</h2>
	<form id="formIdTarea">
		<input type="hidden" name="idTarea" value="${tarea.id}"></input> <input
			type="hidden" name="idProyecto" value="${proyecto.id}"></input>
	</form>
	
</div>


<div class="row">
	<div class="col-md-6">
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<td class="text-center"><b>Id</b></td>
				<td class="text-center">${tarea.id}</td>
			</tr>
			<tr>
				<td class="text-center"><b>Estado</b></td>
				<td class="text-center">${tarea.estado}</td>
			</tr>
			<tr>
				<td class="text-center"><b>Tipo</b></td>
				<td class="text-center">${tarea.tipo}</td>
			</tr>
			<tr>
				<td class="text-center"><b>Horas</b></td>
				<td class="text-center">${tarea.cantHoras}</td>
			</tr>
			<tr>
				<td class="text-center"><b>Fecha Inicio</b></td>
				<td class="text-center"><fmt:formatDate
						value="${tarea.fechaInicio}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td class="text-center"><b>Fecha Fin</b></td>
				<td class="text-center"><fmt:formatDate
						value="${tarea.fechaFin}" pattern="yyyy-MM-dd" /></td>
			</tr>

			<tr>
				<td colspan="2" class="text-center"><b>Usuarios</b></td>
			</tr>
			<tr>

				<td class="text-center"><b>Nombre</b></td>
				<td class="text-center"><b>Opciones</b></td>
			</tr>

			<c:forEach items="${tarea.usuarios}" var="u">
				<tr>

					<td class="text-center">${u.nombreCompleto}</td>
					<td class="text-center" hidden="true" id="idUsuario">${u.id}</td>
					<td class="text-center">
						<form id="form${u.id}">
							<input type="hidden" name="id" value="${u.id}"></input>
						</form>
						<button class="btn btn-default" type="button" data-toggle="modal"
							data-target="#myModal" onclick="buscarId(${u.id})">
							<span class="glyphicon glyphicon-eye-open" aria-hidden="true"
								style="font-size: 10px;"> </span>Ver
						</button>
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>
	<div id="divComentario" class="col-md-6"></div>
</div>

<div class="modal fade" id="myModal"></div>

<c:import url="/general/template_bottom.jsp" />