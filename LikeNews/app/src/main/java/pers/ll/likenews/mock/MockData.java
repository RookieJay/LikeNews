package pers.ll.likenews.mock;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MockData {

    private Map<String, JSONArray> mData = new HashMap<>();
    private String json;

    public MockData(Context context, String fileName) {
        InputStreamReader streamReader = null;
        BufferedReader bufferedReader = null;
        try {
            streamReader = new InputStreamReader(context.getAssets().open(fileName), "UTF-8");
            bufferedReader = new BufferedReader(streamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            json = stringBuilder.toString();
            Log.i("MockData", json);
            JSONArray array = new JSONArray(stringBuilder.toString());
            Log.d("JSONArray长度", String.valueOf(array.length()));
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject object = array.optJSONObject(i);
//                mData.put(object.optString("name"), object.optJSONObject("data"));
//                Log.i("mData的数据", object.optString("name"));
//            }
            mData.put("data", array);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public JSONObject getJSONData(String name) {
//        JSONObject json = mData.get(name);
//        Log.d("getJSONData: ", new Gson().toJson(json));
//        return json == null ? new JSONObject() : json;
//    }

    public JSONArray getJSONData(String name) {
        JSONArray json = mData.get(name);
        Log.d("getJSONData: ", new Gson().toJson(json));
        return json == null ? new JSONArray() : json;
    }

    public String getJson() {
        return json;
    }
}
