package shop.ui;

public interface UIMenuInterface {
    public int size();
    public String getHeading();
    public String getPrompt(int i);
    public void runAction(int i);
}
