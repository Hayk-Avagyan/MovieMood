package com.example.movie_h_b.moviemood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.movie_h_b.moviemood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private EditText emailView;
    private EditText passwordView;
    private EditText confirmPasswordView;
    private Button signIn;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        initViews(view);
        initOnClickListener();
        return view;
    }

    public void initViews(View view) {
        emailView = view.findViewById(R.id.username);
        passwordView = view.findViewById(R.id.password);
        confirmPasswordView = view.findViewById(R.id.password_confirm);
        signIn = view.findViewById(R.id.sign_in);
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void initOnClickListener() {
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                final String emailAddress = emailView.getText().toString();
                final String password = passwordView.getText().toString();
                final String confirmPassword = confirmPasswordView.getText().toString();

                String passwordPattern = getString(R.string.passwordPattern);
                String confirmPasswordPattern = getString(R.string.passwordPattern);
                String emailPattern = getString(R.string.emailPattern);

                if (emailAddress.matches(emailPattern) && password.matches(passwordPattern) &&
                        confirmPassword.matches(confirmPasswordPattern) && password.equals(confirmPassword)) {

                    createUserAccount(emailAddress, password);

                } else {
                    signIn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    showMessage(R.string.invalidEmail);
                }
            }
        });
    }

    private void showMessage(int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void updateUI() {
        assert getFragmentManager() != null;
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, BottomNavigationFragment.newInstance())
                .commit();

    }

    private void createUserAccount(String emailAddress, String password) {

        firebaseAuth.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            showMessage(R.string.accountCreated);
                            updateUI();
                        } else {
                            showMessage(R.string.accountCreationFailed);
                            task.getException().getMessage();
                            signIn.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }
}
