import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;
import be.ac.ua.ansymo.adbc.annotations.requires;

@invariant ({	"$this.nbElements <= $this.heap.length",
				"$this.nbElements >= 0"
			})
public class PriorityQueue<K extends Comparable<K>, V> {
	public Node<K, V>[] heap;
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

	@requires 	({	"true"
				})
	@ensures	({	"$this.heap != null",
				})
	public PriorityQueue(Class<K> keyParameterClass, Class<V> valueParameterClass){
		this.keyParameterClass = keyParameterClass;
		this.valueParameterClass = valueParameterClass;
		this.nbElements = 0;
		this.heap = new Node[15];
	}

	@requires 	({	"key != null",
					"value != null",
					"$this.keyParameterClass.isInstance(key)",
					"$this.valueParameterClass.isInstance(value)"
				})
	@ensures	({	"$this.nbElements == $old($this.nbElements) + 1",
					"$this.contains(value) == $old($this.contains(value)) + 1"
				})
	public void insert(K key, V value) {
		Node insertNode = new Node(key, value);
		if(nbElements >= heap.length) {
			copyAndDouble();
		}
		heap[nbElements] = insertNode;
		if(nbElements > 0) trickleUp(nbElements);
		nbElements++;
	}
	
	@requires 	({	"$this.nbElements != 0"
				})
	@ensures	({	"$this.nbElements == $old($this.nbElements) - 1",
					"$this.contains(value) == $old($this.contains(value)) - 1",
					"$result != null"
				})
	public V remove() {
		if(nbElements > 0)
		{
			V return_value = (V) this.min();
			this.heap[0] = this.heap[nbElements-1];
			this.nbElements--;
			if (this.nbElements > 0)this.settleHeap(0);

			if (this.nbElements <= heap.length/4)
			{
				Node[] newHeap = new Node[heap.length/2];
				for(int i = 0; i < nbElements; i++)
				{
					newHeap[i] = this.heap[i];
				}
				this.heap = newHeap;
			}
			return return_value;
		}
		return null;
	}

	@requires 	({	"$this.nbElements != 0"
				})
	@ensures	({	"$this.nbElements == $old($this.nbElements)",
					"$result != null"
				})
	public V min() {
		return heap[0].getValue();
	}

	@requires 	({	"true"
				})
	@ensures	({	"$this.nbElements == $old($this.nbElements)",
					"$result >= 0"
				})
	private int contains(V value)
	{
		int counter = 0;
		for (Node node:this.heap) {
			if (node.getValue().equals(value))
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
		int leftCompare = heap[elem].getKey().compareTo(heap[leftChild(elem)].getKey());
		int rightCompare = heap[elem].getKey().compareTo(heap[rightChild(elem)].getKey());
		while(hasChild(elem) && (leftCompare < 0 || rightCompare < 0)){
			if(leftCompare <= rightCompare) {
				swap(elem, leftChild(elem));
				elem = leftChild(elem);
			}else if(rightCompare < leftCompare) {
				swap(elem, rightChild(elem));
				elem = rightChild(elem);
			}else {
				System.out.println("that didn't make sense.");
			}
			leftCompare = heap[elem].getKey().compareTo(heap[leftChild(elem)].getKey());
			rightCompare = heap[elem].getKey().compareTo(heap[rightChild(elem)].getKey());
		}
	}
	
	private void copyAndDouble() {
		Node[] doubleHeap = new Node[heap.length * 2];
		for(int i=0; i<heap.length; i++) {
			doubleHeap[i] = heap[i];
		}
		heap = doubleHeap;
	}
}
