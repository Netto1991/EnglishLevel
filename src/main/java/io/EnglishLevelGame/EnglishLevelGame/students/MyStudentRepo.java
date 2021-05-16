package io.EnglishLevelGame.EnglishLevelGame.students;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyStudentRepo extends CrudRepository<Student, Long> {

	List<Student> findByGroup(Group group);
}
