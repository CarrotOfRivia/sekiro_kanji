package com.carrot_of_rivia.sekiro_kanji.utils;

import java.util.HashMap;

public enum Signs {
    DANGER(0),
    RESURRECT(1),
    POISON(2),
    BURN(3),
    ENEMY_ALERT(4);
    private final int index;
    private static final HashMap<Integer, Signs> INT_SIGN_MAP = new HashMap<>();
    Signs(int index){
        this.index=index;
    }

    public int getIndex() {
        return index;
    }

    static {
        for (Signs signs: Signs.values()){
            INT_SIGN_MAP.put(signs.getIndex(), signs);
        }
    }

    public static Signs getSigns(int index){
        if (INT_SIGN_MAP.containsKey(index)){
            return INT_SIGN_MAP.get(index);
        }
        else {
            throw new IllegalArgumentException("No such sign index: " + index);
        }
    }
}
