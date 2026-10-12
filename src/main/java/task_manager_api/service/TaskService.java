package task_manager_api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import task_manager_api.exception.TaskNotFoundException;
import task_manager_api.domain.Task;
import task_manager_api.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() ->
                    new TaskNotFoundException("Tarefa não encontrada: " + id));
}
    public Task updateTask(Long id, Task task) {
    Task existingTask = getTaskById(id);

    existingTask.setTitle(task.getTitle());
    existingTask.setDescription(task.getDescription());
    existingTask.setCompleted(task.getCompleted());

        return taskRepository.save(existingTask);
}
    public void deleteTask(Long id) {
    Task existingTask = getTaskById(id);

    taskRepository.delete(existingTask);
}
}