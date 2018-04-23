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
        DatabaseHelper dbhelper = new DatabaseHelper((this));
        Items user = new Items();
        user.setScore("700");
        user.setName("Ian");
        dbhelper.addScore(user,user);
        fillListView();

    }
    public void fillListView(){
        ListView myListView = findViewById(R.id.list);
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        ArrayList<Items> itemsList = dbhelper.getAllScores();

        CustomListAdapter myAdapter = new CustomListAdapter(itemsList,this);
        myListView.setAdapter(myAdapter);
    }
}
