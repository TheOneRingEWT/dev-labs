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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    @DisplayName("GET request to /items should return Response OK with a TodoItemList with persisted to-do items")
    public void whenTodoItemInDb_thenResponseOKWithTodoItemsList() {
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
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }

    @Test
    @DisplayName("POST request to /items should return Response OK with the todo item that was just created")
    public void whenCreateTodoItem_thenResponseOkWithAddedItem() {
        // Arrange
        TodoItem postItem = new TodoItem().description("some-description");

        TodoItem itemInDb = new TodoItem().id(1224).description("some-description").completed(false);
        when(mockItemsDb.add(any(TodoItem.class))).thenReturn(itemInDb);

        // Act
        ResponseEntity<TodoItem> actual = this.restTemplate.postForEntity("/items", postItem, TodoItem.class);
        TodoItem expected = itemInDb;

        // Assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }

    @Test
    @DisplayName("GET request to /items/{id} should return Response OK with a TodoItem with that ID")
    public void whenRetrieveTodoItemInDb_thenResponseOKWithTodoItem() {
        // Arrange
        int givenId = 1234;

        TodoItem itemInDb = new TodoItem().id(givenId).description("some-description").completed(false);
        when(mockItemsDb.findById(givenId)).thenReturn(itemInDb);

        TodoItem expected = itemInDb;

        // Act
        ResponseEntity<TodoItem> actual = this.restTemplate.getForEntity("/items/" + givenId, TodoItem.class);

        // Assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }

    @Test
    @DisplayName("PUT request to /items/{id} should return Response OK with a modified TodoItem with that ID")
    public void whenModifyTodoItemInDb_thenResponseOKWithTodoItem() {
        // Arrange
        int givenId = 1234;

        TodoItem putItem = new TodoItem().id(givenId).description("new-description").completed(false);
        HttpEntity<TodoItem> httpEntity = new HttpEntity<>(putItem);

        when(mockItemsDb.updateById(givenId, putItem)).thenReturn(putItem);

        TodoItem expected = putItem;

        // Act
        ResponseEntity<TodoItem> actual = this.restTemplate.exchange("/items/" + givenId, HttpMethod.PUT, httpEntity,
                                                                     TodoItem.class);

        // Assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }

    @Test
    @DisplayName("DELETE request to /items/{id} should return Response OK with the deleted TodoItem with that ID")
    public void whenDeleteTodoItemInDb_thenResponseOKWithTodoItem() {
        // Arrange
        int givenId = 1234;

        TodoItem itemInDb = new TodoItem().id(givenId).description("some-description").completed(false);
        HttpEntity<TodoItem> httpEntity = new HttpEntity<>(itemInDb);  // Need for exchange

        when(mockItemsDb.deleteById(givenId)).thenReturn(itemInDb);

        TodoItem expected = itemInDb;

        // Act

        // Using exchange because delete() returns void and we need a Response
        ResponseEntity<TodoItem> actual = this.restTemplate.exchange("/items/" + givenId, HttpMethod.DELETE, httpEntity,
                                                                     TodoItem.class);

        // Assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }
}
