package shop.main;

import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;
import shop.command.Command;
import shop.ui.*;
import java.util.Iterator;
import java.util.Comparator;

public class Control {
    private UIMenu[] _menus;
    private static int _state;
    private static UIForm _getVideoForm;
    private static UIFormTest _numberTest;
    private UIFormTest _stringTest;
    private static Inventory _inventory;
    private static UI _ui;

    public enum State {
        EXITED(0), EXIT(1), START(2), NUMSTATES(10);
        int state;
        State(int num) { this.state = num; }
        int getState() { return state; }
    }

    public enum FormTest implements UIFormTest  {
        STRINGTEST {
            @Override
            public boolean run(String input) {
                return !"".equals(input.trim());
            }
        },
        NUMBERTEST {
            @Override
            public boolean run(String input) {
                try {
                    Integer.parseInt(input);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        },
        YEARTEST {
            @Override
            public boolean run(String input) {
                try {
                    int i = Integer.parseInt(input);
                    return i > 1800 && i < 5000;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
    }

    public enum MenuState implements UIMenuAction {
        DEFAULT {
            @Override
            public void run() {
                _ui.displayError("doh!");
            }

        },
        ADDREMOVE {
            @Override
            public void run() {
                String[] result1 = _ui.processForm(_getVideoForm);
                Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
                UIFormBuilder f = new UIFormBuilder();
                f.add("Number of copies to add/remove", _numberTest);
                String[] result2 = _ui.processForm(f.toUIForm(""));
                Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
                if (! c.run()) {
                    _ui.displayError("Command failed");
                }
            }
        },
        CHECKIN {
            @Override
            public void run() {
                String[] result1 = _ui.processForm(_getVideoForm);
                Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
                if (!Data.newInCmd(_inventory,v).run()) {
                    _ui.displayError("Command failed");
                }
            }
        },
        CHECKOUT {
            @Override
            public void run() {
                String[] result1 = _ui.processForm(_getVideoForm);
                Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
                if (!Data.newOutCmd(_inventory,v).run()) {
                    _ui.displayError("Command failed");
                }
            }
        },
        PRINT {
            @Override
            public void run() {
                _ui.displayMessage(_inventory.toString());
            }
        },
        CLEAR {
            @Override
            public void run() {
                if (!Data.newClearCmd(_inventory).run()) {
                    _ui.displayError("Command failed");
                }
            }
        },
        UNDO {
            @Override
            public void run() {
                if (!Data.newUndoCmd(_inventory).run()) {
                    _ui.displayError("Command failed");
                }
            }
        },
        REDO {
            @Override
            public void run() {
                if (!Data.newRedoCmd(_inventory).run()) {
                    _ui.displayError("Command failed");
                }
            }
        },
        PRINTTOPTEN {
            @Override
            public void run() {
                Comparator<Record> cmp = Comparator.comparing(r -> -(r.numRentals()));
                Iterator<Record> iterator = _inventory.iterator(cmp);
                String result = "";
                int count=0;
                while(iterator.hasNext() && count<10) {
                    Record r = iterator.next();
                    result+=("Video: " + r.video().toString() + ", Num Rentals: " + r.numRentals() + "\n");
                    count++;
                }
                _ui.displayMessage(result);
            }
        },
        EXIT {
            @Override
            public void run() {
                _state = State.EXITED.getState();
            }
        },
        INITWITHBOGUS {
            @Override
            public void run() {
                Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
                Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
                Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
                Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
                Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
                Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
                Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
                Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
                Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
                Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
                Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
                Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
                Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
                Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
                Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
                Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
                Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
                Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
                Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
                Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
                Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
                Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
                Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
                Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
                Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
                Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
            }
        },
        YES {
            @Override
            public void run() {
                State.EXITED.getState();
            }
        },
        NO {
            @Override
            public void run() {
                _state = State.START.getState();
            }
        },
        DEFAULTTEXT {
            @Override
            public void run() { }
        }

    }
    
    Control(Inventory inventory, UI ui) {
        _inventory = inventory;
        _ui = ui;
        _menus = new UIMenu[ State.NUMSTATES.getState()];
        _state = State.START.getState();
        addSTART(State.START.getState());
        addEXIT(State.EXIT.getState());
        UIFormTest yearTest = FormTest.YEARTEST;
        _numberTest = FormTest.NUMBERTEST;
        _stringTest = FormTest.STRINGTEST;

        UIFormBuilder f = new UIFormBuilder();
        f.add("Title", _stringTest);
        f.add("Year", yearTest);
        f.add("Director", _stringTest);

        _getVideoForm = f.toUIForm("Enter Video");
    }

    void run() {
        try {
            while (_state != State.EXITED.getState()) {
                _ui.processMenu(_menus[_state]);
            }
        } catch (Error e) {
            _ui.displayError("UI closed");
        }
    }

    private void addSTART(int stateNum) {
        UIMenuBuilder m = new UIMenuBuilder();

        m.add("Default", MenuState.DEFAULT);
        m.add("Add/Remove copies of a video", MenuState.ADDREMOVE);
        m.add("Check in a video", MenuState.CHECKIN);
        m.add("Check out a video", MenuState.CHECKOUT);
        m.add("Print the inventory", MenuState.PRINT);
        m.add("Clear the inventory", MenuState.CLEAR);
        m.add("Undo", MenuState.UNDO);
        m.add("Redo", MenuState.REDO);
        m.add("Print top ten all time rentals in order", MenuState.PRINTTOPTEN);
        m.add("Exit", MenuState.EXIT);
        m.add("Initialize with bogus contents", MenuState.INITWITHBOGUS);

        _menus[stateNum] = m.toUIMenu("Bob's Video");
    }

    private void addEXIT(int stateNum) {
        UIMenuBuilder m = new UIMenuBuilder();

        m.add("Default", MenuState.DEFAULTTEXT);
        m.add("Yes", MenuState.YES);
        m.add("No", MenuState.NO);

        _menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
    }
}
