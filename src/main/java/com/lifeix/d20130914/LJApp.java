package com.lifeix.d20130914;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LJApp<E> implements List<E>,Collection<E> {

	private transient E[] data;

	private int size;

	private int add = 5;

	@SuppressWarnings("unchecked")
	public LJApp(int initsize) {
		super();
		if (initsize < 0)// 初始化容量不能小于0,抛出IllegalArgumentException异常
			throw new IllegalArgumentException("Illegal Capacity: " + initsize);
		this.data = (E[]) new Object[initsize];
		this.size = 0;
	}

	@SuppressWarnings("unchecked")
	public LJApp() {
		this.data = (E[]) new Object[add];
		size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size <= 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E[] toArray() {
		return data;
	}

	@SuppressWarnings({ "hiding", "unchecked" })
	@Override
	public <E> E[] toArray(E[] a) {
		a = (E[]) new Object[size];
		System.arraycopy(data, 0, a, 0, a.length);
		return a;
	}

	@Override
	public boolean add(E e) {
		int need = size + 1;
		addSize(need);
		data[size] = e;
		size++;
		return false;
	}

	@Override
	public boolean remove(Object o) {
		int index = 0;
		while (!data[index].equals(o))
			index++;
		System.arraycopy(data, index + 1, data, index, size - index - 1);
		data[size - 1] = null;
		size--;
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object e : c) {
			if (indexOf(e) < 0) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends E> c) {
		addSize(size + c.size());
		for(Object e : c){
			add((E)e);
		}
		size += c.size();
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		isSize(index);
		addSize(size + c.size());
		System.arraycopy(data, index, data, index + c.size(), c.size());
		System.arraycopy(c, 0, data, index, c.size());
		size += c.size();
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for (Object e : c) {
			if (remove(e))
				size--;
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		for (int i = 0; i < size; i++) {
			E e = data[i];
			boolean retain = false;
			for (Object o : c) {
				if (o.equals(e)) {
					retain = true;
					break;
				}
			}
			if (!retain) {
				remove(i);
				i--;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		data = (E[]) new Object[add];
		size = 0;
	}

	@Override
	public E get(int index) {
		isSize(index);
		return (E) data[index];
	}

	@Override
	public E set(int index, E element) {
		isSize(index);
		data[index] = element;
		return null;
	}

	@Override
	public void add(int index, E element) {
		addSize(size + 1);
		isSize(index);
		@SuppressWarnings("unchecked")
		E[] newData = (E[]) new Object[size+1];
		System.arraycopy(data, index, newData, index + 1, size - index - 1);
		System.arraycopy(newData, index+1, data, index+1, size-index - 1);
		size++;
		data[index] = element;
	}

	@Override
	public E remove(int index) {
		isSize(index);
		E value = (E) data[index];
		int num = size - index - 1;
		if (num > 0) {
			System.arraycopy(data, index + 1, data, index, num);
		}
		data[size--] = null;
		return value;
	}

	@Override
	public int indexOf(Object o) {
		for(int i=0;i<size;i++){
			if(o==null){
				if(data[i] == o)return i;
			}else{
				if(data[i].equals(o))return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
			for (int i = size - 1; i >= 0; i--)
				if (data[i] == null)
					return i;
		} else {
			for (int i = size - 1; i >= 0; i--)
				if (o.equals(data[i]))
					return i;
		}
		return -1;
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
		if (fromIndex <= 0 || toIndex >= size || toIndex < fromIndex)
			throw new IllegalArgumentException("fromIndex is > toIndex");
		List<E> newData = new LJApp<E>();
		for (int i = fromIndex; i <= toIndex; i++) {
			newData.add(data[i]);
		}
		return newData;
	}

	private void isSize(int index) {
		if (index >= size || index < 0)
			throw new IllegalArgumentException("over size " + index);
	}

	private void addSize(int needSize) {
		while (data.length < needSize) {
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) new Object[(data.length * 3) / 2];
			System.arraycopy(data, 0, newData, 0, size);
			data = newData;
		}
	}

	
}
