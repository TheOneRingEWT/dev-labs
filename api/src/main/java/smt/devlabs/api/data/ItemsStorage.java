package smt.devlabs.api.data;

import org.springframework.stereotype.Service;

import smt.devlabs.openapi.model.TodoItem;
import smt.devlabs.openapi.model.TodoItemList;

@Service
public class ItemsStorage implements ItemsStorageApi {

    private ItemsDb db;

    public ItemsStorage(ItemsDb db) {
        this.db = db;
    }

    @Override
    public TodoItemList retrieveItemsList() {
        return new TodoItemList().items(db.findAll());
    }

    @Override
    public TodoItem createTodoItem(TodoItem todoItem) {
        todoItem.completed(false);
        return db.add(todoItem);
    }

    @Override
    public TodoItem retrieveTodoItem(int id) {
        return db.findById(id);
    }
}