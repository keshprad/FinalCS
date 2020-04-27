package com.rad.entity;
import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
/**
 * Creates a TreeSetQueue.
 *
 * @param <T> the type
 * @author Akshanth Srivatsa
 */
class TreeSetQueue<T> extends AbstractQueue<T> {
    /**
     * The tree set queue to modify.
     */
    private TreeSet<T> set;
    /**
     * Creates a new TreeSetQueue.
     *
     * @param comparator the comparator for the tree set.
     */
    TreeSetQueue(Comparator<T> comparator) {
        set = new TreeSet<T>(comparator);
    }
    /**
     * The iterator over the elements.
     *
     * @return the iterator
     */
    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
    /**
     * The size of the queue.
     *
     * @return the size.
     */
    @Override
    public int size() {
        return set.size();
    }
    /**
     * Adds the item to the set.
     *
     * @param t the object to add.
     * @return true.
     */
    @Override
    public boolean offer(T t) {
        set.add(t);
        return true;
    }
    /**
     * Returns and removes the first element.
     *
     * @return the first element
     */
    @Override
    public T poll() {
        return set.pollFirst();
    }
    /**
     * Returns the first element without removing it.
     *
     * @return the first element.
     */
    @Override
    public T peek() {
        return set.first();
    }
}
