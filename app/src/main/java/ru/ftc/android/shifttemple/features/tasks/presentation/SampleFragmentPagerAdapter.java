package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TaskFragment bf1 = new TaskFragment();
                return bf1;
            case 1:
                TaskFragment bf2 = new TaskFragment();
                return bf2;
            case 2:
                TaskFragment bf3 = new TaskFragment();
                return bf3;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // генерируем заголовок в зависимости от позиции
        return tabTitles[position];
    }
}
