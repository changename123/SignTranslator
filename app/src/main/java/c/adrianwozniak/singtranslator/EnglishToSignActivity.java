package c.adrianwozniak.singtranslator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class EnglishToSignActivity extends AppCompatActivity {

    private String wordToTranslate = "";
    private EditText wordToTranslateEditText;
    private LinearLayout signImageScroll;
    private LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_to_sign);

        new SignAlphabetImagesMap();

        inflater = LayoutInflater.from(this);

        signImageScroll = findViewById(R.id.signImagesLinearLayout);

        wordToTranslateEditText = findViewById(R.id.wordToTranslateEditText);
        wordToTranslateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getWordToTranslate();
                addImages();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getWordToTranslate(){
        wordToTranslate = wordToTranslateEditText.getText().toString().toLowerCase();
    }

    public void addImages() {

        char[] charArray = wordToTranslate.toCharArray();

        removeAllImagesFromScrollView();

        if(wordToTranslate.length()>0){
            for (int i = 0; i < charArray.length; i++) {
                View view = inflater.inflate(R.layout.image_scoll_english_to_sign_layout, signImageScroll, false);

                addImageToImageViewFromResource(getSignImageResourceByChar(charArray[i]), view);

                signImageScroll.addView(view);
            }
        }
    }

    public void removeAllImagesFromScrollView(){
        signImageScroll.removeAllViews();
    }

    public int getSignImageResourceByChar(char c){
        return SignAlphabetImagesMap.getMap().get(getStringFromChar(c));
    }

    public void addImageToImageViewFromResource(int resource, View view){
        ImageView imageView = view.findViewById(R.id.signImageView);
        imageView.setImageResource(resource);
    }

    public String getStringFromChar(char c){
        return Character.toString(c);
    }

}
