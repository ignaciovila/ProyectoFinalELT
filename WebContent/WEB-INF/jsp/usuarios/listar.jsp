<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/general/template_top.jsp" />

<script>
	function buscarNombre(){
		$.post("/trackandbug/usuarios/buscarusuario.html", {
			nombre : $("#textoBuscar").val()
		}, function(data) {
			$("#tablaUsuarios").html(data);
		});
	}
	
	function cargarTablaUsuarios(urlBorrar) {
		$.get(urlBorrar, function() {
			$.get('/trackandbug/usuarios/tablaUsuarios.html', function(respuesta) {
				$('#tablaUsuarios').html(respuesta);
			});
		});
	}
	
	$(function() {
		$.get('/trackandbug/usuarios/tablaUsuarios.html', function(respuesta) {
			$('#tablaUsuarios').html(respuesta);
		});
		
		$("#textoBuscar").keyup(function(e) {
			buscarNombre();
		});

		$("#tablaUsuarios").delegate(".btn-borrar-usuario", "click", function(e) {
			e.preventDefault();
			var urlBorrar = $(this).prop('href');
			bootbox.confirm("Está seguro?", function(result) {
				if (result) {
					cargarTablaUsuarios(urlBorrar);
				}
			});
		});
	});
</script>

<div class='page-header'>
	<div class='btn-toolbar pull-right'>
		<div class='btn-group'>
			<a href="nuevousuario.html" class="btn btn-default">Nuevo usuario</a>
		</div>
	</div>
	<h2>Listado de usuarios</h2>
</div>
<c:choose>
	<c:when test="${not empty usuarios}">
		<div class="row">
			<div class="col-md-3">
				<input type="text" class="form-control" placeholder="Buscar..."
					id="textoBuscar" />
			</div>
		</div>
		<div id="tablaUsuarios" class="tablas"></div>
	</c:when>
	<c:otherwise>
		<h4>No hay usuarios disponibles</h4>
	</c:otherwise>
</c:choose>
<c:import url="/general/template_bottom.jsp" />