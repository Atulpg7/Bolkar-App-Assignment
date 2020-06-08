package com.example.assignment_atul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.assignment_atul.Adapters.Model.HorizontalView;
import com.example.assignment_atul.Adapters.Model.VerticalView;
import com.example.assignment_atul.Adapters.VerticalRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    //Recycler view to stode data
    RecyclerView recyclerView;


    // Adapter to insert custom data in recycle view
    VerticalRecyclerViewAdapter verticalAdapter;


    //Array to store main titles
    ArrayList<VerticalView> titlesArray=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbar to provide back button at the corder
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // Initializing recycler view and its properties
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));


        // Class for fetching data from API
        new fetchData().execute();


        // Applying Vertical adapter in recycler view
        verticalAdapter = new VerticalRecyclerViewAdapter(this,titlesArray);
        recyclerView.setAdapter(verticalAdapter);

    }

    private class fetchData extends AsyncTask<Void,Void,Void>{

        String data="";

        @Override
        protected Void doInBackground(Void... voids) {
            
            try {

                // Reading data from Api through Buffered Reader
                URL url = new URL("https://d51md7wh0hu8b.cloudfront.net/android/v1/prod/Radio/hi/show.json");
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

                String line="";

                while(line !=null){
                    line = bufferedReader.readLine();
                    data+=line;
                }


                // Closing Open connections
                inputStream.close();
                bufferedReader.close();
                httpURLConnection.disconnect();

                // Seperating the data sets recived from api
                JSONArray jsonArray=new JSONArray(data);



                // Traversing though each set
                for(int i=0;i<jsonArray.length();i++){

                    // Storing each set's object array in JsonObjecr
                    JSONObject jo=(JSONObject) jsonArray.get(i);

                    // Setting title ie: Kahaniya etc
                    VerticalView verticalView=new VerticalView();
                    verticalView.setTitle(jo.getString("title"));




                    // Sperating dataset fetched in data tag
                    JSONArray jsonArray2=new JSONArray(jo.getString("data"));

                    //Object to store videos thumbnails and name
                    ArrayList<HorizontalView> videosList=new ArrayList<>();

                    // traversing through each set ie: t, d ,pF etc
                    for(int j=0;j<jsonArray2.length();j++){

                        // Fetching urls of thumbnails, description and title of vide
                        JSONObject jo2=(JSONObject) jsonArray2.get(j);

                        String t=jo2.getString("t");
                        String d=jo2.getString("d");
                        String imageUrl=jo2.getString("pF");

                        HorizontalView horizontalView=new HorizontalView();

                        horizontalView.setName(t);
                        horizontalView.setDescription(d);
                        horizontalView.setImageUrl(imageUrl);



                        // Videos in ArrayList
                        videosList.add(horizontalView);
                    }

                    // Setting ArrayList of Videos to ArrayList of Vertical view
                    verticalView.setArrayList(videosList);
                    titlesArray.add(verticalView);

                }
           
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            verticalAdapter.notifyDataSetChanged();


        }
    }
}
