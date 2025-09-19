package hash;
// Name: Luca Sambat
// Computing ID: aqc8qt
// Homework Name: HW 11 Hash Table
// Resources used (if applicable): Hunter Platt

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Hash Table implementation.
 *
 * @param <K>
 * @param <V>
 */
public class HashTable<K,V> implements SimpleMap<K,V>{

    private static final int INITIAL_CAP = 5;  // a default initial capacity (set low for initial debugging)
    private int currentCapacity = INITIAL_CAP;
    private ArrayList<LinkedList<HashNode<K,V>>> table;


    /*
     * Here are some hints about how to declare your hash table.
     * If you're using an ArrayList, it might look like this:
     * 		private ArrayList<HashNode<K, V>> table;
     * Note that you cannot declare an array of generics (i.e., an array of HashNodes) like this:
     *          private LinkedList<HashNode<K,V>>[] table;
     * but see here https://programming.guide/java/generic-array-creation.html for workarounds.
     */

    /* YOU WILL LIKELY WANT MORE PRIVATE VARIABLES HERE */
    private int size;

    public HashTable() {  // default constructor sets capacity to default value
        this(INITIAL_CAP);
    }

    public HashTable(int capacity) {  // constructor sets capacity to given value
        /* TODO: IMPLEMENT THIS METHOD */
        this.currentCapacity = capacity;
        this.table=new ArrayList<>();
        for (int i=0;i<capacity;i++){ //fill the array list with linked lists
            this.table.add(new LinkedList<>());
        }
        this.size=0;
        /*
         * Here are some hints about how to allocate memory for your hash table.
         * If you're using an array, it might look like this:
         * 		this.table = (HashNode<K,V>[]) new HashNode<?,?>[initialCapacity];
         * If you're using an ArrayList, it might look like this:
         * 		this.table = new ArrayList<>(capacity); // sets list's initial capacity
         */
    }
    public int getSize() { return size; }
    // insert() adds a new key/value pair if the key is not found, otherwise it replaces
    //    the existing key's value
    @Override
    public void insert(K key, V value) {
        /* TODO: IMPLEMENT THIS METHOD */
        if (((double)this.size/this.currentCapacity)>=0.75){ //determine if the load factor greater than 0.75
            resize();
        }
        HashNode<K,V> inserted=new HashNode<>(key,value);
        int hash=Math.abs(key.hashCode())%currentCapacity; //create hash value
        if (table.get(hash).contains(inserted)){ //if key already exists, remove the node
            this.table.get(hash).remove(inserted);
            size--;
        }
        this.table.get(hash).add(inserted); //reinsert the node (or insert new node)
        this.size++;
    }

    private void resize() {
        ArrayList<HashNode<K,V>> temp=new ArrayList<>();
        for (int i=0;i<this.currentCapacity;i++){ //create temp array list containing all previous hash nodes
			temp.addAll(this.table.get(i));
        }
        this.table.clear(); //completely reset the table and size
        this.size=0;
        currentCapacity*=2; //double capacity
        for (int j=0;j<this.currentCapacity;j++){ //readd empty linked lists
            this.table.add(new LinkedList<>());
        }
		for (HashNode<K, V> kvHashNode : temp) { //reinsert all hashnodes that are in temp
			insert(kvHashNode.getKey(), kvHashNode.getValue());
		}

    }

    @Override
    public V retrieve(K key) {
        /* TODO: IMPLEMENT THIS METHOD */
        HashNode<K,V> temp=new HashNode<>(key,null);
        int hash=Math.abs(key.hashCode())%currentCapacity;
        if (table.get(hash).contains(temp)){ //if key exists, return value
            return table.get(hash).get(table.get(hash).indexOf(temp)).getValue();
        } else { //otherwise return null
            return null;
        }
    }

    @Override
    public boolean contains(K key) {
        /* TODO: IMPLEMENT THIS METHOD */
        HashNode<K,V> temp=new HashNode<>(key,null);
        int hash=Math.abs(key.hashCode())%currentCapacity;
		return table.get(hash).contains(temp); //gets the hash value, determines if key exists already
    }

    @Override
    public void remove(K key) {
        /* TODO: IMPLEMENT THIS METHOD */
        HashNode<K,V> temp=new HashNode<>(key,null);
        int hash=Math.abs(key.hashCode())%currentCapacity;
        if (this.table.get(hash).remove(temp)){ //remove the node. if remove comes as true, decrease size
            size--;
        }
    }


    /*
     * OPTIONAL HELPER METHODS: The next two methods will let you print out your
     * entire hash table, or let you make sure all keys that hash to a single
     * bucket's index get stored as they should in your table. You'll need to
     * implement the second method; it depends on how you store entries and
     * handle collisions. This is NOT required, but you may find it helpful when
     * debugging and testing your code.
     */

    public void printHashTable() {
        for (int idx=0; idx < this.currentCapacity; ++idx) {
            System.out.print(idx + ": ");
            printEntriesByIndex(idx);
        }
    }

    private void printEntriesByIndex(int idx) {
        /*
         * To implement this method to help print out one bucket of your hash table, you need to determine:
         * a) If there are no key/value pairs in the bucket idx, print "no entries"
         * b) If there are key/value pairs at that bucket, use a loop to print each one.
         *    Best to use System.out.print() and not println() so they're all on one line.
         * c) At the end of that loop, do System.out.println() to print a new line.
         */
        System.out.println("Not yet implemented...");
    }

}

