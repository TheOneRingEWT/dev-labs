package smt.devlabs.api.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import smt.devlabs.openapi.model.TodoItem;

public class ItemsDbTest {

    private ItemsDb subject;

    @BeforeEach
    public void setupBeforeEach() {
        this.subject = new ItemsDb(new IdCounter());
    }

    @Test
    @DisplayName("Added to-do items should and persisted and returned")
    public void whenCreateTodoItemCalled_thenPersistAndReturn() {
        // Arrange
        String postedDescription = "i was just posted";
        TodoItem addedItem = new TodoItem().description(postedDescription);

        TodoItem expected = new TodoItem().id(1).description(postedDescription);

        // Act
        TodoItem actual = subject.add(addedItem);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should be able to return all todo items")
    public void whenFindAllCalled_thenReturnTodoItemsList() {
        // Arrange
        String postedDescription = "some-description";
        TodoItem addedItem = new TodoItem().description(postedDescription);
        subject.add(addedItem);

        List<TodoItem> expected = new ArrayList<>();
        expected.add(new TodoItem().id(1).description(postedDescription));

        // Act
        List<TodoItem> actual = subject.findAll();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should be able to find item by id")
    public void whenFindByIdCalled_thenReturnItem() {
        // Arrange
        int givenId = 1;

        String postedDescription = "i was just posted";
        TodoItem itemInDb = new TodoItem().description(postedDescription);
        subject.add(itemInDb);

        TodoItem expected = new TodoItem().id(givenId).description(postedDescription);

        // Act
        TodoItem actual = subject.findById(givenId);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return updated item when updating to-do item by id")
    public void whenUpdateByIdCalled_thenReturnUpdatedItem() {
        // Arrange
        int givenId = 1;
        TodoItem itemDb = new TodoItem().id(givenId).description("original-description").completed(false);
        subject.add(itemDb);

        TodoItem updatedTodo = new TodoItem().id(givenId).description("new-description").completed(true);
        TodoItem expected = updatedTodo;

        // Act
        TodoItem actual = subject.updateById(givenId, updatedTodo);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should update item in the list when updating to-do item by id")
    public void whenUpdateByIdCalled_thenUpdatedItemInList() {
        // Arrange
        int givenId = 2;
        TodoItem itemDb = new TodoItem().id(1).description("original-description 1").completed(false);
        subject.add(itemDb);
        TodoItem itemDb2 = new TodoItem().id(givenId).description("original-description 2").completed(false);
        subject.add(itemDb2);
        TodoItem itemDb3 = new TodoItem().id(givenId).description("original-description 3").completed(false);
        subject.add(itemDb3);

        TodoItem updatedItem = new TodoItem().id(givenId).description("new-description").completed(true);
        TodoItem expected = updatedItem;

        List<TodoItem> expectedUpdatedList = Arrays.asList(itemDb, updatedItem, itemDb3);

        // Act
        TodoItem actual = subject.updateById(givenId, updatedItem);
        List<TodoItem> actualUpdatedList = subject.findAll();

        // Assert
        assertEquals(expected, actual);
        assertEquals(expectedUpdatedList, actualUpdatedList);
    }

    @Test
    @DisplayName("Should be able to delete item by id")
    public void whenDeleteByIdCalled_thenDeletItemAndReturn() {
        // Arrange
        int givenId = 1;
        TodoItem itemInDb = new TodoItem().id(givenId).description("some-description");
        subject.add(itemInDb);

        TodoItem expected = itemInDb;

        // Act
        TodoItem actual = subject.deleteById(givenId);

        // Assert
        assertEquals(expected, actual);
    }
}
