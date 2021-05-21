package org.example.repo;

import org.example.models.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepo extends CrudRepository<Pizza,Long> {
}
