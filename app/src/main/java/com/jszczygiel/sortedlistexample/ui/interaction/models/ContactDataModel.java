package com.jszczygiel.sortedlistexample.ui.interaction.models;

import android.content.Context;

import com.jszczygiel.sortedlistexample.ui.utils.RandomNumeric;
import com.slmyldz.random.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactDataModel {

    private String id;
    private String name;
    private List<String> emails;
    private List<String> phoneNumbers;
    private String image;

    private ContactDataModel(Context context) {

        id = UUID.randomUUID().toString();
        name = Randoms.name(context);
        image = "http://thecatapi.com/api/images/get?format=src&type=jpg&random=" + id;
        emails = new ArrayList<>();
        int emailNumberCount = Randoms.Integer(1, 4);
        for (int i = 0; i < emailNumberCount; i++) {
            emails.add(Randoms.email(context));
        }

        phoneNumbers = new ArrayList<>();
        int phoneNumberCount = Randoms.Integer(1, 4);
        for (int i = 0; i < phoneNumberCount; i++) {
            phoneNumbers.add((new RandomNumeric(9)).nextString());
        }

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getEmails() {
        return emails;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public static ContactDataModel generate(Context context) {

        return new ContactDataModel(context);
    }

    public String getImage() {
        return image;
    }
}
