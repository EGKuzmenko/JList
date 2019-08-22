package io.hexlet.ds;

import java.util.*;

public class ListImpl<T> implements List<T> {

    private Node<T> firstInList;

    private Node<T> lastInList;

    private int size;

    public ListImpl() {
        this.size = 0;
        this.firstInList = null;
        this.lastInList = null;
    }

    public ListImpl(Collection <? extends T> c) {
        this();
        if (c == null) {
            throw new NullPointerException();
        }
        addAll(c);
    }

    public T getFirst() {
        if (firstInList == null) {
            throw new NoSuchElementException();
        }

        return firstInList.item;
    }

    public T getLast() {
        if (lastInList == null) {
            throw new NoSuchElementException();
        }

        return lastInList.item;
    }

    public T removeFirst() {
        final Node<T> first = firstInList;
        if (first == null) {
            throw new NoSuchElementException();
        }

        return unlinkFirst(first);
    }

    public T removeLast() {
        final Node<T> last = lastInList;
        if (last == null) {
            throw new NoSuchElementException();
        }

        return unlinkLast(last);
    }

    public void addFirst(final T t) {
        linkFirst(t);
    }

    public void addLast(final T t) {
        linkLast(t);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        return indexOf(o) != -1;
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
    public boolean add(final T t) {
        linkLast(t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<T> node = firstInList; node != null; node = node.nextNode) {
                if (node.item == null) {
                    unlink(node);
                    return true;
                }
            }
        } else {
            for (Node<T> node = firstInList; node != null; node = node.nextNode) {
                if (o.equals(node.item)) {
                    unlink(node);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends T> c) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (c == null) {
            throw new NullPointerException();
        }

        int i = index;
        for (final T item : c) {
            add(i, item);
            i++;
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }

        for (final T item : this) {
            if (!c.contains(item)) {
                this.remove(item);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        this.firstInList = null;
        this.lastInList = null;
        this.size = 0;
    }

    @Override
    public T get(final int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        Node<T> current = firstInList;
        while (i != index) {
            current = current.nextNode;
            i++;
        }

        return current.item;
    }

    @Override
    public T set(final int index, final T element) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        final Node<T> node = getItemByIndex(index);
        T tempItem = node.item;
        node.item = element;
        return tempItem;
    }

    @Override
    public void add(final int index, final T element) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        link(element, getItemByIndex(index));
    }

    @Override
    public T remove(final int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        int i = 0;
        Node<T> current = firstInList;
        while (i != index) {
            current = current.nextNode;
            i++;
        }

        return unlink(current);
    }

    @Override
    public int indexOf(final Object o) {
        int i = 0;

        if (o == null) {
            for (Node<T> node = firstInList; node != null; node = node.nextNode) {
                if (node.item == null) {
                    return i;
                }
                i++;
            }
        } else {
            for (Node<T> node = firstInList; node != null; node = node.nextNode) {
                if (o.equals(node.item)) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(final Object o) {
        int i = this.size() - 1;

        if (o == null) {
            for (Node<T> node = lastInList; node != null; node = node.prevNode) {
                if (node.item == null) {
                    return i;
                }
                i--;
            }
        } else {
            for (Node<T> node = lastInList; node != null; node = node.prevNode) {
                if (o.equals(node.item)) {
                    return i;
                }
                i--;
            }
        }
        return -1;
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

    private Node<T> getItemByIndex(final int index) {
        int i = 0;
        Node<T> current = firstInList;

        if (index == this.size() - 1) {
            current = lastInList;
            return current;
        }

        while (i != index) {
            current = current.nextNode;
            i++;
        }
        return current;
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
