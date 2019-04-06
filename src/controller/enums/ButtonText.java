package controller.enums;

public  enum ButtonText {
  SAVEASTEXT ("Save as");


    private final String BUTTONTEXT;

    private ButtonText(String text){
        this.BUTTONTEXT=text;
    }

    public final String getBUTTONTEXT() {
        return BUTTONTEXT;
    }
}
