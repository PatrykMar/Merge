package project.merge;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import project.merge.adapter.CustomListAdapter;
import project.merge.model.Items;

public class showScoreboard extends AppCompatActivity {
    private List<Items> itemsList = new ArrayList<Items>();
    private ListView listView;
    private CustomListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_scoreboard);

        SQLiteDatabase myDB = null;

        try {

            //Create a Database if doesnt exist otherwise Open It

            myDB = this.openOrCreateDatabase("leaderboard", MODE_PRIVATE, null);

            //Create table in database if it doesnt exist allready

            myDB.execSQL("CREATE TABLE IF NOT EXISTS scores (name TEXT, score TEXT);");

            //Select all rows from the table

            Cursor cursor = myDB.rawQuery("SELECT * FROM scores", null);

            //If there are no rows (data) then insert some in the table

            if (cursor != null) {
                if (cursor.getCount() != 0) {

                    Items temp = new Items();
                    myDB.execSQL("INSERT INTO scores (name,score)  VALUES ('Ian',?);",new String[]{temp.getScore()});
                  //  myDB.execSQL("INSERT INTO scores (name,score) VALUES ();");
                   //myDB.execSQL("INSERT INTO scores (name,score) VALUES ();");

                }

            }


        } catch (Exception e) {

        } finally {

            //Initialize and create a new adapter with layout named list found in activity_main layout

            listView = (ListView) findViewById(R.id.list);
            adapter = new CustomListAdapter(this, itemsList);
            listView.setAdapter(adapter);

            Cursor cursor = myDB.rawQuery("SELECT * FROM scores", null);

            if (cursor.moveToFirst()) {

                //read all rows from the database and add to the Items array

                do {

                    Items items = new Items();
                    //items.setScore("700");
                   // items.setName("Nigga");
                   items.setName(cursor.getString(0));
                   items.setScore(cursor.getString(1));

                    itemsList.add(items);
                }while  (cursor.moveToNext());


                }
            }

            //All done, so notify the adapter to populate the list using the Items Array

            adapter.notifyDataSetChanged();
        }
    }
