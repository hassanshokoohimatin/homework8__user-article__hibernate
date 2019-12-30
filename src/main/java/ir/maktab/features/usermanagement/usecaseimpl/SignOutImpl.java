package ir.maktab.features.usermanagement.usecaseimpl;

import ir.maktab.features.usermanagement.usecase.SignOut;
import ir.maktab.share.AuthenticationService;

public class SignOutImpl implements SignOut {
    @Override
    public void signOut() {
        AuthenticationService.getInstance().setLoginUser(null);
    }
}
