package smt.devlabs.api.data;

import smt.devlabs.openapi.model.TodoItem;
import smt.devlabs.openapi.model.TodoItemList;

public interface ItemsStorageApi {
    public TodoItemList retrieveItemsList();

    public TodoItem createTodoItem(TodoItem todoItem);
}