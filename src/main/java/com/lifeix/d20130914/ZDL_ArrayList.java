package com.lifeix.d20130914;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ZDL_ArrayList<E> implements List{
    private transient Object[] elementData;
	Integer it;
	Object object[] = new Object[100];
	Integer size = 0;
	@Override
	public boolean add(Object e) {
		System.out.println("在下标"+size+"添加"+e);
		object[size++]=e;
		return false;
	}
	@Override
	public void add(int index, Object element) {
		if(index<0){//对象所发到的下标<0，将对象放到首位
			System.out.println("添加的" + element + "指定的下标<0,放到首位");
			index=0;
		}
		if(index>size){//对象所发到的下标》size，将对象放到首位
			System.out.println("添加的下标"+index+">总长度，放到末尾");
			index=size;
		}
		for(int i = size;i>=index;i-- ){
			object[i+1]=object[i];
		}

		System.out.println("在下标"+index+"添加"+element);
		object[index]=element;
		size++;
	}
	@Override
	public boolean addAll(Collection c) {
		Iterator iterator = c.iterator();
		int i=0;
		while(iterator.hasNext()){//遍历集合，添加
			if(i==0){
				System.out.println("添加集合开始...");
			}
			i++;
			add(iterator.next());
		}
		if(i>0){
			System.out.println("添加集合结束.");
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean addAll(int index, Collection c) {
		int collectionSize = c.size();//集合长度
		if(collectionSize==0){//集合长度为0，直接返回
			return false;
		}
		if(index<0){//集合所发到的下标<0，将集合放到首位
			System.out.println("添加集合的下标<0,将集合放到首位");
			index=0;
		}
		if(index>size){//集合所发到的下标>size，将集合放到末位
			System.out.println("添加集合的下标"+index+">总长度，放到末尾");
			index=size;
		}
		System.out.println("准备放入："+collectionSize+"个元素");
		int i = size+collectionSize;
		for(;i>=index;i-- ){//元素后移位置
			object[i+1]=object[i];
		}//123   5 2 
		int n = 0;
		Iterator iterator = c.iterator();//遍历集合，添加
		while(iterator.hasNext()){
			if(n==0){
				System.out.println("添加集合开始...");
			}
			n++;
			add(i+n,iterator.next());
		}
		if(n>0){
			System.out.println("添加集合结束.");
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void clear() {
		
	}

	@Override
	public boolean contains(Object o) {
		
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(int index) {
		return object[index];
	}

	@Override
	public int indexOf(Object o) {
		String s = (String)o;
		for(int i = 0;i<size;i++){
			if(s.equals(object[i])){
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		System.out.println("移除对象"+o);
		int i = 0;
		for(;i<size;i++ ){
			if(object[i]==o){
				object[i] = object[i+1];
				size--;
			}
		}
		return false;
	}

	@Override
	public Object remove(int index) {
		System.out.println("移除下标为"+index);
		int i = index;
		for(;i<size;i++ ){
				object[i] = object[i+1];
		}
		size--;
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object set(int index, Object element) {
		if(index>=0&&index<size)
		return object[index]=element;
		return "";
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		int i=0;
		for(;i<size;i++){
			String string  = (String)object[i];
			sb.append(string);
			if(i<size-1)
				sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}
	public static void main(String[] args) {
		ZDL_ArrayList arrayList = new ZDL_ArrayList();
		arrayList.add(new String("adf"));
		arrayList.add(new String("agagf"));
		arrayList.add(new String("agawf"));
		System.out.println("aaaaaaaaa"+arrayList.size());
		ArrayList list = new ArrayList();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		arrayList.addAll(list);
		System.out.println(arrayList.toString());
		arrayList.add(-1,"hwaefas");
		System.out.println(arrayList.toString());
		System.out.println("\"agawf\"的下标为"+arrayList.indexOf("agawf"));
		list.add("aaa1");
		list.add("bbb2");
		list.add("ccc3");
		arrayList.addAll(2,list);
		System.out.println(arrayList.toString());
		arrayList.remove("aaa1");
		arrayList.remove("bbb");
		System.out.println(arrayList.toString());
		arrayList.remove(2);
		System.out.println(arrayList.toString());
		
	}
}
