package edu.curso.java.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.curso.java.bo.*;
import edu.curso.java.services.*;
import edu.curso.java.controllers.forms.*;

@Controller
@RequestMapping(value="/tareas")

public class TareasController {
	
	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private TareaService tareaService;
	
	@Autowired
	private ComentarioService comentarioService;
	

	@RequestMapping(value="/vertarea")
	public String verTarea(@RequestParam Long id, @RequestParam Long idProyecto,Model model ){
		
		Tarea tarea = tareaService.recuperarTareaPorId(id);
		model.addAttribute("tarea",tarea);
		Proyecto proyecto = proyectoService.recuperarProyectoPorId(idProyecto);
		model.addAttribute("proyecto", proyecto);
		
		return null;
	}
	

	@RequestMapping(value = "/nuevatarea")
	public String nuevaTarea(Long idProyecto, Model model) {
		Proyecto proyecto = proyectoService.recuperarProyectoPorId(idProyecto);
		model.addAttribute("proyecto", proyecto);
		model.addAttribute("tareaForm", new TareaForm());		
		model.addAttribute("usuarios", proyecto.getUsuarios());
		Integer horasRestantes = proyecto.getHorasAsignadas() - proyecto.getHorasUtilizadas();
		model.addAttribute("horasRestantes", horasRestantes);
		return "/tareas/form";
	}
	
	@RequestMapping(value = "/editartarea")
	public String editarTarea(@RequestParam Long id,Long idProyecto,Model model) {
		
		Tarea tarea = tareaService.recuperarTareaPorId(id);
		TareaForm tareaForm = new TareaForm();
		tareaForm.setId(tarea.getId());
		tareaForm.setCantHoras(tarea.getCantHoras().intValue());
		tareaForm.setFechaInicio(tarea.getFechaInicio());
		tareaForm.setFechaFin(tarea.getFechaFin());
		List<Long> idUsuarios = tarea.getUsuarios().stream().map((Usuario u) -> u.getId()).collect(Collectors.toList());
		tareaForm.setIdUsuarios(idUsuarios);
		tareaForm.setTitulo(tarea.getTitulo());
		tareaForm.setEstado(tarea.getEstado());
		tareaForm.setTipo(tarea.getTipo());
		
		model.addAttribute("tareaForm", tareaForm);
		
		Proyecto proyecto = proyectoService.recuperarProyectoPorId(idProyecto);
		model.addAttribute("proyecto", proyecto);
		
		int horasRestantes = proyecto.getHorasAsignadas() - proyecto.getHorasUtilizadas() + tarea.getCantHoras();
		model.addAttribute("horasRestantes", horasRestantes);
		
		model.addAttribute("usuarios", proyecto.getUsuarios());
		
		return "/tareas/form";
	}

	@RequestMapping(value = "/guardartarea", method = RequestMethod.POST)
	public String guardarTarea(@ModelAttribute("tareaForm") TareaForm tareaForm, Long idProyecto, Model model) {

		Tarea tarea = new Tarea();
		Long idFormulario = tareaForm.getId();
		List <Long> idUsuarios = tareaForm.getIdUsuarios();
		
		if(idFormulario != null) {
			tarea  = tareaService.recuperarTareaPorId(idFormulario);
			tarea.setTitulo(tareaForm.getTitulo());
			tarea.setFechaFin(tareaForm.getFechaFin());
			tarea.setFechaInicio(tareaForm.getFechaInicio());
			Integer horas = Integer.valueOf(tareaForm.getCantHoras());
			tarea.setTipo(tareaForm.getTipo());
			tarea.setEstado(tareaForm.getEstado());
			tareaService.actualizarTarea(tarea, idProyecto, idUsuarios, horas);			
		} else {
			tarea  = new Tarea();
			tarea.setTitulo(tareaForm.getTitulo());
			tarea.setFechaFin(tareaForm.getFechaFin());
			tarea.setFechaInicio(tareaForm.getFechaInicio());
			tarea.setCantHoras(Integer.valueOf(tareaForm.getCantHoras()));
			tarea.setTipo(tareaForm.getTipo());
			tarea.setEstado(tareaForm.getEstado());
			idFormulario = tareaService.guardarTarea(tarea, idProyecto, idUsuarios);
		}
		return "redirect:/tareas/vertarea.html?id=" + idFormulario + "&idProyecto=" + idProyecto;
	}
	
	@RequestMapping(value = "/vercomentarios", method = RequestMethod.POST)
	public String verComentario(Long idTarea, Long idProyecto, Model model) {
		Tarea tarea = tareaService.recuperarTareaPorId(idTarea);
		model.addAttribute("tarea", tarea);
		Proyecto proyecto = proyectoService.recuperarProyectoPorId(idProyecto);
		model.addAttribute("proyecto", proyecto);
		return null;
	}
	
	@RequestMapping(value = "/confirmarborradocomentario")
	public String nuevoComentario(Long id, Long idTarea, Long idProyecto, Model model) {
		
		Tarea tarea = tareaService.recuperarTareaPorId(idTarea);
		model.addAttribute("tarea", tarea);
		
		Proyecto proyecto = proyectoService.recuperarProyectoPorId(idProyecto);
		model.addAttribute("proyecto", proyecto);
		
		model.addAttribute("comentario", comentarioService.recuperarComentariosTareas(id));
		
		return null;
	}
	
	@RequestMapping(value = "/borrarcomentario")
	public String borrarComentario(Long id, Long idTarea, Long idProyecto, Model model) {
		
		comentarioService.borrarComentario(id);
		
		return "redirect:/tareas/vertarea.html?id=" + idTarea + "&idProyecto=" + idProyecto;
	}
	
	@RequestMapping(value = "/nuevocomentario")
	public String nuevoComentario(Long idTarea, Long idProyecto, Model model) {
		Tarea tarea = tareaService.recuperarTareaPorId(idTarea);
		Proyecto proyecto = proyectoService.recuperarProyectoPorId(idProyecto);
		model.addAttribute("tarea", tarea);
		model.addAttribute("proyecto", proyecto);
		model.addAttribute("comentarioForm", new ComentarioForm());
		
		return "/tareas/comentarioform";
	}
	

	@RequestMapping(value = "/guardarcomentario", method = RequestMethod.POST)
	public String guardarTarea(@ModelAttribute("comentarioForm") ComentarioForm comentarioForm,  Long idTarea,Long idProyecto, Model model) {

		Comentario comentario = new Comentario();
		comentario.setComentario(comentarioForm.getComentario());
		comentario.setFechaComent(comentarioForm.getFechaComent());	
		Long idFormulario = comentarioService.guardarComentario(comentario);					
		Tarea tarea = tareaService.recuperarTareaPorId(idTarea);			
		tarea.getComentarios().add(comentario);
		tareaService.actualizarTarea(tarea);
		
		return "redirect:/tareas/vertarea.html?id=" + idTarea + "&idProyecto=" + idProyecto;
	}
}
