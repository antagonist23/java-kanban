package model;

public class SubTask extends Task {

    private String nameEpicTask;

    /**
     * Конструктор внутреннего класса для создания model.SubTask подзадач Epic задач
     */
    public SubTask(String nameEpicTask, String nameSubTask, String descriptionSubTask, String statusSubTask) {
        super(nameSubTask, descriptionSubTask, statusSubTask);
        this.nameEpicTask = nameEpicTask;
    }

    /**
     * Конструктор для копирования model.SubTask подзадач Epic задач
     */
    public SubTask(SubTask subtask) {
        this(subtask.nameEpicTask, subtask.getName(), subtask.getDescription(), subtask.getStatus());
    }
    /**
     * Сеттер и геттер
     */
    public String getNameEpicTask() {
        return nameEpicTask;
    }

    public void setNameEpicTask(String nameEpicTask) {
        this.nameEpicTask = nameEpicTask;
    }
    @Override
    public String toString() {
        return "ID подзадачи model.SubTask=\"" + getId() + "\", Название Epic задачи=\"" + nameEpicTask
                + "\", Название подзадачи=\"" + getName() + "\", Описание=\"" + getDescription() + "\", Статус=\"" + getStatus()
                + "\"";
    }
}



