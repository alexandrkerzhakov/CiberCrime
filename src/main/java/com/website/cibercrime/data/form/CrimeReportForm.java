package com.website.cibercrime.data.form;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.website.cibercrime.data.entity.CrimeCase;
import com.website.cibercrime.data.entity.CrimeReport;

public class CrimeReportForm extends Div {

    Binder<CrimeReport> crimeReportBinder = new Binder<>(CrimeReport.class);

    TextField messageNumber = new TextField("КУСП");

    DatePicker messageDate = new DatePicker("Дата регистрации КУСП");

    TextField message = new TextField("Текст сообщения");

    public CrimeReportForm() {
        addClassName("crimeReport-form-view");
//        add(createTitle());
//        add(createFormLayout());
        add(
                messageNumber,
                messageDate,
                message
        );
        crimeReportBinder.bindInstanceFields(this);
    }

    public void clearCrimeReportForm() {
        crimeReportBinder.setBean(new CrimeReport());
    }

    public CrimeReport getBean() {
        return crimeReportBinder.getBean();
    }
    public void setBean(CrimeReport crimeReport) { crimeReportBinder.setBean(crimeReport);
    }

}
