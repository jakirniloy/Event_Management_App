package me.jakirniloy.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class event_information extends AppCompatActivity {
    private EditText editname,editplace,editdate,editcapacity,editBudget,editEmail,editphone,editDescrip;
    // declare existingKey variable
    private String existingKey = null;
    private RadioButton Indoor,Outdoor,Online;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_information);
        editname= findViewById(R.id.etName);
        editplace = findViewById(R.id.etPlace);
        editdate = findViewById(R.id.etDateTime);
        editcapacity =findViewById(R.id.etCapacity);
        editBudget = findViewById(R.id.etBudget);
        editEmail = findViewById(R.id.etEmail);
        editphone = findViewById(R.id.etPhone);
        editDescrip = findViewById(R.id.etDescription);
        Indoor = findViewById(R.id.rdIndoor);
        Outdoor= findViewById(R.id.rdOutdoor);
        Online = findViewById(R.id.rdOnline);

        findViewById(R.id.btncancel).setOnClickListener(view -> finish());
        findViewById(R.id.btnsave).setOnClickListener(view -> save());
        // check in intent if there is any key set in putExtra
        Intent i = getIntent();
        existingKey = i.getStringExtra("EventKey");
        if(existingKey != null && !existingKey.isEmpty()) {
            initializeFormWithExistingData(existingKey);
        }
    }

    private void initializeFormWithExistingData(String eventKey){

        String value = Util.getInstance().getValueByKey(this, eventKey);
        System.out.println("Key: " + eventKey + "; Value: "+value);

        if(value != null) {
            String[] fieldValues = value.split("-::-");
            String name = fieldValues[0];
            String dateTime = fieldValues[1];
            String eventPlace = fieldValues[2];
            String eventType = fieldValues[3];
            String eventCapacity =fieldValues[4];

            String eventBuget =fieldValues[5];
            String Email =fieldValues[6];
            String phone =fieldValues[7];
            String description =fieldValues[8];
            editname.setText(name);
            editplace.setText(eventPlace);
            editdate.setText(dateTime);
            editcapacity.setText(eventCapacity);
            editBudget.setText(eventBuget);
            editEmail.setText(Email);
            editphone.setText(phone);
            editDescrip.setText(description);

            if(eventType.equals("Indoor")){
                Indoor.setChecked(true);
            }else if (eventType.equals("Outdoor")){
                Outdoor.setChecked(true);
            }else if(eventType.equals("Online")){
                Online.setChecked(true);
            }
        }
    }

    private void save() {


        String name = editname.getText().toString().trim();



        String Place = editplace.getText().toString().trim();

        String DateTime = editdate.getText().toString().trim();

        String Capacity =editcapacity.getText().toString().trim();
        int i = Integer.parseInt(Capacity.trim());
        String Budget =editBudget.getText().toString().trim();
        int bud = Integer.parseInt(Budget.trim());
        String Email =editEmail.getText().toString().trim();



        String phone =editphone.getText().toString().trim();

        String Description =editDescrip.getText().toString().trim();
        String EventType = " ";
        boolean isIndoorChecked =  Indoor.isChecked();
        boolean isOutdoorChecked =  Outdoor.isChecked();
        boolean isonlineChecked =  Online.isChecked();


        String errors = "";
        if(isIndoorChecked){
            EventType = "Indoor";
        }else if(isOutdoorChecked){
            EventType = "Outdoor";
        }
        else if (isonlineChecked){
            EventType = "Online";
        }else {
            errors += "NO Event type select yet\n";
        }

        if(phone.length() < 8 || phone.length() > 12){
            errors += "Phone Number isn't valid\n";
        }
        if(i < 0){
            errors += "Capacity can't be negative\n";
        }
        if(bud < 0){
            errors += "Budget can't be negative\n";
        }

        if (errors == "") {
//            System.out.println("Event Name:"+name);
//            System.out.println("Place:"+Place);
//            System.out.println("Date and Time:"+DateTime);
//            System.out.println("Capacity:"+Capacity);
//            System.out.println("Budget:"+Budget);
//            System.out.println("Email:"+Email);
//            System.out.println("Phone:"+phone);
//            System.out.println("Description:"+Description);
//
//            System.out.println(isIndoorChecked);
//            System.out.println(isOutdoorChecked);
//            System.out.println(isOnlineChecked);
            String key  = name+"-::-"+DateTime;
            if(existingKey != null){
                key = existingKey;
            }
            String value = name+"-::-"+DateTime+"-::-"+Place+"-::-"+EventType+"-::-"+Capacity+"-::-"+Budget+"-::-"+Email+"-::-"+phone+"-::-"+Description;
            Util.getInstance().setKeyValue(this,key,value);
            Toast.makeText(this, "Event Information has been Saved Successfully", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this,"Error: " + errors , Toast.LENGTH_SHORT).show();
            //System.out.println("Error: " + errors);
        }

        System.out.println(" ");

    }

//    public void onStart() {
//
//        super.onStart();
//        System.out.println("@Event_On Start");
//    }
//    @Override
//    public void onResume() {
//
//        super.onResume();
//        System.out.println("@Event__On Resume");
//    }
//    @Override
//    public void onPause() {
//
//        super.onPause();
//
//        System.out.println("@Event__On Pause");
//    }
//    @Override
//    public void onRestart() {
//
//        super.onRestart();
//        System.out.println("@Event__On Restart");
//    }
}