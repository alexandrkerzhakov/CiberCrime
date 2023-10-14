package com.website.cibercrime.data.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.website.cibercrime.data.entity.*;
import com.website.cibercrime.data.form.*;
import com.website.cibercrime.data.service.CrmService;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;

@Slf4j
@Route("crimeSummaryViews")
public class CrimeSummaryViews extends VerticalLayout {
    Grid<CrimeSummary> crimeSummaryGrid = new Grid<>(CrimeSummary.class, false);
    TextField filterText = new TextField();
    CrimeSummaryForm crimeSummaryForm = new CrimeSummaryForm();
    CrmService crmService;
    CrimeReportForm crimeReportForm = crimeSummaryForm.getCrimeReportForm();
    CrimeCaseForm crimeCaseForm = crimeSummaryForm.getCrimeCaseForm();
    ClaimantForm claimantForm = crimeSummaryForm.getClaimantForm();
    ListAccountVkForm listAccountVkForm = claimantForm.getListAccountVkForm();
    CrimeSummary crimeSummary;
    CrimeReport crimeReport;
    CrimeCase crimeCase;
    Claimant claimant;

    public CrimeSummaryViews(CrmService crmService) {
        log.info("Create CrimeSummaryViews");
        this.crmService = crmService;
        addClassName("crimeSummaryViews");
        setSizeFull();
        configureCrimeSummaryGrid();
        configureCrimeSummaryForm();
        add(getToolbar(), getMessage());
        updateCrimeSummaryGrid();
        closeEditor();
    }

    private void updateCrimeSummaryGrid() {
        log.info("Update CrimeSummaryGrid");
        crimeSummaryGrid.setItems(crmService.findAllCrimeSummarys(filterText.getValue()));
    }

    private Component getMessage() {
        HorizontalLayout content = new HorizontalLayout(crimeSummaryGrid, crimeSummaryForm);
        content.setFlexGrow(1, crimeSummaryGrid);
        content.setFlexGrow(1, crimeSummaryForm);
        content.addClassNames("crimeSummaryPage");
        content.setSizeFull();
        return content;
    }

    private void configureCrimeSummaryForm() {
        crimeSummaryForm = new CrimeSummaryForm();
        crimeSummaryForm.setWidth("25em");
        crimeSummaryForm.addSaveListener(this::saveCrimeSummary);
        crimeSummaryForm.addDeleteListener(this::deleteCrimeSummary);
        crimeSummaryForm.addCloseListener(e -> closeEditor());

    }

    private void saveCrimeSummary(CrimeSummaryForm.SaveEvent event) {
        log.info("Save CrimeSummary");
        crimeSummary = event.getCrimeSummary();
        log.info("claimantForm.isSubmit -> {}", claimantForm.isSubmit);
        log.info("listAccountVkForm.isAddForm -> {}", listAccountVkForm.isAddForm);

//        if (crimeSummary.getId() == null) {
//            crmService.saveCrimeSummary(crimeSummary);
//        } else
        if (crimeSummary.getId() != null && claimantForm.isSubmit && listAccountVkForm.isAddForm) {
            claimant = crimeSummary.getClaimant();
            log.info("claimant.getAccountVKList() -> {}", claimant.getAccountVKList());
            log.info("listAccountVkForm.getAccountVKList() -> {} ", listAccountVkForm.getAccountVKList());
            HashSet<AccountVK> accountVKHashSet = new HashSet<>(listAccountVkForm.getAccountVKList());
            log.info("accountVKHashSet -> {} ", accountVKHashSet);
            claimant.setAccountVKList(accountVKHashSet.stream().toList());
            log.info("claimant.getAccountVKList() before setAccountVKList() -> {} ", claimant.getAccountVKList());
            crmService.saveCrimeSummary(crimeSummary);
        } else {
            crmService.saveCrimeSummary(crimeSummary);
        }
        log.info("CrimeSummary -> {} ", crimeSummary);
        updateCrimeSummaryGrid();
        closeEditor();
    }

    private void deleteCrimeSummary(CrimeSummaryForm.DeleteEvent event) {
        log.info("Delete CrimeSummary");
        crmService.deleteCrimeSummary(event.getCrimeSummary());
        updateCrimeSummaryGrid();
        closeEditor();
    }

    private void configureCrimeSummaryGrid() {
        crimeSummaryGrid.addClassNames("crimeSummaryGrid-view");
        crimeSummaryGrid.setSizeFull();
        crimeSummaryGrid.addColumn("areaComboBox")
                .setHeader("Территориальный орган");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getCrimeReport()
                        .getMessageNumber())
                .setHeader("КУСП");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getCrimeReport()
                        .getMessageDate())
                .setHeader("Дата КУСП");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getCrimeReport()
                        .getMessage())
                .setHeader("Текст сообщения");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getCrimeCase()
                        .getCaseNumber())
                .setHeader("УД");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getCrimeCase()
                        .getCaseNumberDate())
                .setHeader("Дата УД");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getClaimant()
                        .getFirstName())
                .setHeader("Имя заявителя");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getClaimant()
                        .getFatherName())
                .setHeader("Фамилия заявителя");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getClaimant()
                        .getSecondName())
                .setHeader("Отчество заявителя");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getClaimant()
                        .getPhones()
                        .stream()
                        .map(Phone::getNumber)
                        .toList())
                .setHeader("Список телефонов заявителя");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getClaimant()
                        .getIpList()
                        .stream()
                        .map(IP::getIp)
                        .toList())
                .setHeader("Список IP-адресов");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getClaimant()
                        .getIpList()
                        .stream()
                        .map(IP::getIpDate)
                        .toList())
                .setHeader("Список дат IP-адресов");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getClaimant()
                        .getIpList()
                        .stream()
                        .map(IP::getTime)
                        .toList())
                .setHeader("Список времени IP-адресов");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getClaimant()
                        .getAccountVKList()
                        .stream()
                        .toList())
                .setHeader("Учетные записи");
        crimeSummaryGrid.addColumn(crimeSummary -> crimeSummary
                        .getClaimant()
                        .getAccountVKList()
                        .stream()
                        .map(AccountVK::getIpList)
                        .toList())
                .setHeader("Админка VK");
//        crimeSummaryGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        crimeSummaryGrid.asSingleSelect().addValueChangeListener(event ->
                editCrimeSummaryForm(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateCrimeSummaryGrid());
        Button addCrimeReportButton = new Button("Добавить запись");
        addCrimeReportButton.addClickListener(click -> addCrimeSummary());
        var toolbar = new HorizontalLayout(filterText, addCrimeReportButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editCrimeSummaryForm(CrimeSummary crimeSummary) {
        log.info("Edit CrimeSummaryForm");
        if (crimeSummary == null) {
            log.info("CrimeSummary is null");
            closeEditor();
        } else {
            log.info("Form Initialization");
            crimeSummaryForm.setCrimeSummary(crimeSummary);
            crimeReport = crimeSummary.getCrimeReport();
            crimeCase = crimeSummary.getCrimeCase();
            claimant = crimeSummary.getClaimant();
            crimeReportForm = crimeSummaryForm.getCrimeReportForm();
            crimeCaseForm = crimeSummaryForm.getCrimeCaseForm();
            claimantForm = crimeSummaryForm.getClaimantForm();
            listAccountVkForm = claimantForm.getListAccountVkForm();

            if (crimeSummary.getId() == null) {
                log.info("New CrimeSummary");
                log.info("claimant.getIpList() -> {} ", claimant.getIpList());
                log.info("claimant.getAccountVKList() -> {} ", claimant.getAccountVKList());
                claimant.setAccountVKList(listAccountVkForm.getAccountVKList());
                log.info("claimant.getAccountVKList() before setAccountVKList -> {} ", claimant.getAccountVKList());
            } else {
                log.info("Existing CrimeSummary");
                listAccountVkForm.clear();
                claimantForm.updateListAccountForm(crimeSummary);
            }

            crimeReportForm.setBean(crimeReport);
            crimeCaseForm.setBean(crimeCase);
            claimantForm.setBean(claimant);
        }
        crimeSummaryForm.setVisible(true);
        addClassName("editing");
    }

    private void closeEditor() {
        log.info("Close Editor");
        claimantForm.setSubmit(false);
        listAccountVkForm.clear();
        listAccountVkForm.setAddForm(false);
        crimeSummaryForm.setCrimeSummary(null);
        crimeSummaryForm.setVisible(false);
        removeClassName("editing");
    }

    private void addCrimeSummary() {
        log.info("Add CrimeSummary");
        crimeSummaryGrid.asSingleSelect().clear();
        editCrimeSummaryForm(new CrimeSummary());
    }
}
