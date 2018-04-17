package project.merge;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import project.merge.model.Items;


public class GameOverActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Button playAgain = findViewById(R.id.playAgain);
        playAgain.setOnClickListener(this);
        Intent intent1 = getIntent();
        String message = intent1.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView finalScore = findViewById(R.id.finalScore);
        finalScore.setText("Final " + message);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.playAgain:
                Intent intent2 = new Intent(getApplicationContext(),SelectMenuActivity.class);
                startActivity(intent2);break;

            case R.id.goMenu:
                Intent intent = new Intent(GameOverActivity.this,Menu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("EXIT",true);
                onPause();
                startActivity(intent);break;
        }
    }
}
