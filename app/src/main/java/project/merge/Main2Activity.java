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
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MESSAGE = "ie.ul.ColourMatch.MESSAGE";
    public ConstraintLayout r1;
    public ConstraintLayout r2;
    public int score = 0;
    public int time = 0;
    public ProgressBar bar;
    public TextView scoreView;
    public CountDownTimer timer;
    public Button[] buttons = new Button[16];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        scoreView = findViewById(R.id.textViewScore2);
        scoreView.setText(R.string.startScore);
        r1 = findViewById(R.id.layout3);
        r2 = findViewById(R.id.layout4);
        bar = findViewById(R.id.progressBar2);
        time = R.integer.time;
        final int countDownInterval = 100;
        timer = new CountDownTimer(time,countDownInterval) {
            @Override
            public void onTick(long l) {
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

        buttons[0] = findViewById(R.id.button10);
        buttons[0].setOnClickListener(this);
        buttons[1] = findViewById(R.id.button11);
        buttons[1].setOnClickListener(this);
        buttons[2] = findViewById(R.id.button12);
        buttons[2].setOnClickListener(this);
        buttons[3] = findViewById(R.id.button13);
        buttons[3].setOnClickListener(this);
        buttons[4] = findViewById(R.id.button14);
        buttons[4].setOnClickListener(this);
        buttons[5] = findViewById(R.id.button15);
        buttons[5].setOnClickListener(this);
        buttons[6] = findViewById(R.id.button16);
        buttons[6].setOnClickListener(this);
        buttons[7] = findViewById(R.id.button17);
        buttons[7].setOnClickListener(this);
        buttons[8] = findViewById(R.id.button18);
        buttons[8].setOnClickListener(this);
        buttons[9] = findViewById(R.id.button19);
        buttons[9].setOnClickListener(this);
        buttons[10] = findViewById(R.id.button20);
        buttons[10].setOnClickListener(this);
        buttons[11] = findViewById(R.id.button21);
        buttons[11].setOnClickListener(this);
        buttons[12] = findViewById(R.id.button22);
        buttons[12].setOnClickListener(this);
        buttons[13] = findViewById(R.id.button23);
        buttons[13].setOnClickListener(this);
        buttons[14] = findViewById(R.id.button24);
        buttons[14].setOnClickListener(this);
        buttons[15] = findViewById(R.id.button25);
        buttons[15].setOnClickListener(this);

        main(findViewById(R.id.layout));

    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.button10:
                checkButtonClick(buttons[0]);
                break;

            case R.id.button11:
                checkButtonClick(buttons[1]);
                break;

            case R.id.button12:
                checkButtonClick(buttons[2]);
                break;

            case R.id.button13:
                checkButtonClick(buttons[3]);
                break;

            case R.id.button14:
                checkButtonClick(buttons[4]);
                break;

            case R.id.button15:
                checkButtonClick(buttons[5]);
                break;

            case R.id.button16:
                checkButtonClick(buttons[6]);
                break;

            case R.id.button17:
                checkButtonClick(buttons[7]);
                break;

            case R.id.button18:
                checkButtonClick(buttons[8]);
                break;

            case R.id.button19:
                checkButtonClick(buttons[9]);
                break;

            case R.id.button20:
                checkButtonClick(buttons[10]);
                break;

            case R.id.button21:
                checkButtonClick(buttons[11]);
                break;

            case R.id.button22:
                checkButtonClick(buttons[12]);
                break;

            case R.id.button23:
                checkButtonClick(buttons[13]);
                break;

            case R.id.button24:
                checkButtonClick(buttons[14]);
                break;

            case R.id.button25:
                checkButtonClick(buttons[15]);
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
            bar.setProgress(100);
            bar.setMax(bar.getMax() - 100);
            scoreView.setText("Score: " + score);
            generateBoard();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void generateBoard()
    {
        for (int i = 0; i < buttons.length; i++)
        {
            buttons[i].setBackground(getDrawable(R.drawable.background));
        }
        int[] colourArray = {R.color.red,R.color.blue,R.color.green,R.color.yellow,R.color.cyan,R.color.orange,R.color.indigo,R.color.lime,R.color.pink,R.color.purple,R.color.amber,R.color.deep_purple,R.color.maroon,R.color.teal,R.color.deep_orange,R.color.blue_grey};
        int[] coloursSelected = new int[16];
        for(int i = 0; i < buttons.length; i++)
        {
            boolean contains = true;
            int randomNumber = 0;
            while(contains)
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
            int backgroundColor = ResourcesCompat.getColor(getResources(),colourArray[randomNumber],null);
            buttons[i].setBackgroundColor(backgroundColor);
            coloursSelected[i] = colourArray[randomNumber];
        }
        int randomNumber = (int)(Math.random() * coloursSelected.length);
        int backgroundColor = ResourcesCompat.getColor(getResources(), coloursSelected[randomNumber],null);
        r1.setBackgroundColor(backgroundColor);
        r2.bringToFront();
    }

    public void gameOver()
    {
        Intent intent = new Intent(this,GameOverActivity.class);
        String message = scoreView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
