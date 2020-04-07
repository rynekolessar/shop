package shop.data;

import java.util.*;
import shop.command.Command;
import shop.command.CommandHistory;
import shop.command.UndoableCommand;
import shop.command.CommandHistoryFactory;

/**
 * Implementation of Inventory interface.
 * @see Data
 */
final class InventorySet implements Inventory {
    private Map<Video,Record> _data;
    private final CommandHistory _history;

    InventorySet() {
        this._data = new HashMap<Video,Record>();
        this._history = CommandHistoryFactory.newCommandHistory();
    }

    public int size() {
        return _data.size();
    }

    public Record get(Video v) {
        return _data.get(v);
    }

    public Iterator<Record> iterator() {
        return Collections.unmodifiableCollection(_data.values()).iterator();
    }

    public Iterator<Record> iterator(Comparator<Record> comparator) {
        List<Record> l = new ArrayList<>(_data.values());
        l.sort(comparator);
        return l.iterator();
    }

    Record addNumOwned(Video video, int change) {
        if (video == null || change == 0) {
            throw new IllegalArgumentException();
        }

        RecordObj r = (RecordObj) _data.get(video);
        if (r == null && change < 1) {
            throw new IllegalArgumentException();
        } else if (r == null) {
            _data.put(video, new RecordObj(video, change, 0, 0));
        } else if (r.numOwned+change < r.numOut) {
            throw new IllegalArgumentException();
        } else if (r.numOwned + change < 1) {
            _data.remove(video);
        } else {
            _data.put(video, new RecordObj(video, r.numOwned + change, r.numOut, r.numRentals));
        }
        return r;
    }

    CommandHistory getHistory() {
        return _history;
    }

    void replaceEntry(Video video, Record record) {
        _data.remove(video);
        if (record != null) {
            _data.put(video, ((RecordObj)record).copy());
        }
    }

    Map clear() {
        HashMap<Video,Record> h = new HashMap<Video,Record>(_data);
        _data.clear();
        return h;
    }

    void replaceMap(Map<Video,Record> data) {
        _data = data;
    }

    Record checkIn(Video video) {
        RecordObj r1 = (RecordObj)_data.get(video);
        if (r1 == null || r1.numOut < 1) {
            throw new IllegalArgumentException();
        }
        _data.put(video, new RecordObj(video, r1.numOwned, r1.numOut - 1, r1.numRentals));
        return r1;
    }

    Record checkOut(Video video) {
        RecordObj r1 = (RecordObj)_data.get(video);
        if(r1 == null || r1.numOut == r1.numOwned) {
            throw new IllegalArgumentException();
        }
        _data.put(video,new RecordObj(video, r1.numOwned, r1.numOut + 1, r1.numRentals + 1));
        return r1;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Database:\n");
        Iterator i = _data.values().iterator();
        while (i.hasNext()) {
            buffer.append("  ");
            buffer.append(i.next());
            buffer.append("\n");
        }
        return buffer.toString();
    }

    /**
     * Implementation of Record interface.
     *
     * <p>This is a utility class for Inventory.  Fields are mutable and
     * package-private.</p>
     *
     * <p><b>Class Invariant:</b> No two instances may reference the same Video.</p>
     *
     * @see Record
     */
    private static final class RecordObj implements Record {
        Video video;    // the video
        int numOwned;   // copies owned
        int numOut;     // copies currently rented
        int numRentals; // total times video has been rented

        RecordObj(Video video, int numOwned, int numOut, int numRentals) {
            this.video = video;
            this.numOwned = numOwned;
            this.numOut = numOut;
            this.numRentals = numRentals;
        }
        RecordObj copy() {
            return new RecordObj(video, numOwned, numOut, numRentals);
        }
        public Video video() {
            return video;
        }
        public int numOwned() {
            return numOwned;
        }
        public int numOut() {
            return numOut;
        }
        public int numRentals() {
            return numRentals;
        }
        public boolean equals(Object thatObject) {
            return video.equals(((Record)thatObject).video());
        }
        public int hashCode() {
            return video.hashCode();
        }
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(video);
            buffer.append(" [");
            buffer.append(numOwned);
            buffer.append(",");
            buffer.append(numOut);
            buffer.append(",");
            buffer.append(numRentals);
            buffer.append("]");
            return buffer.toString();
        }
    }
}
