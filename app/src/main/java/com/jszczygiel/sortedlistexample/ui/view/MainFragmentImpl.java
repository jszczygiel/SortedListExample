package com.jszczygiel.sortedlistexample.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.jszczygiel.foundation.views.BaseFragmentImpl;
import com.jszczygiel.sortedlistexample.R;
import com.jszczygiel.sortedlistexample.ui.interaction.DataInteractor;
import com.jszczygiel.sortedlistexample.ui.presentation.MainPresenter;
import com.jszczygiel.sortedlistexample.ui.presentation.MainPresenterImpl;
import com.jszczygiel.sortedlistexample.ui.view.adapters.MainAdapter;
import com.jszczygiel.sortedlistexample.ui.view.viewholders.BaseViewHolder;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.BaseViewModel;

import java.util.List;

import butterknife.BindView;

public class MainFragmentImpl extends BaseFragmentImpl<MainPresenter> implements MainFragment {

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_list)
    RecyclerView recyclerView;
    private MainAdapter adapter;
    private BaseViewHolder.InteractionListener listener = new BaseViewHolder.InteractionListener() {
        @Override
        public void onDelete(BaseViewModel model) {
            getPresenter().onDelete(model.getId());
        }
    };

    @Override
    public MainPresenterImpl initializePresenter() {
        return new MainPresenterImpl(new DataInteractor());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        adapter = new MainAdapter(getContext(), listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        getPresenter().addNewContact();
                        return true;
                    case R.id.add_hundred:
                        getPresenter().add100NewContacts();
                        return true;
                }
                return false;
            }
        });
        getPresenter().onLoad();
    }

    @Override
    public void addOrUpdate(BaseViewModel baseViewModel) {
        adapter.addOrUpdate(baseViewModel);
    }

    @Override
    public void remove(BaseViewModel baseViewModel) {
        adapter.remove(baseViewModel);
    }

    @Override
    public List<BaseViewModel> findById(String id) {
        return adapter.findById(id);
    }

}
