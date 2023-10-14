package com.website.cibercrime.data.service;

import com.website.cibercrime.data.entity.*;
import com.website.cibercrime.data.repository.*;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Service
public class CrmService {
    private final CrimeSummaryRepository crimeSummaryRepository;
    private final CrimeCaseRepository crimeCaseRepository;
    private final ClaimantsRepository claimantsRepository;

    private final AccountVKRepository accountVKRepository;
    private final PhoneRepository phoneRepository;
    private final IPRepository ipRepository;

    public CrmService(CrimeSummaryRepository crimeSummaryRepository, CrimeCaseRepository crimeCaseRepository, ClaimantsRepository claimantsRepository, AccountVKRepository accountVKRepository, PhoneRepository phoneRepository, IPRepository ipRepository) {
        this.crimeSummaryRepository = crimeSummaryRepository;
        this.crimeCaseRepository = crimeCaseRepository;
        this.claimantsRepository = claimantsRepository;
        this.accountVKRepository = accountVKRepository;
        this.phoneRepository = phoneRepository;
        this.ipRepository = ipRepository;
    }

    public List<CrimeSummary> findAllCrimeSummarys(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return crimeSummaryRepository.findAll();
        } else {
            return crimeSummaryRepository.findAll();
        }
    }

    public List<CrimeCase> findAllCrimeCase(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return crimeCaseRepository.findAll();
        } else {
            return crimeCaseRepository.findAll();
        }
    }

//    public List<AccountVK> findAllByClaimant_Id(Long claimantId) {
//        return null;
//    }

//    public List<AccountVK> findListAccountVKbyId(Long id) {
//        accountVKRepository.
//        return accountVKRepository.findAll()
//                .stream()
//                .map(accountVK -> accountVKRepository.getReferenceById(id))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .toList();
//    }

    public long countCrimeSummary() {
        return crimeSummaryRepository.count();
    }

    public void saveCrimeSummary(CrimeSummary crimeSummary) {
        if (crimeSummary == null) {
            System.err.println("crimeSummary is null");
            return;
        }
        crimeSummaryRepository.save(crimeSummary);
    }

    public void deleteCrimeSummary(CrimeSummary crimeSummary) {
        crimeSummaryRepository.delete(crimeSummary);
    }

//    public void deleteCrimeCase(CrimeCase crimeCase) {
//        crimeCaseRepository.delete(crimeCase);
//    }
//    public void updateCrimeSummary(CrimeSummary crimeSummary) {
//        deleteCrimeSummary(crimeSummary);
//        saveCrimeSummary(crimeSummary);
//    }

//    public List<Claimant> findAllClaimants() {
//        return claimantRepository.findAll();
//    }


}
