package c.adrianwozniak.singtranslator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static boolean TURN_OFF_FIRST_OPEN_BLOCKADE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ifFirstAppOpenShowTutorial();
    }

    public void exitButton_OnClick(View view) {
        finish();
    }
    public void signToEnglishButton_OnClick(View view) {
        startSignToEnglishActivity();
    }
    public void englishToSignButton_OnClick(View view) {
        startEnglishToSignActivity();
    }
    public void showTutorialButton_OnClick(View view) {
        startTutorialActivity();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            new HideSystemUI().hide(this);
        }
    }

    private void ifFirstAppOpenShowTutorial(){
        SharedPreferences sharedPreferences
                = this.getSharedPreferences("c.adrianwozniak.singtranslator", Context.MODE_PRIVATE);

        FirstOpenControler firstOpenControler = new FirstOpenControler(sharedPreferences);

        if(firstOpenControler.isAppStatusFirstOpen()== false){
            firstOpenControler.setAppStatusFirstOpenTrue();
            startTutorialActivity();
        }else if (TURN_OFF_FIRST_OPEN_BLOCKADE){
            startTutorialActivity();
            TURN_OFF_FIRST_OPEN_BLOCKADE = false;
        }
    }

    private void startTutorialActivity(){
        startActivity(new Intent(this, TutorialActivity.class));
    }
    private void startSignToEnglishActivity(){
        startActivity(new Intent(this.getApplicationContext(), SignToEnglishActivity.class));
    }
    private void startEnglishToSignActivity(){
        startActivity(new Intent(this,EnglishToSignActivity.class));
    }
}
