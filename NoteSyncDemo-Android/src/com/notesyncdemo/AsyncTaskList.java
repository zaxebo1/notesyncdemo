package com.notesyncdemo;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.notesyncdemo.shared.NoteSyncDemoRequest;
import com.notesyncdemo.shared.NoteSyncDemoRequestFactory;
import com.notesyncdemo.shared.TaskProxy;



public class AsyncTaskList extends AsyncTask<String[][], Void, String[][] > {
	private static final String TAG = "NoteSyncDemoActivity";
	NoteSyncDemoActivity activity;
	static String tag = "vincent";
	
	public AsyncTaskList(NoteSyncDemoActivity activity) {
		this.activity = activity;
	}

	@Override
	protected String[][] doInBackground(String[][]... params) {
		
		Log.i("vincent", "inside the async");
		String[][] clientArray = params[0];
		
		final List<TaskProxy> list = new ArrayList<TaskProxy>();
		NoteSyncDemoRequestFactory factory = Util.getRequestFactory(activity, NoteSyncDemoRequestFactory.class);
		factory.taskRequest().queryTasks().fire(new Receiver<List<TaskProxy>>() {

			@Override
			public void onSuccess(List<TaskProxy> arg0) {
				Log.i("vincent", "inside succes of Async");
				
				
				Log.d(tag, "Size of list"+list.size());
				list.addAll(arg0);
				Log.d(tag, "Size of list"+list.size());
				
			} 
			
			
		});
		
		Log.i("vincent", "async completed taking the list");
		int il = list.size();
		Log.d(TAG, "inside Asynctasklist: size of list returned from server"+list.size());
		
		String[] [] serverArr = null;
		
		if(il==0) { 
			serverArr = null;} //if the list size is zero then server array = null
		
		else {
			
			
			int i=0;
			serverArr= new String[il][11];
			
			
			
			for(TaskProxy task: list) {
				//notelist[i]=task.getName(); // from previous
				
				serverArr[i][0]= i+"";
				serverArr[i][1]= task.getTitle();
				serverArr[i][2]= task.getNote();
			    serverArr[i][3]= task.getCreatedDate();
			    serverArr[i][4]= task.getModifiedDate();
			    serverArr[i][5]= task.getTags();
			    serverArr[i][6]= task.getEncrypted();
			    serverArr[i][7]= task.getTheme();
			    serverArr[i][8]= task.getSelectionStart();
				serverArr[i][9]= task.getSelectionEnd();
				serverArr[i][10]= task.getScroll_position();					
				
				
				Log.d(tag,i+" list title:"+task.getTitle());
				
				//Log.i("vincent", "inside the string"+notelist[i]);
				
				i++;
				
			}
			
		}
		
		
		
		
		String[][] addArrayClient = getDifference(clientArray, serverArr);
    	String[][] addArrayServer = getDifference(serverArr,clientArray);
    	
    	//NoteSyncDemoRequestFactory factory = Util.getRequestFactory(activity, NoteSyncDemoRequestFactory.class);
        NoteSyncDemoRequest request = factory.taskRequest();
        
        if(addArrayServer!=null) {
        	int arlen = addArrayServer.length; 
        	Log.d(TAG, "lenth of addArrayServer:"+arlen);
        	
        	TaskProxy[] task = new TaskProxy[arlen];
        	
            for(int ik=0;ik<arlen;ik++) {
            	//fire must be in the last out of the loop
            	
            	
            	task[ik] = request.create(TaskProxy.class);
            	
            	
            	//task[ik].set_id(params[i][0]);
            	Log.d(TAG,"inside uploading task[ik]:"+ik+":"+addArrayServer[ik][1]);
            	task[ik].setTitle(addArrayServer[ik][1]);
            	task[ik].setNote(addArrayServer[ik][2]);
            	task[ik].setCreatedDate(addArrayServer[ik][3]);
            	task[ik].setModifiedDate(addArrayServer[ik][4]);
            	task[ik].setTags(addArrayServer[ik][5]);
            	task[ik].setEncrypted(addArrayServer[ik][6]);
            	task[ik].setTheme(addArrayServer[ik][7]);
            	task[ik].setSelectionStart(addArrayServer[ik][8]);
            	task[ik].setSelectionEnd(addArrayServer[ik][9]);
            	task[ik].setScroll_position(addArrayServer[ik][10]);
            	
            	// made one task[ik]
            	
            	request.updateTask(task[ik]);
            	
            	
            	
            	
            }
            
            
            request.fire(); // should be called only once for a request.
        }
        
    	
		
		return addArrayClient;
	}

	@Override
	protected void onPostExecute(String[][] result) {
		Log.d(tag, "inside the post execute");
		activity.updateText("Syncing Finished!");
		activity.makeClickable(true);
		
		String[] sendArray = null;
		if(result!=null){
			sendArray = new String[result.length];
		
		
			
			for(int i=0;i<result.length;i++) {
				sendArray[i] ="";
				for(int j=0;j<11;j++) {
					sendArray[i] =  sendArray[i]+result[i][j]+":";
				}
			}
		}	
		
		activity.sendResult(sendArray);
		
		super.onPostExecute(result);
	}
	
	/**
	@Override
	protected void onPostExecute(String[][] serverArr) {
		Log.d(TAG, "onPostExecute() of asynctasklist" );
		activity.setServerArr(serverArr);
	}
	*/
private String[][] getDifference(String[][] smallTuple, String[][] bigTuple) {
		
		// Here we have to find tuple by bigTuple - smallTuple
		// Elements in bigTuple that not in smallTuple has to be returned
		
		//if bigtuple does not have anything then nothing is to be returned
		if(bigTuple==null) {
			
			return null;
		}
		
		// if smalltuple is null then return everything from bigtuple
		if(smallTuple==null) {
			
			return bigTuple;
		}
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		//3 is for created
		for(int i=0;i<bigTuple.length;i++) {
			boolean flag = false;
			for(int j=0;j<smallTuple.length;j++) {
				String bigDate = bigTuple[i][3];
				String smallDate = smallTuple[j][3];
				
				if(bigDate.equals(smallDate)) {
					flag = true;
				}
				
			}
			
			if(flag) {
				// element is present in both no change
			}
			else {
				list.add(bigTuple[i]);
			}
		}
		
		String[][] retarr = new String[list.size()][];
		for(int k=0;k<list.size();k++) {
			retarr[k]= list.get(k);
			
		}
		
		return retarr;
	}
	
}
