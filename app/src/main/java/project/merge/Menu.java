package project.merge;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static android.app.AlertDialog.Builder;
import static android.os.Process.killProcess;

public class Menu extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.littleidea);

        mediaPlayer.start();

    }

    //@Override
   // protected void onPause(){
     //   super.onPause();
       // mediaPlayer.stop();
        //mediaPlayer.release();
    //}


    public void Start(View view)
    {
        Intent intent = new Intent(getApplicationContext(),Start.class);
        startActivity(intent);
    }

    public void showScoreboard(View view)
    {
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
        dialog.setTitle("Exit");
        dialog.setMessage("are you sure to exit?");
        dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //if you want to kill app . from other then your main avtivity.(Launcher)
                killProcess(android.os.Process.myPid());
                System.exit(1);

                //if you want to finish just current activity

                Menu.this.finish();
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

