package br.com.poc.dao;

import br.com.poc.entidade.Tarefa;
import br.com.poc.exception.GenericPersistenciaException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Qualifier("tarefaDAO")
public class TarefaDAO extends PersistenciaDao<Tarefa> {

    private static final long serialVersionUID = 6644637152890772203L;

    private static final Logger log = Logger.getLogger(TarefaDAO.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public void salvar(Tarefa tarefa) throws GenericPersistenciaException {

        if(tarefa == null) {
            throw new GenericPersistenciaException("Tarefa deve ser preenchido");
        }

        try {

            save(tarefa);
            getEntityManager().flush();

        } catch (Exception e) {
            log.error(e.getLocalizedMessage() + this.getClass().getName());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTarefa(Tarefa tarefa) throws GenericPersistenciaException {

        if(tarefa == null) {
            throw new GenericPersistenciaException("Tarefa deve ser preenchido");
        }

        try {

            update(tarefa);
            getEntityManager().flush();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(Integer idTarefa) throws GenericPersistenciaException {



        if(idTarefa == null) {
            throw new GenericPersistenciaException("O Id de Tarefa deve ser informado");
        }

        try {

            delete(idTarefa);
            getEntityManager().flush();

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    public List<Tarefa> list() throws GenericPersistenciaException {

        try {

            return findAll();

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    public Tarefa findTarefaById(Integer idTarefa) throws GenericPersistenciaException {

        try {

            return findById(idTarefa);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }

    }


}
