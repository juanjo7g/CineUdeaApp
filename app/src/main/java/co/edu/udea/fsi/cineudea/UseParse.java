package co.edu.udea.fsi.cineudea;


import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Juan on 08/11/2015.
 */
public class UseParse extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(getApplicationContext(), "VeDONEfElWYGb5Uh7ULLUlZQCS04d938X11WlQ89", "iBXohljGeWGFmAOVufhNNCNvQKxu4TWmVCUlpTHk");
//        PushService.setDefaultPushCallback(getApplicationContext(), MainActivity.class);
        ParseInstallation.getCurrentInstallation().saveEventually();
    }
}
