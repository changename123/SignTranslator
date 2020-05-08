package c.adrianwozniak.singtranslator.util;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class FirstOpenControler extends AppCompatActivity {

    private static SharedPreferences isApplicationFirstOpen;

    public FirstOpenControler(SharedPreferences sharedPreferences){
        isApplicationFirstOpen = sharedPreferences;
    }

    public void setAppStatusFirstOpenTrue(){
        isApplicationFirstOpen.edit().putBoolean("FirstOpen", true).commit();
    }

    public boolean isAppStatusFirstOpen(){
        return isApplicationFirstOpen.getBoolean("FirstOpen", false);
    }

}
