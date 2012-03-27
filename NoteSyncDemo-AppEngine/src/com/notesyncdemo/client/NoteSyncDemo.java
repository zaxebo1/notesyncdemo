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
package com.notesyncdemo.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.notesyncdemo.shared.NoteSyncDemoRequest;
import com.notesyncdemo.shared.NoteSyncDemoRequestFactory;
import com.notesyncdemo.shared.TaskProxy;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NoteSyncDemo implements EntryPoint {
	NoteSyncDemoRequestFactory requestFactory= GWT
		      .create(NoteSyncDemoRequestFactory.class);
	
	  private List<TaskProxy> tasksList;

	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private TextBox newNoteTextBox = new TextBox();
	private Button addNoteButton = new Button("Add");
	private ScrollPanel scrollp = new ScrollPanel();
	   // Create a cell to render each value.
    TextCell textCell = new TextCell();

    // Create a CellList that uses the cell.
    CellList<String> cellList = new CellList<String>(textCell);
    
    private static final List<String> DAYS = Arrays.asList("Sunday", "Monday",
		      "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	 
	  /**
    NoteSyncDemoWidget widget = new NoteSyncDemoWidget();
    RootPanel.get().add(widget);
    */
	  Logger logger = Logger.getLogger("NameOfYourLogger");
	    logger.log(Level.SEVERE, "this message in onModule()");
	  
	
	  
	 
	  
	  //create list<string> and store it.
	  
	  
	  
	// Add a selection model to handle user selection.
	    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
	    cellList.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	        String selected = selectionModel.getSelectedObject();
	        if (selected != null) {
	          Window.alert("You selected: " + selected);
	        }
	      }
	    });
	    
	    /**
	    	List<String> taskTitles =  retrieveTasks();
	    	
	    	cellList.setRowCount(taskTitles.size(), true);
	 	   
		    cellList.setRowData(0, taskTitles);

		    */
	   
	    
	     
	    
	  // Do the add note and add button
	  addPanel.add(newNoteTextBox);
	  addPanel.add(addNoteButton);
	  
	// assemble the main panel
	  scrollp.add(cellList);
	  
	  mainPanel.add(addPanel);  
	  mainPanel.add(scrollp);
	  
	  
	// Associate the Main panel with the HTML host page.
	    RootPanel.get("notesList").add(mainPanel);
	    
	    
	    
	    // Move cursor focus to the input box.
	    newNoteTextBox.setFocus(true);
	    
	    //Schedule the notes
	    Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
	        public boolean execute() {
	        	tasksList= populateCellListandReturnTask(cellList);
	          return true;
	        }
	      }, 3000);
	    
	 // Listen for mouse events on the Add button.
	    addNoteButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        addNote();
	      }
	    });
	    
	    // on key press down
	    newNoteTextBox.addKeyPressHandler(new KeyPressHandler() {
	        public void onKeyPress(KeyPressEvent event) {
	          if (event.getCharCode() == KeyCodes.KEY_ENTER) {
	            addNote();
	          }
	        }
	      });

		
	    
  }// on module is finished
  
	    
	    private void addNote() {
	    	final Logger logger = Logger.getLogger("NameOfYourLogger");
		    logger.log(Level.SEVERE, "inside addNOte Button");
		    
	    	final String note = newNoteTextBox.getText();
	    	newNoteTextBox.setFocus(true);
	    	newNoteTextBox.setText("");
	    	
	    	if(note.length()==0) {
	    		Window.alert("Please enter some text");
	    	}
	    	
	    	
	    	long time = System.currentTimeMillis();
	    	String stime = Long.toString(time);
	    	logger.log(Level.SEVERE, "time is: "+stime);
	    	
	    	NoteSyncDemoRequestFactory factory = GWT.create(NoteSyncDemoRequestFactory.class);
	    	EventBus eventBus = new SimpleEventBus();
	    	factory.initialize(eventBus);
	    	NoteSyncDemoRequest request = factory.taskRequest();
	        
	        TaskProxy task = request.create(TaskProxy.class);
	        
	        task.setTitle(note);
        	task.setNote(note);
        	task.setCreatedDate(stime);
        	task.setModifiedDate(stime);
        	task.setTags("null");
        	task.setEncrypted("null");
        	task.setTheme("null");
        	task.setSelectionStart("1");
        	task.setSelectionEnd("1");
        	task.setScroll_position("0");
	        
        	try {
        		request.updateTask(task).fire();
        	}
        	catch (UmbrellaException e) {
				Throwable b = e.getCause();
				logger.log(Level.SEVERE,"Umbrella exception occured"+b.getMessage());
				 b.printStackTrace();
			}
        	
        	tasksList= populateCellListandReturnTask(cellList);
        	//request.updateTask(task);
        	//request.fire(); // should be called only once for a request.
	    	
        	
	    }
	    
  private List<TaskProxy> populateCellListandReturnTask(CellList<String> cellList2) {
		final Logger logger = Logger.getLogger("NameOfYourLogger");
	    logger.log(Level.SEVERE, "inside retrieveTasks");
	    
	    final List<TaskProxy> list = new ArrayList<TaskProxy>();
	     EventBus eventBus = new SimpleEventBus();
	     
		NoteSyncDemoRequestFactory requestFactory1= GWT.create(NoteSyncDemoRequestFactory.class);
			      
		
		requestFactory1.initialize(eventBus);
		 //NoteSyncDemoRequest request = requestFactory1.taskRequest();
		    requestFactory1.taskRequest().queryTasks().fire(new Receiver<List<TaskProxy>>() {
		      @Override
		      public void onSuccess(List<TaskProxy> tasks) {
		        if (tasks.size() > 0) {
		        	logger.log(Level.SEVERE, "retrieval of tasks more than 0");
		        }
		        else {
		        	logger.log(Level.SEVERE, "retrieval 000");
		        	logger.log(Level.SEVERE, ""+tasks.size());
		        }
		        
		        list.addAll(tasks);
		        logger.log(Level.SEVERE, "list size inside success"+list.size());

		        List<String> notes=new ArrayList<String>();
			    
				   
		    	logger.log(Level.SEVERE, "going to populate celllist"+list.size());
		    	
		  	  
		  	  for(TaskProxy task: list) {
		  		  notes.add(task.getTitle());
		  	  }
		  	      logger.log(Level.SEVERE, "size of notes(string) is:"+notes.size());
		  	    
		  	    java.util.Collections.reverse(notes);
		  	    cellList.setRowCount(notes.size(), true);
			 	   
			    cellList.setRowData(0, notes);
			    cellList.redraw(); 
		      }
		      
		      
		    });
		   
		    
		    logger.log(Level.SEVERE, "outside of success");
		    
		    
		    return list;
		  }




}
