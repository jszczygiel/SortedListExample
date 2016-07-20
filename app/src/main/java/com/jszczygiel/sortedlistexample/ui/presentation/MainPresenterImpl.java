package com.jszczygiel.sortedlistexample.ui.presentation;

import com.jszczygiel.foundation.presenters.BasePresenterImpl;
import com.jszczygiel.sortedlistexample.ui.interaction.DataInteractor;
import com.jszczygiel.sortedlistexample.ui.interaction.models.ContactDataModel;
import com.jszczygiel.sortedlistexample.ui.presentation.mapper.MainMapper;
import com.jszczygiel.sortedlistexample.ui.view.MainFragment;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.BaseViewModel;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainPresenterImpl extends BasePresenterImpl<MainFragment> implements MainPresenter {

    private final DataInteractor dataInteractor;
    private Subscription loadSubscription;

    public MainPresenterImpl(DataInteractor dataInteractor) {
        this.dataInteractor = dataInteractor;
    }

    @Override
    public void onLoad() {
        if (isViewAvailable()) {
            loadSubscription = dataInteractor.observeData().onBackpressureBuffer().map(new Func1<ContactDataModel, List<BaseViewModel>>() {
                @Override
                public List<BaseViewModel> call(ContactDataModel contactDataModel) {
                    return MainMapper.map(contactDataModel);
                }
            }).subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<List<BaseViewModel>>() {
                        @Override
                        public void call(List<BaseViewModel> baseViewModels) {
                            if (isViewAvailable()) {
                                for (BaseViewModel baseViewModel : baseViewModels) {
                                    getView().addOrUpdate(baseViewModel);
                                }
                            }
                        }
                    });
            addSubscriptionToLifeCycle(loadSubscription);
        }
    }

    @Override
    public void addNewContact() {
        if (isViewAvailable()) {
            dataInteractor.addNewContact(getView().getContext());
        }
    }

    @Override
    public void add100NewContacts() {
        if (isViewAvailable()) {
            for (int i = 0; i < 100; i++) {
                dataInteractor.addNewContact(getView().getContext());
            }
        }
    }

    @Override
    public void onDelete(String id) {
        if (isViewAvailable()) {
            Observable.from(getView().findById(id)).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseViewModel>() {
                @Override
                public void call(BaseViewModel baseViewModel) {
                    if (isViewAvailable()) {
                        getView().remove(baseViewModel);
                    }
                }
            });
        }
    }

}
