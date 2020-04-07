package shop.data;

import shop.command.UndoableCommand;

/**
 * Implementation of command to add or remove inventory.
 * @see Data
 */
public class CmdAdd implements UndoableCommand {
    private boolean _runOnce;
    private InventorySet _inventory;
    private Video _video;
    private int _change;
    private Record _oldvalue;

    public CmdAdd(InventorySet inventory, Video video, int change) {
        this._inventory = inventory;
        this._video = video;
        this._change = change;
    }

    public boolean run() {
        if (_runOnce) {
            return false;
        }
        _runOnce = true;

        try {
            _oldvalue = _inventory.addNumOwned(_video,_change);
            _inventory.getHistory().add(this);

            return true;
        } catch (IllegalArgumentException | ClassCastException e) {
            return false;
        }
    }

    public void undo() {
        _inventory.replaceEntry(_video,_oldvalue);
    }

    public void redo() {
        _inventory.addNumOwned(_video,_change);
    }
}
