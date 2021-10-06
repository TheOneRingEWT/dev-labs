package smt.devlabs.api.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return items.stream().filter(item -> item.getId().equals(id)).findFirst().get();
    }

    public TodoItem updateById(int id, TodoItem item) {

        this.items = items.stream().map(todo -> {
            if (item.getId().equals(id)) {
                return item;
            } else {
                return todo;
            }
        }).collect(Collectors.toList());

        return items.stream().filter(todo -> todo.getId().equals(id)).findFirst().get();
    }

    public TodoItem deleteById(int id) {

        TodoItem itemDeleted = items.stream().filter(todo -> todo.getId().equals(id)).findFirst().get();

        this.items = items.stream().filter(item -> !item.getId().equals(id)).collect(Collectors.toList());

        return itemDeleted;
    }
}