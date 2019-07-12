package ru.ftc.android.shifttemple.features.tasks.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import android.content.Intent;

import com.google.android.material.tabs.TabLayout;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.login.domain.model.User;

import static ru.ftc.android.shifttemple.features.tasks.presentation.TaskFragment.fillUser;

public class MainActivity extends AppCompatActivity {

    public static void start(final Context context, User user){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        fillUser(user);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получаем ViewPager и устанавливаем в него адаптер
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(
                new SampleFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));

        // Передаём ViewPager в TabLayout
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

}
