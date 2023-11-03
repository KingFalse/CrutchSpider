package com.cnwy.views.start;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Start")
@Route(value = "start")
@RouteAlias(value = "")
public class StartView extends VerticalLayout {

    public StartView() {
        setMargin(true);
        setSpacing(false);

        Image img = new Image("images/Jenkins.svg", "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("欢迎使用点点点🤗");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph(""));


        TextField start = new TextField();
        start.setPlaceholder("请输入链接...");
        start.setClearButtonVisible(true);
        start.setPrefixComponent(VaadinIcon.THUMBS_UP_O.create());
        start.setSuffixComponent(new Span(":)"));
        start.setWidth("60%");
        start.setAutofocus(true);
        add(start);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
