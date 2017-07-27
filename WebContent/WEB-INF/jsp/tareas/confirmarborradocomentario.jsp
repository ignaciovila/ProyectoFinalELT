<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
				
<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          
        </div>
        <div class="modal-body">
          <div class="panel panel-default">
			
			<div class="panel-body">${c.comentario}</div>

		</div>
		
			
		</div>
        <div class="modal-footer">
        	<h3 style="text-align:left">Esta seguro que desea Borrar este Comentario?</h3><br>
			<div class='btn-toolbar pull-right'>
				<div class='btn-group'>
					<a href="borrarcomentario.html?id=${comentario.id}&idTarea=${tarea.id}&idProyecto=${proyecto.id}" class="btn btn-default">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>Borrar
					</a>
					
				</div>
			</div>
        </div>
				
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->