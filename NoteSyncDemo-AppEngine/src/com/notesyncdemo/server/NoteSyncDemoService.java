package com.notesyncdemo.server;

import java.util.List;

import com.notesyncdemo.annotation.ServiceMethod;
import com.notesyncdemo.server.DataStore;
import com.notesyncdemo.server.Task;


public class NoteSyncDemoService {
	static DataStore db = new DataStore();

	@ServiceMethod
	public Task createTask() {
		return db.update(new Task());
	}

	@ServiceMethod
	public Task readTask(Long id) {
		return db.find(id);
	}

	@ServiceMethod
	public Task updateTask(Task task) {
		//this is where you can put c2dm message
		task.setEmailAddress(DataStore.getUserEmail());
		task = db.update(task);
		//DataStore.sendC2DMUpdate(TaskChange.UPDATE + TaskChange.SEPARATOR + task.getId());
		return task;
	}

	@ServiceMethod
	public void deleteTask(Task task) {
		db.delete(task.getId());

	}

	@ServiceMethod
	public List<Task> queryTasks() {
		return db.findAll();
	}
}
