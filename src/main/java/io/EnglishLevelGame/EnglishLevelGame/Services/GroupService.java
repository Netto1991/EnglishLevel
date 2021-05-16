package io.EnglishLevelGame.EnglishLevelGame.Services;

import io.EnglishLevelGame.EnglishLevelGame.students.Group;

public interface GroupService {
	Group showOneGroup(Integer groupId);
	
	Iterable<Group> findAllGroup();
	
	void saveGroup(Group group);
	
	void deleteGroup(Group group);
}
