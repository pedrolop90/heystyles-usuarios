package com.heystyles.usuarios.core.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.heystyles.common.exception.InvalidEnumValueException;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TipoCuentaBanco {

    CA, CC, C;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static TipoCuentaBanco fromValue(String value) {
        if (value != null && value.isEmpty()) {
            return null;
        }
        for (TipoCuentaBanco p : values()) {
            if (p.name().equals(value)) {
                return p;
            }
        }
        throw new InvalidEnumValueException("TipoCuentaBanco", value);
    }


}
