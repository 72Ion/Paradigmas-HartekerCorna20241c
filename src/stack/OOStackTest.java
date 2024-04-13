package stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class OOStackTest {

  @Test public void test01StackShouldBeEmptyWhenCreated() {
    assertTrue( new OOStack().isEmpty() );
  }

  @Test public void test02PushAddElementsToTheStack() {
    assertFalse( new OOStack().push( "Something" ).isEmpty() );
  }

  @Test public void test03PopRemovesElementsFromTheStack() {
    OOStack stack = new OOStack();
    stack.push( "Something" );
    stack.pop();
    assertTrue( stack.isEmpty() );
  }

  @Test public void test04PopReturnsLastPushedObject() {
    OOStack stack = new OOStack();
    String pushedObject = "Something";
    stack.push( pushedObject );
    assertEquals( stack.pop(), pushedObject );
  }

  @Test public void test05StackBehavesLIFO() {
    OOStack stack = new OOStack();
    String firstPushedObject = "First";
    String secondPushedObject = "Second";

    stack.push( firstPushedObject );
    stack.push( secondPushedObject );

    assertEquals( stack.pop(), secondPushedObject );
    assertEquals( stack.pop(), firstPushedObject );
    assertTrue( stack.isEmpty() );
  }

  @Test public void test06TopReturnsLastPushedObject() {
    String pushedObject = "Something";
    assertEquals( new OOStack().push( pushedObject ).top(), 
                  pushedObject );
  }

  @Test public void test07TopDoesNotRemoveObjectFromStack() {
    OOStack stack = new OOStack();
    stack.push( "Something" );
    assertEquals( stack.size(), 1 );
    stack.top();
    assertEquals( stack.size(), 1 );
  }

  @Test public void test08CanNotPopWhenThereAreNoObjectsInTheStack() {
    OOStack stack = new OOStack();
    try {
      stack.pop();
      fail( "Expected MessageNotUnderstood Error was not thrown." );
    } catch (Error e) {
      assertTrue( e.getMessage().equals( OOStack.stackEmptyErrorDescription ) );
    }
  }

  @Test public void test09CanNotPopWhenThereAreNoObjectsInTheStackAndTheStackHadObjects() {
    OOStack stack = new OOStack();
    stack.push( "Something" );
    stack.pop();
    try {
      stack.pop();
      fail( "Expected MessageNotUnderstood Error was not thrown." );
    } catch (Error e) {
      assertTrue( e.getMessage().equals( OOStack.stackEmptyErrorDescription ) );
    }
  }

  @Test public void test10CanNotTopWhenThereAreNoObjectsInTheStack() {
    OOStack stack = new OOStack();
    try {
      stack.top();
      fail( "Expected MessageNotUnderstood Error was not thrown." );
    } catch (Error e) {
      assertTrue( e.getMessage().equals( OOStack.stackEmptyErrorDescription ) );
    }
  }
}