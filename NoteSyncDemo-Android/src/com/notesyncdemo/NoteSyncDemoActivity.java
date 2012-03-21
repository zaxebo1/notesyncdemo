/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.notesyncdemo;

import java.util.ArrayList;



import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import com.notesyncdemo.client.MyRequestFactory;
import com.notesyncdemo.client.MyRequestFactory.HelloWorldRequest;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Main activity - requests "Hello, World" messages from the server and provides
 * a menu item to invoke the accounts activity.
 */
public class NoteSyncDemoActivity extends Activity {
    /**
     * Tag for logging. 
     */
	
    private static final String TAG = "NoteSyncDemoActivity";

    /**
     * The current context.
     */
    private Context mContext = this;
    
    private String[][] serverArr;

    /**
     * A {@link BroadcastReceiver} to receive the response from a register or
     * unregister request, and to update the UI.
     */
    private final BroadcastReceiver mUpdateUIReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String accountName = intent.getStringExtra(DeviceRegistrar.ACCOUNT_NAME_EXTRA);
            int status = intent.getIntExtra(DeviceRegistrar.STATUS_EXTRA,
                    DeviceRegistrar.ERROR_STATUS);
            String message = null;
            String connectionStatus = Util.DISCONNECTED;
            if (status == DeviceRegistrar.REGISTERED_STATUS) {
                message = getResources().getString(R.string.registration_succeeded);
                connectionStatus = Util.CONNECTED;
            } else if (status == DeviceRegistrar.UNREGISTERED_STATUS) {
                message = getResources().getString(R.string.unregistration_succeeded);
            } else {
                message = getResources().getString(R.string.registration_error);
            }

            // Set connection status
            SharedPreferences prefs = Util.getSharedPreferences(mContext);
            prefs.edit().putString(Util.CONNECTION_STATUS, connectionStatus).commit();

            // Display a notification
            Util.generateNotification(mContext, String.format(message, accountName));
        }
    };

    /**
     * Begins the activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        // Register a receiver to provide register/unregister notifications
        registerReceiver(mUpdateUIReceiver, new IntentFilter(Util.UPDATE_UI_INTENT));
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = Util.getSharedPreferences(mContext);
        String connectionStatus = prefs.getString(Util.CONNECTION_STATUS, Util.DISCONNECTED);
        if (Util.DISCONNECTED.equals(connectionStatus)) {
            startActivity(new Intent(this, AccountsActivity.class));
        }
        setScreenContent(R.layout.hello_world);
    }

    /**
     * Shuts down the activity.
     */
    @Override
    public void onDestroy() {
        unregisterReceiver(mUpdateUIReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        // Invoke the Register activity
        menu.getItem(0).setIntent(new Intent(this, AccountsActivity.class));
        return true;
    }

    // Manage UI Screens

    private void setHelloWorldScreenContent() {
        setContentView(R.layout.hello_world);

        final TextView helloWorld = (TextView) findViewById(R.id.hello_world);
        
        final Button sayFireButton = (Button) findViewById(R.id.say_fire);
        sayFireButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				sayFireButton.setClickable(false);
				helloWorld.setText("Sync Activity Started. Please wait, it will take minute or two to complete Syncing Notes. On first time it will take more time.(8min)");
				startSync();
			}
		});
        
        
        final Button sayHelloButton = (Button) findViewById(R.id.say_hello);
        sayHelloButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                sayHelloButton.setEnabled(false);
                helloWorld.setText(R.string.contacting_server);

                // Use an AsyncTask to avoid blocking the UI thread
                new AsyncTask<Void, Void, String>() {
                    private String message;

                    @Override
                    protected String doInBackground(Void... arg0) {
                        MyRequestFactory requestFactory = Util.getRequestFactory(mContext,
                                MyRequestFactory.class);
                        final HelloWorldRequest request = requestFactory.helloWorldRequest();
                        Log.i(TAG, "Sending request to server");
                        request.getMessage().fire(new Receiver<String>() {
                            @Override
                            public void onFailure(ServerFailure error) {
                                message = "Failure: " + error.getMessage();
                            }

                            @Override
                            public void onSuccess(String result) {
                                message = result;
                            }
                        });
                        return message;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        helloWorld.setText("Yup, Everything looks fine! "+result);
                        sayHelloButton.setEnabled(true);
                    }
                }.execute();
            }
        });
    }

    protected void startSync() {
		
    	
    	Bundle extras = getIntent().getExtras();
    	Log.d("vincent","intent notesyndemo"+getIntent().describeContents()+"and action is:"+getIntent().getAction());
    	if(getIntent().getAction().equalsIgnoreCase("vincent.start")) {
    		// Get the client data
    		String[] array = extras.getStringArray("array");
        	Log.d(TAG,"length of array in notesyncdemo:"+array.length);
        	String[][] clientArray = getDoubleArray(array);      	

        	final TextView helloWorld = (TextView) findViewById(R.id.hello_world);
        	helloWorld.setText("Fetching the data from cloud");
        	
        	//fetching the cloud data
        	fetch(clientArray); // fetches the data from the server and stores in serverArr
        	        	
        	helloWorld.setText("Sync Activity Started. Please wait, it will take minute or two to complete Syncing Notes");
        	
        	
        	
        	
    	}
    	else {
    		final TextView helloWorld = (TextView) findViewById(R.id.hello_world);
    		helloWorld.setText("Please call me from the OI notepad's Options Menu> Sync");
    	}
    	
    	
		
	}

	

	private void updateServer(String[][] addArrayServer) {
		AsyncUpdate update = new AsyncUpdate(this);
		
		update.execute(addArrayServer);
		
		
	}

	private String[][] getDifference(String[][] smallTuple, String[][] bigTuple) {
		
		// Here we have to find tuple by bigTuple - smallTuple
		// Elements in bigTuple that not in smallTuple has to be returned
		
		//if bigtuple does not have anything then nothing is to be returned
		if(bigTuple==null) {
			Log.d(TAG, "inside the getdifference and returning the null");
			return null;
		}
		
		// if smalltuple is null then return everything from bigtuple
		if(smallTuple==null) {
			Log.d(TAG, "inside the getdifference and returning the bigtple");
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
		Log.d(TAG, "inside the getdifference and returning the retarr");
		return retarr;
	}

	private void fetch(String[][] clientArray) {
		
		AsyncTaskList atl = new AsyncTaskList(this);
        atl.execute(clientArray);
		
		
	}

	private String[][] getDoubleArray(String[] array) {
		String oneline="";
		int dblen=array.length;
		
		String[][] dbarr =new String[dblen][11];
		
		for(int ud=0;ud<dblen;ud++) {
			oneline = array[ud];
			
			int c=0;
		       while(c<11) {
		           int i= oneline.indexOf(':');
		           String st = oneline.substring(0,i);
		           dbarr[ud][c] = st;
		           oneline= oneline.substring(i+1);
		           c++;
		        }
		}
		
		return dbarr;
	}

	/**
     * Sets the screen content based on the screen id.
     */
    private void setScreenContent(int screenId) {
        setContentView(screenId);
        switch (screenId) {
            case R.layout.hello_world:
                setHelloWorldScreenContent();
                break;
        }
    }

	public String[][] getServerArr() {
		Log.d(TAG, "inside the get serverarr");
		return serverArr;
	}

	

	public void setServerArr(String[][] serverArr2) {
		Log.d(TAG, "inside the set serverarr");
		this.serverArr = serverArr;
		
	}
	
	

	public void updateText(String text) {
		final TextView helloWorld = (TextView) findViewById(R.id.hello_world);
    	helloWorld.setText(text);
		
	}
	
	void makeClickable(boolean v) {
		Button sayFire = (Button) findViewById(R.id.say_fire);
		sayFire.setClickable(v);
	}
	
	void sendResult(String[] array) {
		Intent i = new Intent();
		i.putExtra("array", array);
		setResult(0,i);
		finish();
	}
}
