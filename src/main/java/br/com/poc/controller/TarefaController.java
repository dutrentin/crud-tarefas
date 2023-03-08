package br.com.poc.controller;

import br.com.poc.entidade.Tarefa;
import br.com.poc.exception.GenericPersistenciaException;
import br.com.poc.service.TarefaService;
import io.swagger.annotations.ApiOperation;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    private static final Logger log = Logger.getLogger(TarefaController.class);

    @Autowired
    private TarefaService tarefaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Tarefa>> getTarefas() throws IOException, BiffException {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.listar());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tarefa> getTarefas(@PathVariable Integer id) throws IOException, BiffException {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.findTarefaById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Tarefa tarefa) {

        this.tarefaService.salvar(tarefa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand("").toUri();

        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/alterar/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Altera os dados da entidade Tarefa")
    public void alterar(@RequestBody Tarefa tarefa){

        try {
            this.tarefaService.alterar(tarefa);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/remover/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Remove os dados da entidade Tarefa")
    public void remover(@PathVariable Integer id){

        try {
            this.tarefaService.remover(id);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }




}
