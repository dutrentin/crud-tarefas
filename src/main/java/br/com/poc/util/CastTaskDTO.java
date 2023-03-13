package br.com.poc.util;

import br.com.poc.dto.TaskDTO;
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
        return taskDTO;
    }
}
