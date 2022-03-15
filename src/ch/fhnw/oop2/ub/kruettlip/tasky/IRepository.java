package ch.fhnw.oop2.ub.kruettlip.tasky;

import java.util.List;
import java.util.UUID;

public interface IRepository<T> {
    List<T> getAll();
    T get(UUID id);
    void add(T element);
    void update(T element);
    void delete(T element);
}
