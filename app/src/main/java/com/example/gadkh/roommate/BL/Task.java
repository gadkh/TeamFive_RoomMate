package com.example.gadkh.roommate.BL;

public class Task {
    private String taskDetails;
    private boolean isDone;
    private User assignedUser;

    public Task(String taskDetails, boolean isDone, User assignedUser) {
        this.taskDetails = taskDetails;
        this.isDone = isDone;
        this.assignedUser = assignedUser;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }
}
