package com.example.asmandroidnangcao;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RssActivity extends AppCompatActivity {
    ListView listView;



    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayList<String> arrayLink = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_main);
        listView = (ListView) findViewById(R.id.lv);
        AsyncTask<String,Void,String> content =new RSSFeed().execute("https://ngoisao.net/rss/hau-truong.rss");
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://ngoisao.net/")));
            }
        });
        listView.setAdapter(arrayAdapter);
    }
    public class RSSFeed extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader reader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLParser xmlParser = new XMLParser();
            try {
                Document document = xmlParser.getDocument(s);
                NodeList nodeList = document.getElementsByTagName("item");
                String title="";
                for(int i=0;i<nodeList.getLength();i++){
                    Element element = (Element)nodeList.item(i);
                    title+=xmlParser.getValue(element,"title")+"\n";
                    arrayList.add(title);
                    arrayLink.add(xmlParser.getValue(element,"link"));
                }
                arrayAdapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }
    }
}
