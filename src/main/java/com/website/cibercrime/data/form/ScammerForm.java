package com.website.cibercrime.data.form;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.website.cibercrime.data.converter.IPConverter;
import com.website.cibercrime.data.entity.Claimant;
import com.website.cibercrime.data.entity.Phone;
import com.website.cibercrime.data.entity.Scammer;

import java.util.Arrays;

public class ScammerForm extends Div {
    Binder<Scammer> scammerBinder = new Binder<>(Scammer.class);
    TextField firstName = new TextField("Имя мошенника");
    TextField secondName = new TextField("Фамилия мошенника");
    TextField fatherName = new TextField("Отчество мошенника");
    TextField scammerPhones = new TextField("Список телефонов мошенника");

    TextField scammerIP = new TextField("Сведения об администрировании IP-адресов");

    public ScammerForm() {
        addClassName("scammer-form-view");
//        add(createTitle());
//        add(createFormLayout());
        add(
                firstName,
                secondName,
                fatherName,
                scammerPhones,
                scammerIP
        );

        scammerBinder.bindInstanceFields(this);
//        scammerBinder.forField(claimantPhones)
//                .bind(
//                        claimant -> String.join(" ", claimant.getPhones().stream().map(Phone::getNumber).toList()),
//                        (claimant, phoneLine) -> claimant.setPhones(Arrays
//                                .stream(phoneLine.split(" "))
//                                .map(Phone::new)
//                                .toList())
//                );

//        scammerBinder.forField(claimantIP)
//                .withConverter(new IPConverter())
//                .bind(Claimant::getIpList, Claimant::setIpList);
    }

    public void clearScammerForm() {
        scammerBinder.setBean(new Scammer());
    }

    public Scammer getBean() {
        return scammerBinder.getBean();
    }

    public void setBean(Scammer scammer) {
        scammerBinder.setBean(scammer);
    }
}
