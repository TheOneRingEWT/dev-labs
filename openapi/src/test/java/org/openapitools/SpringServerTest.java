/*
 * The purpose of this test is to check if the OpenAPITools code generator was properly configured. It merely checks if the code generated successfully 
 * created a server stub with the correct endpoints.
 *
*/

package org.openapitools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import smt.devlabs.openapi.model.TodoItem;
import smt.devlabs.openapi.model.TodoItemList;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OpenAPI2SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringServerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenCallItemsEndpoint_thenResponseNotImplemented() {
        // Arrange

        // Act
        ResponseEntity<TodoItemList> actual = this.restTemplate.getForEntity("/api/v1/items", TodoItemList.class);

        // Assert
        TodoItemList expected = new TodoItemList();
        expected.addItemsItem(new TodoItem().id(0).description("description").completed(true));
        expected.addItemsItem(new TodoItem().id(0).description("description").completed(true));

        assertEquals(HttpStatus.NOT_IMPLEMENTED, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }

    @Test
    public void whenCallItemsWithIdEndpoint_thenResponseNotImplemented() {
        // Arrange

        // Act
        ResponseEntity<TodoItem> actual = this.restTemplate.getForEntity("/api/v1/items/0", TodoItem.class);

        // Assert
        TodoItem expected = new TodoItem().id(0).description("description").completed(true);

        assertEquals(HttpStatus.NOT_IMPLEMENTED, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }
}
