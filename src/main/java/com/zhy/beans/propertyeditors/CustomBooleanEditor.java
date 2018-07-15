package com.zhy.beans.propertyeditors;

import com.zhy.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class CustomBooleanEditor extends PropertyEditorSupport{

    public static final String VALUE_TRUE = "true";
    public static final String VALUE_FALSE = "false";

    public static final String VALUE_ON = "on";
    public static final String VALUE_OFF = "off";

    public static final String VALUE_YES = "yes";
    public static final String VALUE_NO = "no";

    public static final String VALUE_1 = "1";
    public static final String VALUE_0 = "0";

    private final boolean allowEmpty;

    public CustomBooleanEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    public void setAsText(String text) throws IllegalArgumentException{
        String input = (text != null ? text.trim() : null);
        if (this.allowEmpty && !StringUtils.hasLength(input)) {
            this.setValue(null);
        } else if (VALUE_TRUE.equalsIgnoreCase(input) || VALUE_ON.equalsIgnoreCase(input) || VALUE_YES.equalsIgnoreCase(input) || VALUE_1.equalsIgnoreCase(input)) {
            this.setValue(Boolean.TRUE);
        } else if (VALUE_FALSE.equalsIgnoreCase(input) || VALUE_OFF.equalsIgnoreCase(input) || VALUE_NO.equalsIgnoreCase(input) || VALUE_0.equalsIgnoreCase(input)) {
            this.setValue(Boolean.FALSE);
        } else {
            throw new IllegalArgumentException("Invalid boolean value[" + text + "]");
        }
    }

    @Override
    public String getAsText() {
        if (Boolean.TRUE.equals(this.getValue())) {
            return VALUE_TRUE;
        } else if (Boolean.FALSE.equals(this.getValue())) {
            return VALUE_FALSE;
        } else {
            return "";
        }
    }

}
