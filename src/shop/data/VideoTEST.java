package shop.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VideoTEST  {

    @Test
    public void testEquals() {
        VideoObj v1 = new VideoObj("Title", 2020, "Director");
        VideoObj v2 = new VideoObj("Title", 2020, "Director");
        assertEquals(v1, v2);

        v2 = new VideoObj("NotTitle", 2020, "Director");
        assertNotEquals(v1, v2);
    }

    @Test
    public void testCompareTo() {
        VideoObj v1 = new VideoObj("Title", 2020, "Director");
        VideoObj v2 = new VideoObj("Title", 2020, "Director");
        assertEquals(v1.compareTo(v2), 0);


        v2 = new VideoObj("NotTitle", 2020, "Director");
        assertTrue(v1.compareTo(v2) < 0);

        v2 = new VideoObj("Egg", 2020, "Director");
        assertTrue(v1.compareTo(v2) > 0);


        v2 = new VideoObj("Title", 2021, "Director");
        assertTrue(v1.compareTo(v2) < 0);

        v2 = new VideoObj("Title", 1999, "Director");
        assertTrue(v1.compareTo(v2) > 0);


        v2 = new VideoObj("Title", 2020, "Tarantino");
        assertTrue(v1.compareTo(v2) > 0);

        v2 = new VideoObj("Title", 2020, "Ummm");
        assertTrue(v1.compareTo(v2) < 0);
    }

    @Test
    public void testToString() {
        VideoObj v1 = new VideoObj("Title", 2020, "Director");

        assertEquals(v1.toString(), "Title (2020) : Director");
    }

    @Test
    public void testHashCode() {
        assertEquals
                (-875826552,
                        new VideoObj("None", 2009, "Zebra").hashCode());
        assertEquals
                (-1391078111,
                        new VideoObj("Blah", 1954, "Cante").hashCode());
    }
}
