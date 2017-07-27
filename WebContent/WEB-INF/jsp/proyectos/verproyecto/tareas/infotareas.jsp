<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

				
<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h3 class="modal-title">Titulo: ${tarea.titulo} </h3>
        </div>
        <div class="modal-body">
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
				<td class="text-center"><b>Fecha Inicio</b></td>
				<td class="text-center"><fmt:formatDate value="${tarea.fechaInicio}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td class="text-center"><b>Fecha Fin</b></td>
				<td class="text-center"><fmt:formatDate value="${tarea.fechaFin}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
		
		
		</table>
		</div>
        <div class="modal-footer">
 
        </div>
				
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
