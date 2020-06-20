package com.algaworks.osworks.api.exceptionhandler;

public class Field {
    private String fieldName;
    private String message;

    public Field(String name, String message) {
        this.fieldName = name;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFieldName(String name) {
        this.fieldName = name;
    }
}