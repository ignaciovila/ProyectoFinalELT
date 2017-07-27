<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/general/template_top.jsp" />
<script>
	$(function() {
		$('#usuarioForm').validate();
		$('#fechaAlta').datepicker({"dateFormat": "yy-mm-dd", minDate: -0});
	});
</script>

<div class='page-header'>
  <div class='btn-toolbar pull-right'>
    <div class='btn-group'>
      <a href="listar.html" class="btn btn-default">Volver</a>
    </div>
  </div>
  <h2>Nuevo usuario</h2>
</div>

<form:form id="usuarioForm" method="post" modelAttribute="usuarioForm"
	action="guardarusuario.html">
	<form:input path="id" type="hidden" />
	<div class="form-group">
		<label for="nombreCompleto">Nombre completo</label>
		<form:input path="nombreCompleto" class="form-control  required" type="text" />
	</div>

	<div class="form-group">
		<label for="usuario">Usuario</label>
		<form:input path="usuario" class="form-control required" type="text" />
	</div>

	<div class="form-group">
		<label for="fechaAlta">Fecha alta</label>
		<form:input id="fechaAlta" path="fechaAlta" class="form-control required" type="text" />
	</div>
	
	
	<div class="form-group">
		<label for="password">Password</label>
		<form:input path="password" type="password" class="form-control  required" />
	</div>
		<div class="checkbox">
 		<label> <form:checkbox path="projectManager" /> Usuario Principal
 		</label>
 	</div>

	<input type="submit" class="btn btn-default" value="Guardar">
</form:form>

<c:import url="/general/template_bottom.jsp" />