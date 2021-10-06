package smt.devlabs.api.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IdCounterTest {
    private IdCounter subject;

    @BeforeEach
    public void setupBeforeEach() {
        this.subject = new IdCounter();
    }

    @Test
    @DisplayName("Should incremenet counter by one after every call")
    public void whenGetNewIdCalled_thenReturnIdAndIncementBy1() {
        assertEquals(1, subject.getNewId());
        assertEquals(2, subject.getNewId());
    }
}
