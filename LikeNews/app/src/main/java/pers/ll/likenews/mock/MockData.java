package pers.ll.likenews.mock;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MockData {

    private Map<String, JSONObject> mData = new HashMap<>();

    public MockData(Context context) {
        InputStreamReader streamReader = null;
        BufferedReader bufferedReader = null;
        try {
            streamReader = new InputStreamReader(context.getAssets().open("news.json"), "UTF-8");
            bufferedReader = new BufferedReader(streamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            Log.i("MockData", stringBuilder.toString());
            JSONArray array = new JSONArray(stringBuilder.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.optJSONObject(i);
                mData.put(object.optString("name"), object.optJSONObject("data"));
            }
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

    public JSONObject getJSONData(String name) {
        JSONObject json = mData.get(name);
        return json == null ? new JSONObject() : json;
    }
}
