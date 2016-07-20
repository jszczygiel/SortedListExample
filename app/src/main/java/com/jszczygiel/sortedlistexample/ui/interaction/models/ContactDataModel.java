package com.jszczygiel.sortedlistexample.ui.interaction.models;

import android.content.Context;

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
        image= Randoms.imageUrl("jpg");
        emails = new ArrayList<>();
        emails.add(Randoms.email(context));
        emails.add(Randoms.email(context));

        phoneNumbers = new ArrayList<>();
        phoneNumbers.add(Randoms.alphaNumericString(9));
        phoneNumbers.add(Randoms.alphaNumericString(9));

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
