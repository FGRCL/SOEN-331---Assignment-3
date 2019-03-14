import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;
import be.ac.ua.ansymo.adbc.annotations.requires;

@invariant ({
		"$this.capacity > 0",
		"$this.nbElements <= $this.capacity",
		"$this.nbElements >= 0"
})
public class PriorityQueue<K extends Comparable<K>, V> {

	public Node<K, V>[] heap;
	public int capacity;
	public int nbElements;
	public Class<K> keyParameterClass;
	public Class<V> valueParameterClass;

	public class Node<K extends Comparable<K>, V>{
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

	@requires ({
			"capacity > 0"
	})
	@ensures ({
			"$this.heap != null"
	})
	public PriorityQueue(Class<K> keyParameterClass, Class<V> valueParameterClass, int capacity){
		this.keyParameterClass = keyParameterClass;
		this.valueParameterClass = valueParameterClass;
		this.nbElements = 0;
		this.heap = new Node[capacity];
		this.capacity = capacity;
	}

	@requires ({
			"key != null",
			"value != null",
			"$this.keyParameterClass.isInstance(key)",
			"$this.valueParameterClass.isInstance(value)",
			"$this.isFull() == false"
	})
	@ensures ({
			"$this.nbElements == $old($this.nbElements) + 1",
			"$this.contains(value) == $old($this.contains(value)) + 1"
	})
	public void insert(K key, V value) {
		Node insertNode = new Node(key, value);
		heap[nbElements] = insertNode;
		if(nbElements > 0) trickleUp(nbElements);
		nbElements++;
	}
	
	@requires ({
			"$this.isEmpty() == false"
	})
	@ensures ({
			"$this.nbElements == $old($this.nbElements) - 1",
			"$this.contains($result) == $old($this.contains($this.min())) - 1",
			"$result != null",
			"$result == $old($this.min())"
	})
	public V remove() {
		// Save frist value in heap to return
		V return_value = this.heap[0].getValue();
		// Take the last element in the heap and place it at the beginning
		this.heap[0] = this.heap[nbElements-1];
		// Decrement the number of elements
		this.nbElements--;
		// If the heap still has anything in it, settle heap
		if (this.nbElements > 0) { this.settleHeap(0); }
		return return_value;
	}

	@requires ({
			"$this.isEmpty() == false"
	})
	@ensures ({
			"$this.nbElements == $old($this.nbElements)",
			"$result != null"
	})
	public V min() {
		return heap[0].getValue();
	}

	@requires({
			"true"
	})
	@ensures({
			"$result >= 0"
	})
	public int getSize() {
		return this.nbElements;
	}

	public int contains(V value)
	{
		int counter = 0;
		for (int i=0; i<nbElements; i++) {
			if (heap[i].getValue().equals(value))
				counter++;
		}
		return counter;
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
	
	private boolean isRoot(int elem) {
		return elem == 0;
	}
	
	private boolean hasChild(int elem) {
		return (leftChild(elem)<=(nbElements-1)  || rightChild(elem)<=(nbElements-1));
	}

	private void swap(int a, int b) {
		Node temp = heap[a];
		heap[a] = heap[b];
		heap[b] = temp;
	}

	private void trickleUp(int elem) {
		while(!isRoot(elem) && heap[elem].getKey().compareTo(heap[parent(elem)].getKey()) < 0) {
			swap(elem, parent(elem));
			elem = parent(elem);
		}
	}

	private void settleHeap(int elem) {
		boolean leftLessThanElem;
		boolean righLessThanElem;
		boolean leftLessThanRight;

		while (hasChild(elem)) {
			leftLessThanElem = heap[leftChild(elem)].getKey().compareTo(heap[elem].getKey()) < 0;
			righLessThanElem = heap[rightChild(elem)].getKey().compareTo(heap[elem].getKey()) < 0;
			leftLessThanRight = heap[leftChild(elem)].getKey().compareTo(heap[rightChild(elem)].getKey()) < 0;

			if (leftLessThanElem && leftLessThanRight) {
				swap(elem, leftChild(elem));
				elem = leftChild(elem);
			} else if (righLessThanElem) {
				swap(elem, rightChild(elem));
				elem = rightChild(elem);
			} else {
				break;
			}

		}
	}
	
	public boolean isFull() {
		return this.nbElements == this.capacity;
	}

	public boolean isEmpty() {
		return this.nbElements == 0;
	}


}
