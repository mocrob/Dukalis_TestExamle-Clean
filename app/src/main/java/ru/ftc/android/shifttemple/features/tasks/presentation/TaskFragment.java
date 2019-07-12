package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ru.ftc.android.shifttemple.BaseFragment;
import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;

public final class TaskFragment extends BaseFragment implements TaskListView {

    public TaskFragment(int tabPosition) {
        this.tabPosition = tabPosition;
    }

    private int tabPosition;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button createTaskButton;
    private TaskAdapter adapter;
    //private String activeUserCity;

    private TaskListPresenter presenter;

    private static User user;

    public static void fillUser(User users){
        user = users;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        initView(view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_task_fragment, container, false);
    }

    private void initView(View view ){

        progressBar = view.findViewById(R.id.tasks_progress);
        recyclerView = view.findViewById(R.id.tasks_recycle_view);
        createTaskButton = view.findViewById(R.id.task_create_button);
        final FloatingActionButton floatingActionButton = view.findViewById(R.id.create_task_fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.onOpenCreateTaskClicked();
            }
        });

        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.reload(tabPosition);
                presenter.onCreateTaskClicked();
            }
        });

        adapter = new TaskAdapter(getContext(), new TaskAdapter.SelectTaskListener(){
            @Override
            public void onTaskSelect(Task task) {
                presenter.reload(tabPosition);
                switch (tabPosition){
                    case 0:
                        presenter.onTaskSelected(task);
                        break;
                    case 1:
                        presenter.onAppliedTaskSelected(task);
                        break;
                    case 2:
                        presenter.onCreatedTaskSelected(task);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTaskLongClick(Task task) {
                presenter.onTaskLongClicked(task);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    @Override
    protected MvpPresenter<TaskListView> getPresenter() {
        presenter = PresenterFactory.createPresenter(getContext(), tabPosition);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTaskList(List<Task> list) {
        adapter.setTasks(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void openCreateTaskScreen() {
        CreateTaskActivity.start(getContext(), user);
    }

    @Override
    public void openFullTaskCard(Task task) {
        ShowTaskActivity.start(getContext(), task);
    }

    @Override
    public void openFullCreatedTaskCard(Task task) {
        ShowCreatedTaskActivity.start(getContext(),task);
    }

    @Override
    public void openFullAppliedTaskCard(Task task) {
        ShowAppliedTaskActivity.start(getContext(),task);

    }

    @Override
    public void showSuccess() {

    }
}
