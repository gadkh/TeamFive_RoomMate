package com.example.gadkh.roommate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gadkh.roommate.BL.MyEvent;
import com.example.gadkh.roommate.BL.Task;
import com.example.gadkh.roommate.BL.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TaskRequestsActivity extends AppCompatActivity {

    private Button btnAddItem;
    private EditText etNewItem;
    private TaskListFragment taskListFragment;
    private ArrayList<Task> tasks;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;
    private String user_id;
    private User FBUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_requests);
        // ADD HERE
        taskListFragment = new TaskListFragment();
        setTaskListFragment();
        etNewItem = findViewById(R.id.etNewItem);
        Intent i = getIntent();
        user_id = i.getStringExtra("message");
        initFireBase();
        initButtons();

    }

    public void initFireBase() {
        firebaseAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();

        myRef.child("Users").child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    FBUser = dataSnapshot.getValue(User.class);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void setTaskListFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, taskListFragment);
        ft.commit();
        tasks = new ArrayList<>();
        taskListFragment.showTable(tasks);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    private void initButtons() {
        btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasks.add(new Task(etNewItem.getText().toString(), false,FBUser));
                taskListFragment.showTable(tasks);
            }
        });
    }

}
