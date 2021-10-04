package smt.devlabs.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import smt.devlabs.api.DoItApplication;
import smt.devlabs.api.data.ItemsDb;
import smt.devlabs.openapi.model.TodoItem;
import smt.devlabs.openapi.model.TodoItemList;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DoItApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemsControllerIntegrationTest {

    // A test for returning ok for items endpoint
    // return a todoitemlist with 1 item inside when calling endpoint
    // A test for returning ok for a post same items endpoint
    // retun response for adding item to list
    // add a todo item and then call the items list to see if that item is in there

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ItemsDb mockItemsDb;

    @Test
    @DisplayName("GET request to /items should return Response Status OK")
    public void whenRetrieveItemsList_thenResponseOk() {
        // Act
        ResponseEntity<TodoItemList> actual = this.restTemplate.getForEntity("/items", TodoItemList.class);

        // Assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    @DisplayName("GET request to /items should return a TodoItemList with persisted to-do items")
    public void whenTodoItemInDb_thenRetrieveItemsList() {
        // Arrange
        List<TodoItem> itemsInDbList = new ArrayList<>();
        TodoItem itemInDb = new TodoItem().id(1).description("some-description").completed(false);
        itemsInDbList.add(itemInDb);

        when(mockItemsDb.findAll()).thenReturn(itemsInDbList);

        TodoItemList expected = new TodoItemList();
        expected.addItemsItem(itemInDb);

        // Act
        ResponseEntity<TodoItemList> actual = this.restTemplate.getForEntity("/items", TodoItemList.class);

        // Assert
        assertEquals(expected, actual.getBody());
    }

    @Test
    @DisplayName("POST request to /items should return Response Status OK")
    public void whenCreateTodoItem_thenResponseOk() {
        // Arrange
        TodoItem item1 = new TodoItem();

        // Act
        ResponseEntity<TodoItem> actual = this.restTemplate.postForEntity("/items", item1, TodoItem.class);

        // Assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    @DisplayName("POST request to /items should return the todo item that was just created")
    public void whenCreateTodoItem_thenResponseWithAddedItem() {
        // Arrange
        TodoItem postItem = new TodoItem().description("some-description");

        TodoItem itemInDb = new TodoItem().id(1224).description("some-description").completed(false);
        when(mockItemsDb.add(any(TodoItem.class))).thenReturn(itemInDb);

        // Act
        ResponseEntity<TodoItem> actual = this.restTemplate.postForEntity("/items", postItem, TodoItem.class);
        TodoItem expected = itemInDb;

        // Assert
        assertEquals(expected, actual.getBody());
    }
}
