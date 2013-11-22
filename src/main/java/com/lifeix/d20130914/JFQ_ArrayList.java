package com.lifeix.d20130914;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JFQ_ArrayList implements List {

	private int size = 0;
	private Object[] arrs = null;
	public JFQ_ArrayList() {
		this(0);
	}
	
	public JFQ_ArrayList(int size) {
		arrs = new Object[size];
	}
	
	public JFQ_ArrayList(Object[] arrays) {
		if(null == arrays) {
			throw new NullPointerException("null array");
		}
		this.arrs = arrays;
		size = arrays.length;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFQ_ArrayList jfq = new JFQ_ArrayList();
		jfq.set(0, 3);
		jfq.add(1);
		jfq.add(2);
		jfq.add(1);
		jfq.add(2);
		jfq.add(1);
		jfq.add(2);
		jfq.add(1);
		jfq.add(2);
		jfq.add(1);
		jfq.add(2);
		jfq.add(1);
		jfq.add(2);
		jfq.add(1);
		jfq.add(2);
		jfq.add(1);
		jfq.add(2);
		jfq.add(1);
		jfq.add(2);
		jfq.add(1);
		jfq.add(2);
		jfq.add(1);
		jfq.add(2);
		jfq.remove(2);
		jfq.add(1, 4);
		jfq.addAll(1,new ArrayList(){{add(5);add(6);}});
		for(int i = 0; i < jfq.size(); i++) {
			System.out.println(jfq.get(i));
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size < 1);
	}

	public boolean contains(Object o) {
		for(Object obj : arrs) {
			if(o.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	public Iterator iterator() {
		return null;
	}

	public Object[] toArray() {
		return arrs;
	}

	public Object[] toArray(Object[] a) {
		
		return null;
	}

	public boolean add(Object e) {
		if((size + 1) >= Integer.MAX_VALUE) {
			throw new OutOfMemoryError("out of memory: " + arrs.length);
		}
		if(arrs.length == 0) {
			arrs[0] = e;
		} else if(arrs.length <= size) {
			Object[] temp = new Object[ size*(1 + 3/2)];
			for(int i = 0; i < arrs.length; i++) {
				temp[i] = arrs[i];
			}
			arrs = temp;
		}
		arrs[size++] = e;
		return true;
	}

	public boolean remove(Object o) {
		if(size < 1) {
			return false;
		}
		Object[] temp = new Object[size];
		int count = 0;
		for(Object obj : arrs) {
			if(o.equals(obj)) {
				continue;
			}
			temp[count++] = obj;
		}
		arrs = temp;
		temp = null;
		--size;
		return true;
	}

	public boolean containsAll(Collection c) {
		if(size < c.size()) {
			throw new IllegalArgumentException("the array size is less than set size");
		}
		Iterator it = c.iterator();
		int count = 0;
		while(it.hasNext()) {
			Object o = it.next();
			for(Object obj : arrs) {
				if(o.equals(obj)) {
					count++;
				}
			}
		}
		return (count == c.size());
	}

	public boolean addAll(Collection c) {
		return addAll(size,c);
	}

	public boolean addAll(int index, Collection c) {
		if((size*(1 + 3/2) + c.size()) >= Integer.MAX_VALUE) {
			throw new OutOfMemoryError("out of memory: " + size);
		}
		if((size - arrs.length) < c.size()) {
			Object[] temp = new Object[size*(1 + 3/2) + c.size()];
			for(int i = 0; i < arrs.length; i++) {
				temp[i] = arrs[i];
			}
			arrs = temp;
		}
		size += c.size();
		if(index < size) {
			Object[] oo = c.toArray();
			Object[] temp = new Object[arrs.length];
			Object[] temp1 = Arrays.copyOfRange(arrs, index, size-index);
			Object[] temp2 = Arrays.copyOfRange(arrs, 0, index);
			int count = 0;
			for(Object obj : temp2) {
				temp[count++] = obj;
			}
			for(Object obj : oo) {
				temp[count++] = obj;
			}
			for(Object obj : temp1) {
				temp[count++] = obj;
			}
			arrs = temp;
			return true;
			
		} else {
			Iterator it = c.iterator();
			while(it.hasNext()) {
				arrs[size++] = it.next();
			}
		}
		return true;
	}

	public boolean removeAll(Collection c) {
		if(c.size() < 1) {
			throw new IllegalArgumentException("the set is empty");
		}
		if(size < c.size()) {
			throw new IllegalArgumentException("the set is less than set size");
		}
		Iterator it = c.iterator();
		Object[] temp = new Object[size];
		while(it.hasNext()) {
			int count = 0;
			Object o = it.next();
			for(Object obj : arrs) {
				if(!o.equals(obj)) {
					temp[count++] = obj;
				}
			}
			arrs = temp;
			temp = new Object[arrs.length];
		}
		size = arrs.length;
		return true;
	}


	public void clear() {
		size = 0;
		arrs = new Object[16];
	}

	public Object get(int index) {
		if(index >= size) {
			throw new IllegalArgumentException("the parameter is greater than size:" + size);
		}
		if(arrs.length < 1) {
			throw new IndexOutOfBoundsException("index " + index + " is not here");
		}
		return arrs[index];
	}

	public Object set(int index, Object element) {
		if((size + 1) >= Integer.MAX_VALUE) {
			throw new OutOfMemoryError("out of memory: " + size);
		}
		if(index < 0) {
			throw new IllegalArgumentException("the index must positive:" + index);
		}
		if(arrs.length == 0) {
			arrs = new Object[10];
		}
		if(arrs.length == size) {
			Object[] temp = new Object[size*(1 + 3/2)];
			int count = 0;
			for(Object obj : arrs) {
				temp[count++] = obj;
			}
			arrs = temp;
		}
		int count = 0;
		for(int i = 0; i <= size; i++) {
			if(count == index) {
				size += 1;
				Object temp = arrs[count];
				while(count <= size) {
					Object o = arrs[++count];
					arrs[count] = temp;
					temp = o;
				}
				arrs[index] = element;
				break;
			}
			arrs[count++] = arrs[i];
		}
 		return element;
	}

	public void add(int index, Object element) {
		set(index, element);
	}

	public Object remove(int index) {
		if(size < 1) {
			return false;
		}
		Object[] temp = new Object[arrs.length];
		int count = 0;
		Object o = null;
		for(int i = 0; i < size; i++) {
			Object obj = arrs[i];
			if(i == index) {
				o = obj;
			} else {
				temp[count++] = obj;
			}
		}
		--size;
		arrs = temp;
		temp = null;
		return o;
	}

	public int indexOf(Object o) {
		int count = 0;
		for(Object obj : arrs) {
			if(o.equals(obj)) {
				return count;
			}
			count++;
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		for(int i = (size - 1); i >=0; i--) {
			if(arrs[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean retainAll(Collection c) {
		return false;
	}

}
