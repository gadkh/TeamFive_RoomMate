package com.example.gadkh.roommate.Activities;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gadkh.roommate.BL.User;
import com.example.gadkh.roommate.Fragments.LogIn_Fragment;
import com.example.gadkh.roommate.Fragments.SignUp_Fragment;
import com.example.gadkh.roommate.Interfces.ICommunicationFragmentToActivity;
import com.example.gadkh.roommate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements ICommunicationFragmentToActivity {
    private LogIn_Fragment logIn_Fragment;
    private SignUp_Fragment signUpFragment;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;
    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logIn_Fragment = new LogIn_Fragment();
        signUpFragment = new SignUp_Fragment();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.placeholder,logIn_Fragment);
        ft.commit();
    }

    @Override
    public void backFromJoinUsFragment(String mail,String password) {
        signUpNewUserWithEmailPass(mail,password);
    }
    @Override
    public void backFromDetailsFragment(User user) {
        writeNewUser(user);
    }

    private void writeNewUser(User user) {
        myRef.child("Users").child(user.getUser_id()).setValue(user);
    }

    public void initFireBase() {
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
    }

    private void signUpNewUserWithEmailPass(String mail, String password) {
        initFireBase();
        firebaseAuth.createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Sign up has Failed, no internet connection",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
