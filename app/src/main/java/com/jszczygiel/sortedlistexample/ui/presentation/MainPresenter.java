package com.jszczygiel.sortedlistexample.ui.presentation;

import com.jszczygiel.foundation.presenters.interfaces.BasePresenter;
import com.jszczygiel.sortedlistexample.ui.view.MainFragment;

public interface MainPresenter extends BasePresenter<MainFragment> {
    void onLoad();

    void addNewContact();

    void add100NewContacts();

    void onDelete(String id);
}
