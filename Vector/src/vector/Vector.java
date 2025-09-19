// Name: Luca Sambat
// Computing ID: aqc8qt
// Homework Name: HW 6 Vectors
// Resources used (if applicable): GPT
package vector;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class Vector<T> implements SimpleList<T> {

	private T[] itemArray;
	private int size = 0;
	private static final int INITIAL_CAPACITY = 100;

	public Vector() {
		this(INITIAL_CAPACITY);  // calls the other constructor that takes an int parameter
	}

	public Vector(int capacity) {
		this.itemArray = (T[]) new Object[capacity];
		this.size = 0;
	}

	public int capacity() {
		return this.itemArray.length;
	}

	/**
	 * When the underlying array fills up, we need to somehow resize it to accommodate the
	 * Vectors's elements.
	 * Ignores the request if the requested new capacity is too small to fit the elements
	 * already in the Vector
	 */
	public void resize(int newCapacity) {
		T[] temp=(T[])new Object[this.itemArray.length]; //create an empty temporary T[]
		for (int i=0;i<temp.length;i++){ //copys all information from this to the temp
			temp[i]=this.itemArray[i];
		}
		this.itemArray=(T[]) new Object[newCapacity]; //resizes the vector
		for (int i=0;i<newCapacity;i++){ //fills in the information from the temp. if it doesn't exist, use null.
			if (i>=temp.length){
				this.itemArray[i]=null;
			} else {
				this.itemArray[i]=temp[i];
			}
		}
		if (newCapacity<this.size){
			int tempsize=this.size;
			for (int i=0;i<tempsize-newCapacity;i++){
				this.size--;
			}
		}
	}
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void clear() {
		for (int i=0;i<this.itemArray.length;i++){ //clears items in list before it sets the size to zero
			this.itemArray[i]=null;
		}
		this.size=0;
	}

	@Override
	public void insertAtTail(T item) {
		this.size++;
		if (this.size>this.capacity()){
			this.resize(this.capacity()*2);
		}
		this.itemArray[this.size-1]=item;
	}

	@Override
	public void insertAtHead(T item) {
		this.size++;
		if (this.size>this.capacity()){
			this.resize(this.capacity()*2);
		}
		for (int i=this.size-1;i>0;i--){ //moves all items up 1 spot in the vector and then replaces the zero index with the item
			T temp=this.itemArray[i-1];
			this.itemArray[i-1]=this.itemArray[i];
			this.itemArray[i]=temp;
		}
		this.itemArray[0]=item;
	}
	@Override
	public void insertAt(int index, T item) {
		this.size++;
		if (this.size>this.capacity()){
			this.resize(this.capacity()*2);
		}
		if (index>=this.size){
			int difference=index-this.size;
			for (int i=0;i<difference;i++){
				insertAtTail(null);
			}
			insertAtTail(item);
		} else {
			for (int i=this.size;i>index;i--){ //goes backwards to move items after the index up 1 space and then inserts the item at the given index
				T temp=this.itemArray[i-1];
				this.itemArray[i-1]=this.itemArray[i];
				this.itemArray[i]=temp;
			}
			this.itemArray[index]=item;
		}
	}

	@Override
	public T removeAtTail() {
		this.size--;
		return null;
	}

	@Override
	public T removeAtHead() {
		this.itemArray[0]=null; //clearing the information of the zero index
		for (int i=0;i<this.size-1;i++){ //moves everything down 1 space
			T temp=this.itemArray[i+1];
			this.itemArray[i+1]=this.itemArray[i];
			this.itemArray[i]=temp;
		}
		this.size--;
		return null;
	}

	@Override
	public int find(T item) {
		int index=-1;
		for (int i=0;i<this.size;i++){ //loops through all items and finds index
			if (this.itemArray[i].equals(item)){
				index=i;
				break;
			}
		}
		return index;
	}

	@Override
	public T get(int index) {
		return this.itemArray[index];
	}

	/*
	 * This toString() method allow you to print a nicely formatted version of your Vector. E.g.
	 *     System.out.println( myVector );
	 * It uses utility methods in the Java Arrays library.
	 */
	@Override
	public String toString() {
//		return Arrays.toString(this.itemArray); // prints entire array from 0 to capacity-1
		return Arrays.toString(Arrays.copyOfRange(this.itemArray, 0, this.size)); // prints from 0 to size-1
	}

}