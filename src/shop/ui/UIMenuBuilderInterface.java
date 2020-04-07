package shop.ui;

public interface UIMenuBuilderInterface {
    public UIMenu toUIMenu(String heading);
    public void add(String prompt, UIMenuAction action);
}
