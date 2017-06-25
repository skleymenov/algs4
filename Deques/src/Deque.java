/*-----------------------------------------------------------------------------
*
*  Author:          Sergey Kleymenov
*  Written:         6/24/2017
*  Last Updated:    6/24/2017
*
*  A double-ended queue or deque (pronounced "deck")
*  is a generalization of a stack and a queue that supports
*  adding and removing items from either the front or the back of the data structure.
*
------------------------------------------------------------------------------*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable
{
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node
    {
        Item item;
        Node next;
        Node prev;
    }

    public Deque()                           // construct an empty deque
    {

    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return true;
    }

    public int size()                        // return the number of items on the deque
    {
        return 0;
    }

    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (oldFirst == null) last = first;
        size += 1;
    }

    public void addLast(Item item)           // add the item to the end
    {
        if (item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (oldLast == null) first = last;
        size += 1;

    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        size -= 1;
        return item;
    }

    public Item removeLast()                 // remove and return the item from the end
    {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        size -= 1;
        return item;

    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;

        @Override
        public boolean hasNext()
        {
            return current != null;
        }

        @Override
        public Item next()
        {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

}
