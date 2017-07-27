package edu.curso.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.curso.java.bo.Comentario;
import edu.curso.java.dao.ComentarioDAO;

@Service
@Transactional
public class ComentarioServiceImp implements ComentarioService {
	@Autowired
	ComentarioDAO comentarioDAO;

	@Override
	public List<Comentario> recuperarComentariosTareas(Long idTarea){
		return comentarioDAO.recuperarComentariosTareas(idTarea);
	}

	@Override
	public void borrarComentario(Long id) {
		comentarioDAO.borrarComentario(id);
	}

	@Override
	public Long guardarComentario(Comentario comentario) {
		return comentarioDAO.create(comentario);
	} 
}
