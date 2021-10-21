package smt.devlabs.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import smt.devlabs.api.DoItApplication;
import smt.devlabs.openapi.model.TodoItem;
import smt.devlabs.openapi.model.TodoItemList;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DoItApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ItemsControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("POST request to /items should return Response OK with the todo item that was just created")
    public void whenCreateTodoItem_thenResponseOkWithAddedItem() {
        // Arrange
        TodoItem postItem = new TodoItem().description("some-description");
        TodoItem expected = new TodoItem().id(1).description("some-description").completed(false);

        // Act
        ResponseEntity<TodoItem> actual = this.restTemplate.postForEntity("/items", postItem, TodoItem.class);

        // Assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }

    @Test
    @DisplayName("GET request to /items should return Response OK with a TodoItemList with persisted to-do items")
    public void whenTodoItemInDb_thenResponseOKWithTodoItemsList() {
        // Arrange
        TodoItem postItem = new TodoItem().description("some-description");

        TodoItem expectedTodoItem = new TodoItem().id(1).description("some-description").completed(false);
        TodoItemList expected = new TodoItemList();
        expected.addItemsItem(expectedTodoItem);

        // Act
        this.restTemplate.postForEntity("/items", postItem, TodoItem.class);
        ResponseEntity<TodoItemList> actual = this.restTemplate.getForEntity("/items", TodoItemList.class);

        // Assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }

    @Test
    @DisplayName("GET request to /items/{id} should return Response OK with a TodoItem with that ID")
    public void whenRetrieveTodoItemInDb_thenResponseOKWithTodoItem() {
        // Arrange
        int givenId = 1;

        TodoItem postItem = new TodoItem().description("some-description");
        this.restTemplate.postForEntity("/items", postItem, TodoItem.class);

        TodoItem expected = new TodoItem().id(givenId).description("some-description").completed(false);

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
        int givenId = 1;

        TodoItem postItem = new TodoItem().description("old-description");
        this.restTemplate.postForEntity("/items", postItem, TodoItem.class);

        TodoItem putItem = new TodoItem().id(givenId).description("new-description").completed(false);
        HttpEntity<TodoItem> httpEntity = new HttpEntity<>(putItem);

        TodoItem expected = putItem;

        // Act
        // Using exchange() because put() returns void and we need a Response
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
        int givenId = 1;

        TodoItem postItem = new TodoItem().description("some-description");
        this.restTemplate.postForEntity("/items", postItem, TodoItem.class);

        TodoItem expected = new TodoItem().id(givenId).description("some-description").completed(false);

        // Act
        // Using exchange() because delete() returns void and we need a Response
        ResponseEntity<TodoItem> actual = this.restTemplate.exchange("/items/" + givenId, HttpMethod.DELETE, null,
                TodoItem.class);

        // Assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }
}
