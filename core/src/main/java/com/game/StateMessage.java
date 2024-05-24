package com.game;

public enum StateMessage {
    SANS("* Sans"),

    SINS("* You feel your sins crawling your back"),

    ACT_DESCRIPTION("* SANS 1 ATK 1 DEF\n* The easiest enemy.\n* Can only deal 1 damage"),

    SANS_SPARING("* SANS 1 ATK 1 DEF\n* The easiest enemy.\n* Can only deal 1 damage"),
    CHECK("* Check"),
    SPARE("* Spare"),

    EMPTY(""),

    PIE("* Pie"),

    SNOW_PIECE("* SnowPiece"),
    GLAMBURGUER("* Glamburguer"),

    ITEM_CONSUMED("Item consumed"),
    SANS_DIALOG_1("It seems that you are determined to finish me off...\n\n[ Enter to Continue ]"),
    SANS_DIALOG_2("How about we put our differences aside and stop this fight?\n\n[ Enter to Continue ]"),
    SANS_DIALOG_3("Then ... what do you think, do we stop this fight?\n\n[ Enter to Continue ]"),

    SANS_DIALOG_4("Ohh....so you really want to end this fight?\n\n[ Enter to Continue ]"),

    SANS_DIALOG_5("I didn't expect that choice on your part....\n\n[ Enter to Continue ]"),

    SANS_DIALOG_6("If you really want to finish this battle...\n \n\n[ Enter to Continue ]"),

    SANS_DIALOG_7("DON'T COME B A C K"),
    SANS_DIALOG_8("Did you really think that would work?\n \n\n[ Enter to Continue ]"),
    SANS_DIALOG_9("I didn't expect that... it seems like you win...\n \n\n[ Enter to Continue ]");

    private final String value;

    StateMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
