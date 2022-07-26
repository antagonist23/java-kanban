public class SubTask extends Task {
    private final String nameEpicTask;

    /**
     * Конструктор внутреннего класса для создания SubTask подзадач Epic задач
     */
    SubTask(String nameEpicTask, String nameSubTask, String descriptionSubTask, String statusSubTask) {
        super(nameSubTask, descriptionSubTask, statusSubTask);
        this.nameEpicTask = nameEpicTask;
    }

    /**
     * Конструктор для копирования SubTask подзадач Epic задач
     */
    SubTask(SubTask subtask) {
        this(subtask.nameEpicTask, subtask.getName(), subtask.getDescription(), subtask.getStatus());
    }

    @Override
    public String toString() {
        return "ID подзадачи SubTask=\"" + getId() + "\", Название Epic задачи=\"" + nameEpicTask
                + "\", Название подзадачи=\"" + getName() + "\", Описание=\"" + getDescription() + "\", Статус=\"" + getStatus()
                + "\"";
    }
}



