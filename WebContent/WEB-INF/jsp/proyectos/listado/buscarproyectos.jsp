<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

	function buscarId(id){
		var datos = $("#form"+id).serialize();
		$.post('infoproyectos.html', datos, function(respuesta) {
			$('#myModal').html(respuesta);
		});
	}
	
	function borrarId(id){
		var datos = $("#form"+id).serialize();
		$.post('confirmarborrado.html', datos, function(respuesta) {
			$('#myModal').html(respuesta);
		});
	}

</script>

<table id="tablaProyectos"
	class="table table-striped table-bordered table-hover">

	<tr>
		<th class="text-center">Id</th>
		<th class="text-center">Nombre Completo</th>
		<th class="text-center">Descripcion</th>
		<th class="text-center">Responsable</th>
		<th class="text-center">Opciones</th>
	</tr>
	<c:forEach items="${proyectos}" var="p">
		<tr>
			<td id="idProyecto" class="text-center">${p.id}</td>
			<td class="text-center">${p.nombre}</td>
			<td class="text-center">${p.descripcion}</td>
			<td class="text-center">${p.usuarioPrincipal.nombreCompleto}</td>
			<td class="text-center">
				<form id="form${p.id}">
					<input type="hidden" name="id" value="${p.id}"></input>
				</form> <a
					href="/trackandbug/proyectos/verproyecto/verproyecto.html?id=${p.id}"
					class="btn btn-default">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>Ver
				</a>
				<button class="btn btn-default" type="button"
					onclick="buscarId(${p.id})" data-toggle="modal"
					data-target="#myModal">
					<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>Info
				</button>
				<a href="/trackandbug/proyectos/editarproyecto.html?id=${p.id}"
					class="btn btn-default">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Editar
				</a>
				<button class="btn btn-default" type="button"
					onclick="borrarId(${p.id})" data-toggle="modal"
					data-target="#myModal">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>Borrar
				</button> 
			</td>
		</tr>
	</c:forEach>

</table>

<div class="modal fade" id="myModal"></div>