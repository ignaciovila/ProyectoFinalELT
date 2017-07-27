package edu.curso.java.bo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Comentario {
	
	@Id
	@GeneratedValue
	private Long id;
	private String comentario;	
	private Date fechaComent ;
	private boolean editado;
	
	
	public boolean isEditado() {
		return editado;
	}
	public void setEditado(boolean editado) {
		this.editado = editado;
	}
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
