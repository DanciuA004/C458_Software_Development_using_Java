package com.mthree.todo.data;

import com.mthree.todo.model.ToDo;

import java.util.List;

public interface ToDoDao {

    ToDo add(ToDo todo);

    List<ToDo> getAll();

    ToDo findById(int id);

    boolean update(ToDo todo);

    boolean deleteById(int id);
}
