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
import smt.devlabs.openapi.model.TodoItemList;

@ExtendWith(MockitoExtension.class)
public class ItemsStorageTest {

    private ItemsStorage subject;

    @Mock
    private ItemsDb mockItemsDb;

    @BeforeEach
    public void setupBeforeEach() {
        this.subject = new ItemsStorage(mockItemsDb);
    }

    @Test
    @DisplayName("Should return TodoItemList from database")
    public void whenRetrieveItemsList_thenReturnItemsList() {
        // Arrange
        TodoItem dbItem = new TodoItem();
        List<TodoItem> dbItemList = new ArrayList<>();
        dbItemList.add(dbItem);

        when(mockItemsDb.findAll()).thenReturn(dbItemList);

        TodoItemList expected = new TodoItemList().items(dbItemList);

        // Act
        TodoItemList actual = subject.retrieveItemsList();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should add to-do item to database and default completed=false")
    public void whenCreateTodoItemCalled_thenPersistWithDefaultCompleted() {
        // Arrange
        String postedDescription = "i was just posted";
        TodoItem addedItem = new TodoItem().description(postedDescription);

        // Act
        subject.createTodoItem(addedItem);

        TodoItem expected = new TodoItem().description(postedDescription).completed(false);

        // Assert
        verify(mockItemsDb).add(expected);
    }

    @Test
    @DisplayName("Should retrieve to-do item by id")
    public void whenRetreiveTodoItemCalled_thenReturnTodoItem() {
        // Arrange
        int givenId = 1234;
        TodoItem dbItem = new TodoItem().id(givenId);

        when(mockItemsDb.findById(givenId)).thenReturn(dbItem);

        TodoItem expected = dbItem;

        // Act
        TodoItem actual = subject.retrieveTodoItem(givenId);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should update to-do item by id")
    public void whenUpdateTodoItemCalled_thenReturnTodoItem() {
        // Arrange
        int givenId = 1234;
        TodoItem putItem = new TodoItem().id(givenId);

        when(mockItemsDb.updateById(givenId, putItem)).thenReturn(putItem);

        TodoItem expected = putItem;

        // Act
        TodoItem actual = subject.updateTodoItem(givenId, putItem);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should delete to-do item by id")
    public void whenDeleteTodoItemCalled_thenDeleteAndReturnTodoItem() {
        // Arrange
        int givenId = 1234;
        TodoItem deletedItem = new TodoItem().id(givenId);

        when(mockItemsDb.deleteById(givenId)).thenReturn(deletedItem);

        TodoItem expected = deletedItem;

        // Act
        TodoItem actual = subject.deleteTodoItem(givenId);

        // Assert
        assertEquals(expected, actual);
    }
}
