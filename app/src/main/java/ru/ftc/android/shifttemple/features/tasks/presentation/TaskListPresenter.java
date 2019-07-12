package ru.ftc.android.shifttemple.features.tasks.presentation;

//import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.features.tasks.domain.TasksInteractor;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Success;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.network.Carry;

final class TaskListPresenter extends MvpPresenter<TaskListView> {

    private final TasksInteractor interactor;

    private int tabPosition;

    TaskListPresenter(TasksInteractor interactor, int tabPosition){this.interactor = interactor; this.tabPosition = tabPosition;}

    public void reload(int tabPosition){
        switch (tabPosition){
            case 0:
                loadTasks();
                break;
            case 1:
                loadAppliedTasks();
                break;
            case 2:
                loadCreatedTasks();
                break;
            default:
                break;
        }
    }
    @Override
    protected void onViewReady(){
        switch (tabPosition){
            case 0:
                loadTasks();
                break;
            case 1:
                loadAppliedTasks();
                break;
            case 2:
                loadCreatedTasks();
                break;
            default:
                break;
        }

    }

    private void loadAppliedTasks(){
        view.showProgress();
        interactor.loadAppliedTasks(new Carry<List<Task>>() {
            @Override
            public void onSuccess(List<Task> result) {
                view.showTaskList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }
    private void loadCreatedTasks(){
        view.showProgress();
        interactor.loadCreatedTasks(new Carry<List<Task>>() {
            @Override
            public void onSuccess(List<Task> result) {
                view.showTaskList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    private void loadTasks(){
        view.showProgress();
        interactor.loadTasks(new Carry<List<Task>>() {
            @Override
            public void onSuccess(List<Task> result) {
                view.showTaskList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    void onTaskSelected(Task task)
    {
        view.showProgress();
        interactor.loadTask(String.valueOf(task.getId()), new Carry<Task>() {
            @Override
            public void onSuccess(Task result) {
                view.hideProgress();
                //TO DO Show card of task
                view.openFullTaskCard(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }
 void onAppliedTaskSelected(final Task task)
    {
        view.showProgress();
        interactor.loadTask(String.valueOf(task.getId()), new Carry<Task>() {
            @Override
            public void onSuccess(Task result) {
                view.hideProgress();
                //TO DO Show card of task
               //
                view.openFullAppliedTaskCard(task);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }
 void onCreatedTaskSelected(final Task task)
    {
        view.showProgress();
        interactor.loadTask(String.valueOf(task.getId()), new Carry<Task>() {
            @Override
            public void onSuccess(Task result) {
                view.hideProgress();
                //TO DO Show card of task
                view.openFullCreatedTaskCard(task);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    void onTaskLongClicked(Task task){
        view.showProgress();
        /*for Example apply task to user*/
        interactor.applyTask(String.valueOf(task.getId()),new Carry<Task>(){

            @Override
            public void onSuccess(Task result) {
                loadTasks();/*it's for example do something, may be change color to green*/
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    private final AtomicInteger atomicInteger = new AtomicInteger();

    public void onOpenCreateTaskClicked(){
        view.openCreateTaskScreen();
    }
    public void onCreateTaskClicked(){
        /*code bellow is fake, change to call create task activity and more*/
        int id = atomicInteger.incrementAndGet();
        String name = "Name_"+id;
        String author = "Author_"+id;
        int pages = 7*id;

        Task task = new Task(10,"fake");
        interactor.createTask(task, new Carry<Task>() {
            @Override
            public void onSuccess(Task result) {
                /**/
                loadTasks();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }
    public void createTask(Task task)
    {
        interactor.createTask(task, new Carry<Task>(){
            @Override
            public void onSuccess(Task result) {
                view.showSuccess();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError("");
            }
        });
    }

    public void applyTask(Task task){
        interactor.applyTask(String.valueOf(task.getId()), new Carry<Task>() {
            @Override
            public void onSuccess(Task result) {

            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError("");
            }
        });
    }

    public void cancelTask(Task task){
        interactor.cancelTask(String.valueOf(task.getId()), new Carry<Task>() {
            @Override
            public void onSuccess(Task result) {

            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError("");
            }
        });
    }
    public void doneTask(Task task){
        interactor.completeTask(String.valueOf(task.getId()), new Carry<Task>() {
            @Override
            public void onSuccess(Task result) {

            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError("");
            }
        });
    }
    public void complainTask(Task task){
        interactor.complainTask(String.valueOf(task.getId()), new Carry<Task>() {
            @Override
            public void onSuccess(Task result) {

            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError("");
            }
        });
    }
}
