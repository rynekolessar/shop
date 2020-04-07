package shop.ui;

/**
 * @see UIMenuBuilder
 */
public final class UIMenu extends UIStructure<UIMenuAction>{
    UIMenu(String heading, Pair<String, UIMenuAction>[] structure) {
        super(heading, structure);
    }

    public void runAction(int i) {
        _struct[i].t2.run();
    }
}
