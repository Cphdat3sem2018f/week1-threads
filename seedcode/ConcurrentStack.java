import java.util.ArrayList;

public class ConcurrentStack<T> {
	private final ArrayList<T> stack;
	private int size;

	public ConcurrentStack(){
		size=0;
		stack = new ArrayList<T>(size);
	}

	public void add(T e) {
		synchronized(stack){
			stack.add(e);
			size++;
		}
	}

	public T remove() {
		synchronized(stack.get(size-1)) {
			if (size > 0) {
				return stack.remove(--size);
			} else {
				return null;
			}
		}
	}

	public T peek() {
		synchronized(this){
			if(size>0) {
				int lastIndex=size-1;
				return stack.get(lastIndex);
			} else {
				return null;
			}
		}
	}

	//Example usage of ConcurrentStack
	public static void main(String[] args) {
		ConcurrentStack<Integer> stack = new ConcurrentStack<>();
		for(int i=0;i<10;i++) {
			stack.add(i);
		}
		for(int i=0;i<10;i++) {
			if (stack.peek() != null) {
				System.out.println(stack.remove());
			}
		}
	}
}
