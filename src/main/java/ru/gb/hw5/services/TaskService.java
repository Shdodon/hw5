package ru.gb.hw5.services;

import ru.gb.hw5.domain.Task;
import ru.gb.hw5.domain.TaskStatus;
import ru.gb.hw5.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class TaskService {

    /**
     * Поле доступа к базе данных
     */
    private TaskRepository taskRepository;

    /**
     * Метод просмотра всех задач
     * @return список всех задач
     */
    public List<Task> getAllTasks() {
        System.out.println(LocalDateTime.now());
        return taskRepository.findAll();
    }

    /**
     * Метод добавления задачи
     * @param task задача, которую добавляем
     * @return задача, которую добавили
     */
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Метод получения списка задач со статусом status
     * @param status статус задачи
     * @return список задач со статусом status
     */
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status);
    }

    /**
     * Метод поиска задачи с определенным id
     * @param id задачи, которую ищем
     * @return найденная задача
     */
    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    /**
     * Метод удаления задачи по id
     * @param id задачи, которую необходимо удалить
     */
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    /**
     * Метод изменения статуса задачи
     * @param id задачи, которую меняем
     * @param detailedTask задача, которую меняем
     * @return измененную задача
     */
    public Task updateTaskStatus (Long id, Task detailedTask) {
        Optional<Task> foundTask = taskRepository.findById(id);
        if (foundTask.isPresent()) {
            Task task = foundTask.get();
            task.setStatus(detailedTask.getStatus());
            return taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Задача с id=" + id + " не найдена!");}
    }
}
