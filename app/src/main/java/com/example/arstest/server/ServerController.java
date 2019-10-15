package com.example.arstest.server;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ServerController  {

    public ServerController(){}
    public ServerController(ContentValues data, String url)
    {
        NetworkTask networkTask = new NetworkTask("http://10.0.102.44:3000/"+url, data);
        networkTask.execute();
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = (JsonArray) jsonParser.parse(result);
            for(int i=0; i<jsonArray.size(); i++){
                JsonObject object = (JsonObject) jsonArray.get(i);
                Log.i("name",object.get("Name")+"");
            }
        }
    }
}