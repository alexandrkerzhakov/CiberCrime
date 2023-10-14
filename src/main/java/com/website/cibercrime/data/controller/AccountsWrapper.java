package com.website.cibercrime.data.controller;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.website.cibercrime.data.entity.AccountVK;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonRootName("accountVKList")
public class AccountsWrapper {
    private List<AccountVK> accountVKList;

    public AccountsWrapper(List<AccountVK> accountVKList) {
        this.accountVKList = accountVKList;
    }
}
