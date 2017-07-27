<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

				
<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h3 class="modal-title">Nombre: ${usuario.nombreCompleto} </h3>
        </div>
        <div class="modal-body">
          	<table class="table table-striped table-bordered table-hover">
			<tr>
				<td class="text-center"><b>Id</b></td>
				<td class="text-center">${usuario.id}</td>
			</tr>
			<tr>
				<td class="text-center"><b>Usuario</b></td>
				<td class="text-center">${usuario.usuario}</td>
			</tr>
			<tr>
				<td class="text-center"><b>Fecha Alta</b></td>
				<td class="text-center"><fmt:formatDate value="${usuario.fechaAlta}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td class="text-center"><b>Usuario Principal</b></td>
				<c:choose>	
				<c:when test="${usuario.projectManager}">
							<td class="text-center">SI</td>
						</c:when>
						<c:otherwise>
							<td class="text-center">NO</td>
						</c:otherwise>	
						</c:choose>	
			</tr>
		
		
		</table>
		</div>
        <div class="modal-footer">
 
        </div>
				
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
