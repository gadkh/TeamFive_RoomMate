package com.example.gadkh.roommate;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gadkh.roommate.BL.MyEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Map;

public class GuestsSchedulerActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private Dialog myDialog;
    private TextView dialog_date;
    private EditText dialog_descriptopn;
    private Button dialog_save_btn;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private DatabaseReference eventRef;
    private String date;
    private String dateFormat;
    private String des;
    private MyEvent event;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guests_scheduler);

        Intent i = getIntent();
        user_id = i.getStringExtra("message");

        initFireBase();
        setPicker();
        initDialog();
    }

    private void initDialog() {
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.event_on_calendar);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog_date = myDialog.findViewById(R.id.dateId);
        dialog_descriptopn = myDialog.findViewById(R.id.eventDescription);
        dialog_save_btn = myDialog.findViewById(R.id.saveBtn);

        dialog_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event = new MyEvent(dialog_descriptopn.getText().toString(), user_id, date);
                saveEvent(event);
            }
        });
    }

    public void initFireBase() {
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();

       // myRef.addValueEventListener(valueEventListener);
    }

    //    private void setQuery(String eventId) {
//        Query query = eventRef.orderByChild("eventId").equalTo(eventId);
//        query.addListenerForSingleValueEvent(valueEventListener);
//    }
    private void saveEvent(MyEvent event) {
        myRef.child("Events").child(date).setValue(event);
        myDialog.cancel();
    }

    private void setPicker() {
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                dialog_descriptopn.setText("");

                i1++;
                dateFormat = i2 + "/" + i1 + "/" + i;
                date = (i2 + "" + i1 + "" + i);

                dialog_date.setText(dateFormat);
                myRef.child("Events").child(date).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            MyEvent myEvent = dataSnapshot.getValue(MyEvent.class);
                            des = myEvent.getDescription();
                            dialog_descriptopn.setText(des);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                myDialog.show();
            }
        });
    }
}

//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                String des = dataSnapshot.child("description").getValue().toString();
//
////                        eventRef = FirebaseDatabase.getInstance().getReference().child("Events").child(date);
//               // MyEvent myEvent = dataSnapshot.getValue(MyEvent.class);
//               // String des = myEvent.getDescription();
//
//                try {
//                    dialog_descriptopn.setText(des);
//                    System.out.println(des);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });



