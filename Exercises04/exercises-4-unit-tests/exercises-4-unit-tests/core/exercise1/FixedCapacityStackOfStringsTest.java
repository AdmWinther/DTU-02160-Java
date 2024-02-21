package core.exercise1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class FixedCapacityStackOfStringsTest {

    FixedCapacityStackOfStrings stack;

    @BeforeEach
    void createStack(){
        this.stack = new FixedCapacityStackOfStrings(5);
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        assertTrue(stack.isEmpty());
        this.stack.push("Item1");
        assertFalse(stack.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void isFull() {
        assertFalse(stack.isFull());
        this.stack.push("Item1");
        this.stack.push("Item2");
        this.stack.push("Item3");
        this.stack.push("Item4");
        this.stack.push("Item5");
        assertTrue(stack.isFull());
    }

    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(0,this.stack.size());
        this.stack.push("testItem");
        assertEquals(1,this.stack.size());
    }

    @org.junit.jupiter.api.Test
    void push() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                this.stack.push("item1");
                this.stack.push("item1");
                this.stack.push("item1");
                this.stack.push("item1");
                this.stack.push("item1");
                this.stack.push("item1");
        });
    }

    @org.junit.jupiter.api.Test
    void pop() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            this.stack.pop();
        });
    }

    @org.junit.jupiter.api.Test
    void peek() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            this.stack.peek();
        });
        this.stack.push("ThisIsMe");
        this.stack.push("ThisIsYou");
        assertEquals("ThisIsYou",this.stack.peek());
        this.stack.pop();
        assertEquals("ThisIsMe",this.stack.peek());
    }
}