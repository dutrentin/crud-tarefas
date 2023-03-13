package br.com.poc.util;

import br.com.poc.dto.PersonDTO;
import br.com.poc.dto.TaskDTO;
import br.com.poc.entidade.Person;
import br.com.poc.entidade.Task;

public class CastTaskDTO {

    public static TaskDTO castTaskToTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setId(task.getId());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setDateConclusion(task.getDateConclusion());
        taskDTO.setDateTask(task.getDateTask());
        taskDTO.setCreationDate(task.getCreationDate());

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(task.getPerson().getName());
        personDTO.setEmail(task.getPerson().getEmail());
        personDTO.setId(task.getPerson().getId());

        taskDTO.setPerson(personDTO);

        return taskDTO;
    }

    public static TaskDTO castTaskDTOToTask(TaskDTO taskDTO){
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setId(taskDTO.getId());
        task.setStatus(taskDTO.isStatus());
        task.setDateConclusion(taskDTO.getDateConclusion());
        task.setDateTask(taskDTO.getDateTask());
        task.setCreationDate(taskDTO.getCreationDate());

        Person person = new Person();
        person.setName(taskDTO.getPerson().getName());
        person.setEmail(taskDTO.getPerson().getEmail());
        person.setId(taskDTO.getPerson().getId());

        task.setPerson(person);

        return taskDTO;
    }
}
