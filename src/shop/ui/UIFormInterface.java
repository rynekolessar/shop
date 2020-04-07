package shop.ui;

public interface UIFormInterface {
    public int size();
    public String getHeading();
    public String getPrompt(int i);
    public boolean checkInput(int i, String input);
}
