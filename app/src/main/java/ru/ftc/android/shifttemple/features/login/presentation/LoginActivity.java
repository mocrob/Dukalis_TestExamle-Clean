package ru.ftc.android.shifttemple.features.login.presentation;

import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.features.tasks.presentation.MainActivity;

public final class LoginActivity extends BaseActivity implements LoginView {

    private LoginPresenter presenter;

    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initView();
    }

    private void initView() {
        final RecyclerView recyclerView = findViewById(R.id.users_recycle_view);
        final Button nextButton = findViewById(R.id.next_button);
        final FloatingActionButton floatingActionButton = findViewById(R.id.Fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onCreateUserClick();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onNavigateNextClick(adapter.getSelectedUser());
            }
        });

        adapter = new UserAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showUserList(List<User> userList) {
        adapter.setUserList(userList);
    }

    @Override
    public void openBookListScreen() {
        /*TaskFragment.start(this);*/
    }

    @Override
    public void openTaskListScreen(User user) {
        MainActivity.start(this, user);
    }

    @Override
    public void showNotSelectedUserError() {
        Toast.makeText(this, getText(R.string.not_selected_user_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void openCreateUserScreen() {
        CreateUserActivity.start(this);
    }

    @Override
    public void showError() {

    }

    @Override
    protected MvpPresenter<LoginView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }
}