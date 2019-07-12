package ru.ftc.android.shifttemple;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;

public abstract class BaseFragment extends Fragment {

    protected abstract <T extends MvpView> MvpPresenter<T> getPresenter();

    protected abstract <T extends MvpView> T getMvpView();

    private MvpPresenter<MvpView> presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = getPresenter();
        presenter.attachView(getMvpView());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}