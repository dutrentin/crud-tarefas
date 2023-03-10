package br.com.poc.service;

import br.com.poc.dao.TaskDAO;
import br.com.poc.entidade.Task;
import br.com.poc.entidade.User;
import br.com.poc.exception.GenericPersistenciaException;
import br.com.poc.util.FilterTask;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service("taskService")
public class TaskService implements Serializable {

    private static final long serialVersionUID = 8774548879624495574L;

    private static final Logger log = Logger.getLogger(TaskService.class);

    @Autowired
    private TaskDAO taskDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Task task) throws GenericPersistenciaException {
        try {
            this.taskDAO.save(task);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage() + this.getClass().getName()+ " erro ao salvar");
            throw new GenericPersistenciaException(e.getMessage());
        }
    }

    /**
     * Método responsável por atualizar os dados
     * da entidade Bebida na base de dados
     *
     * @param Bebida
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Task task) throws GenericPersistenciaException {

        try {

            //Bebida bebida = this.bebidaDAO.findBebidaById(Integer.parseInt(bebidaDTO.getId()));
            //preencheTipoBebida(bebidaDTO, bebida);
            //bebida.setDescricaoBebida(bebidaDTO.getNome());

            this.taskDAO.update(task);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

    /**
     * Método responsável por remover os dados
     * da entidade Tarefa na base de dados
     *
     * @param Tarefa
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(Integer idTask) throws GenericPersistenciaException {

        try {
            this.taskDAO.remove(idTask);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }


    /**
     * Método responsável por listar os dados
     * da entidade Tarefa na base de dados
     *
     * @param Tarefa
     */

    public List<Task> list(FilterTask filterTask) throws GenericPersistenciaException {
        Task task = new Task();
        task.setActive(Boolean.TRUE);
        task.setDateConclusionTask(new Date());
        task.setDateCreationTask(new Date());
        task.setDateTask(new Date());
        task.setDescriptionTask("Levar o lixo");
        task.setUser(new User(1,"eduardo.trentin","teste"));
        taskDAO.save(task);
        taskDAO.findAll();


        try {
            List<Task> returnList = this.taskDAO.list(filterTask);
            return  returnList;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

    /**
     * Método responsável por consultar os dados
     * de uma única entidade Tarefa na base de dados
     *
     * @param Tarefa
     */

    public Task findTaskById(Integer idTask) throws GenericPersistenciaException {

        try {

            return this.taskDAO.findTaskById(idTask);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

}
