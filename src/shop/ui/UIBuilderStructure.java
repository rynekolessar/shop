package shop.ui;

import java.util.ArrayList;
import java.util.List;

abstract class UIBuilderStructure<T1> {
    protected List<Pair<String, T1>> _structure;

    public UIBuilderStructure() { _structure = new ArrayList<>(); }

    public void add(String prompt, T1 t) { _structure.add(new Pair<>(prompt, t)); }
}

