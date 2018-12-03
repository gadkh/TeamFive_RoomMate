package com.example.gadkh.roommate;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.gadkh.roommate.BL.Task;
import com.example.gadkh.roommate.BL.User;

import java.util.ArrayList;

public class TaskRequestsActivity extends AppCompatActivity {

    private Button btnAddItem;
    private EditText etNewItem;
    private TaskListFragment taskListFragment;
    private ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_requests);
        // ADD HERE
        taskListFragment = new TaskListFragment();
        setTaskListFragment();
        etNewItem = findViewById(R.id.etNewItem);
        initButtons();
    }

    private void setTaskListFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, taskListFragment);
        ft.commit();
        tasks = new ArrayList<>();
        tasks.add(new Task("Test", false, new User()));
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
                tasks.add(new Task(etNewItem.getText().toString(), false, new User()));
            }
        });
    }

}
