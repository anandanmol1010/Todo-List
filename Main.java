import java.util.ArrayList;
import java.util.Scanner;

class Task{
    String name;
    boolean isCompleted;

    public Task(String name) {
        this.name = "[ ] "+name;
        this.isCompleted = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
        this.name = this.name.replace("[ ] ","[✔] ");
    }
}

interface todoWorking{
    void addTask(String TaskName);
    void removeTask(int r);
    void markCompleted(int r);
    void showTasks();
}

class TodoList implements todoWorking{
    String name;
    ArrayList<Task> tasks;


    public TodoList(String name) {
        this.name = name;
        this.tasks = new ArrayList<Task>();
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void addTask(String TaskName){
        tasks.add(new Task(TaskName));
    }
    @Override
    public void removeTask(int r){
        if(r>=0 && r<tasks.size()){
            tasks.remove(r);
        }
        else {
            Main.gap();
            System.out.println("Wrong Input!");
        }
    }
    @Override
    public void markCompleted(int r){
        if(r>=0 && r<tasks.size()){
            tasks.get(r).markCompleted();
        }
        else {
            Main.gap();
            System.out.println("Wrong Input!");
        }
    }
    @Override
    public void showTasks(){
        if(tasks.isEmpty()){
            System.out.println("No Tasks!");
        }
        else{
            for(int i=0;i<tasks.size();i++){
                System.out.println((i+1)+". "+tasks.get(i).getName());
            }
        }
    }
}


class TodoManage{
    ArrayList<TodoList> all_lists;

    public TodoManage() {
        this.all_lists = new ArrayList<TodoList>();;
    }

    public boolean isEmpty(){
        return all_lists.isEmpty();
    }

    public void showAllLists(){
        for(int i=0;i<all_lists.size();i++){
            System.out.println((i+1)+". "+all_lists.get(i).getName());
        }
    }

    public void removeTodoList(int r) {
        if(r>=0 && r<all_lists.size()){
            all_lists.remove(r);
        }
        else {
            Main.gap();
            System.out.println("Wrong Input!");
        }
    }

    public void addTodoList(String todoListName) {
        all_lists.add(new TodoList(todoListName));
    }

    public TodoList getTodolist(int r){
        if(r>=0 && r<all_lists.size()){
            return all_lists.get(r);
        }
        else {
            return null;
        }
    }
}



public class Main {
    public static void main(String[] args) {
        boolean running=true;
        Scanner sc = new Scanner(System.in);
        TodoManage todoLists = new TodoManage();
        while (running) {
            gap();

            System.out.println("---------Todo-List------------------");
            System.out.println("1. Create Todo List");
            System.out.println("2. Use Todo List");
            System.out.println("3. Remove Todo List");
            System.out.println("4. Quit");
            System.out.print("Enter choice: ");
            int n = sc.nextInt();

            gap();

            int i;
            boolean usingList;

            switch (n) {
                case 1:
                    System.out.println("Enter name of Todo List:- ");
                    String todo_list_name = sc.next();
                    todoLists.addTodoList(todo_list_name);
                    i = todoLists.all_lists.size()-1;

                    usingList = true;
                    while (usingList) {
                        gap();

                        TodoList selected = todoLists.getTodolist(i);

                        System.out.println("\n--- Using Todo List: " + selected.getName() + " ---");
                        System.out.println("1. Add task");
                        System.out.println("2. Remove task");
                        System.out.println("3. Mark task as completed");
                        System.out.println("4. Quit");
                        System.out.print("Enter choice: ");
                        int c = sc.nextInt();

                        gap();

                        switch (c) {
                            case 1:
                                System.out.print("Enter task name: ");
                                String taskName = sc.next();
                                selected.addTask(taskName);
                                break;

                            case 2:
                                if(selected.tasks.isEmpty()){
                                    System.out.println("No Task found");
                                }
                                else {
                                    selected.showTasks();
                                    System.out.print("Enter task number to remove: ");
                                    int r = sc.nextInt() - 1;
                                    selected.removeTask(r);
                                }
                                break;

                            case 3:
                                if(selected.tasks.isEmpty()){
                                    System.out.println("No Task found");
                                }
                                else {
                                    selected.showTasks();
                                    System.out.print("Enter task number to mark completed: ");
                                    int m = sc.nextInt() - 1;
                                    selected.markCompleted(m);
                                }
                                break;

                            case 4:
                                usingList = false;
                                break;
                            default:
                                System.out.println("Wrong Choice! Plz Retry\n");
                        }
                    }

                    break;

                case 2:
                    if (todoLists.isEmpty()) {
                        gap();
                        System.out.println("List is Empty");
                    } else {
                        todoLists.showAllLists();

                        System.out.print("Choose list number: ");
                        i = sc.nextInt() - 1;

                        usingList = true;
                        while (usingList) {

                            TodoList selected = todoLists.getTodolist(i);

                            gap();
                            if (selected.getName().isEmpty()) {
                                System.out.println("Wrong Choice! Plz Retry\n");
                            }
                            else {
                                System.out.println("--- Using Todo List: " + selected.getName() + " ---");
                                System.out.println("1. Add task");
                                System.out.println("2. Remove task");
                                System.out.println("3. Mark task as completed");
                                System.out.println("4. Quit");
                                System.out.print("Enter choice: ");
                                int c = sc.nextInt();

                                switch (c) {
                                    case 1:
                                        System.out.print("Enter task name: ");
                                        String taskName = sc.next();
                                        selected.addTask(taskName);
                                        break;

                                    case 2:
                                        if (selected.tasks.isEmpty()) {
                                            gap();
                                            System.out.println("No Task found");
                                        } else {
                                            selected.showTasks();
                                            gap();
                                            System.out.print("Enter task number to remove: ");
                                            int r = sc.nextInt() - 1;
                                            selected.removeTask(r);
                                        }
                                        break;

                                    case 3:
                                        if (selected.tasks.isEmpty()) {
                                            gap();
                                            System.out.println("No Task found");
                                        } else {
                                            selected.showTasks();
                                            System.out.print("Enter task number to mark completed: ");
                                            int m = sc.nextInt() - 1;
                                            selected.markCompleted(m);
                                        }
                                        break;

                                    case 4:
                                        usingList = false;
                                        break;
                                    default:
                                        gap();
                                        System.out.println("Wrong Choice! Plz Retry\n");
                                }
                            }
                        }
                    }
                    break;

                case 3:
                    if(todoLists.isEmpty()) {
                        gap();
                        System.out.println("List is Empty");
                    }

                    else {
                        todoLists.showAllLists();
                        System.out.println("Enter number of Todo which you want to remove");
                        int r = sc.nextInt()-1;
                        todoLists.removeTodoList(r);
                    }
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    gap();
                    System.out.println("Wrong Choice! Plz Retry\n");
            }
        }
    }

    public static void gap(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
