package com.website.cibercrime.data.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.website.cibercrime.data.entity.AccountVK;
import com.website.cibercrime.data.entity.Claimant;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Slf4j

public class ListAccountVkForm extends Div {
    private List<AccountVKForm> accountVKForms = new ArrayList<>();

    private List<AccountVK> accountVKList = new ArrayList<>();
    private final Button addFormButton = new Button("Add Form", event -> addAccountVKForm());
    private final Button resubmitButton = new Button("ReSubmit", event -> resubmitForms());
    public boolean isAddForm = false;

    public ListAccountVkForm() {
        addClassName("listAccountVk-form-view");
        add(addFormButton, resubmitButton);
    }

    private void addAccountVKForm() {
        AccountVKForm accountVKForm = new AccountVKForm();
        accountVKForms.add(accountVKForm);
        this.add(accountVKForm);
        this.isAddForm = true;
    }

    private void resubmitForms() {
        // Validate and process the submitted forms
        for (AccountVKForm accountVKForm : accountVKForms) {
            AccountVK accountVK = new AccountVK();
        }
    }
    public void clear() {
        log.info("ListAccountVkForm Clear");
        for (AccountVKForm accountVKForm : accountVKForms) {
            this.remove(accountVKForm);
        }
        this.getAccountVKForms().clear();
        this.getAccountVKList().clear();
    }
}