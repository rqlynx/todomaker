package jp.abc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.abc.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
