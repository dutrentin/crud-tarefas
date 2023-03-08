package br.com.poc.service;

import br.com.poc.dao.TarefaDAO;
import br.com.poc.dao.TarefaDAO;
import br.com.poc.entidade.Tarefa;
import br.com.poc.exception.GenericPersistenciaException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service("tarefaService")
public class TarefaService implements Serializable {

    private static final long serialVersionUID = 8774548879624495574L;

    private static final Logger log = Logger.getLogger(TarefaService.class);

    @Autowired
    private TarefaDAO tarefaDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    public void salvar(Tarefa tarefa) throws GenericPersistenciaException {
        try {
            this.tarefaDAO.salvar(tarefa);
            System.out.println("Salvou " + tarefa.getDescricaoTarefa());
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
    public void alterar(Tarefa tarefa) throws GenericPersistenciaException {

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

    public List<Tarefa> listar() throws GenericPersistenciaException {

        try {
            return  this.tarefaDAO.list();
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

    public Tarefa findTarefaById(Integer idTarefa) throws GenericPersistenciaException {

        try {

            return this.tarefaDAO.findTarefaById(idTarefa);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

}
