package br.com.poc.dao;

import br.com.poc.entidade.Person;
import br.com.poc.exception.GenericPersistenciaException;
import br.com.poc.util.FilterPerson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Qualifier("personDAO")
public class PersonDAO extends PersistenciaDao<Person> {

    private static final long serialVersionUID = 6644637442890772203L;

    private static final Logger log = Logger.getLogger(PersonDAO.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public void savePerson(Person person) throws GenericPersistenciaException {

        if(person == null) {
            throw new GenericPersistenciaException("Pessoa deve ser preenchido");
        }

        try {

            save(person);
            getEntityManager().flush();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage() + this.getClass().getName());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePerson(Person person) throws GenericPersistenciaException {

        if(person == null) {
            throw new GenericPersistenciaException("Pessoa deve ser preenchido");
        }

        try {

            update(person);
            getEntityManager().flush();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removePerson(Integer idPerson) throws GenericPersistenciaException {



        if(idPerson == null) {
            throw new GenericPersistenciaException("O Id de Pessoa deve ser informado");
        }

        try {

            delete(idPerson);
            getEntityManager().flush();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    public List<Person> list(FilterPerson filterPerson) throws GenericPersistenciaException {

        try {

            StringBuilder hql = new StringBuilder().append("SELECT p FROM Person p ");
            hql.append(" WHERE 1 = 1 ");

            if(filterPerson.getEmail() != null && !filterPerson.getEmail().equals("-")){
                hql.append(" AND UPPER(p.email) like UPPER(CONCAT('%', :email, '%')) ");
            }
            if(filterPerson.getName() != null && !filterPerson.getName().equals("-")){
                hql.append(" AND UPPER(p.name) like UPPER(CONCAT('%', :name, '%')) ");
            }

            hql.append(" ORDER BY p.name");
            Query query = getEntityManager().createQuery(hql.toString());

            if(filterPerson.getEmail() != null && !filterPerson.getEmail().equals("-")){
                query.setParameter("email", filterPerson.getEmail());
            }
            if(filterPerson.getName() != null && !filterPerson.getName().equals("-")){
                query.setParameter("name", filterPerson.getName());
            }

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    public Person findPersonById(Integer idPerson) throws GenericPersistenciaException {

        try {

            return findById(idPerson);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }

    }

    public Person findPersonByName(String name) throws GenericPersistenciaException {

        try {

            StringBuilder hql = new StringBuilder().append("SELECT p FROM Person p where p.name = :name ");
            Query query = getEntityManager().createQuery(hql.toString());
            query.setParameter("name", name);
            return (Person) query.getResultList().get(0);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }

    }


}
