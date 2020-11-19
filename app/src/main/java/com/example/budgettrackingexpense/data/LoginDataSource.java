package com.example.budgettrackingexpense.data;

import android.content.Intent;

import com.example.budgettrackingexpense.MainActivity;
import com.example.budgettrackingexpense.data.model.LoggedInUser;
import com.example.budgettrackingexpense.ui.login.LoginActivity;

import java.io.IOException;

import static androidx.core.content.ContextCompat.startActivity;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication

        }
    }
