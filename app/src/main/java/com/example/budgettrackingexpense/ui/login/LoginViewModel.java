package com.example.budgettrackingexpense.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;
import android.widget.Toast;

import com.example.budgettrackingexpense.data.LoginRepository;
import com.example.budgettrackingexpense.data.Result;
import com.example.budgettrackingexpense.data.model.LoggedInUser;
import com.example.budgettrackingexpense.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginViewModel extends ViewModel {
    FirebaseAuth mAuth;
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            mAuth.signInWithEmailAndPassword(username , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        loginFormState.setValue(new LoginFormState(true));
                    }
                    else
                    {
                        loginFormState.setValue(new LoginFormState(false));
                    }
                }
            });

        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {

        if(username == null)
        {
            return false;
        }
        if (username.contains("@"))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
       // return password != null && password.trim().length() > 5;
        if(password == null)
        {
            return false;
        }
        else if(password.trim().length()>=5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}