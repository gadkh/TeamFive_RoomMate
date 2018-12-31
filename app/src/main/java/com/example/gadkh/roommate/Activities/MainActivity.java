package com.example.gadkh.roommate.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gadkh.roommate.BL.User;
import com.example.gadkh.roommate.Fragments.LogIn_Fragment;
import com.example.gadkh.roommate.Fragments.SignUp_Details_Fragment;
import com.example.gadkh.roommate.Fragments.SignUp_Fragment;
import com.example.gadkh.roommate.Interfces.ICommunicationFragmentToActivity;
import com.example.gadkh.roommate.NavigationActivity;
import com.example.gadkh.roommate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements ICommunicationFragmentToActivity {
    private LogIn_Fragment logIn_Fragment;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;
    private FirebaseDatabase mFirebaseDatabase;
    private  FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
        }

        logIn_Fragment = new LogIn_Fragment();
        setFragment(logIn_Fragment);
    }

    @Override
    public void backFromJoinUsFragment(String mail,String password) {
        signUpNewUserWithEmailPass(mail,password);
    }
    @Override
    public void backFromDetailsFragment(User user) {
        writeNewUser(user);
        setFragment(logIn_Fragment);
    }

    private void writeNewUser(User user) {
        myRef.child("Users").child(user.getUser_id()).setValue(user);
    }

    public void initFireBase() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
    }
    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.replace(R.id.placeholder,fragment);
        ft.commit();
    }
    private void signUpNewUserWithEmailPass(String mail, String password) {
        initFireBase();
        firebaseAuth.createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            SignUp_Details_Fragment signUp_details_fragment = new SignUp_Details_Fragment();
                            setFragment(signUp_details_fragment);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Sign up has Failed, no internet connection",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
