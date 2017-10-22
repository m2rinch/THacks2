package com.melissarinch.backup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private com.melissarinch.backup.ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ExpandableListView)findViewById(R.id.listView);
        initData();
        listAdapter = new com.melissarinch.backup.ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
    }

    public void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("First Name");
        listDataHeader.add("Last Name");
        listDataHeader.add("Gender");
        listDataHeader.add("Date of Birth");
        listDataHeader.add("Phone");
        listDataHeader.add("ID");
        listDataHeader.add("Address");

        try {
            JSONObject patientInfo = new JSONObject(loadJSONFromAsset("patientinfo.json"));
            String firstName = patientInfo.getJSONArray("name").getJSONObject(0).getJSONArray("given").getString(0);
            String lastName = patientInfo.getJSONArray("name").getJSONObject(0).getJSONArray("given").getString(1);
            String gender = patientInfo.getString("gender");
            String birthDate = patientInfo.getString("birthDate");
            String address = patientInfo.getJSONArray("address").getJSONObject(0).getString("text");
            String phone = patientInfo.getJSONArray("telecom").getJSONObject(1).getString("value");
            String identifier = patientInfo.getString("id");


            ArrayList<String> firstNameL = new ArrayList<>();
            ArrayList<String> lastNameL = new ArrayList<>();
            ArrayList<String> genderL = new ArrayList<>();
            ArrayList<String> birthDateL = new ArrayList<>();
            ArrayList<String> phoneL = new ArrayList<>();
            ArrayList<String> identifierL = new ArrayList<>();
            ArrayList<String> addressL = new ArrayList<>();

            firstNameL.add(firstName);
            lastNameL.add(lastName);
            birthDateL.add(birthDate);
            genderL.add(gender);
            phoneL.add(phone);
            identifierL.add(identifier);
            addressL.add(address);

        listHash.put(listDataHeader.get(0), firstNameL);
        listHash.put(listDataHeader.get(1), lastNameL);
        listHash.put(listDataHeader.get(2), genderL);
        listHash.put(listDataHeader.get(3), birthDateL);
        listHash.put(listDataHeader.get(4), phoneL);
        listHash.put(listDataHeader.get(5), identifierL);
            listHash.put(listDataHeader.get(6), addressL);

        }catch (JSONException e){
            e.printStackTrace();
        }


    }

//
//            ExpandableListAdapter<String> adapter = new ArrayAdapter<String>(this,
//                    android.R.layout.simple_list_item_1, patientArray);
//
//            final ExpandableListView patientList = (ExpandableListView) findViewById(R.id.patientList);
//            patientList.setAdapter(adapter);
//
//            // Create a message handling object as an anonymous class.
//            AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
//                public void onItemClick(AdapterView parent, View v, int position, long id) {
//                    // inflate the layout
//                    String main = patientList.getSelectedItem().toString();
//                    switch (main){
//                        case "First Name":
//                            //inflate firstName
//                            break;
//                        case "Last Name":
//                            //inflate lastName
//                            break;
//                        case "Gender":
//                            //inflate gender
//                            break;
//                        case "Date of Birth":
//                            //inflate dob
//                            break;
//                        case "Phone":
//                            //inflate phone
//                            break;
//                        case "ID":
//                            //inflate identifier
//                            break;
//                    }
//                }
//            };
//
//            patientList.setOnItemClickListener(mMessageClickedHandler);
//
//
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//    }
//
    public String loadJSONFromAsset(String filename) {
        String json;
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
