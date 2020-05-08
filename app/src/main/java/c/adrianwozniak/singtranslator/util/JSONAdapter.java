package c.adrianwozniak.singtranslator.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONAdapter {

    public static String getDataFrom(String JSON, String jsonClassName){
        String result = "";

        try {
            JSONObject jsonObject = new JSONObject(JSON);
            String predictions = jsonObject.getString("predictions");
            JSONArray jsonArray = new JSONArray(predictions);
            JSONObject predictionsData = jsonArray.getJSONObject(0);
            result = predictionsData.getString(jsonClassName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }


}
