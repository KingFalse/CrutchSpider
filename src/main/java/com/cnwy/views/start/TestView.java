package com.cnwy.views.start;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import de.f0rce.ace.AceEditor;
import de.f0rce.ace.enums.AceMode;
import de.f0rce.ace.enums.AceTheme;

@PageTitle("Start")
@Route(value = "test")
public class TestView extends VerticalLayout {

    public TestView() {
        setMargin(true);
        setSpacing(false);

        Image img = new Image("images/Jenkins.svg", "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("欢迎使用点点点🤗");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph(""));


        AceEditor ace = new AceEditor();
        ace.setTheme(AceTheme.terminal);
        ace.setMode(AceMode.sql);
        ace.setValue("从 DB_01 中选择 *，其中 ID = 123");

        add(ace);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
