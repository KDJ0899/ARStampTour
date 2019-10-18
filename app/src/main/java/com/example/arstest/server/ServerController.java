package com.example.arstest.server;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ServerController  {

    private JsonObject jsonObject;
    private JsonArray jsonArray;

    public ServerController(ContentValues data,String url,String method)
    {

        NetworkTask networkTask = new NetworkTask("http://10.0.102.44:3000"+url, data, method);
        networkTask.execute();
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;
        private String method;

        public NetworkTask(String url, ContentValues values, String method) {

            this.url = url;
            this.values = values;
            this.method = method;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values, method); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result==null)
                return;

            JsonParser jsonParser = new JsonParser();
            jsonArray = (JsonArray)jsonParser.parse(result);

            for(int i=0; i<jsonArray.size(); i++){
                jsonObject = jsonArray.get(i).getAsJsonObject();
                Log.i("json",jsonObject.get("Name").toString());
            }

        }
    }
    public JsonArray getObjects() {
        return jsonArray;
    }

}