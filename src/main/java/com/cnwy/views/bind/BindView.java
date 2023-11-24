package com.cnwy.views.bind;

import com.cnwy.views.detail.DetailView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PageTitle("bind")
@Route(value = "bind/:traceID")
public class BindView extends VerticalLayout implements BeforeEnterObserver {

    public static final Map<String, List<BindField>> cacheContext = new HashMap<>();
    String traceID;
    Grid<BindField> grid;
    public BindView() {

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        layout.setDefaultVerticalComponentAlignment(Alignment.END);
        layout.setJustifyContentMode(JustifyContentMode.END);

//        Button primaryButton = new Button("添加");
//        layout.add(primaryButton);
//        primaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        primaryButton.addClickListener(event -> showBinding(""));

        Button saveButton = new Button("上一步");
        saveButton.addClickListener(e->{
            UI.getCurrent().navigate("detail/"+traceID);
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layout.add(saveButton);
        Button saveButton2 = new Button("下一步");
        saveButton2.addClickListener(e->{
            UI.getCurrent().getPage().executeJs("window.java.startTest();");
        });
        saveButton2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layout.add(saveButton2);
        add(layout);

        grid = new Grid<>(BindField.class, false);
        grid.setSizeFull();
        grid.addColumn(BindField::getFieldName).setHeader("字段名");
        grid.addColumn(BindField::getxPath).setHeader("XPath");
        grid.addColumn(BindField::getRegex).setHeader("正则");

        add(grid);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.getRouteParameters().get("traceID").ifPresent(traceID -> this.traceID = traceID);
        grid.setItems(cacheContext.get(traceID));
    }


//    private void showBinding(String regex) {
//        ConfirmDialog dialog = new ConfirmDialog();
//        dialog.setHeader("选择要绑定的属性");
//        Select<String> select = new Select<>();
//        select.setItems("开始时间", "结束时间", "学历要求");
//        select.setPlaceholder("请选择");
//        select.setWidthFull();
//        dialog.add(select);
//        dialog.setCancelable(true);
//
//        dialog.setConfirmText("保存");
//        dialog.open();
//    }

}
