package com.notesyncdemo;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.notesyncdemo.shared.NoteSyncDemoRequest;
import com.notesyncdemo.shared.NoteSyncDemoRequestFactory;
import com.notesyncdemo.shared.TaskProxy;




public class AsyncUpdate extends AsyncTask<String[][], Void, String[][] > {

	NoteSyncDemoActivity activity;
	static String tag = "vincent";
	
	public AsyncUpdate(NoteSyncDemoActivity activity) {
		this.activity = activity;
	}
	
	@Override
	protected String[][] doInBackground(String[][]... params) {
		
		NoteSyncDemoRequestFactory factory = Util.getRequestFactory(activity, NoteSyncDemoRequestFactory.class);
        NoteSyncDemoRequest request = factory.taskRequest();
        
		
        for(int i=0;i<params.length;i++) {
        	TaskProxy task = request.create(TaskProxy.class);
        	String[] el = params[0][i];
        	
        	//task.set_id(params[i][0]);
        	
        	task.setTitle(params[0][i][1]);
        	task.setNote(params[0][i][2]);
        	task.setCreatedDate(params[0][i][3]);
        	task.setModifiedDate(params[0][i][4]);
        	task.setTags(params[0][i][5]);
        	task.setEncrypted(params[0][i][6]);
        	task.setTheme(params[0][i][7]);
        	task.setSelectionStart(params[0][i][8]);
        	task.setSelectionEnd(params[0][i][9]);
        	task.setScroll_position(params[0][i][10]);
        	
        	// made one task
        	
        	request.updateTask(task).fire();
        	
        }
        	
		return params[0];
	}
	
	protected void onPostExecute(String[][] serverArr) {
		Log.d("vincent","updated to the server");
	}
	
	
}

