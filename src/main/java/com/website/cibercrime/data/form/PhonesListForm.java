//package com.website.cibercrime.data.form;
//
//import com.vaadin.flow.component.html.Div;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.binder.Binder;
//import com.website.cibercrime.data.entity.Phone;
//
//import java.util.Arrays;
//
//public class PhonesListForm extends Div {
//
//    Binder<Phone> phoneBinder = new Binder<>(Phone.class);
//
//    TextField listPhones = new TextField("Список телефонов");
//
//    public PhonesListForm() {
//        add(listPhones);
//
//    phoneBinder.forField(listPhones)
//            .
//
//    bind(claimant ->String.join(" ",claimant.getPhones().
//
//    stream().
//
//    map(Phone::getNumber).
//
//    toList()),
//            (claimant,phoneLine)->claimant.setPhones(Arrays.stream(phoneLine.split(" "))
//            .
//
//    map(Phone::new)
//                .
//
//    toList())
//            );
//
//
//}
