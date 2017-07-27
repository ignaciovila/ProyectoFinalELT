<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
				
<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h3 class="modal-title">Proyecto: ${proyecto.nombre} </h3>
        </div>
        <div class="modal-body">
          	<table class="table table-striped table-bordered table-hover">
			<tr>
				<td><b>Id</b></td>
				<td>${proyecto.id}</td>
			</tr>
			<tr>
				<td><b>Descripcion</b></td>
				<td>${proyecto.descripcion}</td>
			</tr>
			<tr>
				<td><b>Responsable</b></td>
				<td><c:if
						test="${not empty proyecto.usuarioPrincipal.nombreCompleto}">
			${proyecto.usuarioPrincipal.nombreCompleto}
			</c:if></td>
			</tr>
		</table>
			
		</div>
        <div class="modal-footer">
        	<h3 style="text-align:left">Esta seguro que desea Borrar este Proyecto?</h3><br>
			<div class='btn-toolbar pull-right'>
				<div class='btn-group'>
					<a href="borrarproyecto.html?id=${proyecto.id}" class="btn btn-default">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>Borrar
					</a>
					<a href="/trackandbug/proyectos/listado/listar.html" class="btn btn-default">Cancelar</a>
				</div>
			</div>
        </div>
				
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
