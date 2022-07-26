package model;

import manager.Manager;

public class Task {
    /**
     * Класс для создания model.Task задач
     */
    private int id;
    private String name;
    private String description;
    private String status;
    /**
     * Конструктор для создания model.Task задач
     */
    public Task(String nameTask, String descriptionTask, String statusTask) {
        this.id = Manager.getId() + 1;
        Manager.setId(this.id);
        this.name = nameTask;
        this.description = descriptionTask;
        this.status = statusTask;
    }

    /**
     * Конструктор для создания задач наследников Epic задач и model.SubTask подзадач
     */
    Task(String nameTask, String descriptionTask) {
        this.id = Manager.getId() + 1;
        Manager.setId(this.id);
        this.name = nameTask;
        this.description = descriptionTask;
    }

    /**
     * Конструктор для копирования model.Task задач
     */
    public Task(Task task) {
        this(task.name, task.description, task.status);
    }

    /**
     * get и set методы
     */
    int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
    public void setId(int id) {
        this.id = id;
    }
    String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID задачи model.Task=\"" + id + "\", Название задачи=\"" + name + "\", Описание=\"" + description
                + "\", Статус=\"" + status + "\"";
    }
}
