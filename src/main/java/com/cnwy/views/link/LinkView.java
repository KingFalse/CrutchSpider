package com.cnwy.views.link;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Link")
@Route(value = "link")
@Uses(Icon.class)
public class LinkView extends Div {

    public static List<SamplePerson> ss = new ArrayList<>();

    private Grid<SamplePerson> grid;


    public LinkView() {
        setSizeFull();
//        addClassNames("link-view");

        VerticalLayout layout = new VerticalLayout(createGrid());
//        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
//        layout.setSizeFull();
        layout.setHeight("100%");
        layout.setPadding(false);
        layout.setSpacing(false);

        Button primaryButton = new Button("确认");
        primaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button secondaryButton = new Button("取消");

        HorizontalLayout horizontalLayout = new HorizontalLayout(primaryButton, secondaryButton);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        horizontalLayout.setPadding(true);
        horizontalLayout.setSpacing(true);
        horizontalLayout.setMargin(true);
        layout.add(horizontalLayout);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        add(layout);
//        add(horizontalLayout);
    }


    private Component createGrid() {
        grid = new Grid<>(SamplePerson.class, false);
        grid.addColumn("title").setAutoWidth(true);
        grid.addColumn("link").setAutoWidth(true);

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        grid.setItems(ss);


        return grid;
    }

    private void refreshGrid() {
        grid.getDataProvider().refreshAll();
    }

}
