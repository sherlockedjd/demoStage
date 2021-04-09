package logicunit.for_api;

import java.util.Date;
import java.util.List;

public class Todo {

	private String title;
	private String text;
	private boolean done;
	private Date createdOn;
	private Date completedOn;
	private int id;
	private Todo_Likes likes;
	private List<Todo_Tasks> tasks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Todo(String title, String text, boolean done, Todo_Likes likes) {
		this.setTitle(title);
		this.setText(text);
		this.setDone(done);
		this.setLikes(likes);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(Date completedOn) {
		this.completedOn = completedOn;
	}

	public String present() {
		System.out.println(this.done);
		System.out.println(this.text);
		System.out.println(this.title);
		System.out.println(this.completedOn);
		System.out.println(this.createdOn);
		return "all good";
	}

	public Todo_Likes getLikes() {
		return likes;
	}

	public void setLikes(Todo_Likes likes) {
		this.likes = likes;
	}

	public List<Todo_Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Todo_Tasks> tasks) {
		this.tasks = tasks;
	}

}
