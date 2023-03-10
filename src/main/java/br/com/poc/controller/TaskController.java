package br.com.poc.controller;

import br.com.poc.dto.TaskDTO;
import br.com.poc.entidade.Task;
import br.com.poc.exception.GenericPersistenciaException;
import br.com.poc.service.TaskService;
import br.com.poc.util.FilterTask;
import io.swagger.annotations.ApiOperation;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class TaskController {

    private static final Logger log = Logger.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasks() throws IOException, BiffException {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.list(null));
    }

    @GetMapping("/{max}/{page}/{idUsuario}/{filterTitle}/{filterStatus}/{creationDate}/{conclusionDate}/{orderBy}")
    @ResponseBody
    public ResponseEntity<List<Task>> findAlltasks(@PathVariable("max") int maxResults, @PathVariable("page") int page,
                                                   @PathVariable("creationDate") Integer idUsuario,
                                                   @PathVariable("filterTitle") String filterTitle, @PathVariable("filterStatus") boolean filterStatus,
                                                   @PathVariable("creationDate") String creationDate, @PathVariable("conclusionDate") String conclusionDate,
                                                   @PathVariable("orderBy") String orderBy) throws ParseException {

        FilterTask filterTask = new FilterTask();

        preencheFiltro(maxResults, page, filterTitle, filterStatus, creationDate, orderBy, idUsuario, filterTask );

        return ResponseEntity.status(HttpStatus.OK).body(taskService.list(filterTask));

    }

    @GetMapping(value = "/findAll/{max}/{page}/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<TaskDTO>> findAlltasks2(@PathVariable("max") int maxResults, @PathVariable("page") int page,
                                                      @PathVariable("idUsuario") int idUsuario) throws ParseException {

        TaskDTO task = new TaskDTO();
        task.setCreationDate(new Date());
        task.setDateConclusion(new Date());
        task.setStatus(true);
        task.setId(10L);
        task.setDescription("Teste de tarefa");
        task.setTitle("Tarefa teste");

        List<TaskDTO> lista = new ArrayList<>();
        lista.add(task);

        FilterTask filtroTarefa = new FilterTask();
        filtroTarefa.setIdUser(idUsuario);
        filtroTarefa.setMaxResults(maxResults);
        filtroTarefa.setCurrentPage(page);

        //preencheFiltro(maxResults, page, filterTitle, filterStatus, creationDate, orderBy, filtroTarefa);

        return ResponseEntity.status(HttpStatus.OK).body(lista);

    }

    private void preencheFiltro( int maxResults,  int page,
                                 String filterTitle,  boolean filterStatus,
                                 String creationDate, String orderBy,Integer idUsuario,
                                 FilterTask filtroTarefa) {
        filtroTarefa.setCurrentPage(page);
        filtroTarefa.setMaxResults(maxResults);
        filtroTarefa.setOrder(orderBy);
        //filtroTarefa.setDataCriacao(creationDate);
        //filtroTarefa.setDataConclusao(creationDate);
        filtroTarefa.setDescriptionFilter(filterTitle);
        filtroTarefa.setFilterStatus(filterStatus);
        filtroTarefa.setIdUser(idUsuario);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTarefas(@PathVariable Integer id) throws IOException, BiffException {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findTaskById(id));
    }


    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Produces("application/json")
    @Consumes("application/json")
    public ResponseEntity<Void> save(@RequestBody TaskDTO task) {
        Task newTask = new Task();
        newTask.setDescriptionTask(task.getDescription());

        this.taskService.save(newTask);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand("").toUri();

        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/alterar/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Altera os dados da entidade Tarefa")
    public void alterar(@RequestBody Task tarefa){

        try {
            this.taskService.update(tarefa);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/remover/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Remove os dados da entidade Tarefa")
    public void remover(@PathVariable Integer id){

        try {
            this.taskService.remove(id);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

}
