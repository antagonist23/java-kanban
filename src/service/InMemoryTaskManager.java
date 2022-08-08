package service;

import model.Epic;
import model.SubTask;
import model.Task;
import model.TaskStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private Integer id;
    private Map<Integer, Task> task;
    private Map<Integer, SubTask> subTask;
    private Map<Integer, Epic> epic;
    private HistoryManager historyManager;


    public InMemoryTaskManager() {
        this.id = 1;
        this.task = new HashMap<>();
        this.subTask = new HashMap<>();
        this.epic = new HashMap<>();
        this.historyManager = Managers.getDefaultHistory();
    }

    // Обновление уникального ID для задач
    private Integer generateId() {
        return id++;
    }

    @Override
    public void printAllTasks() {
        task.entrySet().forEach(entry -> {
            System.out.println(entry.getValue());
        });
        epic.entrySet().forEach(entry -> {
            System.out.println(entry.getValue());
        });
        subTask.entrySet().forEach(entry -> {
            System.out.println(entry.getValue());
        });
    }

    @Override
    public void addTask(Task task) {
        task.setId(generateId());
        this.task.put(task.getId(), task);
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(generateId());
        this.epic.put(epic.getId(), epic);
    }

    @Override
    public void addSubTask(SubTask subTask) {
        subTask.setId(generateId());
        this.subTask.put(subTask.getId(), subTask);
        subTask.getEpic().setSubTaskId(subTask.getId());
    }

    @Override
    public Task getTask(Integer id) {
        if (task.containsKey(id)) {
            historyManager.add(task.get(id));
            return task.get(id);
        } else {
            return null;
        }
    }

    @Override
    public SubTask getSubTask(Integer id) {
        if (subTask.containsKey(id)) {
            historyManager.add(subTask.get(id));
            return subTask.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Epic getEpic(Integer id) {
        if (epic.containsKey(id)) {
            historyManager.add(epic.get(id));
            return epic.get(id);
        } else {
            return null;
        }
    }
// тут надо исправить
@Override
public Object updateTask(Task update) {
    if (task.isEmpty()) {
        System.out.println("Список задач пуст");
    } else if (task.containsKey(update.getId())) {
        task.put(update.getId(), update);
        System.out.println("Обновление выполнено");
        return update;
    } else {
        System.out.println("Такой задачи нет");
    }
    return null;
}
    @Override
    public Object updateSubTask(SubTask update) {
        if (subTask.isEmpty()) {
            System.out.println("Список подзадач пуст");
        } else if (subTask.containsKey(update.getId())) {
            task.put(update.getId(), update);
            System.out.println("Обновление выполнено");
            return update;
        } else {
            System.out.println("Такой подзадачи нет");
        }
        return null;
    }
    // до сюда исправить
    @Override

    public void updateEpic(Epic epic) {

        Epic pastObjEpic = this.epic.get(epic.getId());

        List<Integer> subTasks = pastObjEpic.getSubTasks();

        epic.setSubTasks(subTasks);

        this.epic.put(epic.getId(), epic);

        for (Integer value : epic.getSubTasks()) {

            SubTask subTask = this.subTask.get(value);

            subTask.setEpic(epic);

        }

        epic.setStatus(calculateEpicStatus(epic));

    }

    @Override
    public String subTasksInEpicToString(Epic epic) {
        if (epic.getSubTasks() == null) {
            return "У эпика отсутствуют подзадачи";
        } else {
            Map<Integer, SubTask> tempMap = new HashMap<>();
            for (Integer taskId : epic.getSubTasks()) {
                tempMap.put(taskId, subTask.get(taskId));
            }
            StringBuilder stb = new StringBuilder();
            for (Map.Entry<Integer, SubTask> entry : tempMap.entrySet()) {
                stb.append(entry.getValue()).append("\n");
            }
            return stb.toString();
        }
    }

    @Override
    public void deleteTask(Integer id) {
        if (task.isEmpty()) {
            System.out.println("Список задач трекера пуст.");
            return;
        }
        if (!task.containsKey(id)) {
            System.out.println("Задача с указанным номером отсутствует в списке трекера.");
            return;
        }
        task.remove(id);
    }

    @Override
    public void deleteSubTask(Integer id) {
        if (subTask.isEmpty()) {
            System.out.println("Список подзадач трекера пуст.");
            return;
        }
        if (!subTask.containsKey(id)) {
            System.out.println("Подзадача с указанным номером отсутствует в списке трекера.");
            return;
        }
        SubTask subTask = this.subTask.get(id);
        Epic epic = subTask.getEpic();
        List<Integer> subTasks = epic.getSubTasks();
        subTasks.remove(id);
        this.subTask.remove(id);
        epic.setStatus(calculateEpicStatus(epic));
    }

    @Override
    public void deleteEpic(Integer id) {
        if (epic.isEmpty()) {
            System.out.println("Список эпиков трекера пуст.");
            return;
        }
        if (!epic.containsKey(id)) {
            System.out.println("Эпик с указанным номером отсутствует в списке трекера.");
            return;
        }
        Epic epic = this.epic.get(id);
        List<Integer> subTasks = epic.getSubTasks();
        for (Integer subTask : subTasks) {
            this.subTask.remove(subTask);
        }
        this.epic.remove(id);
    }

    @Override
    public void deleteAllTasks() {
        boolean allTasksClean = task.isEmpty() && subTask.isEmpty() && epic.isEmpty();
        if (!allTasksClean) {
            task.clear();
            subTask.clear();
            epic.clear();
            System.out.println("Список задач трекера очищен.");
        } else {
            System.out.println("Список задач трекера уже пуст.");
        }
    }

    private TaskStatus calculateEpicStatus(Epic epic) {
        int amountSubtask = 0;
        int amountStatusNew = 0;
        int amountStatusDone = 0;

        Map<Integer, SubTask> result = new HashMap<>();

        for (Integer id : epic.getSubTasks()) {
            result.put(id, subTask.get(id));
            amountSubtask++;
        }

        for (SubTask subTask : result.values()) {
            if (subTask.getStatus().equals(TaskStatus.NEW)) {
                amountStatusNew++;
            } else if (subTask.getStatus().equals(TaskStatus.DONE)) {
                amountStatusDone++;
            }
        }

        if ((amountSubtask == amountStatusNew) || epic.getSubTasks().isEmpty()) {
            return TaskStatus.NEW;
        } else if (amountSubtask == amountStatusDone) {
            return TaskStatus.DONE;
        }
        return TaskStatus.IN_PROGRESS;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}
