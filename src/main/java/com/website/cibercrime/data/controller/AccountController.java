package com.website.cibercrime.data.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.cibercrime.data.entity.AccountVK;
import com.website.cibercrime.data.repository.AccountVKRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class AccountController {
    private final ObjectMapper objectMapper;
    private final AccountVKRepository accountVKRepository;

    public AccountController(AccountVKRepository accountVKRepository, ObjectMapper objectMapper) {
        this.accountVKRepository = accountVKRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/api/v1/accounts/")
    public String getAccounts(@RequestParam Long claimantId) {
        log.info("/api/v1/accounts/?claimantId={}", claimantId);
        List<AccountVK> accountVKList = accountVKRepository.findByClaimantId(claimantId);

        try {
            return objectMapper.writeValueAsString(new AccountsWrapper(accountVKList));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing accounts", e);
        }

    }

}
