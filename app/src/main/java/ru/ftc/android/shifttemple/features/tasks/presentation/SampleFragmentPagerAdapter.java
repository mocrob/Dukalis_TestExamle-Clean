package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Взять", "Взятые", "Созданные" };
    private Context context;
    private TaskFragment bf1 = new TaskFragment(0);
    private TaskFragment bf2 = new TaskFragment(1);
    private TaskFragment bf3 = new TaskFragment(2);

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
                try {
                    bf1.getActivity().getSupportFragmentManager().beginTransaction().remove(bf1).commit();
                }
                catch (NullPointerException npe){
                }
                try {
                    bf2.getActivity().getSupportFragmentManager().beginTransaction().remove(bf2).commit();
                }
                catch (NullPointerException npe){
                }
                try {
                    bf3.getActivity().getSupportFragmentManager().beginTransaction().remove(bf3).commit();
                }
                catch (NullPointerException npe){
                }
                bf1 = new TaskFragment(position);
                return bf1;
            case 1:
                try {
                    bf1.getActivity().getSupportFragmentManager().beginTransaction().remove(bf1).commit();
                }
                catch (NullPointerException npe){
                }
                try {
                    bf2.getActivity().getSupportFragmentManager().beginTransaction().remove(bf2).commit();
                }
                catch (NullPointerException npe){
                }
                try {
                    bf3.getActivity().getSupportFragmentManager().beginTransaction().remove(bf3).commit();
                }
                catch (NullPointerException npe){
                }
                bf2 = new TaskFragment(position);
                return bf2;
            case 2:
                try {
                    bf1.getActivity().getSupportFragmentManager().beginTransaction().remove(bf1).commit();
                }
                catch (NullPointerException npe){
                }
                try {
                    bf2.getActivity().getSupportFragmentManager().beginTransaction().remove(bf2).commit();
                }
                catch (NullPointerException npe){
                }
                try {
                    bf3.getActivity().getSupportFragmentManager().beginTransaction().remove(bf3).commit();
                }
                catch (NullPointerException npe){
                }
                bf3 = new TaskFragment(position);
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
