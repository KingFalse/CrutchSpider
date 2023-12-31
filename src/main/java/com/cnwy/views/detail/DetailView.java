package com.cnwy.views.detail;

import com.cnwy.views.bind.BindField;
import com.cnwy.views.bind.BindView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.f0rce.ace.AceEditor;
import de.f0rce.ace.enums.AceMode;
import de.f0rce.ace.enums.AceTheme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@PageTitle("Detail")
@Route(value = "detail/:traceID")
public class DetailView extends VerticalLayout implements BeforeEnterObserver {

    public static final Map<String, String> cacheContext = new HashMap<>();
    public static final Map<String, String> cacheXpath = new HashMap<>();
    String traceID;
    AceEditor ace;
    TextField start = new TextField();

    public DetailView() {
        start.setPlaceholder("请输入正则表达式...");
        start.setClearButtonVisible(true);
        start.setPrefixComponent(VaadinIcon.THUMBS_UP_O.create());
        start.setSuffixComponent(new Span(":)"));
        start.setWidthFull();
        start.addKeyPressListener(Key.ENTER, e -> {
            Pattern pattern = Pattern.compile(start.getValue().strip());
            Matcher matcher = pattern.matcher(cacheContext.get(traceID));
            String result = "";
            if (matcher.find()) {
                if (matcher.groupCount() == 0) {
                    result = matcher.group();
                    System.err.println("正则匹配结果:" + matcher.group());
                } else if (matcher.groupCount() == 1) {
                    result = matcher.group(1);
                    System.err.println("正则匹配结果:" + matcher.group(1));
                }
            }

            if (!result.isEmpty()) {
                Notification notification = Notification.show("正则匹配结果: " + result, 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } else {
                Notification notification = Notification.show("无匹配结果!", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }

        });
        start.setAutofocus(true);
        start.focus();
        add(start);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        layout.setDefaultVerticalComponentAlignment(Alignment.END);
        layout.setJustifyContentMode(JustifyContentMode.END);


        Button primaryButton = new Button("添加");
        layout.add(primaryButton);
        primaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        primaryButton.addClickListener(event -> showBinding(start.getValue().strip()));

        Button viewButton = new Button("下一步");
        layout.add(viewButton);
        viewButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        viewButton.addClickListener(event -> showBindResult());
        add(layout);

        ace = new AceEditor();
        ace.setSizeFull();
        ace.setReadOnly(true);
        ace.setShowGutter(true);
        ace.setTheme(AceTheme.xcode);
        ace.setMode(AceMode.text);
        add(ace);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    private void showBindResult() {
        UI.getCurrent().navigate("bind/" + traceID);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        beforeEnterEvent.getRouteParameters().get("traceID").ifPresent(traceID -> this.traceID = traceID);
        setup();
    }

    private void setup() {
        ace.setValue(cacheContext.get(traceID));
    }

    private void showBinding(String regex) {
        if (regex.isBlank()) {
            Notification notification = Notification.show("请输入正则表达式!", 3000, Notification.Position.TOP_END);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            return;
        }
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("选择要绑定的属性");
        Select<String> select = new Select<>();
        select.setItems("公司名称","专业要求","职位名称","城市","学历要求","英语等级要求","届别要求","招聘政策","招聘人数","招聘开始时间","招聘截止时间","招聘岗位描述","经验年限","福利代理");
        select.setPlaceholder("请选择");
        select.setWidthFull();
        dialog.add(select);
        dialog.setCancelable(true);

        dialog.setConfirmText("保存");
        dialog.setCancelText("取消");
        dialog.addConfirmListener(event -> {
            if (select.getValue() == null || select.getValue().isBlank()) {
                Notification notification = Notification.show("请选择要绑定的属性!", 3000, Notification.Position.TOP_END);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            if (!BindView.cacheContext.containsKey(traceID)) {
                BindView.cacheContext.put(traceID, new ArrayList<>());
            }
            List<BindField> fields = BindView.cacheContext.get(traceID);
            fields.add(new BindField(DetailView.cacheXpath.get(traceID), regex, select.getValue().strip()));
            BindView.cacheContext.put(traceID, fields);
            Notification notification = Notification.show("已将正则: " + regex + " 绑定到字段: " + select.getValue(), 3000, Notification.Position.TOP_CENTER);
            System.err.println(traceID);
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            start.focus();
        });
        dialog.open();
    }

}
