package c.adrianwozniak.singtranslator;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    public TutorialAdapter(Context context){
        this.context = context;
    }

    private int[] slider_images = {
            R.drawable.tutorial_question,
            R.drawable.tutorial_camera,
            R.drawable.tutorial_idea
    };

    private String[] slide_headings = {
            "What is it?",
            "How to use?",
            "Let's start!"
    };
    private String[] slide_descriptions = {
            "This app will help you to communicate with deaf people, by using image recognition.",
            "Ask your interlocutor to spell work you want to translate, make photo each sign " +
                    "using this app, we will translate it for you!",
            "Click finish and try all our features!"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.tutorial_slider_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView slideHeading = view.findViewById(R.id.slide_head);
        TextView slideDescription = view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slider_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
