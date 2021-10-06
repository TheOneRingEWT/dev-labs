package smt.devlabs.api.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import smt.devlabs.openapi.model.TodoItem;

@ExtendWith(MockitoExtension.class)
public class ItemsDbTest {

    private ItemsDb subject;

    @Mock
    private IdCounter mockIdCounter;

    @BeforeEach
    public void setupBeforeEach() {
        this.subject = new ItemsDb(mockIdCounter);
    }

    @Test
    @DisplayName("Added to-do items should and persisted and returned")
    public void whenCreateTodoItemCalled_thenPersistAndReturn() {
        // Arrange
        int newId = 1224;
        when(mockIdCounter.getNewId()).thenReturn(newId);

        String postedDescription = "i was just posted";
        TodoItem addedItem = new TodoItem().description(postedDescription);

        TodoItem expected = new TodoItem().id(newId).description(postedDescription);

        // Act
        TodoItem actual = subject.add(addedItem);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Added to-do items should generate a new Id")
    public void whenCreateTodoItemCalled_thenGetNewId() {
        // Arrange
        TodoItem addedItem = new TodoItem().description("i was just posted");

        // Act
        subject.add(addedItem);

        // Assert
        verify(mockIdCounter).getNewId();
    }

    @Test
    @DisplayName("Should be able to find all items")
    public void whenFindAllCalled_thenReturnItems() {
        // Arrange
        String postedDescription = "i was just posted";
        TodoItem addedItem = new TodoItem().description(postedDescription);

        int newId = 1224;
        when(mockIdCounter.getNewId()).thenReturn(newId);

        List<TodoItem> expected = new ArrayList<>();
        expected.add(new TodoItem().id(newId).description(postedDescription));

        // Act
        subject.add(addedItem);
        List<TodoItem> actual = subject.findAll();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should be able to clear all items")
    public void whenClearCalled_thenClearItems() {
        // Arrange
        String postedDescription = "i was just posted";
        TodoItem addedItem = new TodoItem().description(postedDescription);

        List<TodoItem> expected = new ArrayList<>();

        // Act
        subject.add(addedItem);
        subject.clear();

        // Assert
        assertEquals(expected, subject.findAll());
    }

    @Test
    @DisplayName("Should be able to find item by id")
    public void whenFindByIdCalled_thenReturnItem() {
        // Arrange
        int givenId = 1224;

        String postedDescription = "i was just posted";
        TodoItem addedItem = new TodoItem().description(postedDescription);

        when(mockIdCounter.getNewId()).thenReturn(givenId);

        TodoItem expected = new TodoItem().id(givenId).description(postedDescription);

        // Act
        subject.add(addedItem);
        TodoItem actual = subject.findById(givenId);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should be able to update item by id")
    public void whenUpdateByIdCalled_thenReturnUpdatedItem() {
        // Arrange
        int givenId = 1224;
        TodoItem updatedTodo = new TodoItem().id(givenId).description("new-description").completed(true);

        TodoItem itemDb = new TodoItem().id(givenId).description("original-description").completed(false);

        when(mockIdCounter.getNewId()).thenReturn(givenId);

        TodoItem expected = updatedTodo;

        // Act
        subject.add(itemDb);
        TodoItem actual = subject.updateById(givenId, updatedTodo);

        // Assert
        assertEquals(expected, actual);
    }
}
