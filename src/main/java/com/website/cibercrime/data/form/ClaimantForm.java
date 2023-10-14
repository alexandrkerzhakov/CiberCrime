package com.website.cibercrime.data.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.website.cibercrime.data.converter.IPConverter;
import com.website.cibercrime.data.entity.AccountVK;
import com.website.cibercrime.data.entity.Claimant;
import com.website.cibercrime.data.entity.CrimeSummary;
import com.website.cibercrime.data.entity.Phone;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Getter
@Setter
@Slf4j

public class ClaimantForm extends Div {
    Binder<Claimant> claimantBinder = new Binder<>(Claimant.class);
    TextField firstName = new TextField("Имя заявителя");
    TextField secondName = new TextField("Фамилия заявителя");
    TextField fatherName = new TextField("Отчество заявителя");
    TextField claimantPhones = new TextField("Список телефонов заявителя");
    TextArea claimantIP = new TextArea("Сведения об администрировании IP-адресов"); //
    //    TextField claimantIP = new TextField("Сведения об администрировании IP-адресов");
    ListAccountVkForm listAccountVkForm = new ListAccountVkForm();
    public boolean isSubmit = false;
    private final Button submitButton = new Button("Submit", event -> submitForms());


    public ClaimantForm() {
        addClassName("claimant-form-view");
        claimantIP.addClassName("claimantIP");
//        add(createTitle());
//        add(createFormLayout());
        add(
                firstName,
                secondName,
                fatherName,
                claimantPhones,
                claimantIP,
                listAccountVkForm,
                submitButton
        );

        claimantBinder.bindInstanceFields(this);
        claimantBinder.forField(claimantPhones)
                .bind(
                        claimant -> String.join(" ", claimant.getPhones().stream().map(Phone::getNumber).toList()),
                        (claimant, phoneLine) -> claimant.setPhones(Arrays
                                .stream(phoneLine.split(" "))
                                .map(Phone::new)
                                .toList())
                );

        claimantBinder.forField(claimantIP)
                .withConverter(new IPConverter())
                .bind(Claimant::getIpList, Claimant::setIpList);
    }

    public static void deleteAccountVKForm() {

    }

    public void clearClaimantForm() {
        claimantBinder.setBean(new Claimant());
    }

    public Claimant getBean() {
        return claimantBinder.getBean();
    }

    public void setBean(Claimant claimant) {
        claimantBinder.setBean(claimant);
    }

    private void submitForms() {
        log.info("Submit Forms");
        // Validate and process the submitted forms
        this.isSubmit = true;
        for (AccountVKForm accountVKForm : listAccountVkForm.getAccountVKForms()) {
            if (accountVKForm.validate()) {
                AccountVK accountVK = accountVKForm.getAccountVK();
                log.info("Create AccountVK -> {}", accountVK);
                listAccountVkForm.getAccountVKList().add(accountVK);
                log.info("ListAccountVkForm.getAccountVKList() before add -> {}", listAccountVkForm.getAccountVKList());
                // Process the accountVK object
            } else {
                // Handle invalid form
            }
        }
        this.setListAccountVkForm(listAccountVkForm);
    }

    public void updateListAccountForm(CrimeSummary crimeSummary) {
        log.info("Update ListAccountForm");
        Claimant claimant = crimeSummary.getClaimant();
        for (AccountVK accountVK : claimant.getAccountVKList()) {
            AccountVKForm accountVKForm = new AccountVKForm();
            accountVKForm.setBean(accountVK);
            listAccountVkForm.add(accountVKForm);
            log.info("Add AccountVKForm -> for AccountVK -> {} ", accountVK);
            listAccountVkForm.getAccountVKForms().add(accountVKForm);
            listAccountVkForm.getAccountVKList().add(accountVK);
            log.info("Add AccountVKForm, AccountVK in listAccountVkForm.getAccountVKForms(), listAccountVkForm.getAccountVKList()");
        }
        this.setListAccountVkForm(listAccountVkForm);
    }
}
