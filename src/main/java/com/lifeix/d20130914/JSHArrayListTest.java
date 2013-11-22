package com.lifeix.d20130914;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JSHArrayListTest {
	public static void main(String[] args) {
		List<Integer> list = new JSHArrayList<Integer>();
		System.out.println("init list size :" + list.size());
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		System.out.println("list size :" + list.size());
		printList(list);
		list.add(2, 5);
		list.add(4, 7);
		System.out.println("list size :" + list.size());
		printList(list);
		List<Integer> temporaryList = new JSHArrayList<Integer>();
		temporaryList.addAll(list);
		System.out.println("temporaryList size :" + temporaryList.size());
		printList(temporaryList);
		temporaryList.remove(4);
		temporaryList.remove(2);
		System.out.println("temporaryList size :" + temporaryList.size());
		printList(temporaryList);
		list.removeAll(temporaryList);
		System.out.println("list size :" + list.size());
		printList(list);
	}
	
	public static <T> void printList(List<T> list) {
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < list.size(); i++) {
			str.append(list.get(i)).append(",");
		}
		System.out.println(str.deleteCharAt(str.length()-1).toString());
	}
}

class JSHArrayList<E> implements List<E> {
	
	private Object[] elementData;

	private int size;
	
	public JSHArrayList(int initCapacity) {
		super();
		if(initCapacity < 0) {
			throw new IllegalArgumentException("Illegal Capacity: "+ initCapacity);
		}
		this.elementData = new Object[initCapacity];
	}
	
	public JSHArrayList() {
		this(10);
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Deprecated
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elementData, size);
	}

	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size)
			return (T[]) Arrays.copyOf(elementData, size, a.getClass());
		System.arraycopy(elementData, 0, a, 0, size);
		if (a.length > size) 
			a[size] = null;
		return a;
	}

	@Override
	public boolean add(E e) {
		ensureCapacity(size + 1);
		elementData[size++] = e;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if(o == null) {
			for(int index = 0; index < size; index++) {
				if(elementData[index] == null) {
					fastRemove(index);
					return true;
				}
			}
		}else {
			for(int index = 0; index < size; index++) {
				if(o.equals(elementData[index])) {
					fastRemove(index);
					return true;
				}
			}
		}
		return false;
	}
	
	private void fastRemove(int index) {
		int numMoved = size - index -1;
		if(numMoved > 0) {
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		}
		elementData[--size] = null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		Object[] a = c.toArray();
		for(int i = 0; i < a.length; i++) {
			if(!contains(a[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		Object[] a = c.toArray();
		int numNew = a.length;
		ensureCapacity(size + numNew);
		System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		RangeCheck(index);
		
		Object[] a = c.toArray();
		int numNew = a.length;
		ensureCapacity(size + numNew);
		
		int numMoved = size - index;
		if(numMoved > 0) {
			System.arraycopy(elementData, index, elementData, index+numNew, numMoved);
		}
		System.arraycopy(a, 0, elementData, index, numNew);
		size += numNew;
		return numNew != 0;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		Object[] a = c.toArray();
		for(int i = 0; i < a.length; i++) {
			if(!remove(a[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
		for(int index = 0; index < size; index++) {
			elementData[index] = null;
		}
		size = 0;
	}

	@Override
	public E get(int index) {
		RangeCheck(index);
		return (E) elementData[index];
	}

	@Override
	public E set(int index, E element) {
		RangeCheck(index);
		E oldValue = (E) elementData[index];
		elementData[index] = element;
		return oldValue;
	}

	@Override
	public void add(int index, E element) {
		RangeCheck(index);
		ensureCapacity(size+1); 
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = element;
		size++;
	}

	@Override
	public E remove(int index) {
		RangeCheck(index);
		E oldValue = (E) elementData[index];
		int numMoved = size - index - 1;
		if(numMoved > 0) {
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		}
		elementData[--size] = null;
		return oldValue;
	}

	@Override
	public int indexOf(Object o) {
		if(o == null) {
			for(int index = 0; index < size; index++) {
				if(elementData[index] == null)
					return index;
			}
		}else {
			for(int index = 0; index < size; index++) {
				if(o.equals(elementData[index]))
					return index;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		if(o == null) {
			for(int index = size-1; index >= 0; index--) {
				if(elementData[index] == null)
					return index;
			}
		}else {
			for(int index = size-1; index >= 0; index--) {
				if(o.equals(elementData[index]))
					return index;
			}
		}
		return -1;
	}

	@Deprecated
	public ListIterator<E> listIterator() {
		return null;
	}

	@Deprecated
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}
	
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = elementData.length;
		if(minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity*3)/2 + 1;
			if(newCapacity < minCapacity)
				newCapacity = minCapacity;
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}
	
	private void RangeCheck(int index) {
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
}
