<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/general/template_top.jsp" />

<script type="text/javascript">
	$(function() {
		$("#tareaForm").validate();

		$.validator.addMethod("fechaFinTarea",function(value, element) {
							var startDate = $('.fechaInicioTarea').val();

							return Date.parse(startDate) <= Date.parse(value) || value == "";
						},
						"  La Fecha de Finalizacion debe ser > a la Fecha de Inicio ");


		$.validator
				.addMethod("horasAsignadas", function(value, element) {
					
					
					return parseInt($('.horasAsignadas').val(), 10) <= parseInt($('.horasRestantes').val(),10);

				},
						"  La horas asignadas deben ser <= a las horas sin utilizar del proyecto ");
		
	
		$('#fechaInicioTarea').datepicker({
			"dateFormat" : "yy-mm-dd",
			minDate : $('.fechaInicioProyecto').val(),
			maxDate: $('.fechaFinProyecto').val()
			
		});
		$('#fechaFinTarea').datepicker({
			"dateFormat" : "yy-mm-dd",
			minDate : $('.fechaInicioProyecto').val(),
			maxDate: $('.fechaFinProyecto').val()
		});
		
	var $datepicker = $('#fechaInicioTarea');
		
		$datepicker.datepicker('setDate', $('.fechaInicioProyecto').val());
		
	var $datepicker2 = $('#fechaFinTarea');
		
		$datepicker2.datepicker('setDate', $('.fechaInicioProyecto').val());
		

		$(".js-example-basic-single").select2();
		$(".js-example-basic-multiple").select2();

	});
</script>

<div class='page-header'>
	<div class='btn-toolbar pull-right'>
		<div class='btn-group'>
			<a
				href="/trackandbug/proyectos/verproyecto/verproyecto.html?id=${proyecto.id}"
				class="btn btn-default">Volver</a>
		</div>
	</div>
	<h2>Proyecto: ${proyecto.nombre} - Form Tarea</h2>
</div>


<form:form id="tareaForm" method="post" modelAttribute="tareaForm"
	action="guardartarea.html?idProyecto=${proyecto.id}">

	<form:input path="id" type="hidden" />
	<div class="form-group">
		<label for="Titulo">Titulo</label>
		<form:input path="titulo" class="form-control  required" type="text" />
	</div>

	<div class="form-group">
		<label for="fechaInicioTarea">Fecha Inicio</label>
		<form:input id="fechaInicioTarea" path="fechaInicio"
			class="form-control fechaInicioTarea required" type="text" />
	</div>
	<div class="form-group">
		<label for="fechaFinTarea">Fecha Fin</label>
		<form:input id="fechaFinTarea" path="fechaFin"
			class="form-control fechaFinTarea required" type="text" />
	</div>

	

	<div class="form-group" hidden="true">
		<input id="fechaInicioProyecto" value="${proyecto.fechaInicio}"
			class="form-control fechaInicioProyecto " type="text" />
			 <input
			id="fechaFinProyecto" value="${proyecto.fechaFinalizacion}"
			class="form-control fechaFinProyecto " type="text" />
			 
	</div>


	<div class="form-group">
		<label for="horasAsignadas">Cantidad de Horas </label>
		<form:input path="cantHoras"
			class="form-control required horasAsignadas" id="horasAsignadas"
			type="number" min="1" />
	
	</div>
	<div class="form-group" hidden="true">

			<label for="horasRestantes">Cantidad de Horas Restantes</label>
		<input value="${horasRestantes}"
			class="form-control required horasRestantes" id="horasRestantes"
			type="number" />
</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label for="estadoTarea">Estado</label>
		</div>
		<form:select path="estado" class="col-md-2 js-example-basic-single">
			<form:option value="pendiente">PENDIENTE</form:option>
			<form:option value="en proceso">EN PROCESO</form:option>
			<form:option value="finalizado">FINALIZADO</form:option>
			<form:option value="cancelado">CANCELADO</form:option>
		</form:select>
	</div>
	<div class="form-group row">
		<div class="col-md-2">
			<label for="tipoTarea">Tipo</label>
		</div>
		<form:select path="tipo" class="col-md-2 js-example-basic-single">
			<form:option value="desarrollo">DESARROLLO</form:option>
			<form:option value="testing">TESTING</form:option>
			<form:option value="analisis">ANALISIS</form:option>
		</form:select>
	</div>

	<div class="form-group row">
		<div class="col-md-2">
			<label for="idUsuarios">Usuarios</label>
		</div>
		<form:select class="col-md-2 js-example-basic-multiple"
			multiple="multiple" path="idUsuarios" items="${usuarios}"
			itemLabel="nombreCompleto" itemValue="id" />
	</div>

	<input type="submit" class="btn btn-default" value="Guardar">
</form:form>


<c:import url="/general/template_bottom.jsp" />