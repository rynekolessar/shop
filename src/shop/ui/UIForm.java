package shop.ui;

/**
 * @see UIFormBuilder
 */
public final class UIForm extends UIStructure<UIFormTest> {
    UIForm(String heading, Pair<String, UIFormTest>[] structure) {
        super(heading, structure);
    }

    public boolean checkInput(int i, String input) {
        if (null == _struct[i])
            return true;
        return _struct[i].t2.run(input);
    }
}
