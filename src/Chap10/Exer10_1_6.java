package Chap10;

import java.util.ArrayDeque;
import java.util.Deque;

// queue with two stack
public class Exer10_1_6 {
	Deque<Integer> inboxStack = new ArrayDeque<Integer>();
	Deque<Integer> outboxStack = new ArrayDeque<Integer>();
	
	public void enqueue(Integer value) {
		inboxStack.add(value);
	}
	
	public Integer dequeue() {
		if (outboxStack.size() == 0) {
			while (inboxStack.size() > 0) {
				outboxStack.add(inboxStack.pollLast());
			}
		}
		return outboxStack.pollLast();
	}
	
	public String toString() {
		if (inboxStack.size() == 0 && outboxStack.size() == 0)
			return null;
		
		String result = "";
		Object[] tmp_out = outboxStack.toArray();
		if (tmp_out.length != 0)
			for (int i = 0; i < tmp_out.length; i ++) {
				result = result + tmp_out[tmp_out.length - 1 - i] + ", ";
			}
		
		Object[] tmp_in = inboxStack.toArray();
		if (tmp_in.length != 0)
			for (int i = 0; i < tmp_in.length; i ++) {
				result = result + tmp_in[i] + ", ";
			}
		
		return result;
	}
	
	public static void main(String[] args) {
		Exer10_1_6 test = new Exer10_1_6();
		test.enqueue(0);
		test.enqueue(1);
		test.dequeue();
		test.enqueue(2);
		test.enqueue(3);
		test.dequeue();
		System.out.println(test.toString());
	}
}
