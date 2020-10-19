package com.tectro.mobileapp3.data_models;

import androidx.annotation.NonNull;

import com.tectro.mobileapp3.enums.NumberModifiers;

public class Number {
    private final int value;
    private final NumberModifiers modifier;

    @NonNull
    @Override
    public String toString() {
        switch (modifier)
        {
            case plus3: return value+" + 3";
            case multi2: return value+" * 2";
            default: return String.valueOf(value);
        }
    }

    public int getValue() {
        switch (modifier)
        {
            case plus3: return value+3;
            case multi2: return value*2;
            default: return value;
        }
    }

    //region Accessors

    public int getRaw(){return value;}
    public NumberModifiers getModifier() {
        return modifier;
    }

    public Number(int value, NumberModifiers modifier) {
        this.value = value;
        this.modifier = modifier;
    }
    public Number(int value) {
        this.value = value;
        this.modifier = NumberModifiers.NONE;
    }
    //endregion
}
