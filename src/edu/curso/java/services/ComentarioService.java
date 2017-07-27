package edu.curso.java.services;

import java.util.List;

import edu.curso.java.bo.Comentario;

public interface ComentarioService {

	List<Comentario> recuperarComentariosTareas(Long idTarea);

	void borrarComentario(Long id);

	Long guardarComentario(Comentario comentario);
	
}