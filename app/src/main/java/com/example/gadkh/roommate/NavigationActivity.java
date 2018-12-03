package com.example.gadkh.roommate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NavigationActivity extends AppCompatActivity {
    private Button guestScheduleBtn;
    private Button groceriesBtn;
    private Button tasksRequestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        setAllButtons();
    }

    private void setAllButtons() {
        setGuestBtn();
        setTasksListBtn();
        setGroceriesBtn();
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
                startActivity(i);
            }
        });
    }
}
