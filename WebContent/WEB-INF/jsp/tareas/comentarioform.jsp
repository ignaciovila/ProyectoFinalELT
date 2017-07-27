<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	$(function() {
		$("#comentarioForm").validate();
		
		$('#fechaComent').datepicker({
			"dateFormat" : "yy-mm-dd"
		});
		
	});
</script>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 class="modal-title">Tarea: ${tarea.titulo}</h3>
		</div>
		<div class="modal-body">

			<form:form id="comentarioForm" method="post"
				modelAttribute="comentarioForm"
				action="guardarcomentario.html?idTarea=${tarea.id}&idProyecto=${proyecto.id}">
				<form:input path="id" type="hidden" />

				<div class="form-group" hidden="true">

					<form:input id="fechaComent" path="fechaComent"
						class="form-control required" type="text" />
				</div>
				<div class="form-group">
					<label for="Comentario">Comentario</label>
					<form:textarea path="comentario" class="form-control required"
						type="text" />
				</div>
				<div class="modal-footer">
					<div class='btn-toolbar pull-right'>
						<div class='btn-group'>
							<input type="submit" class="btn btn-default" value="Guardar">
							
						</div>
					</div>

				</div>

			</form:form>

		</div>

	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
