package edu.curso.java.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.curso.java.bo.Tarea;

@SuppressWarnings("unchecked")
@Repository
public class TareaDAOImp extends GenericDAOImp<Tarea, Long> implements TareaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Tarea> buscarTareasPorNombre(Long idProyecto, String titulo){
		String sql = "select * from proyecto_tarea as p inner join tarea as t on p.tareas_id = t.id where p.Proyecto_id = :idProyecto AND t.titulo like :titulo ";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.addEntity(Tarea.class);
		query.setLong("idProyecto",idProyecto);
		query.setString("titulo","%" + titulo + "%");
	
		System.out.println(query.list());
		return query.list();
	}
	
	public List<Tarea> returnList(){
		return sessionFactory.getCurrentSession().createQuery("from Tarea").list();
	}
}
