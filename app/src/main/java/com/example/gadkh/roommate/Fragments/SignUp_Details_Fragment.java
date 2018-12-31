package com.example.gadkh.roommate.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gadkh.roommate.BL.User;
import com.example.gadkh.roommate.Interfces.ICommunicationFragmentToActivity;
import com.example.gadkh.roommate.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp_Details_Fragment extends Fragment {

    private FirebaseAuth mAuth;
    private RadioGroup radio_group;
    private Button regBtn;
    private EditText et_fullName;
    private String editTextFullName;
    private RadioButton genderRadioBtn;
    private String gender;
    private User newUser;
    private LogIn_Fragment logIn_fragment;
    public ICommunicationFragmentToActivity listener;
    private View v1;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up__details_, container, false);
        logIn_fragment = new LogIn_Fragment();

        radio_group = view.findViewById(R.id.radio_group);
        regBtn = view.findViewById(R.id.regSignUpDetailsId);

        initializeAttributes(view);
        setRegBtn(view);
        this.v1 = view;
        return view;
    }

    public void listenerTrigger() {
        this.listener.backFromDetailsFragment(newUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ICommunicationFragmentToActivity) {
            //init the listener
            listener = (ICommunicationFragmentToActivity)context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    private void initializeAttributes(View view) {
        et_fullName =  view.findViewById(R.id.fullNameSignUpDetailsId);
    }

    private void setRegBtn(View view) {


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateReg(view)){
                    Toast.makeText(getActivity(), "Sign up has Failed",Toast.LENGTH_SHORT).show();
                }
                else {
                    int rgs_id = radio_group.getCheckedRadioButtonId();
                    genderRadioBtn = v1.findViewById(rgs_id);
                    if(genderRadioBtn == null){
                    }
                    else{
                    }
                    gender = genderRadioBtn.getText().toString();
                    System.out.println(mAuth.getCurrentUser().getUid() + "  , " + gender );
                    newUser = new User(editTextFullName,mAuth.getCurrentUser().getUid(), gender);
                    listenerTrigger();
                }
            }
        });
    }
    private boolean validateReg(View view) {
        boolean valid = true;
        editTextFullName = et_fullName.getText().toString().trim();


        if(editTextFullName.isEmpty()|| et_fullName.length()>20){
            et_fullName.setError("Please Enter Valid Name (Up To 20 Chars)");
            et_fullName.requestFocus();
            valid = false;
        }
        return valid;
    }


}
