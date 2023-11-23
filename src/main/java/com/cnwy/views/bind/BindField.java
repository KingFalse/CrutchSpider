package com.cnwy.views.bind;

public class BindField {
    String xPath;
    String regex;
    String fieldName;

    public BindField(String xPath, String regex, String fieldName) {
        this.xPath = xPath;
        this.regex = regex;
        this.fieldName = fieldName;
    }

    public String getxPath() {
        return xPath;
    }

    public void setxPath(String xPath) {
        this.xPath = xPath;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
