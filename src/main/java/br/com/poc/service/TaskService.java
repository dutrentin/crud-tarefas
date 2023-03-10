package br.com.poc.service;

import br.com.poc.dao.TaskDAO;
import br.com.poc.entidade.Task;
import br.com.poc.exception.GenericPersistenciaException;
import br.com.poc.util.FilterTask;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service("taskService")
public class TaskService implements Serializable {

    private static final long serialVersionUID = 8774548879624495574L;

    private static final Logger log = Logger.getLogger(TaskService.class);

    @Autowired
    private TaskDAO tarefaDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    public void salvar(Task tarefa) throws GenericPersistenciaException {
        try {
            this.tarefaDAO.save(tarefa);
            System.out.println("Salvou " + tarefa.getDescriptionTask());
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
    public void alterar(Task tarefa) throws GenericPersistenciaException {

        try {

            //Bebida bebida = this.bebidaDAO.findBebidaById(Integer.parseInt(bebidaDTO.getId()));
            //preencheTipoBebida(bebidaDTO, bebida);
            //bebida.setDescricaoBebida(bebidaDTO.getNome());

            this.tarefaDAO.update(tarefa);

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
    public void remover(Integer idTarefa) throws GenericPersistenciaException {

        try {
            this.tarefaDAO.remove(idTarefa);

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

    public List<Task> listar(FilterTask filtroTarefa) throws GenericPersistenciaException {
        /*Tarefa tarefa = new Tarefa();
        tarefa.setAtivo(Boolean.TRUE);
        tarefa.setDataCriacao(new Date());
        tarefa.setDataConclusao(new Date());
        tarefa.setDescricaoTarefa("Levar o lixo");
        tarefa.setUsuario(new Usuario(2,"dutrentin","teste"));
        tarefaDAO.salvar(tarefa);
        tarefaDAO.findAll();
         */

        try {
            List<Task> retorno = this.tarefaDAO.list(filtroTarefa);
            return  retorno;
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

    public Task findTarefaById(Integer idTarefa) throws GenericPersistenciaException {

        try {

            return this.tarefaDAO.findTaskById(idTarefa);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

}
