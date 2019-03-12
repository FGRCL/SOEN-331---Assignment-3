import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.requires;

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

	@requires 	({	"key != null",
					"key.getClass() == K",
					"value.getClass() == V"
				})
	@ensures	({	"$this.nbElements == $old($this.nbElements) + 1",
					"this.heap.contains(new Node(key, value))"
				})
	public void insert(K key, V value) {
		Node insertNode = new Node(key, value);
	}

	@requires 	({	"$this.nbElements != 0",
				})
	@ensures	({	"$this.nbElements == $old($this.nbElements) - 1",
				})
	public V remove() {
		return null;
	}
	
	public Node min() {
		return heap[0];
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
