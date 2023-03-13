package br.com.poc.dao;

import br.com.poc.entidade.Task;
import br.com.poc.exception.GenericPersistenciaException;
import br.com.poc.util.FilterTask;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Qualifier("taskDAO")
public class TaskDAO extends PersistenciaDao<Task> {

    private static final long serialVersionUID = 6625637152890772203L;

    private static final Logger log = Logger.getLogger(TaskDAO.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveTask(Task task) throws GenericPersistenciaException {

        if(task == null) {
            throw new GenericPersistenciaException("Tarefa deve ser preenchido");
        }

        try {

            save(task);
            getEntityManager().flush();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage() + this.getClass().getName());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTask(Task task) throws GenericPersistenciaException {

        if(task == null) {
            throw new GenericPersistenciaException("Tarefa deve ser preenchido");
        }

        try {

            update(task);
            getEntityManager().flush();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeTask(Integer idTask) throws GenericPersistenciaException {



        if(idTask == null) {
            throw new GenericPersistenciaException("O Id de Tarefa deve ser informado");
        }

        try {

            delete(idTask);
            getEntityManager().flush();

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    public List<Task> list(FilterTask filterTask) throws GenericPersistenciaException {

        try {

            StringBuilder hql = new StringBuilder().append("SELECT t FROM Task t ")
                    .append(" join t.person p ")
                    .append(" WHERE 1 = 1 ");

            if(filterTask != null && filterTask.getIdPerson() > 0){
                hql.append(" AND p.id = :idPerson ");
            }
            if(filterTask.getDateInitial() != null){
                hql.append(" AND p.dateTask > :dataInitial ");
            }
            if(filterTask.getDateFinal() != null){
                hql.append(" AND p.dateTask <= :dataFinal ");
            }

            if(filterTask.getTitleTask() != null){
                hql.append(" AND UPPER(t.titleTask) like UPPER(CONCAT('%', :title, '%')) ");
            }
            if(filterTask.getDateTask() != null){
                hql.append(" AND UPPER(p.name) like UPPER(CONCAT('%', :name, '%')) ");
            }

            hql.append(" ORDER BY t.titleTask ");

            Query query = getEntityManager().createQuery(hql.toString());

            if(filterTask != null && filterTask.getIdPerson() > 0){
                query.setParameter("idPerson", filterTask.getIdPerson());
            }
            if(filterTask.getDateInitial() != null){
                query.setParameter("dataInitial", filterTask.getDateInitial());
            }
            if(filterTask.getDateFinal() != null){
                query.setParameter("dataFinal", filterTask.getDateFinal());
            }

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    public Task findTaskById(Integer idTask) throws GenericPersistenciaException {

        try {

            return findById(idTask);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }

    }

}
