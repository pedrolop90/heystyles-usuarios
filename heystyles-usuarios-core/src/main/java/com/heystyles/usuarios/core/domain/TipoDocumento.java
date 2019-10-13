package com.heystyles.usuarios.core.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.heystyles.common.exception.InvalidEnumValueException;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TipoDocumento {

    CC, TI;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static TipoDocumento fromValue(String value) {
        if (value != null && value.isEmpty()) {
            return null;
        }
        for (TipoDocumento p : values()) {
            if (p.name().equals(value)) {
                return p;
            }
        }
        throw new InvalidEnumValueException("tipoDocumento", value);
    }


}
