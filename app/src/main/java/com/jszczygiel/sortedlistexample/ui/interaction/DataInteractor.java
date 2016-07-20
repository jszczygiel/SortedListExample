package com.jszczygiel.sortedlistexample.ui.interaction;

import android.content.Context;

import com.jszczygiel.foundation.rx.PublishSubject;
import com.jszczygiel.sortedlistexample.ui.interaction.models.ContactDataModel;

import rx.Observable;

public class DataInteractor {
    PublishSubject<ContactDataModel> subject;

    public DataInteractor() {
        subject = PublishSubject.createWith(PublishSubject.BUFFER);
    }

    public Observable<ContactDataModel> observeData() {
        return subject;
    }

    public void addNewContact(Context context){
        ContactDataModel model=ContactDataModel.generate(context);
        subject.onNext(model);
    }
}
