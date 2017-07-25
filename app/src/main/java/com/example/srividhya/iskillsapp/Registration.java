package com.example.srividhya.iskillsapp;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
import com.example.srividhya.iskillsapp.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.R.attr.description;
import static android.R.attr.name;
import static android.R.attr.progress;


public class Registration extends AppCompatActivity {
    EditText customerid, firstname, lastname, address, city, state, country, postalcode, phonenumber;
    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();


    // url to create new product
    private static String url_create_product = "http://10.0.0.128/api/Customer/InsertCustomer";

    // JSON Node names
    private static final String TAG_SUCCESS = "Message";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // Edit Text
        customerid = (EditText) findViewById(R.id.id);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        address = (EditText) findViewById(R.id.address);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);
        country = (EditText) findViewById(R.id.country);
        postalcode = (EditText) findViewById(R.id.postalcode);
        phonenumber = (EditText) findViewById(R.id.phonenumber);


        // Create button
        Button register = (Button) findViewById(R.id.register);

        // button click event
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                //Intent i = new Intent(getApplicationContext(), TabbedActivity.class);
                //startActivity(i);
                new InsertUser().execute();

            }
        });
    }


    class InsertUser extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Registration.this);
            pDialog.setMessage("Adding details..");
           // pDialog.setIndeterminate(false);
           // pDialog.setCancelable(true);
            pDialog.show();
        }


        protected String doInBackground(String... args) {

           // Toast.makeText(getApplicationContext(),"res",Toast.LENGTH_SHORT).show();
            String CustomerId = customerid.getText().toString();
            String FirstName = firstname.getText().toString();
            String LastName = lastname.getText().toString();
            String Address = address.getText().toString();
            String City = city.getText().toString();
            String State = state.getText().toString();
            String Country = country.getText().toString();
            String PostalCode = postalcode.getText().toString();
            String PhoneNumber = phonenumber.getText().toString();
            String result="";

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://localhost:9000/api/Customer/InsertCustomer");

            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("CustomerId", CustomerId));
                params.add(new BasicNameValuePair("FirstName", FirstName));
                params.add(new BasicNameValuePair("LastName", LastName));
                params.add(new BasicNameValuePair("Address", Address));
                params.add(new BasicNameValuePair("City", City));
                params.add(new BasicNameValuePair("State", State));

                params.add(new BasicNameValuePair("PostalCode", PostalCode));
                params.add(new BasicNameValuePair("Country", Country));
                params.add(new BasicNameValuePair("Phone", PhoneNumber));

            httppost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                StringBuilder sb = new StringBuilder();
                String line;
                InputStream instream = entity.getContent();
                BufferedReader bf = new BufferedReader(new InputStreamReader(instream));
                while ((line = bf.readLine()) != null ) {
                    sb.append(line).append("\n");
                }
                result = sb.toString();
                Log.i("Read from server", result);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Intent intent = new Intent(LoginActivity.this, PembukaActivity.class);
        //startActivity(intent);

            return result;
        }


        /**
         * After completing background task Dismiss the progress dialog
         **/

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            Toast.makeText(getApplicationContext(),"result",Toast.LENGTH_SHORT).show();
            pDialog.dismiss();
        }

    }
}


/*




   /* private void insertUser(){

            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ROOT_URL) //Setting the Root URL
                    .build(); //Finally building the adapter

            //Creating object for our interface
            RegisterAPI api = adapter.create(RegisterAPI.class);

            //Defining the method insertuser of our interface
            api.insertUser(

                    //Passing the values by getting it from editTexts
                    firstname.getText().toString(), lastname.getText().toString(),
                    address.getText().toString(), city.getText().toString(),
                    state.getText().toString(),  country.getText().toString(),
                    postalcode.getText().toString(),phonenumber.getText().toString(),

                    //Creating an anonymous callback
                    new Callback<Response>() {
                        @Override
                        public void success(Response result, Response response) {
                            //On success we will read the server's output using bufferedreader
                            //Creating a bufferedreader object
                            BufferedReader reader = null;

                            //An string to store output from the server
                            String output = "";

                            try {
                                //Initializing buffered reader
                                reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                                //Reading the output in the string
                                output = reader.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //Displaying the output as a toast
                            Toast.makeText(Registration.this, output, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            //If any error occured displaying the error as toast
                            Toast.makeText(Registration.this, error.toString(),Toast.LENGTH_LONG).show();
                        }
                    });
        }
        */



