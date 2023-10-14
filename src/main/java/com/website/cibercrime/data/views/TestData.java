package com.website.cibercrime.data.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.website.cibercrime.data.entity.CrimeSummary;

@Route("testData")
public class TestData extends VerticalLayout {
    Grid<CrimeSummary> grid = new Grid<>(CrimeSummary.class);

    public TestData() {
        add(grid);
    }
}
