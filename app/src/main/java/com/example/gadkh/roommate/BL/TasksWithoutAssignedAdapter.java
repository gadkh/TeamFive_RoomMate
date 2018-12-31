package com.example.gadkh.roommate.BL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.gadkh.roommate.R;

import java.util.ArrayList;

public class TasksWithoutAssignedAdapter extends ArrayAdapter<Task> {
    private final LayoutInflater inflater;

    public TasksWithoutAssignedAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.task_item, parent, false);
        }
        // Lookup view for data population

        // Populate the data into the template view using the data object
        CheckBox isDoneCheckbox = (CheckBox) convertView.findViewById(R.id.isDone);
        isDoneCheckbox.setChecked(task.isDone());
        isDoneCheckbox.setText(task.getTaskDetails());
        // Return the completed view to render on screen
        return convertView;
    }
}
