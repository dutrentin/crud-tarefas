package br.com.poc.dao;

import br.com.poc.entidade.User;
import br.com.poc.exception.GenericPersistenciaException;
import br.com.poc.util.FilterUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Qualifier("userDAO")
public class UserDAO extends PersistenciaDao<User> {

    private static final long serialVersionUID = 6644637152890772203L;

    private static final Logger log = Logger.getLogger(UserDAO.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(User user) throws GenericPersistenciaException {

        if(user == null) {
            throw new GenericPersistenciaException("Usuário deve ser preenchido");
        }

        try {

            save(user);
            getEntityManager().flush();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage() + this.getClass().getName());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(User user) throws GenericPersistenciaException {

        if(user == null) {
            throw new GenericPersistenciaException("Usuário deve ser preenchido");
        }

        try {

            update(user);
            getEntityManager().flush();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(Integer idUser) throws GenericPersistenciaException {



        if(idUser == null) {
            throw new GenericPersistenciaException("O Id de Usuário deve ser informado");
        }

        try {

            delete(idUser);
            getEntityManager().flush();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    public List<User> list() throws GenericPersistenciaException {

        try {

            StringBuilder hql = new StringBuilder().append("SELECT u FROM User u ");


            Query query = getEntityManager().createQuery(hql.toString());
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    public User findUserById(Integer idUser) throws GenericPersistenciaException {

        try {

            return findById(idUser);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }

    }


}
