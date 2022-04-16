package me.jakirniloy.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//   Reference objects for handling event lists
    private ListView lvEvents;
    private ArrayList<Event> events;
    private CustomEventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("@MainActivity-onCreate()");
        Button btnCreateNew = findViewById(R.id.btncreat);


        btnCreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(MainActivity.this,event_information.class);
                startActivity(i);
            }
        });

        Button btnhistory = findViewById(R.id.btnhistory);
        btnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Invoke History here");
            }
        });
        Button btnExit= findViewById(R.id.btnexit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // initialize list-reference by ListView object defined in XML
        lvEvents = findViewById(R.id.lvEvents);
        // load events from database if there is any
        loadData();
        
      String Value = Util.getInstance().getValueByKey(this,"MID EXAM-::-14-03-2022");

      if(Value != null){
          String [] columnValues = Value.split("-::-");
      }
      else
      {
          System.out.println("Null Value");
      }

    }

    private void loadData(){
        events = new ArrayList<>();
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }
        //events = new Event[rows.getCount()];
        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String[] fieldValues = eventData.split("-::-");

            String name = fieldValues[0];
            String dateTime = fieldValues[1];
            String eventPlace = fieldValues[2];
            String eventType = fieldValues[3];
            String eventCapacity =fieldValues[4];

            String eventBuget =fieldValues[5];
            String Email =fieldValues[6];
            String phone =fieldValues[7];
            String description =fieldValues[8];


            Event e = new Event(key, name, eventPlace, dateTime,eventCapacity, eventBuget,Email, phone, description, eventType);
            events.add(e);
        }
        db.close();
        adapter = new CustomEventAdapter(this, events);
        lvEvents.setAdapter(adapter);

        // handle the click on an event-list item
        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                // String item = (String) parent.getItemAtPosition(position);
                Event e = events.get(position);
                System.out.println(position);

                Intent i = new Intent(MainActivity.this, event_information.class);
                i.putExtra("EventKey", e.key);
                startActivity(i);
            }
        });
        // handle the long-click on an event-list item
        lvEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //String message = "Do you want to delete event - "+events[position].name +" ?";
                String message = "Do you want to delete event - "+events.get(position).name +" ?";
                System.out.println(message);
                //showDialog(message, "Delete Event", events.get(position).key);
                return true;
            }
        });
    }



    @Override
    public void onStart(){
        super.onStart();
        System.out.println("@MainActivity-onStart()");
    }
    @Override
    public void onResume(){
        super.onResume();
        System.out.println("@MainActivity-onResume()");
    }
    @Override
    public void onPause(){
        super.onPause();
        System.out.println("@MainActivity-onPause()");
    }
    @Override
    public void onStop(){
        super.onStop();
        System.out.println("@MainActivity-onStop()");
        // clear the event data from memory as the page is completely hidden by now
        events.clear();
    }
    @Override
    public void onRestart(){
        super.onRestart();
        System.out.println("@MainActivity-onRestart()");
        // re-load events from database after coming back from the next page
        loadData();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("@MainActivity-onDestroy()");
    }
}