package ir.maktab.features.usermanagement.usecase;

import ir.maktab.entities.User;

public interface SignIn {
    User signIn(String username , String password);
}
