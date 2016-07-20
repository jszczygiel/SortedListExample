package com.jszczygiel.sortedlistexample.ui.view;

import com.jszczygiel.foundation.views.interfaces.BaseFragment;
import com.jszczygiel.sortedlistexample.ui.presentation.MainPresenter;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.BaseViewModel;

import java.util.List;

public interface MainFragment extends BaseFragment<MainPresenter> {
    void addOrUpdate(BaseViewModel baseViewModel);

    void remove(BaseViewModel baseViewModel);

    List<BaseViewModel> findById(String id);
}
