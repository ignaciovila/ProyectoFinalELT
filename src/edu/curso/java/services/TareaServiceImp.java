package edu.curso.java.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.curso.java.bo.*;
import edu.curso.java.dao.*;

@Service
@Transactional
public class TareaServiceImp implements TareaService {
	private static final Logger log = Logger.
			getLogger(TareaServiceImp.class);
	
	@Autowired
	ProyectoDAO ProyectoDAO;
	@Autowired
	TareaDAO TareaDAO;
	@Autowired
	ComentarioDAO comentarioDAO;	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Override
	public List<Tarea> recuperarTareas() {
		return TareaDAO.returnList();
	}
	
	@Override
	public Tarea recuperarTareaPorId(Long id) {
		log.info("Recuperando la tarea con id: " + id);
		return TareaDAO.read(id);
	}

	@Override
	public void borrarTareaPorId(Long id) {
		TareaDAO.delete(id);
	}

	@Override
	public List<Tarea> buscarTareasPorNombre(Long idProyecto, String nombre){
		return TareaDAO.buscarTareasPorNombre(idProyecto, nombre);
		
	}
	
	@Override
	public void actualizarTarea(Tarea tarea, Long idProyecto, List<Long> idUsuarios, Integer horas) {
		tarea.getUsuarios().clear();
		for (Long idUsuario : idUsuarios) {
			tarea.getUsuarios().add(usuarioDAO.read(idUsuario));			
		}				
		Proyecto proyecto = ProyectoDAO.read(idProyecto);				
		Integer horasUt = (proyecto.getHorasUtilizadas() - tarea.getCantHoras()) + horas;
		tarea.setCantHoras(horas);
		proyecto.setHorasUtilizadas(horasUt);
		
		ProyectoDAO.update(proyecto);
		TareaDAO.update(tarea);				
	}
	public void actualizarTarea(Tarea tarea) {
		TareaDAO.update(tarea);			
	}

	@Override
	public Long guardarTarea(Tarea tarea, Long idProyecto, List<Long> idUsuarios) {
		
		for (Long idUsuario : idUsuarios) {
			tarea.getUsuarios().add(usuarioDAO.read(idUsuario));
			
		}
		 TareaDAO.create(tarea);
		 Proyecto proyecto = ProyectoDAO.read(idProyecto);		 
		 Integer horas = proyecto.getHorasUtilizadas() + tarea.getCantHoras();
		 proyecto.setHorasUtilizadas(horas);
		 proyecto.getTareas().add(tarea);
		 ProyectoDAO.update(proyecto);
		 return tarea.getId();
		
	}
}