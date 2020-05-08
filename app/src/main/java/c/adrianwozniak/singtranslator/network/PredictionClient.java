package c.adrianwozniak.singtranslator.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import c.adrianwozniak.singtranslator.util.ImageFileAdapter;
import c.adrianwozniak.singtranslator.util.JSONAdapter;
import c.adrianwozniak.singtranslator.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PredictionClient extends AsyncTask<String, Void, String> {

    private static final String TAG = "PredictionClient";

    public ICustomVisionReciver customVisionReciver = null;
    private Context context;

    private Bitmap bitmap;

    public PredictionClient(Context context, Bitmap bitmap){
        this.context = context;
        this.bitmap = bitmap;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            return hitPredictionEndpoint(strings[0]);
        }catch (Exception e){
            Log.e(TAG, "doInBackground: " + e.getMessage(), e );
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result != null){
            customVisionReciver.customVisionReciver(result);
        }
    }

    private final String hitPredictionEndpoint(String url) throws IOException {

        MediaType MEDIA_TYPE = MediaType.get("image/png");

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", ImageFileAdapter.getFilePath(), RequestBody.create(MEDIA_TYPE, ImageFileAdapter.returnFileFrom(bitmap)))
                .build();

        Request request = new Request.Builder()
                .header("Prediction-Key", context.getResources().getString(R.string.PredictionKey))
                .header("Content-Type", "application/octet-stream")
                .header("publishedName", context.getResources().getString(R.string.PredictionIterationName))
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        String predictionResultJSON = response.body().string();
        String predictionResult = JSONAdapter.getDataFrom(predictionResultJSON, "tagName");

        Log.d(TAG, "hitPredictionEndpoint: " + predictionResultJSON);

        if (!response.isSuccessful()) {
            throw new IOException(TAG +" response failure " + response);
        }

        return predictionResult;
    }
}
