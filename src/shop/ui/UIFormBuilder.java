package shop.ui;

import java.util.ArrayList;

public final class UIFormBuilder extends UIBuilderStructure<UIFormTest>{
    public UIFormBuilder() {
        _structure = new ArrayList<>();
    }
    public UIForm toUIForm(String heading) {
        if (null == heading) { throw new IllegalArgumentException(); }

        if (_structure.size() < 1) { throw new IllegalStateException(); }

        Pair<String, UIFormTest>[] array = new Pair[_structure.size()];

        for (int i = 0; i <_structure.size(); i++) {
            array[i] = _structure.get(i);

        }
        return new UIForm(heading, array);
    }
}
