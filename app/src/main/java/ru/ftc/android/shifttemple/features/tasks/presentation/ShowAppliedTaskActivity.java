package ru.ftc.android.shifttemple.features.tasks.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;

public class ShowAppliedTaskActivity extends BaseActivity implements TaskListView {

    public static void start(final Context context, Task task) {
        Intent intent = new Intent(context, ShowAppliedTaskActivity.class);
        intent.putExtra(Task.class.getSimpleName(), task);
        fillTask(task);
        context.startActivity(intent);

    }

    private TaskListPresenter presenter;

    @Override
    protected MvpPresenter<TaskListView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this,0);
        return presenter;
    }

    @Override
    protected  MvpView  getMvpView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_applied_task);
        initView();
    }

    private TextView addressText;
    private TextView descShort;
    private TextView descFull;
    private TextView updatedText;
    private Button cancelButton;
    private static Task task;
    public static void fillTask(Task tasker){
        task = tasker;
    }
    //Bundle arguments = getIntent().getExtras();
    //private Task task = (Task) arguments.getSerializable(Task.class.getSimpleName());

    private void initView(){
        //addressText = findViewById(R.id.cityFullCardTextView);
        //descShort = findViewById(R.id.shortDescFullCardTextView);
        //descFull = findViewById(R.id.fullDescFullCardTextView);
        //updatedText = findViewById(R.id.udateDateTimeText);
        cancelButton = findViewById(R.id.cancelAppliedButton);
        //addressText.setText(task.getAddress());
        //descShort.setText(task.getDescriptionShort());
        //descFull.setText(task.getDescriptionFull());
        //updatedText.setText(task.getUpdateDateTime());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.cancelTask(task);
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showTaskList(List<Task> list) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void openCreateTaskScreen() {

    }

    @Override
    public void openFullTaskCard(Task task) {

    }

    @Override
    public void openFullCreatedTaskCard(Task task) {

    }

    @Override
    public void openFullAppliedTaskCard(Task task) {

    }

    @Override
    public void showSuccess() {

    }
}
