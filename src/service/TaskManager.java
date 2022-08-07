package service;

import model.Epic;
import model.SubTask;
import model.Task;
import model.TaskStatus;

import java.util.List;

public interface TaskManager {

    void printAllTasks();

    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubTask(SubTask subTask);

    Task getTask(Integer id);

    SubTask getSubTask(Integer id);

    Epic getEpic(Integer id);

    void updateTask(Task task, Integer id, TaskStatus status);

    void updateSubTask(SubTask subTask, Integer id, TaskStatus status);

    void updateEpic(Epic epic);

    String subTasksInEpicToString(Epic epic);

    void deleteTask(Integer id);

    void deleteSubTask(Integer id);

    void deleteEpic(Integer id);

    void deleteAllTasks();

    List<Task> getHistory();

}
