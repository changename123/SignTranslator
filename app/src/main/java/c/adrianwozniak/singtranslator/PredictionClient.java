package c.adrianwozniak.singtranslator;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PredictionClient extends AsyncTask<String, Void, String> {

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
            Log.i("ENDPOINT", "HIT");

            return hitPredictionEndpoint(strings[0]);
        }catch (Exception e){
            Log.i("ENDPOINT doinb fail", e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        if(result != null){
            customVisionReciver.customVisionReciver(result);
            Log.i("ENDPOINT interface", result);
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
        Log.i("ENDPOINT request JSON: ", predictionResultJSON);
        String predictionResult = JSONAdapter.getDataFrom(predictionResultJSON, "tagName");
        Log.i("ENDPOINT request is ok", predictionResult);

        if (!response.isSuccessful()) {
            throw new IOException("ENDPOINT response fail " + response);
        }

        return predictionResult;



    }



}
