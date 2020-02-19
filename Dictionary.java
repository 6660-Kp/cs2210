
public class Dictionary implements DictionaryADT{

	Node[] myDictionary;
	int dicsize;
	final int fac = 33;
	int counter;
	
	public Dictionary(int size) {
		myDictionary = new Node[size];
		dicsize = size;
		counter = 0;
	}


/*Method insert must return the value 1 if 
	the insertion of pair produces a collision, and it will
	return the value 0 otherwise*/
	public int insert(Record pair) throws DictionaryException {
		int position = hashFunction(pair.getConfig(),dicsize, fac);
		boolean dupKey = checkKey(pair.getConfig(), myDictionary[position]);
		if (dupKey == true) {
			throw new DictionaryException();
		}
		else {
			Node newNode = new Node(pair);
		// used to count the total number of elements inside of the dictionary
			counter ++;
			Node currentPos = myDictionary[position];
			Node lastNode = null;
			// if there's no node at the position
			if (currentPos == null) {
				myDictionary[position] = newNode;
				return 0;
			}
			// keep visiting the next node until it reaches null
			while (currentPos != null) {
				lastNode = currentPos;
				currentPos = currentPos.getNext();
			}
			currentPos = newNode;
			currentPos.setPrevious(lastNode);
			lastNode.setNext(currentPos);
			
			return 1;
		}
	}

	@Override
	public void remove(String config) throws DictionaryException {
		int position = hashFunction(config, dicsize, fac);
		Node result = searchNode(config, myDictionary[position]);
		if(result == null) {
			throw new DictionaryException();
		}
		else {
// to find if the node going to be removed is the first one or the last one or the one in the middle
			if (result.getNext()==null && result.getPrevious()!=null) {
				result.getPrevious().setNext(null);
			}
			else if(result.getPrevious()==null && result.getNext()!= null) {
				myDictionary[position]=result.getNext();
				myDictionary[position].setPrevious(null);
			}
			else if(result.getPrevious()==null && result.getNext()==null) {
				myDictionary[position]=null;
			}
			else {
				result.getPrevious().setNext(result.getNext());
				result.getNext().setPrevious(result.getPrevious());
			}
			counter --;
		}
		
	}
/*A method which returns the score stored in the dictionary
for the given configuration, or -1 if the configuration is not in the dictionary*/
	@Override
	public int get(String config) {
		int position = hashFunction(config, dicsize, fac);
		Node result = searchNode(config, myDictionary[position]);
		if (result == null) {
			return -1;
		}
		else {
			return result.getScore();
		}
	}

	@Override
	public int numElements() {
		return counter;
	}
	
	private int hashFunction(String key, int size, int x) {
		int stringLength = key.length();
		int result = (int)key.charAt(stringLength-1);
		for (int i = stringLength-2;i>=0;i--) {
			result = (result*x+(int)key.charAt(i))%size;
		}
		return result;
	}
	/* to search the node of the key given
	target - the key that trying to search
	current - the current node searching
	return 
	null if it is not in the dictionary
	the node of the key
	 */
	private Node searchNode (String target, Node cur) {
		if (cur == null) {
			return null;
		}
		if (cur.getConfig().equals(target)) {
			return cur;
		}
		else if(cur.getNext()==null) {
			return null;
		}
		else {
			return searchNode(target, cur.getNext());
		}
	}
	private boolean checkKey (String targetKey, Node cur) {
		if(cur == null) {
			return false;
		}
		if (cur.getConfig().equals(targetKey)) {
			return true;
		}
		if (cur.getNext() == null) {
			return false;
		}
		return checkKey(targetKey, cur.getNext());

	}
	

	
}
