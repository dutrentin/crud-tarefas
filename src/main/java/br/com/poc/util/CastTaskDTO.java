package br.com.poc.util;

import br.com.poc.dto.TaskDTO;
import br.com.poc.entidade.Task;

public class CastTaskDTO {

    public static TaskDTO castTaskToTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(task.getTitleTask());
        taskDTO.setDescription(task.getDescriptionTask());
        taskDTO.setId(task.getId());
        taskDTO.setStatus(task.getActive());
        taskDTO.setDateConclusion(task.getDateConclusionTask());
        taskDTO.setDateTask(task.getDateTask());
        taskDTO.setCreationDate(task.getDateCreationTask());
        return taskDTO;
    }

    public static TaskDTO castTaskDTOToTask(TaskDTO taskDTO){
        Task task = new Task();
        task.setTitleTask(taskDTO.getTitle());
        task.setDescriptionTask(taskDTO.getDescription());
        task.setId(taskDTO.getId());
        task.setActive(taskDTO.isStatus());
        task.setDateConclusionTask(taskDTO.getDateConclusion());
        task.setDateTask(taskDTO.getDateTask());
        task.setDateCreationTask(taskDTO.getCreationDate());
        return taskDTO;
    }
}
