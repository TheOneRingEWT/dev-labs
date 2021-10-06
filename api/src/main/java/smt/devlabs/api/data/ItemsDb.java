package smt.devlabs.api.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import smt.devlabs.openapi.model.TodoItem;

@Repository
public class ItemsDb {
    private List<TodoItem> items = new ArrayList<>();
    private IdCounter idCounter;

    public ItemsDb(IdCounter idCounter) {
        this.idCounter = idCounter;
    }

    public void clear() {
        items.clear();
    }

    public TodoItem add(TodoItem item) {
        item.id(idCounter.getNewId());
        items.add(item);

        return items.get(items.size() - 1);
    }

    public List<TodoItem> findAll() {
        return items;
    }

    public TodoItem findById(int id) {
        return items.stream().filter(item -> id == item.getId()).findFirst().get();
    }
}