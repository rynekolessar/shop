package shop.ui;

public interface UIFormBuilderInterface {
    public UIForm toUIForm(String heading);
    public void add(String prompt, UIFormTest test);
}
