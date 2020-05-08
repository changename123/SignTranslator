package c.adrianwozniak.singtranslator.ui.intro;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import c.adrianwozniak.singtranslator.util.HideSystemUI;
import c.adrianwozniak.singtranslator.R;
import c.adrianwozniak.singtranslator.ui.intro.util.TutorialAdapter;


public class TutorialActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private LinearLayout dotLayout;

    private TutorialAdapter tutorialAdapter;

    private TextView[] dots;

    private Button nextButton;
    private Button backButton;

    private int currentPage = 0;
    private int startPagePosition = 0;
    private int lastPagePosition = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);


        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

        dotLayout = findViewById(R.id.dotsLayout);

        viewPager = findViewById(R.id.viewPager);

        tutorialAdapter = new TutorialAdapter(this);

        viewPager.setAdapter(tutorialAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        addDotsIndicator(startPagePosition);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(++currentPage);
                if(currentPage == lastPagePosition){
                   finish();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(--currentPage);
            }
        });
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i;
            if(i == 0){
                nextButton.setEnabled(true);
                backButton.setEnabled(false);
                backButton.setVisibility(View.INVISIBLE);

                nextButton.setText("NEXT");
                backButton.setText("");
            }else if(i == dots.length-1){
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);

                nextButton.setText("FINISH");
                backButton.setText("BACK");
            }else{
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);

                nextButton.setText("NEXT");
                backButton.setText("BACK");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public void addDotsIndicator(int posiotion){
        int totalPages = 3;

        dots = new TextView[totalPages];

        dotLayout.removeAllViews();

        for(int i = 0; i< dots.length; i++){
            dots[i] = new TextView(this.getApplicationContext());
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            dotLayout.addView(dots[i]);
        }
        if(dots.length>0){
            dots[posiotion].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
           new HideSystemUI().hide(this);
        }
    }


}
