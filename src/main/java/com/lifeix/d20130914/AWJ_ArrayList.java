package com.lifeix.d20130914;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class AWJ_ArrayList<E> implements List<E>,java.io.Serializable{


	private static final long serialVersionUID = 8683452581122892190L;

	private int size;

	private Object[] data;

	private int count = 0;

	public AWJ_ArrayList(int initialCapacity) {
		super();
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: "+
					initialCapacity);
		this.data = new Object[initialCapacity];
	}

	public AWJ_ArrayList() {
		this(10);
	}

	public AWJ_ArrayList(Collection<? extends E> c) {
		data = c.toArray();
		size = data.length;
		if (data.getClass() != Object[].class)
			data = Arrays.copyOf(data, size, Object[].class);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	public Object[] toArray() {
		return Arrays.copyOf(data, size);
	}

	public <T> T[] toArray(T[] a) {
		if (a.length < size)
			return (T[]) Arrays.copyOf(data, size, a.getClass());
		System.arraycopy(data, 0, a, 0, size);
		if (a.length > size)
			a[size] = null;
		return a;
	}

	public void ensureCapacity(int minCapacity) {
		count++;
		int oldCapacity = data.length;
		if (minCapacity > oldCapacity) {
			Object oldData[] = data;
			int newCapacity = (oldCapacity * 3)/2 + 1;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			// minCapacity is usually close to size, so this is a win:
			data = Arrays.copyOf(data, newCapacity);
		}
	}

	public boolean add(E e) {
		ensureCapacity(size + 1);  // Increments count!!
		data[size++] = e;
		return true;
	}

	public boolean remove(Object o) {
		if (o == null) {
			for (int index = 0; index < size; index++)
				if (data[index] == null) {
					fastRemove(index);
					return true;
				}
		} else {
			for (int index = 0; index < size; index++)
				if (o.equals(data[index])) {
					fastRemove(index);
					return true;
				}
		}
		return false;
	}

	private void fastRemove(int index) {
		count++;
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(data, index+1, data, index,
					numMoved);
		data[--size] = null; // Let gc do its work
	}

	public boolean addAll(Collection<? extends E> c) {
		Object[] a = c.toArray();
		int numNew = a.length;
		ensureCapacity(size + numNew);  // Increments count
		System.arraycopy(a, 0, data, size, numNew);
		size += numNew;
		return numNew != 0;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException(
					"Index: " + index + ", Size: " + size);

		Object[] a = c.toArray();
		int numNew = a.length;
		ensureCapacity(size + numNew);  // Increments count

		int numMoved = size - index;
		if (numMoved > 0)
			System.arraycopy(data, index, data, index + numNew,
					numMoved);

		System.arraycopy(a, 0, data, index, numNew);
		size += numNew;
		return numNew != 0;
	}

	public void clear() {
		count++;

		// Let gc do its work
		for (int i = 0; i < size; i++)
			data[i] = null;

		size = 0;

	}

	public E get(int index) {
		RangeCheck(index);
		return (E) data[index];
	}

	public E set(int index, E element) {
		RangeCheck(index);
		E oldValue = (E) data[index];
		data[index] = element;
		return oldValue;
	}



	public void add(int index, E element) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException(
					"Index: "+index+", Size: "+size);

		ensureCapacity(size+1);  // Increments count!!
		System.arraycopy(data, index, data, index + 1,
				size - index);
		data[index] = element;
		size++;

	}

	public E remove(int index) {
		RangeCheck(index);

		count++;
		E oldValue = (E) data[index];

		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(data, index+1, data, index,
					numMoved);
		data[--size] = null; // Let gc do its work

		return oldValue;
	}

	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++)
				if (data[i]==null)
					return i;
		} else {
			for (int i = 0; i < size; i++)
				if (o.equals(data[i]))
					return i;
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		if (o == null) {
			for (int i = size-1; i >= 0; i--)
				if (data[i]==null)
					return i;
		} else {
			for (int i = size-1; i >= 0; i--)
				if (o.equals(data[i]))
					return i;
		}
		return -1;
	}

	private void RangeCheck(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException(
					"Index: "+index+", Size: "+size);
	}

	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean containsAll(Collection<?> c) {
		Iterator<?> e = c.iterator();
		while (e.hasNext())
			if (!contains(e.next()))
				return false;
		return true;
	}

	public boolean removeAll(Collection<?> c) {
		boolean modified = false;
		Iterator<?> e = iterator();
		while (e.hasNext()) {
			if (c.contains(e.next())) {
				e.remove();
				modified = true;
			}
		}
		return modified;
	}

	public boolean retainAll(Collection<?> c) {
		boolean modified = false;
		Iterator<E> e = iterator();
		while (e.hasNext()) {
			if (!c.contains(e.next())) {
				e.remove();
				modified = true;
			}
		}
		return modified;
	}


	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
