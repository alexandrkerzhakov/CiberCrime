package com.website.cibercrime.data.form;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;
import com.website.cibercrime.data.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class CrimeSummaryForm extends FormLayout {
    Binder<CrimeSummary> crimeSummaryBinder = new Binder<>(CrimeSummary.class);
    ComboBox<String> areaComboBox = new ComboBox<>("Территориальный орган");

    {
        areaComboBox.setItems("XXX", "DDD");
    }

    CrimeReportForm crimeReportForm = new CrimeReportForm();
    CrimeCaseForm crimeCaseForm = new CrimeCaseForm();
    ClaimantForm claimantForm = new ClaimantForm();
    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    public void setCrimeSummary(CrimeSummary crimeSummary) {
        crimeSummaryBinder.setBean(crimeSummary);
    }

    public CrimeSummaryForm() {
        addClassName("crimeSummary-form-view");
        crimeSummaryBinder.bindInstanceFields(this);

        add(
                areaComboBox,
                crimeReportForm,
                crimeCaseForm,
                claimantForm,
                createButtonsLayout()
        );
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, crimeSummaryBinder.getBean())));
        close.addClickListener(event -> {
            System.out.println(claimantForm.getBean().getAccountVKList());
            System.out.println(claimantForm.getListAccountVkForm().getAccountVKList());
            fireEvent(new CloseEvent(this));
        });

        crimeSummaryBinder.addStatusChangeListener(e -> save.setEnabled(crimeSummaryBinder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (crimeSummaryBinder.isValid()) {
//            System.out.println(crimeSummaryBinder.getBean().getCrimeReport().getMessage());
//            System.out.println(crimeSummaryBinder.getBean().getCrimeCase().getCaseNumber());
//            System.out.println(crimeSummaryBinder.getBean().getClaimant().getIpList());
//            System.out.println(crimeSummaryBinder.getBean().getClaimant().getFatherName());
            fireEvent(new SaveEvent(this, crimeSummaryBinder.getBean()));
        } else {
            System.out.println("!crimeSummaryBinder.isValid()");
//            System.out.println(crimeSummaryBinder.getBean().getCrimeReport().getMessage());
//            System.out.println(crimeSummaryBinder.getBean().getCrimeCase().getCaseNumber());
//            System.out.println(crimeSummaryBinder.getBean().getClaimant().getIpList());
//            System.out.println(crimeSummaryBinder.getBean().getClaimant().getAccountVKList());
//            System.out.println(crimeSummaryBinder.getBean().getClaimant().getFatherName());
        }
    }

    public static abstract class CrimeSummaryFormEvent extends ComponentEvent<CrimeSummaryForm> {
        private CrimeSummary crimeSummary;

        protected CrimeSummaryFormEvent(CrimeSummaryForm messageForm, CrimeSummary crimeSummary) {
            super(messageForm, false);
            this.crimeSummary = crimeSummary;
        }

        public CrimeSummary getCrimeSummary() {
            return crimeSummary;
        }

    }

    public static class SaveEvent extends CrimeSummaryFormEvent {
        SaveEvent(CrimeSummaryForm messageForm, CrimeSummary crimeSummary) {
            super(messageForm, crimeSummary);
        }
    }

    public static class DeleteEvent extends CrimeSummaryFormEvent {
        DeleteEvent(CrimeSummaryForm messageForm, CrimeSummary crimeSummary) {
            super(messageForm, crimeSummary);
        }

    }

    public static class CloseEvent extends CrimeSummaryFormEvent {
        CloseEvent(CrimeSummaryForm messageForm) {
            super(messageForm, null);
        }

    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
