package com.website.cibercrime.data.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValueContext;
import com.website.cibercrime.data.converter.IPConverter;
import com.website.cibercrime.data.entity.AccountVK;
import com.website.cibercrime.data.entity.Claimant;
import com.website.cibercrime.data.entity.Phone;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static com.website.cibercrime.data.form.ClaimantForm.accountVKFormList;

@Getter
@Setter
@Slf4j

public class AccountVKForm extends Div {

    Binder<AccountVK> accountVKBinder = new Binder<>(AccountVK.class);
    TextField identifier = new TextField("Идентификатор учетной записи");
    TextArea accountVkIP = new TextArea("Сведения об администрировании IP-адресов");
//    TextField accountVkIP = new TextField("Сведения об администрировании IP-адресов");

    private final Button deleteFormButton = new Button("Delete Form", event -> ClaimantForm.deleteAccountVKForm());




    public AccountVKForm() {
        addClassName("accountVK-form-view");
        accountVkIP.addClassName("accountVkIP");
//        add(createTitle());
//        add(createFormLayout());
        add(
                identifier,
                accountVkIP,
                deleteFormButton
//                addVKField
        );
        accountVKBinder.bindInstanceFields(this);
        accountVKBinder.forField(accountVkIP)
                .withConverter(new IPConverter())
                .bind(AccountVK::getIpList, AccountVK::setIpList);
    }

    public boolean validate() {
        // Perform validation on form fields
//        return identifier != null && !identifier.isEmpty() && accountVkIP != null && !accountVkIP.isEmpty();
        return true;
    }

    private void deleteAccountVKForm() {

    }

    public AccountVK getAccountVK() {
        log.info("getAccountVK()");
        // Create an AccountVK object based on form field values
        AccountVK accountVK = new AccountVK();
        if (accountVKBinder.writeBeanIfValid(accountVK)) {
            log.info("New AccountVK -> {}", accountVK);
            return accountVK;
        }
        return accountVK;
    }

    public void clearAccountVKForm() {
        accountVKBinder.setBean(new AccountVK());
    }

    public AccountVK getBean() {
        return accountVKBinder.getBean();
    }

    public void setBean(AccountVK accountVK) {
        accountVKBinder.setBean(accountVK);
    }
}
