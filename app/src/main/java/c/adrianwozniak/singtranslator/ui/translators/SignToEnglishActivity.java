package c.adrianwozniak.singtranslator.ui.translators;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import c.adrianwozniak.singtranslator.util.HideSystemUI;
import c.adrianwozniak.singtranslator.network.ICustomVisionReciver;
import c.adrianwozniak.singtranslator.network.PredictionClient;
import c.adrianwozniak.singtranslator.R;


public class SignToEnglishActivity extends AppCompatActivity implements ICustomVisionReciver {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private static final String TAG = "SignToEnglishActivity";

    private Button captureImage_Button;
    private ImageView sign_ImageView;
    private TextView signResult_TextView;
    private TextView signTitle_TextView;

    private String translatedWord = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_to_english);

        sign_ImageView = findViewById(R.id.signImageView);
        signResult_TextView = findViewById(R.id.singResultTextView);
        signTitle_TextView = findViewById(R.id.signTitleTextView);
        captureImage_Button = findViewById(R.id.signCaptureImageButton);

        Log.d("DEBUG", "start activity");

        changeImageCaptureButtonText();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult:");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bitmap bitmapCapturedFromCamera = getImageBitmapFromCamera(data.getExtras());
            setSignImage(bitmapCapturedFromCamera);
            setTitleText("Result is:");

            try {
                String endpointURL = getResources().getString(R.string.PredictionEndpoint);
                PredictionClient client = new PredictionClient(this, bitmapCapturedFromCamera);
                client.customVisionReciver = this;
                client.execute(endpointURL);
            } catch (Exception e) {
                Log.e(TAG, "onActivityResult: " + e.getMessage(), e);
                e.printStackTrace();
            }


        }
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            new HideSystemUI().hide(this);
        }
    }

    public void captureImageButton_OnClick(View view) {
        startCamera();
    }

    public void newWordButton_OnClick(View view) {
        cleanTranslatedWord();
    }

    public void removeLastCharButton_OnClick(View view) {
        try {
            if (translatedWord.length() >= 1) {
                removeLastCharFromTranslatedWord(translatedWord);
            } else {
                finish();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startCamera() {
        try{
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", true);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.i("DEBUG", e.toString());
        }
    }

    private void setTitleText(String text){
        signTitle_TextView.setText(text);
    }

    private void setSignResultText(String text){
        signResult_TextView.setText(text);
    }

    private void setSignImage(Bitmap imageBitmap){
        sign_ImageView.setImageBitmap(imageBitmap);
    }

    private Bitmap getImageBitmapFromCamera(Bundle extras){
        return (Bitmap) extras.get("data");
    }

    private void cleanTranslatedWord(){
        translatedWord="";
        setTitleText("You must take photo for resoult");
        setSignResultText(translatedWord);
    }

    private void addResultToTranslatedWord(String s){
        translatedWord+=s;
        setSignResultText(translatedWord);
    }

    private void changeImageCaptureButtonText(){
        if(translatedWord.length()>0){
            captureImage_Button.setText("TAKE NEXT PHOTO");
        }else{
            captureImage_Button.setText("TAKE FIRST PHOTO");
        }
    }

    private void removeLastCharFromTranslatedWord(String translatedWord) {
            this.translatedWord = translatedWord.substring(0, translatedWord.length() - 1);
            setSignResultText(this.translatedWord);
    }

    @Override
    public void customVisionReciver(String string) {
        addResultToTranslatedWord(string);
        changeImageCaptureButtonText();
    }
}
