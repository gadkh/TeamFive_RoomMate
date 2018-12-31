package com.example.gadkh.roommate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.gadkh.roommate.BL.Task;
import com.example.gadkh.roommate.BL.TasksAdapter;
import com.example.gadkh.roommate.BL.TasksWithoutAssignedAdapter;

import java.util.ArrayList;

public class GroceriesListFragment extends Fragment {
    private ListView tasksList;
    private ArrayList<Task> tasks = new ArrayList<>();
    private TasksWithoutAssignedAdapter tasksAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_item_list, container, false);
        tasksList = view.findViewById(R.id.taskList);
        tasksAdapter = new TasksWithoutAssignedAdapter(view.getContext(), tasks);
        tasksList.setAdapter(tasksAdapter);

        return view;

    }

    public void showTable(ArrayList<Task> tasks) {
        this.tasks.clear();
        if (!tasks.isEmpty()) {
            this.tasks.addAll(tasks);
        }
        if (tasksAdapter != null) {
            tasksAdapter.notifyDataSetChanged();
        }
    }
}
