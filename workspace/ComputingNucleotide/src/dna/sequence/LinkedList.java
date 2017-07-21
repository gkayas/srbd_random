package dna.sequence;
import java.util.ArrayList;
import java.util.Iterator;

class Node<T> {
	public T data;
	public Node<T> next;
	
	public Node (T val) {
		data = val;
		next = null;
	}
}

public class LinkedList<T> {
	private Node<T> head;
	private Node<T> current;
	private int size;
	
	public LinkedList () {
		head = current = null;
	}
	
	public boolean empty () {
		return head == null;
	}
	
	public boolean contains (T obj) {
		Node cursor = head;
		
		while(cursor != null) {
			if(cursor.equals(obj)) {
				return true;
			}
			cursor = cursor.next;
		}
		return false;
	}
	
	public boolean last () {
		return current.next == null;
	}
	
	public boolean full () {
		return false;
	}
	
	public void findFirst () {
		current = head;
	}
	public void findNext () {
		current = current.next;
	}
	
	public T retrieve () {
		return current.data;
	}
	
	public void update (T val) {
		current.data = val;
	}
	
	public void insert (T val) {
		Node<T> tmp;
		if (empty()) {
			current = head = new Node<T> (val);
		}
		else {
			tmp = current.next;
			current.next = new Node<T> (val);
			current = current.next;
			current.next = tmp;
		}
		size++;
	}
	
	public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> tmp = head;
			while (tmp.next != current)
				tmp = tmp.next;
			tmp.next = current.next;
		}
		if (current.next == null)
			current = head;
		else
			current = current.next;
		size--;
	}
	
	public int size () {
		return size;
	}
	
	public Iterator<T> iterator() {
		ArrayList<T> ar = new ArrayList<T>();
		Node cursor = head;
		
		while(cursor != null) {
			ar.add((T) cursor.data);
			cursor = cursor.next;
		}
		return ar.iterator();
	}
	
	@Override
	public String toString() {
		StringBuffer sbr = new StringBuffer();
		sbr.append("List Items :\n");
		
		ArrayList<T> ar = new ArrayList<T>();
		Node cursor = head;
		
		while(cursor != null) {
			sbr.append(cursor.data.toString());
			cursor = cursor.next;
		}
		return sbr.toString();
	}
}



class HashMapCustom<K, V> {
    
    private Entry<K,V>[] table;   //Array of Entry.
    private int capacity= 4;  //Initial capacity of HashMap
    
    static class Entry<K, V> {
        K key;
        V value;
        Entry<K,V> next;
    
        public Entry(K key, V value, Entry<K,V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    

   @SuppressWarnings("unchecked")
   public HashMapCustom(){
      table = new Entry[capacity];
   }

   public void put(K newKey, V data){
      if(newKey==null)
          return;    //does not allow to store null.
     
      //calculate hash of key.
      int hash=hash(newKey);
      //create new entry.
      Entry<K,V> newEntry = new Entry<K,V>(newKey, data, null);
     
      //if table location does not contain any entry, store entry there.
       if(table[hash] == null){
        table[hash] = newEntry;
       }else{
          Entry<K,V> previous = null;
          Entry<K,V> current = table[hash];
          
          while(current != null){ //we have reached last entry of bucket.
          if(current.key.equals(newKey)){           
              if(previous==null){  //node has to be insert on first of bucket.
                    newEntry.next=current.next;
                    table[hash]=newEntry;
                    return;
              }
              else{
                  newEntry.next=current.next;
                  previous.next=newEntry;
                  return;
              }
          }
          previous=current;
            current = current.next;
        }
        previous.next = newEntry;
       }
   }

   
   public V get(K key){
       int hash = hash(key);
       if(table[hash] == null){
        return null;
       }else{
        Entry<K,V> temp = table[hash];
        while(temp!= null){
            if(temp.key.equals(key))
                return temp.value;
            temp = temp.next; //return value corresponding to key.
        }         
        return null;   //returns null if key is not found.
       }
   }

   private int hash(K key){
       return Math.abs(key.hashCode()) % capacity;
   }

}
