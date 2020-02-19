
public class Node {
	Node next = null;
	Node previous = null;
	String config;
	int score;
	Record record;

	public Node(Record record) {
		this.config = record.getConfig();
		this.score = record.getScore();
		this.record = record;
	}
	
	 // return the key saved in this node
	 
	public String getConfig() {
		return config;
	}
	
	// return the data saved in this node
	 
	public int getScore() {
		return score;
	}

	 //return the next node
	 
	public Node getNext() {
		return next;
	}
	
	 //return the previous node
	 
	public Node getPrevious() {
		return previous;
	}
	
	 // the node to be set as the next node of the current one
	 
	public void setNext(Node nextNode) {
		next = nextNode;
	}
		
	// the node to be set as the previous node of the current one
	 
	public void setPrevious(Node previousNode) {
		previous = previousNode;
	}
	
	public Record getRecord() {
		return record;
	}

}
