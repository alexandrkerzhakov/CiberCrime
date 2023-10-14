package com.website.cibercrime.data.form;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.website.cibercrime.data.entity.CrimeCase;

public class CrimeCaseForm extends Div {

    Binder<CrimeCase> crimeCaseBinder = new Binder<>(CrimeCase.class);

    TextField caseNumber = new TextField("УД");

    DatePicker caseNumberDate = new DatePicker("Дата возбуждения УД");


    public CrimeCaseForm() {
        addClassName("crimeCase-form-view");
//        add(createTitle());
//        add(createFormLayout());
        add(
                caseNumber,
                caseNumberDate
        );
        crimeCaseBinder.bindInstanceFields(this);
    }

    public void clearCaseForm() {
        crimeCaseBinder.setBean(new CrimeCase());
    }

    public CrimeCase getBean() {
        return crimeCaseBinder.getBean();
    }
    public void setBean(CrimeCase crimeCase) {
        crimeCaseBinder.setBean(crimeCase);
    }

}
