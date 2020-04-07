package shop.data;

public interface Record {
    /**
     * @return the video.
     * <p><b>Invariant:</b> <code>video() != null</code>.</p>
     */
    public Video video();

    /**
     * @return the number of copies of the video that are in the inventory.
     * <p><b>Invariant:</b> <code>numOwned() > 0</code>.</p>
     */
    public int numOwned();

    /**
     * @return the number of copies of the video that are currently checked out.
     * <p><b>Invariant:</b> <code>numOut() <= numOwned()</code>.</p>
     */
    public int numOut();

    /**
     * @return the total number of times this video has ever been checked out.
     * <p><b>Invariant:</b> <code>numRentals() >= numOut()</code>.</p>
     */
    public int numRentals();

    /**
     * Delegates to video
     * @param thatObject
     * @return false if thatObject is not a Record created by this package.
     */
    public boolean equals(Object thatObject);

    /**
     * Delegates to video.
     */
    public int hashCode();

    /**
     * @return a string representation of the object in the following format:
     * <code>"video [numOwned,numOut,numRentals]"</code>.
     */
    public String toString();
}
