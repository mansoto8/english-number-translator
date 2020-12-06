package com.sampleapp.numbertranslator.core;

public enum ConnectorType {
    EMPTY(""),SPACE(" "),AND(" and ");

    private String text;

    ConnectorType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
