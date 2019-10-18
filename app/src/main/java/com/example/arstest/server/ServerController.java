package com.example.arstest.server;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import com.example.arstest.DTO.localGU;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class ServerController  {

    private JsonObject jsonObject;
    private JsonArray jsonArray;
    NetworkTask networkTask;
    public static List<localGU> list;

    public ServerController(ContentValues data,String url)
    {
        networkTask = new NetworkTask("http://10.0.102.44:3000"+url, data);
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
            if(result==null)
                return;

            JsonParser jsonParser = new JsonParser();
            jsonArray = (JsonArray)jsonParser.parse(result);

        }
    }
    public JsonArray getObjects() {
        return jsonArray;
    }

}