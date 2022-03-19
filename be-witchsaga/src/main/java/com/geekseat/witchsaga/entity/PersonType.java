package com.geekseat.witchsaga.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PersonType {

    MALE("Male"), FEMALE("Female");

    private final String value;

    PersonType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
