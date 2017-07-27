package edu.curso.java.dao;

import java.util.List;

import edu.curso.java.bo.Comentario;
import edu.curso.java.bo.Usuario;

public interface ComentarioDAO extends GenericDAO<Comentario, Long> {

	List<Comentario> recuperarComentariosTareas(Long idTarea);

	void borrarComentario(Long id);
}