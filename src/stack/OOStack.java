package stack;


import java.util.ArrayList;
import java.util.List;

public class OOStack {

	SuperCosa cosa;
	static public String stackEmptyErrorDescription = "Stack is empty";
	public List stack = new ArrayList();

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public OOStack push(String string) {
		stack.add( string );
		return this;
	}

	public Object pop() {
		return cosa.pop(this);
		//		if (isEmpty()) {
		//			throw new Error( OOStack.stackEmptyErrorDescription );
		//		} else {
		//			return stack.remove( size() - 1 );
		//		}
	}

	public Object top() {
		if (isEmpty()) {
			throw new Error( OOStack.stackEmptyErrorDescription );
		} else {
			return stack.get( size() - 1 );
		}
	}

	public int size() {
		return stack.size();
	}

}
