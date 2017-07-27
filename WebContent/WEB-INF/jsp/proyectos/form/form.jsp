<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/general/template_top.jsp" />

<script type="text/javascript">
	$(function() {
		$.validator.addMethod("fechaFinalizacion", function(value, element) {
			var startDate = $('.fechaInicio').val();
			return Date.parse(startDate) <= Date.parse(value) || value == "";
		}, "  La Fecha de Finalizacion debe ser mayor a la Fecha de Inicio");
		
		$.validator.addMethod("horasAsignadas", function(value, element) {
			return parseInt($('.horasAsignadas').val(), 10) >= parseInt($('.horasRestantes').val(),10);
		},"  La horas asignadas deben ser mayor o igual a las horas sin utilizar del proyecto ");

		$("#ProyectoForm").validate();		

		$('#fechaInicio').datepicker({
			"dateFormat" : "yy-mm-dd",
			minDate : -0
		});
		
		$('#fechaFinalizacion').datepicker({
			"dateFormat" : "yy-mm-dd",
			minDate : -0
		});
		
		$(".js-example-basic-single").select2();
		$(".js-example-basic-multiple").select2();
	});
</script>

<div class='page-header'>
	<div class='btn-toolbar pull-right'>
		<div class='btn-group'>
			<a href="/trackandbug/proyectos/listado/listar.html"
			class="btn btn-default">Volver</a>
		</div>
	</div>
	<h2>Proyecto</h2>
</div>

<form:form method="post" id="ProyectoForm" modelAttribute="proyectoForm"
action="guardarproyecto.html">

<form:input path="id" type="hidden" />
<div class="form-group">
	<label for="nombre">Nombre </label>
	<form:input path="nombre" name="nombre" class="form-control required"
	type="text" />
</div>

<div class="form-group">
	<label for="descripcion">Descripcion</label>
	<form:input path="descripcion" name="descripcion" class="form-control required"
	type="text" />
</div>

<div class="form-group">
	<label for="fechaInicio">Fecha Inicio</label>
	<form:input id="fechaInicio" path="fechaInicio"
	class="form-control fechaInicio required" type="text" />

</div>

<div class="form-group">
	<label for="fechaFin">Fecha Fin</label>
	<form:input id="fechaFinalizacion" path="fechaFinalizacion"
	class="form-control fechaFinalizacion required" type="text" />
</div>

<div class="form-group">
	<label for="horasAsignadas">Cantidad de Horas Asignadas</label>
	<form:input path="horasAsignadas"
	class="form-control required horasAsignadas" id="horasAsignadas"
	type="number" min="1" />
</div>

<div class="form-group" hidden="true">
	<label for="horasRestantes">Cantidad de Horas Restantes</label> <input
	value="${horasRestantes}"
	class="form-control required horasRestantes" id="horasRestantes"
	type="number" />
</div>

<div class="form-group">
	<label for="usuarioPrincipal" class="etiqueta col-md-2">Usuario Principal</label>
	<form:select path="idUsuarioPrincipal" items="${usuariosPrincipales}"
	class="col-md-2 js-example-basic-single"
	itemLabel="nombreCompleto" itemValue="id" />
</div>

<div class="form-group">
<label for="idUsuarios" class="etiqueta col-md-2">Otros usuarios</label>
	<form:select path="idUsuarios" items="${usuarios}" 
	class="col-md-2 js-example-basic-multiple"
	itemLabel="nombreCompleto" itemValue="id" multiple="multiple"/>
</div>

<input type="submit" class="btn btn-default" value="Guardar">

</form:form>

<c:import url="/general/template_bottom.jsp" />