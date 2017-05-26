package stacktest;

import org.junit.Before;
import org.junit.Test;
import ku.util.Stack;
import ku.util.StackFactory;
import static org.junit.Assert.*;
import org.junit.Assume;

public class StackTest {
	private Stack<String> stack;

	@Before
	public void setUp() {
		stack = makeStack(5);
	}

	@Test(timeout = 100)
	public void newStackIsEmpty() {
		assertTrue(stack.isEmpty());
		assertFalse(stack.isFull());
		assertEquals(0, stack.size());
		
	}

	@Test(expected = java.util.EmptyStackException.class)
	public void testPopEmptyStack() {
		Assume.assumeTrue(stack.isEmpty());
		stack.pop();
		fail("Pop empty stack should throw exception");
	}

	@Test
	public void testPeekEmptyStack() {
		Assume.assumeTrue(stack.isEmpty());
		assertEquals(null, stack.peek());
	}

	// Stack Full
	@Test
	public void testIsFull() {
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
		assertFalse(stack.isEmpty());
		assertTrue(stack.isFull());
		assertEquals(5, stack.size());
	}

	@Test(expected = java.lang.IllegalStateException.class)
	public void testPushIsFull() {
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
		Assume.assumeTrue(stack.isFull());
		stack.push("F");
		fail("Push full stack should throw exception");
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testPushNullElement() {
		stack.push(null);
		fail("Push Null to stack should throw exception");
	}

	private Stack makeStack(int capacity) {
		return StackFactory.makeStack(capacity);
	}

}
