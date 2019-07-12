package ru.ftc.android.shifttemple.features.tasks.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.login.domain.model.User;

import static ru.ftc.android.shifttemple.features.tasks.presentation.TaskFragment.fillUser;

public class MainActivity extends AppCompatActivity {

    public static void start(final Context context, User user){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        context.startActivity(intent);
        fillUser(user);
        activeUser(user);
    }

    private TextView karma;
    private TextView dukalis;
    private static User user;
    public static void activeUser(User userit){
        user = userit;
    }
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        karma = findViewById(R.id.userCarmaAmountText);
        dukalis = findViewById(R.id.userDukalisAmountText);
        Bundle arguments = getIntent().getExtras();
        user = (User) arguments.getSerializable(User.class.getSimpleName());

        karma.setText(String.valueOf(user.getKarma()));
        dukalis.setText(String.valueOf(user.getDukalises()));

        // Получаем ViewPager и устанавливаем в него адаптер
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(
                new SampleFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));

        // Передаём ViewPager в TabLayout
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

}
