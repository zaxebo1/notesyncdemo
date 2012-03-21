package com.notesyncdemo.shared;

import com.google.web.bindery.requestfactory.shared.ProxyForName;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

@ProxyForName(value = "com.notesyncdemo.server.Task", locator = "com.notesyncdemo.server.TaskLocator")
public interface TaskProxy extends ValueProxy {

	String getEmailAddress();

	Long getId();

	String getName();

	Boolean isDone();

	String getUserId();

	String getNote();

	void setEmailAddress(String emailAddress);

	void setName(String name);

	void setNote(String note);

	void setDone(Boolean done);

	void setUserId(String userId);

	String getTitle();

	void setTitle(String title);

	String getTags();

	void setTags(String tags);

	String getEncrypted();

	void setEncrypted(String encrypted);

	String getTheme();

	void setTheme(String theme);

	String getSelectionStart();

	void setSelectionStart(String selectionStart);

	String getSelectionEnd();

	void setSelectionEnd(String selectionEnd);

	String getScroll_position();

	void setScroll_position(String scroll_position);

	String getCreatedDate();

	void setCreatedDate(String createdDate);

	String getModifiedDate();

	void setModifiedDate(String modifiedDate);

}
