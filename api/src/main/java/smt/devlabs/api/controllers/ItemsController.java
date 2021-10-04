package smt.devlabs.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import smt.devlabs.api.data.ItemsStorageApi;
import smt.devlabs.openapi.api.ItemsApi;
import smt.devlabs.openapi.model.TodoItem;
import smt.devlabs.openapi.model.TodoItemList;

@RestController
public class ItemsController implements ItemsApi {

    ItemsStorageApi storage;

    public ItemsController(ItemsStorageApi storage) {
        this.storage = storage;
    };

    @Override
    public ResponseEntity<TodoItemList> retrieveItemsList() {
        return ResponseEntity.ok().body(storage.retrieveItemsList());
    }

    @Override
    public ResponseEntity<TodoItem> createTodoItem(TodoItem todoItem) {
        return ResponseEntity.ok().body(storage.createTodoItem(todoItem));
    }

}
