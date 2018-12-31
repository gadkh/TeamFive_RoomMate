package com.example.gadkh.roommate;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gadkh.roommate.Activities.MainActivity;
import com.example.gadkh.roommate.Fragments.LogIn_Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NavigationActivity extends AppCompatActivity {
    private Button guestScheduleBtn;
    private Button groceriesBtn;
    private Button tasksRequestBtn;
    private Button logOutBtn;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initFireBase();
        setAllButtons();
    }

    public void initFireBase(){
        firebaseAuth = FirebaseAuth.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        user_id = user.getUid();
    }

    private void setAllButtons() {
        setGuestBtn();
        setTasksListBtn();
        setGroceriesBtn();
        setlogOutBtn();
    }

    private void setlogOutBtn() {
        logOutBtn = (Button) findViewById(R.id.logOutId);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startAct();
            }
        });
    }

    private void startAct() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void setGroceriesBtn() {
        groceriesBtn = (Button) findViewById(R.id.groceriesBtn);
        groceriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setTasksListBtn() {
        tasksRequestBtn = findViewById(R.id.tasksRequestBtn);
        tasksRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), TaskRequestsActivity.class);
                i.putExtra("message",user_id);
                startActivity(i);
            }
        });
    }

    private void setGuestBtn() {
        guestScheduleBtn = (Button) findViewById(R.id.guestsBtn);
        guestScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), GuestsSchedulerActivity.class);
                i.putExtra("message",user_id);
                startActivity(i);
            }
        });
    }
}
