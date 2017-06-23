

import static org.junit.Assert.*;

/*
 * Created by Sergey Kleymenov on 22/06/2017.
 */
public class PercolationTest
{
    @org.junit.Test
    public void open() throws Exception
    {
        final int size = 10;
        Percolation model = new Percolation(size);
        for (int i = 1; i <= size; i++)
        {
            for (int j = 1; j <= size; j++)
            {
                assertFalse(model.isOpen(i, j));
            }
        }
        for (int i = 1; i <= size; i++)
        {
            for (int j = 1; j <= size; j++)
            {
                model.open(i, j);
                assertTrue(model.isOpen(i, j));
            }
        }
    }

    @org.junit.Test
    public void isFull() throws Exception
    {
        final int size = 10;
        Percolation model = new Percolation(size);
        assertFalse(model.isFull(1, 1));
        model.open(2, 1);
        assertFalse(model.isFull(2, 1));
        model.open(1, 1);
        assertTrue(model.isFull(2, 1));
        assertTrue(model.isFull(1, 1));
    }

    @org.junit.Test
    public void numberOfOpenSites() throws Exception
    {
        final int size = 10;
        Percolation model = new Percolation(size);
        assertEquals(0, model.numberOfOpenSites());

        for (int i = 1; i <= size; i++)
        {
            model.open(i, i);
            assertEquals(i, model.numberOfOpenSites());
        }
    }

    @org.junit.Test
    public void percolates() throws Exception
    {
        final int size = 10;
        Percolation model = new Percolation(size);

        assertFalse(model.percolates());
        for (int i = 1; i < size; i++)
        {
            model.open(1, i);
            assertFalse(model.percolates());
        }

        model.open(size, 1);
        assertTrue(model.percolates());

    }

}