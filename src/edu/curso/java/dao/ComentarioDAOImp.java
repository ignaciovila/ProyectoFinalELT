package edu.curso.java.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.curso.java.bo.*;

@Repository
public class ComentarioDAOImp extends GenericDAOImp<Comentario, Long> implements ComentarioDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Comentario> recuperarComentariosTareas(Long idTarea) {

		String sql = "select c.id, c.comentario, c.editado, c.fechaComent" + "from comentario as c inner join"
				+ "(select comentarios_id from tarea_comentario as t where t.Tarea_id :idTarea) as co on c.id = co.comentarios_id";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.addEntity(Comentario.class);
		query.setLong("idTarea", idTarea);
		return query.list();
	}

	public void borrarComentario(Long id) {
		String sql = "delete from tarea_comentario where comentarios_id = :id";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setLong("id", id);
		query.executeUpdate();
		String sql2 = "delete from comentario where id = :id";
		SQLQuery query2 = sessionFactory.getCurrentSession().createSQLQuery(sql2);
		query2.setLong("id", id);
		query2.executeUpdate();

	}
}
