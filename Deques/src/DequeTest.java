import static org.junit.Assert.*;

/**
 * Created by marchhare on 25/06/2017.
 */
public class DequeTest
{
    @org.junit.Test
    public void isEmpty() throws Exception
    {
        Deque<Integer> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(5);
        assertFalse(deque.isEmpty());
        deque.removeLast();
        assertTrue(deque.isEmpty());
    }

    @org.junit.Test
    public void size() throws Exception
    {
        Deque<Integer> deque = new Deque<>();
        assertEquals(0, deque.size());
        for (int i = 1; i <= 100; i++)
        {
            deque.addFirst(i);
            assertEquals(i, deque.size());
        }
        for (int i = 100; i > 0; i--)
        {
            assertEquals(i, deque.size());
            deque.removeFirst();
        }

        assertEquals(0, deque.size());
    }

    @org.junit.Test
    public void addFirst() throws Exception
    {
        Deque<Integer> deque = new Deque<>();

        for (int i = 0; i < 100; i++)
        {
            deque.addFirst(i);
        }
        for (int i = 99; i >= 0; i--)
        {
            assertEquals((long)i, (long)deque.removeFirst());
        }
    }

    @org.junit.Test
    public void addLast() throws Exception
    {

    }

    @org.junit.Test
    public void removeFirst() throws Exception
    {
    }

    @org.junit.Test
    public void removeLast() throws Exception
    {
    }

    @org.junit.Test
    public void iterator() throws Exception
    {
    }

}