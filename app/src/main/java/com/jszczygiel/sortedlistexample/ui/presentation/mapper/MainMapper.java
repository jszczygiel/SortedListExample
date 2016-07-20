package com.jszczygiel.sortedlistexample.ui.presentation.mapper;

import com.jszczygiel.sortedlistexample.ui.interaction.models.ContactDataModel;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.BaseViewModel;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.EmailViewModel;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.PersonViewModel;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.PhoneViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainMapper {
    private MainMapper() {
    }

    public static List<BaseViewModel> map(ContactDataModel contactDataModel) {
        List<BaseViewModel> toReturn = new ArrayList<>();

        toReturn.add(new PersonViewModel(contactDataModel.getId(), contactDataModel.getName(), contactDataModel.getImage()));
        for (String email : contactDataModel.getEmails()) {
            toReturn.add(new EmailViewModel(contactDataModel.getId(), email));
        }

        for (String phone : contactDataModel.getPhoneNumbers()) {
            toReturn.add(new PhoneViewModel(contactDataModel.getId(), phone));
        }
        return toReturn;
    }
}

