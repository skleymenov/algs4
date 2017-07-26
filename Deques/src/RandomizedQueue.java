/*-----------------------------------------------------------------------------
*
*  Author:          Sergey Kleymenov
*  Written:         7/28/2017
*  Last Updated:    7/28/2017
*
*  A randomized queue is similar to a stack or queue,
*  except that the item removed is chosen uniformly at random from items in the data structure.
*
------------------------------------------------------------------------------*/

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private int size = 0;
    private Item[] elements;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        size = 0;
        elements = (Item[]) new Object[1];
    }

    public boolean isEmpty()                 // is the queue empty?
    {
        return size == 0;
    }

    public int size()                        // return the number of items on the queue
    {
        return size;
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
        {
            copy[i] = elements[i];
        }
        elements = copy;
    }

    private void checkEmpty()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
    }

    public void enqueue(Item item)           // add the item
    {
        if (item == null)
        {
            throw new NullPointerException();
        }

        elements[size++] = item;
        if (size == elements.length)
        {
            resize(2 * elements.length);
        }
    }

    public Item dequeue()                    // remove and return a random item
    {
        checkEmpty();
        int index = StdRandom.uniform(size);
        Item value = elements[index];

        size--;
        if (index < size)
        {
            elements[index] = elements[size];
        }
        elements[size] = null;

        if (size > 0 && size == elements.length / 4)
        {
            resize(elements.length / 2);
        }

        return value;
    }

    public Item sample()                     // return (but do not remove) a random item
    {
        checkEmpty();
        int index = StdRandom.uniform(size);
        Item value = elements[index];
        return value;
    }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private Item[] output;
        private int counter = 0;

        private RandomizedQueueIterator()
        {
            output = (Item[]) new Object[size];
            for (int i = 0; i < size; i++)
            {
                output[i] = elements[i];
            }
            StdRandom.shuffle(output);
        }

        @Override
        public boolean hasNext()
        {
            return counter < size;
        }

        @Override
        public Item next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            return output[counter++];
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }
}

