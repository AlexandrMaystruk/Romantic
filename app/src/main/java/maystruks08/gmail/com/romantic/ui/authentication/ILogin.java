package maystruks08.gmail.com.romantic.ui.authentication;

import android.app.Activity;

public interface ILogin {

    interface View {
        void onLoginSuccess(String message);

        void onLoginFailure(String message);
    }

    interface Presenter {

        void login(Activity activity, String email, String password);
    }

    interface Model {
        void performFirebaseLogin(Activity activity, String email, String password);
    }

    interface OnLoginListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
