package shop.ui;

abstract class UIStructure<T1> {
    protected final String _heading;
    protected final Pair<String, T1>[] _struct;

    UIStructure(String heading, Pair<String, T1>[] struct) {
        _heading = heading;
        _struct = struct;
    }
    public int size() {
        return _struct.length;
    }
    public String getHeading() {
        return _heading;
    }
    public String getPrompt(int i) {
        return _struct[i].t1.toString();
    }
}