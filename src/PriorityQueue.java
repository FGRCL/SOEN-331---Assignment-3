
public class PriorityQueue<K extends Comparable<K>, V> {
	private Node<K, V>[] heap;
	private int nbElements;
	
	private class Node<K extends Comparable<K>, V>{
		private K key;
		private V value;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
	}

	
	private void insert(K key, V value) {
		Node insertNode = new Node(key, value);
	}
	
	private V remove() {
		return null;
	}
	
	private V min() {
		return null;
	}
	
	private int parent(int i) { 
		return ((i-1)/2);
	}
	
	private int leftChild(int i) {
		return ((2*i)+1);
	}
	
	private int rightChild(int i) {
		return ((2*i)+2);
	}
	
	private void swap(int a, int b) {
		Node temp = heap[a];
		heap[a] = heap[b];
		heap[b] = temp;
	}
}
