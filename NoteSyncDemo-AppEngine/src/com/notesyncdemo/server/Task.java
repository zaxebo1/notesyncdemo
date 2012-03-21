package com.notesyncdemo.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

  private String createdDate;
  private String modifiedDate;
  private String emailAddress;
  

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Boolean done = Boolean.FALSE;
  private String name;
  private String userId;
  
  private String _id;
  private String title;
  private String note;
  
  private String tags;
  private String encrypted;
  private String theme;
  
  private String selectionStart;
  private String selectionEnd;
  private String scroll_position;
  
  
  

  public Task() {
  }

  

  public String getEmailAddress() {
    return this.emailAddress;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Boolean isDone() {
    return done;
  }

  public String getUserId() {
    return userId;
  }
  
  public String getNote() {
            return note;
          }

  

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public void setNote(String note) {
            this.note = note;
          }

  public void setDone(Boolean done) {
    this.done = done;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
	  // TODO fill the right thing
	  
    StringBuilder builder = new StringBuilder();
    builder.append("Task [dueDate=");
    
    builder.append(", done=");
    builder.append(done);
    builder.append(", name=");
    builder.append(name);
    builder.append("]");
    return builder.toString();
  }

public String get_id() {
	return _id;
}

public void set_id(String _id) {
	this._id = _id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getTags() {
	return tags;
}

public void setTags(String tags) {
	this.tags = tags;
}

public String getEncrypted() {
	return encrypted;
}

public void setEncrypted(String encrypted) {
	this.encrypted = encrypted;
}

public String getTheme() {
	return theme;
}

public void setTheme(String theme) {
	this.theme = theme;
}

public String getSelectionStart() {
	return selectionStart;
}

public void setSelectionStart(String selectionStart) {
	this.selectionStart = selectionStart;
}

public String getSelectionEnd() {
	return selectionEnd;
}

public void setSelectionEnd(String selectionEnd) {
	this.selectionEnd = selectionEnd;
}

public String getScroll_position() {
	return scroll_position;
}

public void setScroll_position(String scroll_position) {
	this.scroll_position = scroll_position;
}

public String getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
}

public String getModifiedDate() {
	return modifiedDate;
}

public void setModifiedDate(String modifiedDate) {
	this.modifiedDate = modifiedDate;
}
}
