package io.EnglishLevelGame.EnglishLevelGame.students;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MyGroupRepo extends CrudRepository<Group, Integer> {
	
	Optional<Group> findById(Integer groupId);

}
