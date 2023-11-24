package com.cnwy.views.list;

import com.alibaba.fastjson2.JSON;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PageTitle("links")
@Route(value = "links/:traceID")
public class LinkView extends VerticalLayout implements BeforeEnterObserver {

    public static final Map<String, List<Map<String, String>>> cacheLinks = new HashMap<>();
    String traceID;
    Grid<Map<String, String>> grid;

    public LinkView() {

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        layout.setDefaultVerticalComponentAlignment(Alignment.END);
        layout.setJustifyContentMode(JustifyContentMode.END);

//        Button primaryButton = new Button("添加");
//        layout.add(primaryButton);
//        primaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        primaryButton.addClickListener(event -> showBinding(""));

//        Button saveButton = new Button("上一步");
//        saveButton.addClickListener(e->{
//            UI.getCurrent().navigate("detail/"+traceID);
//        });
//        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        layout.add(saveButton);
        Button saveButton2 = new Button("提交服务器执行");
        saveButton2.addClickListener(e -> {
//            UI.getCurrent().getPage().executeJs("window.java.startTest();");
            Notification.show("执行成功");
        });
        saveButton2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layout.add(saveButton2);
        add(layout);

        grid = new Grid<>();
        grid.setSizeFull();
        add(grid);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.getRouteParameters().get("traceID").ifPresent(traceID -> this.traceID = traceID);
        System.err.println("当前缓存只/:"+JSON.toJSONString(cacheLinks));
        for (String key : cacheLinks.get(traceID).get(0).keySet()) {
            grid.addColumn(map -> map.get(key)).setHeader(key);
        }
        grid.setItems(cacheLinks.get(traceID));
        grid.setSizeFull();
    }

}
