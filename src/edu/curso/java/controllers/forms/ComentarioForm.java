package edu.curso.java.controllers.forms;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ComentarioForm {
	private Long id;
	private String comentario;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaComent = new Date();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Date getFechaComent() {
		return fechaComent;
	}
	public void setFechaComent(Date fechaComent) {
		this.fechaComent = fechaComent;
	}
	
	
}
