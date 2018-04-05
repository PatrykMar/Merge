package project.merge;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MESSAGE = "ie.ul.ColourMatch.MESSAGE";
    public ConstraintLayout r1;
    public ConstraintLayout r2;
    public int score = 0;
    public int time = 0;
    public ProgressBar bar;
    public TextView scoreView;
    public CountDownTimer timer;
    public Button[] buttons = new Button[9];
    public boolean expertDifficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent1 = getIntent();
        expertDifficulty = intent1.getExtras().getBoolean("DifficultyHard");
        scoreView = findViewById(R.id.textViewScore);
        scoreView.setText(R.string.startScore);
        r1 = findViewById(R.id.layout);
        r2 = findViewById(R.id.layout2);
        bar = findViewById(R.id.progressBar);
        time =  R.integer.time;
        final int countDownInterval = 100;
        timer = new CountDownTimer(time,countDownInterval) {
            @Override
            public void onTick(long l) {
                Log.println(Log.WARN,"","Another tick passed");
                bar.incrementProgressBy(countDownInterval);
                if (bar.getProgress() >= bar.getMax())
                {
                    onFinish();
                }
            }

            @Override
            public void onFinish() {
                timer.cancel();
                gameOver();
            }
        }.start();
        buttons[0] = findViewById(R.id.button);
        buttons[0].setOnClickListener(this);
        buttons[1] = findViewById(R.id.button2);
        buttons[1].setOnClickListener(this);
        buttons[2] = findViewById(R.id.button3);
        buttons[2].setOnClickListener(this);
        buttons[3] = findViewById(R.id.button4);
        buttons[3].setOnClickListener(this);
        buttons[4] = findViewById(R.id.button5);
        buttons[4].setOnClickListener(this);
        buttons[5] = findViewById(R.id.button6);
        buttons[5].setOnClickListener(this);
        buttons[6] = findViewById(R.id.button7);
        buttons[6].setOnClickListener(this);
        buttons[7] = findViewById(R.id.button8);
        buttons[7].setOnClickListener(this);
        buttons[8] = findViewById(R.id.button9);
        buttons[8].setOnClickListener(this);

        main(findViewById(R.id.layout));
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.button:
                checkButtonClick(buttons[0]);
                break;

            case R.id.button2:
                checkButtonClick(buttons[1]);
                break;

            case R.id.button3:
                checkButtonClick(buttons[2]);
                break;

            case R.id.button4:
                checkButtonClick(buttons[3]);
                break;

            case R.id.button5:
                checkButtonClick(buttons[4]);
                break;

            case R.id.button6:
                checkButtonClick(buttons[5]);
                break;

            case R.id.button7:
                checkButtonClick(buttons[6]);
                break;

            case R.id.button8:
                checkButtonClick(buttons[7]);
                break;

            case R.id.button9:
                checkButtonClick(buttons[8]);
                break;
        }
    }

    public void main(View view)
    {
        generateBoard();
    }

    public void checkButtonClick(final Button button)
    {
        Drawable background = r1.getBackground();
        int r1Color = ((ColorDrawable) background).getColor();
        Drawable background2 = button.getBackground();
        int buttonColor = ((ColorDrawable) background2).getColor();

        if (r1Color != buttonColor)
        {
            if (score > 0)
            {
                score -= 50;
            }
            scoreView.setText("Score: " + score);
        }
        else
        {

            score += 100;
            //time -= 1000;
            bar.setProgress(100);
            if(expertDifficulty)
            {
                bar.setMax(bar.getMax() - 50);
            }
            else
            {
                bar.setMax(bar.getMax() - 100);
            }
            scoreView.setText("Score: " + score);
            generateBoard();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void generateBoard()
    {
        if (expertDifficulty)
        {
            for (int i = 0; i < buttons.length; i++)
            {
                buttons[i].setBackground(getDrawable(R.drawable.background));
            }
            int[] colourArray = {R.color.green100,R.color.green200,R.color.green300,R.color.green400,R.color.green500,R.color.green600,R.color.green700,R.color.green800,R.color.green900};
            int[] coloursSelected = new int[9];
            for (int i = 0; i < buttons.length; i++)
            {
                boolean contains = true;
                int randomNumber = 0;
                while (contains)
                {
                    contains = false;
                    randomNumber = (int)(Math.random() * colourArray.length);
                    for (int j = 0; j < coloursSelected.length && !contains; j++)
                    {
                        if (colourArray[randomNumber] == coloursSelected[j])
                        {
                            contains = true;
                        }
                    }
                }
                int backgroundColor = ResourcesCompat.getColor(getResources(), colourArray[randomNumber], null);
                buttons[i].setBackgroundColor(backgroundColor);
                coloursSelected[i] = colourArray[randomNumber];
            }
            int randomNumber = (int)(Math.random() * coloursSelected.length);
            int backgroundColor = ResourcesCompat.getColor(getResources(), coloursSelected[randomNumber], null);
            r1.setBackgroundColor(backgroundColor);
            r2.bringToFront();
        }
        else
        {
            for (int i = 0; i < buttons.length; i++)
            {
                buttons[i].setBackground(getDrawable(R.drawable.background));
            }
            int[] colourArray = {R.color.red,R.color.blue,R.color.green,R.color.yellow,R.color.orange,R.color.purple,R.color.pink,R.color.cyan,R.color.lime};
            int[] coloursSelected = new int[9];
            for (int i = 0; i < buttons.length; i++)
            {
                boolean contains = true;
                int randomNumber = 0;
                while (contains)
                {
                    contains = false;
                    randomNumber = (int)(Math.random() * colourArray.length);
                    for (int j = 0; j < coloursSelected.length && !contains; j++)
                    {
                        if (colourArray[randomNumber] == coloursSelected[j])
                        {
                            contains = true;
                        }
                    }
                }
                int backgroundColor = ResourcesCompat.getColor(getResources(), colourArray[randomNumber], null);
                buttons[i].setBackgroundColor(backgroundColor);
                coloursSelected[i] = colourArray[randomNumber];
            }
            int randomNumber = (int)(Math.random() * coloursSelected.length);
            int backgroundColor = ResourcesCompat.getColor(getResources(), coloursSelected[randomNumber], null);
            r1.setBackgroundColor(backgroundColor);
            r2.bringToFront();
        }
    }

    public void gameOver()
    {
        Log.println(Log.WARN,"","Game over method entered");
        Intent intent = new Intent(this,GameOverActivity.class);
        String message = scoreView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
