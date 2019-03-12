import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;
import be.ac.ua.ansymo.adbc.annotations.requires;
import java.lang.reflect.Array;

@invariant ({	"$this.nbElements <= $this.heap.length",
				"$this.nbElements >= 0"
			})
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
	
	public PriorityQueue()
	{
		this.nbElements = 0;
		this.heap = new Node[0];
	}


	@requires 	({	"key != null",
					"key.getClass() == K",
					"value.getClass() == V"
				})
	@ensures	({	"$this.nbElements == $old($this.nbElements) + 1",
					"this.contains(new Node(key, value))"
				})
	public void insert(K key, V value) {
		Node insertNode = new Node(key, value);
		if(nbElements >= heap.length) {
			copyAndDoubleHeap();
		}
		heap[nbElements] = insertNode;
		trickleUp(nbElements);
		nbElements++;
	}
	
	@requires 	({	"$this.nbElements != 0",
				})
	@ensures	({	"$this.nbElements == $old($this.nbElements) - 1",
					"!$this.contains($old($this.min()))",
					"$result != null"
				})
	public V remove() {
		return null;
	}

	@requires 	({	"$this.nbElements != 0",
				})
	@ensures	({	"$this.nbElements == $old($this.nbElements",
					"$result != null"
				})
	public Node min() {
		return heap[0];
	}

	public boolean contains(V value)
	{
		for (Node node:this.heap)
			if (node.getValue().equals(value))
				return true;
		return false;
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
		while((heap[elem].getKey().compareTo(heap[parent(elem)].getKey()) < 0 && !isRoot(elem))) {
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
	
	private void copyAndDoubleHeap() {
		Node[] newHeap = new Node[heap.length * 2];
		for(int i=0; i<heap.length; i++) {
			newHeap[0] = heap[0];
		}
		heap = newHeap;
	}
}
