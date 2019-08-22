package io.hexlet.ds;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListImpl<T> implements List<T> {

    private Node<T> firstInList;

    private Node<T> lastInList;

    private int size;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private static class Node<T> {

        T item;
        Node<T> nextNode;
        Node<T> prevNode;

        public Node(final T item, final Node<T> prevNode, final Node<T> nextNode) {
            this.item = item;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }
    }

    private T unlinkFirst(Node<T> first) {
        final T item = first.item;
        final Node<T> nextNode = first.nextNode;
        first.item = null;
        first.nextNode = null;

        firstInList = nextNode;
        if (nextNode == null) {
            lastInList = null;
        } else {
            nextNode.prevNode = null;
        }
        size--;
        return item;
    }

    private T unlinkLast(Node<T> last) {
        final T item = last.item;
        final Node<T> prevNode = last.prevNode;
        last.item = null;
        last.prevNode = null;

        lastInList = prevNode;
        if (prevNode == null) {
            firstInList = null;
        } else {
            prevNode.nextNode = null;
        }
        size--;
        return item;
    }

    private void linkFirst(final T t) {
        final Node<T> nextNode = firstInList;
        final Node<T> newFirst = new Node<>(t, null, nextNode);
        firstInList = newFirst;
        if (nextNode == null) {
            lastInList = newFirst;
        } else {
            nextNode.prevNode = newFirst;
        }
        size++;
    }

    private void linkLast(final T t) {
        final Node<T> prevNode = lastInList;
        final Node<T> newLast = new Node<>(t, prevNode, null);
        lastInList = newLast;
        if (prevNode == null) {
            firstInList = newLast;
        } else {
            prevNode.nextNode = newLast;
        }
        size++;
    }

    private T unlink(Node<T> node) {
        final T item = node.item;
        final Node<T> prevNode = node.prevNode;
        final Node<T> nextNode = node.nextNode;

        if (prevNode == null) {
            firstInList = nextNode;
        } else {
            prevNode.nextNode = nextNode;
            node.prevNode = null;
        }

        if (nextNode == null) {
            lastInList = prevNode;
        } else {
            nextNode.prevNode = prevNode;
            node.nextNode = null;
        }

        node.item = null;
        size--;
        return item;
    }

    private void link(final T t, Node<T> node) {
        final Node<T> prevNode = node.prevNode;
        final Node<T> newNode = new Node<>(t, prevNode, node);

        node.prevNode = newNode;
        if (prevNode == null) {
            firstInList = newNode;
        } else {
            prevNode.nextNode = newNode;
        }
        size++;
    }

}
