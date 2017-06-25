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
    }

    @org.junit.Test
    public void size() throws Exception
    {
    }

    @org.junit.Test
    public void addFirst() throws Exception
    {
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