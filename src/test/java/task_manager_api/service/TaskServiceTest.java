package task_manager_api.service;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;
import task_manager_api.exception.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import task_manager_api.domain.Task;
import task_manager_api.repository.TaskRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    @Test
    void shouldReturnAllTasks() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);

        when(taskRepository.findAll()).thenReturn(List.of());

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(0, tasks.size());
    }

    @Test
    void shouldReturnTasksFromRepository() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);

        Task task = new Task(
                "Estudar Java",
                "Praticar testes unitários"
        );

        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(1, tasks.size());
        assertEquals("Estudar Java", tasks.get(0).getTitle());
        assertEquals(
                "Praticar testes unitários",
                tasks.get(0).getDescription()
        );
    }
    @Test
    void shouldReturnTaskById() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);

        Task task = new Task(
            "Estudar Java",
            "Praticar testes unitários"
             );
        task.setId(1L);

        when(taskRepository.findById(1L))
            .thenReturn(java.util.Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Estudar Java", result.getTitle());
}
    @Test
    void shouldThrowExceptionWhenTaskDoesNotExist() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskService taskService = new TaskService(taskRepository);

        when(taskRepository.findById(999L))
            .thenReturn(java.util.Optional.empty());

        assertThrows(
            TaskNotFoundException.class,
            () -> taskService.getTaskById(999L)
    );
}
@Test
void shouldUpdateTask() {
    TaskRepository taskRepository = mock(TaskRepository.class);
    TaskService taskService = new TaskService(taskRepository);

    Task existingTask = new Task(
            "Estudar Java",
            "Descrição antiga"
    );
    existingTask.setId(1L);

    Task updatedTask = new Task(
            "Estudar Spring Boot",
            "Descrição atualizada"
    );
    updatedTask.setCompleted(true);

    when(taskRepository.findById(1L))
            .thenReturn(java.util.Optional.of(existingTask));

    when(taskRepository.save(existingTask))
            .thenReturn(existingTask);

    Task result = taskService.updateTask(1L, updatedTask);

    assertEquals("Estudar Spring Boot", result.getTitle());
    assertEquals("Descrição atualizada", result.getDescription());
    assertEquals(true, result.getCompleted());
}
@Test
void shouldDeleteTask() {
    TaskRepository taskRepository = mock(TaskRepository.class);
    TaskService taskService = new TaskService(taskRepository);

    Task task = new Task(
            "Estudar Java",
            "Praticar testes unitários"
    );
    task.setId(1L);

    when(taskRepository.findById(1L))
            .thenReturn(java.util.Optional.of(task));

    taskService.deleteTask(1L);

    verify(taskRepository).delete(task);
}
@Test
void shouldCreateTask() {
    TaskRepository taskRepository = mock(TaskRepository.class);
    TaskService taskService = new TaskService(taskRepository);

    Task task = new Task(
            "Estudar Java",
            "Praticar testes unitários"
    );

    when(taskRepository.save(task)).thenReturn(task);

    Task result = taskService.createTask(task);

    assertEquals("Estudar Java", result.getTitle());
    assertEquals(
            "Praticar testes unitários",
            result.getDescription()
    );
}
}
