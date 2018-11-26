package com.example.gadkh.roommate.Interfces;
import com.example.gadkh.roommate.BL.User;

public interface ICommunicationFragmentToActivity {
    public void backFromJoinUsFragment(String mail,String password);
    public void backFromDetailsFragment(User user);
}
