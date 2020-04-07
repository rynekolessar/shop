package shop.data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
    private final String _title;
    private final int _year;
    private final String _director;

    VideoObj(String title, int year, String director) {
        _title = title;
        _year = year;
        _director = director;
    }

    public String director() {
        return _director;
    }

    public String title() {
        return _title;
    }

    public int year() {
        return _year;
    }

    public int compareTo(Video that) {
        VideoObj thatVideo = (VideoObj) that;
        if (this._title.compareTo(thatVideo._title) != 0) {
            return this._title.compareTo(thatVideo._title);
        } else {
            if (this._year != thatVideo._year) {
                return this._year - thatVideo._year;
            } else {
                return this._director.compareTo(thatVideo._director);
            }
        }
    }
    public boolean equals(Object thatObject) {
        if (thatObject instanceof VideoObj) {
            VideoObj other = (VideoObj) thatObject;
            return (this._director.equals(other.director()) && this._title.equals(other.title()) && this._year == other.year());
        }
        return false;
    }

    public int hashCode() {
        int result = 17;
        int prime = 37;
        result = prime * result + _title.hashCode();
        result = prime * result + _year;
        result = prime * result + _director.hashCode();
        return result;
    }

    public String toString() {
        return _title + " (" + _year + ") : " + _director;
    }
}
