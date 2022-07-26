import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Класс для объекта-менеджера
 */
public class Manager {
    private static int Id = 0;

    private final TreeMap<Integer, Task> taskStorage = new TreeMap<>();

    private final TreeMap<Integer, EpicTask> epicTaskStorage = new TreeMap<>();

    private final TreeMap<Integer, SubTask> subTaskStorage = new TreeMap<>();

    /**
     * get и set методы
     */
    static int getId() {
        return Id;
    }

    static void setId(int id) {
        Id = id;
    }

    TreeMap<Integer, Task> getTaskStorage() {
        return taskStorage;
    }

    TreeMap<Integer, EpicTask> getEpicTaskStorage() {
        return epicTaskStorage;
    }

    TreeMap<Integer, SubTask> getSubTaskStorage() {
        return subTaskStorage;
    }

    /**
     * 1. Методы для сохранения задач разных типов - 3 шт
     */

   //метод для сохранения таска
    void saveTaskToStorage(Task task) {
        switch (task.getClass().toString()) {
            case "class Task": {
                taskStorage.put(getId(), task);
                break;
            }
        }
    }
    //метод для сохранения Сабтаска
    void saveSubTaskToStorage(SubTask subTask) {
        switch (subTask.getClass().toString()) {
            case "class SubTask": {
                subTaskStorage.put(getId(), subTask);
                break;
            }
        }
    }
    //метод для сохранения ЭпикТаска
    void saveEpicTaskToStorage(EpicTask epicTask) {
        switch (epicTask.getClass().toString()) {
            case "class EpicTask": {
                epicTaskStorage.put(getId(), epicTask);
                break;
            }
        }
    }


    /**
     * 2. Методы для каждого из типа задач(Задача/Эпик/Подзадача):
     * 2.1 Получение списка всех задач;
     */

    //метод для таска
    ArrayList<Task> getCompleteListOfAnyTask(TreeMap<Integer, Task> task) {
        ArrayList<Task> completeListOfAnyTask = new ArrayList<>();

        for (Integer key : task.keySet()) {
            completeListOfAnyTask.add(task.get(key));
        }
        return completeListOfAnyTask;
    }
   // метод для субтаска
    ArrayList<SubTask> getCompleteListOfAnySubTask(TreeMap<Integer, SubTask> subTask) {
        ArrayList<SubTask> completeListOfAnySubTask = new ArrayList<>();

        for (Integer key : subTask.keySet()) {
            completeListOfAnySubTask.add(subTask.get(key));
        }
        return completeListOfAnySubTask;
    }
    // метод для эпика
    ArrayList<EpicTask> getCompleteListOfAnyEpicTasks(TreeMap<Integer, EpicTask> epicTask) {
        ArrayList<EpicTask> completeListOfAnyEpicTask = new ArrayList<>();

        for (Integer key : epicTask.keySet()) {
            completeListOfAnyEpicTask.add(epicTask.get(key));
        }
        return completeListOfAnyEpicTask;
    }

    /**
     * 2.2 Удаление всех задач;
     */
    //удаление таска
    void deleteAllTasks(TreeMap<Integer, Task> task) {
        task.clear();
    }
    //удаление эпиктаска
    void deleteAllEpicTasks(TreeMap<Integer, EpicTask> epicTask) {
        epicTask.clear();
    }
    //удаление субтаска
    void deleteAllSubTasks(TreeMap<Integer, SubTask> subTask) {
        subTask.clear();
    }

    /**
     * 2.3 Получение по идентификатору;
     */
    Object getTaskOfAnyTypeById(int id) {
        Object taskOfAnyKind = null;

        if (taskStorage.get(id) != null) {
            taskOfAnyKind = taskStorage.get(id);
        } else if (epicTaskStorage.get(id) != null) {
            taskOfAnyKind = epicTaskStorage.get(id);
        } else if (subTaskStorage.get(id) != null) {
            taskOfAnyKind = subTaskStorage.get(id);
        }
        return taskOfAnyKind;
    }


    /**
     * 2.4 Создание. Сам объект должен передаваться в качестве параметра;
     */
    Object createCopyOfTaskOfAnyType(Object object) {
        switch (object.getClass().toString()) {
            case "class Task": {
                taskStorage.put(getId(), (Task) object);
                return new Task((Task) object);
            }
            case "class SubTask": {
                subTaskStorage.put(getId(), (SubTask) object);
                return new SubTask((SubTask) object);
            }
            case "class EpicTask": {
                epicTaskStorage.put(getId(), (EpicTask) object);
                return new EpicTask((EpicTask) object);
            }
            default:
                return null;
        }
    }

    /**
     * 2.5 Обновление. Новая версия объекта с верным идентификатором передаются в виде параметра;
     */
    void updateTaskOfAnyType(int id, Object object) {
        switch (object.getClass().toString()) {
            case "class Task": {
                taskStorage.put(id, (Task) object);
                break;
            }
            case "class EpicTask": {
                epicTaskStorage.put(id, (EpicTask) object);
                break;
            }
            case "class SubTask": {
                subTaskStorage.put(id, (SubTask) object);
                break;
            }
        }
    }

    /**
     * 2.6 Удаление по идентификатору.
     */
    void removeTaskOfAnyTypeById(int id) {
        for (Integer task : taskStorage.keySet()) {
            if (id == task) {
                taskStorage.remove(id);
                break;
            }
        }
        for (Integer epicTask : epicTaskStorage.keySet()) {
            if (id == epicTask) {
                epicTaskStorage.remove(id);
                break;
            }
        }
        for (Integer subTask : subTaskStorage.keySet()) {
            if (id == subTask) {
                subTaskStorage.remove(id);
                break;
            }
        }
    }

    /**
     * 3. Дополнительные методы:
     * 3.1 Получение списка всех подзадач определённого эпика.
     */
    ArrayList<SubTask> getCompleteListOfSubTaskByEpicTask(EpicTask epicTask) {
        return epicTask.getSubTasks();
    }

    /**
     * 4. Метод для управления статусом для эпик задач.
     */
    static String getEpicTaskStatus(ArrayList<SubTask> subTasks) {
        String statusEpicTask;
        int countNew = 0;
        int countDone = 0;

        for (SubTask subTask : subTasks) {
            if (subTask.getStatus().equalsIgnoreCase("NEW")) {
                countNew++;
            }
            if (!subTask.getStatus().equalsIgnoreCase("DONE")) {
                countDone++;
            }
        }
/**
 * Если у эпика нет подзадач или все они имеют статус NEW | DONE, то статус должен быть NEW | DONE.
 * Во всех остальных случаях статус должен быть IN_PROGRESS.
 */
        if ((subTasks.isEmpty()) || (countNew == subTasks.size())) {
            statusEpicTask = "NEW";
        } else if (countDone == subTasks.size()) {
            statusEpicTask = "DONE";
        } else {
            statusEpicTask = "IN_PROGRESS";
        }
        return statusEpicTask;
    }
}