package br.com.poc.controller;

import br.com.poc.dto.TaskDTO;
import br.com.poc.dto.TaskTransferDTO;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class TaskController {

    private static final Logger log = Logger.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy__HH_mm_s");

    @GetMapping(value = "/findAll/{max}/{page}/{idPerson}/{title}/{dateInitial}/{dateFinal}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<TaskTransferDTO> findAlltasks2(@PathVariable("max") int maxResults, @PathVariable("page") int page,
                                                      @PathVariable("idPerson") int idPerson,@PathVariable("title") String title,
                                                      @PathVariable("dateInitial")  String dateInitial, @PathVariable("dateFinal")  String dateFinal) throws ParseException{


        TaskTransferDTO taskTransferDTO = new TaskTransferDTO();

        FilterTask filterTask = new FilterTask();

        setFilters(maxResults, page, idPerson, title,  dateInitial, dateFinal, filterTask);

        taskTransferDTO =  this.taskService.list(filterTask);

        //criaRetornoFake(taskTransferDTO);

        //preencheFiltro(maxResults, page, filterTitle, filterStatus, creationDate, orderBy, filtroTarefa);

        return ResponseEntity.status(HttpStatus.OK).body(taskTransferDTO);

    }

    private void setFilters( int maxResults, int page, int idPerson,String title, String dateInitial, String dateFinal, FilterTask filterTask) throws ParseException{
        if(!dateInitial.equals("-")){
            filterTask.setDateInitial(formatter.parse(dateInitial));
        }
        if(!dateFinal.equals("-")){
            filterTask.setDateFinal(formatter.parse(dateFinal));
        }
        filterTask.setIdPerson(idPerson);
        filterTask.setMaxResults(maxResults);
        filterTask.setCurrentPage(page);
    }

    private void criaRetornoFake(TaskTransferDTO taskTransferDTO) {
        TaskDTO task = new TaskDTO();
        task.setCreationDate(new Date());
        task.setDateConclusion(new Date());
        task.setStatus(true);
        task.setId(10);
        task.setDescription("Teste de tarefa");
        task.setTitle("Tarefa teste");

        taskTransferDTO.getTasks().add(task);
        taskTransferDTO.setTotal(1);
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
        filtroTarefa.setIdPerson(idUsuario);
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

        completeFields(task, newTask);

        this.taskService.saveTask(newTask);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand("").toUri();

        return ResponseEntity.created(uri).build();
    }

    private void completeFields(TaskDTO task, Task newTask) {
        newTask.setDescriptionTask(task.getDescription());
        newTask.setActive(task.isStatus());
        newTask.setDateTask(task.getDateTask());
        newTask.setDateCreationTask(new Date());
        newTask.setTitleTask(task.getTitle());
        newTask.setPerson(task.getPerson());
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "Altera os dados da entidade Tarefa")
    public void updateTask(@RequestBody Task tarefa){

        try {
            this.taskService.update(tarefa);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Remove os dados da entidade Tarefa")
    public void removeTask(@PathVariable Integer id){

        try {
            this.taskService.remove(id);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

}
