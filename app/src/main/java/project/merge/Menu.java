package project.merge;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import project.merge.model.Items;

import static android.app.AlertDialog.Builder;
import static android.os.Process.killProcess;

public class Menu extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        onStart();

    }

    @Override
    protected void onStart(){
        super.onStart();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.littleidea);
        mediaPlayer.start();
    }

    @Override
    protected void onPause(){
       super.onPause();
        mediaPlayer.stop();
        //mediaPlayer.release();
    }


    public void Start(View view)
    {
        Intent intent = new Intent(getApplicationContext(),SelectMenuActivity.class);
        startActivity(intent);
    }

    public void showScoreboard(View view)
    {
        Items name = new Items();
        Items score = new Items();
        name.setName("Filip");
        score.setScore("150");
        Intent intent = new Intent(getApplicationContext(),showScoreboard.class);
        startActivity(intent);

    }

    public void showSettings(View view)
    {
        Intent intent = new Intent(getApplicationContext(),option.class);
        startActivity(intent);
    }

    public void Quit(View view)
    {
        Builder dialog = new Builder(Menu.this);
        dialog.setTitle("Quit");
        dialog.setMessage("Do you want to quit??");
        dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //if you want to kill app . from other then your main avtivity.(Launcher)
                killProcess(android.os.Process.myPid());
                System.exit(1);

                //if you want to finish just current activity
                if(getIntent().getBooleanExtra("EXIT",false))
                {
                    finish();
                }


            }
        });
        dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}

