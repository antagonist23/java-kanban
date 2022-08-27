package model;

//Класс подзадачи
public class SubTask extends Task {
    Epic epic;    //Ссылка на родительский Эпик

    //Конструктор
    public SubTask(Integer num, String name, String details, Epic epic) {
        super(num, name, details, TaskType.SUBTASK);

        this.epic = epic;
    }

    public SubTask(String name, String details, Epic epic) {
        super(name, details, TaskType.SUBTASK);

        this.epic = epic;
    }

    //Получение Эпика
    public Epic getEpic() {
        return epic;
    }

    //Задание Эпика
    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    //Отображение задачи
    @Override
    public String toString() {
        return getNum() + "," + getType() + "," + getName() + "," + getStatus() + "," + getDetails() + "," + getEpic().getNum();
    }
}
