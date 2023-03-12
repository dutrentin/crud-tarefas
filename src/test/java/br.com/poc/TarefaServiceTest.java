package br.com.poc;

import br.com.poc.dao.TaskDAO;
import br.com.poc.entidade.Task;
import br.com.poc.entidade.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Autowired
    private TaskDAO tarefaDAO;

    @Test
    public void whenAddTarefa_thenGetTarefa() throws SQLException {
        Task tarefa = new Task();
        tarefa.setActive(Boolean.TRUE);
        tarefa.setDateCreationTask(new Date());
        tarefa.setDateConclusionTask(new Date());
        tarefa.setDescriptionTask("Levar o lixo");
        tarefa.setPerson(new Person(1,"dutrentin","teste"));
        tarefaDAO.saveTask(tarefa);
        tarefaDAO.findAll();
        //assertEquals(1, tarefaDAO.findAll().size());
    }

}

