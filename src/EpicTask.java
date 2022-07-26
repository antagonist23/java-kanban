import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс для создания Epic задач
 */
public class EpicTask extends Task {
    private final ArrayList<SubTask> subTasks;

    /**
     * Конструктор для создания Epic задач
     */
    EpicTask(String nameEpicTask, String descriptionEpicTask, ArrayList<SubTask> subTasks) {
        super(nameEpicTask, descriptionEpicTask);
        this.setStatus(Manager.getEpicTaskStatus(subTasks)); // 4. Метод для управления статусом для эпик задач.
        this.subTasks = subTasks;
    }

    /**
     * Конструктор для копирования Epic задач
     */
    EpicTask(EpicTask epicTask) {
        this(epicTask.getName(), epicTask.getDescription(), epicTask.subTasks);
    }

    /**
     * get метод
     */
    ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    @Override
    public String toString() {
        return "ID задачи Epic=\"" + getId() + "\", Название Epic задачи=\"" + getName() + "\", Описание=\"" + getDescription() + "\""
                + ", " + Arrays.toString(subTasks.toArray()) + ", Статус=\"" + getStatus() + "\"";
    }
}