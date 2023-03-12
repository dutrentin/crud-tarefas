package br.com.poc.service;

import br.com.poc.dao.PersonDAO;
import br.com.poc.entidade.Person;
import br.com.poc.exception.GenericPersistenciaException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service("personService")
public class PersonService implements Serializable {

    private static final long serialVersionUID = 8774542279624495574L;

    private static final Logger log = Logger.getLogger(PersonService.class);

    @Autowired
    private PersonDAO personDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Person person) throws GenericPersistenciaException {
        try {
            this.personDAO.savePerson(person);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage() + this.getClass().getName()+ " erro ao salvar");
            throw new GenericPersistenciaException(e.getMessage());
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Person person) throws GenericPersistenciaException {

        try {
            this.personDAO.updatePerson(person);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(Integer idPerson) throws GenericPersistenciaException {

        try {
            this.personDAO.removePerson(idPerson);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

    public List<Person> list() throws GenericPersistenciaException {
        try {
            List<Person> returnList = this.personDAO.list();
            return  returnList;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

    /**
     * Método responsável por consultar os dados
     * de uma única entidade person na base de dados
     *
     * @param person
     */

    public Person findPersonById(Integer idPerson) throws GenericPersistenciaException {

        try {

            return this.personDAO.findPersonById(idPerson);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

}
