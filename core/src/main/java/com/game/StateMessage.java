package com.game;

public enum StateMessage {
    SANS("* Sans"),

    SINS("* You feel your sins crawling your back"),

    ACT_DESCRIPTION("* SANS 1 ATK 1 DEF\n* The easiest enemy.\n* Can only deal 1 damage"),
    CHECK("* Check"),
    SPARE("* Spare"),

    EMPTY(""),

    PIE("* Pie"),

    SNOW_PIECE("* SnowPiece"),
    GLAMBURGUER("* Glamburguer"),

    ITEM_CONSUMED("Item consumed");

    private final String value;

    StateMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
