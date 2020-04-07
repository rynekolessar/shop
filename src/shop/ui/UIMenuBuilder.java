package shop.ui;

import java.util.ArrayList;

public final class UIMenuBuilder extends UIBuilderStructure<UIMenuAction> {
    public UIMenuBuilder() {
        _structure = new ArrayList<>();
    }

    public UIMenu toUIMenu(String heading) {
        if (null == heading) { throw new IllegalArgumentException(); }

        if (_structure.size() <= 1) { throw new IllegalStateException(); }

        Pair<String, UIMenuAction>[] array = new Pair[_structure.size()];

        for (int i = 0; i < _structure.size(); i++) {
            array[i] = _structure.get(i);
        }

        return new UIMenu(heading, array);
    }
}
