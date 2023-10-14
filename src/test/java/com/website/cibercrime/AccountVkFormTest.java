package com.website.cibercrime;

import com.website.cibercrime.data.entity.AccountVK;
import com.website.cibercrime.data.entity.IP;
import com.website.cibercrime.data.form.AccountVKForm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AccountVkFormTest {
    AccountVK accountVK = new AccountVK();

    @BeforeEach
    public void setAccountVk() {
        accountVK.setIdentifier("id762354");
        accountVK.setIpList(List.of(
                new IP("152.16.25.11", "27.05.2023", "12:05:48"),
                new IP("162.16.25.11", "27.05.2023", "12:05:48"),
                new IP("172.16.25.11", "27.05.2023", "12:05:48")));
    }

    @Test
    public void formFieldsPut() {
        AccountVKForm accountVKForm = new AccountVKForm();
        accountVKForm.setBean(accountVK);
        assertEquals("id762354", accountVKForm.getAccountVK().getIdentifier());
        assertEquals(List.of(
                new IP("152.16.25.11", "27.05.2023", "12:05:48"),
                new IP("162.16.25.11", "27.05.2023", "12:05:48"),
                new IP("172.16.25.11", "27.05.2023", "12:05:48")), accountVKForm.getAccountVK().getIpList());
    }
}