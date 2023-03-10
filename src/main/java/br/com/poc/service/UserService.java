package br.com.poc.service;

import br.com.poc.dao.UserDAO;
import br.com.poc.entidade.User;
import br.com.poc.exception.GenericPersistenciaException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service("userService")
public class UserService implements Serializable {

    private static final long serialVersionUID = 8774542279624495574L;

    private static final Logger log = Logger.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(User user) throws GenericPersistenciaException {
        try {
            this.userDAO.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage() + this.getClass().getName()+ " erro ao salvar");
            throw new GenericPersistenciaException(e.getMessage());
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void update(User user) throws GenericPersistenciaException {

        try {

            //Bebida bebida = this.bebidaDAO.findBebidaById(Integer.parseInt(bebidaDTO.getId()));
            //preencheTipoBebida(bebidaDTO, bebida);
            //bebida.setDescricaoBebida(bebidaDTO.getNome());

            this.userDAO.update(user);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remover(Integer iduser) throws GenericPersistenciaException {

        try {
            this.userDAO.remove(iduser);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

    public List<User> list() throws GenericPersistenciaException {
        try {
            List<User> returnList = this.userDAO.list();
            return  returnList;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

    /**
     * Método responsável por consultar os dados
     * de uma única entidade user na base de dados
     *
     * @param user
     */

    public User findUserById(Integer idUser) throws GenericPersistenciaException {

        try {

            return this.userDAO.findUserById(idUser);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new GenericPersistenciaException(e.getMessage());
        }

    }

}
