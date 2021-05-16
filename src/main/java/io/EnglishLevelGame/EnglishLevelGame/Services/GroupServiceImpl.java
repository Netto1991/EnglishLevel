package io.EnglishLevelGame.EnglishLevelGame.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.EnglishLevelGame.EnglishLevelGame.students.Group;
import io.EnglishLevelGame.EnglishLevelGame.students.MyGroupRepo;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private MyGroupRepo groupRepo;

	public GroupServiceImpl(MyGroupRepo groupRepo) {
		this.groupRepo = groupRepo;
	}

	public MyGroupRepo getGroupRepo() {
		return groupRepo;
	}

	public void setGroupRepo(MyGroupRepo groupRepo) {
		this.groupRepo = groupRepo;
	}
	
	public Iterable<Group> findAllGroup(){
		return groupRepo.findAll();
	}
	
	public Group showOneGroup(Integer groupId) {
		return groupRepo.findById(groupId).get();
	}

	@Override
	public void saveGroup(Group group) {
		groupRepo.save(group);
		
	}

	@Override
	public void deleteGroup(Group group) {
		groupRepo.delete(group);
		
	}
	
	
}
