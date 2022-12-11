package cs2450.TeamStorm.com;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Game18Screen extends AppCompatActivity {
    // Audio player object to play background music
    private static MediaPlayer player;

    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11, iv12, iv13, iv14, iv15, iv16, iv17, iv18;

    TextView p1Text;

    Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 107, 108, 109, 201, 202, 203, 204, 205, 206, 207, 208, 209};
    String[][] scores = new String[9][6];

    int image10, image11, image12, image13, image14, image15, image16, image17, image18, image20, image21, image22, image23, image24, image25, image26, image27, image28;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    int playerPoints = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game18_screen);

        // create ancestral navigation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (NavUtils.getParentActivityName(this) != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        // set MediaPlayer
        player = MainActivity.getPlayer();

        // mute audio
        ImageButton stop = (ImageButton) findViewById(R.id.gameMusicButton);
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (player != null) {
                    if(!player.isPlaying()){
                        player.start();
                    }
                }
            }
        });

        // resume audio after muting
        ImageButton resume = (ImageButton) findViewById(R.id.gameunmutebutton);
        resume.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (player != null) {
                    player.pause();
                }
            }
        });

        Button newGame = (Button)findViewById(R.id.newGameButton2);
        newGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Game18Screen.this, GameScreen.class));
            }
        });

        Button endGame = (Button)findViewById(R.id.endGameButton);
        endGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                iv1.setImageResource(image10);
                iv2.setImageResource(image11);
                iv3.setImageResource(image12);
                iv4.setImageResource(image13);
                iv5.setImageResource(image14);
                iv6.setImageResource(image15);
                iv7.setImageResource(image16);
                iv8.setImageResource(image17);
                iv9.setImageResource(image18);
                iv10.setImageResource(image20);
                iv11.setImageResource(image21);
                iv12.setImageResource(image22);
                iv13.setImageResource(image23);
                iv14.setImageResource(image24);
                iv15.setImageResource(image25);
                iv16.setImageResource(image26);
                iv17.setImageResource(image27);
                iv18.setImageResource(image28);

                iv1.setEnabled(false);
                iv2.setEnabled(false);
                iv3.setEnabled(false);
                iv4.setEnabled(false);
                iv5.setEnabled(false);
                iv6.setEnabled(false);
                iv7.setEnabled(false);
                iv8.setEnabled(false);
                iv9.setEnabled(false);
                iv10.setEnabled(false);
                iv11.setEnabled(false);
                iv12.setEnabled(false);
                iv13.setEnabled(false);
                iv14.setEnabled(false);
                iv15.setEnabled(false);
                iv16.setEnabled(false);
                iv17.setEnabled(false);
                iv18.setEnabled(false);
            }
        });

        Button tryAgain = (Button)findViewById(R.id.tryAgainButton);

        tryAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                iv1.setImageResource(R.drawable.card);
                iv2.setImageResource(R.drawable.card);
                iv3.setImageResource(R.drawable.card);
                iv4.setImageResource(R.drawable.card);
                iv5.setImageResource(R.drawable.card);
                iv6.setImageResource(R.drawable.card);
                iv7.setImageResource(R.drawable.card);
                iv8.setImageResource(R.drawable.card);
                iv9.setImageResource(R.drawable.card);
                iv10.setImageResource(R.drawable.card);
                iv11.setImageResource(R.drawable.card);
                iv12.setImageResource(R.drawable.card);
                iv13.setImageResource(R.drawable.card);
                iv14.setImageResource(R.drawable.card);
                iv15.setImageResource(R.drawable.card);
                iv16.setImageResource(R.drawable.card);
                iv17.setImageResource(R.drawable.card);
                iv18.setImageResource(R.drawable.card);

                iv1.setEnabled(true);
                iv2.setEnabled(true);
                iv3.setEnabled(true);
                iv4.setEnabled(true);
                iv5.setEnabled(true);
                iv6.setEnabled(true);
                iv7.setEnabled(true);
                iv8.setEnabled(true);
                iv9.setEnabled(true);
                iv10.setEnabled(true);
                iv11.setEnabled(true);
                iv12.setEnabled(true);
                iv13.setEnabled(true);
                iv14.setEnabled(true);
                iv15.setEnabled(true);
                iv16.setEnabled(true);
                iv17.setEnabled(true);
                iv18.setEnabled(true);

                if(playerPoints > 0){
                    playerPoints--;
                    p1Text.setText("Player points: " + playerPoints);
                }
            }
        });

        p1Text = (TextView) findViewById(R.id.pointsText);

        iv1 = (ImageView) findViewById(R.id.imageView);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv3 = (ImageView) findViewById(R.id.imageView3);
        iv4 = (ImageView) findViewById(R.id.imageView4);
        iv5 = (ImageView) findViewById(R.id.imageView5);
        iv6 = (ImageView) findViewById(R.id.imageView6);
        iv7 = (ImageView) findViewById(R.id.imageView7);
        iv8 = (ImageView) findViewById(R.id.imageView8);
        iv9 = (ImageView) findViewById(R.id.imageView9);
        iv10 = (ImageView) findViewById(R.id.imageView10);
        iv11 = (ImageView) findViewById(R.id.imageView11);
        iv12 = (ImageView) findViewById(R.id.imageView12);
        iv13 = (ImageView) findViewById(R.id.imageView13);
        iv14 = (ImageView) findViewById(R.id.imageView14);
        iv15 = (ImageView) findViewById(R.id.imageView15);
        iv16 = (ImageView) findViewById(R.id.imageView16);
        iv17 = (ImageView) findViewById(R.id.imageView17);
        iv18 = (ImageView) findViewById(R.id.imageView18);


        iv1.setTag("0");
        iv2.setTag("1");
        iv3.setTag("2");
        iv4.setTag("3");
        iv5.setTag("4");
        iv6.setTag("5");
        iv7.setTag("6");
        iv8.setTag("7");
        iv9.setTag("8");
        iv10.setTag("9");
        iv11.setTag("10");
        iv12.setTag("11");
        iv13.setTag("12");
        iv14.setTag("13");
        iv15.setTag("14");
        iv16.setTag("15");
        iv17.setTag("16");
        iv18.setTag("17");

        //set images to image variables
        frontOfCards();

        Collections.shuffle(Arrays.asList(cardsArray));

        iv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv1, theCard);
            }
        });

        iv2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv2, theCard);
            }
        });

        iv3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv3, theCard);
            }
        });

        iv4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv4, theCard);
            }
        });

        iv5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv5, theCard);
            }
        });

        iv6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv6, theCard);
            }
        });

        iv7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv7, theCard);
            }
        });

        iv8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv8, theCard);
            }
        });

        iv9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv9, theCard);
            }
        });

        iv10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv10, theCard);
            }
        });

        iv11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv11, theCard);
            }
        });

        iv12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv12, theCard);
            }
        });

        iv13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv13, theCard);
            }
        });

        iv14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv14, theCard);
            }
        });

        iv15.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv15, theCard);
            }
        });

        iv16.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv16, theCard);
            }
        });

        iv17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv17, theCard);
            }
        });

        iv18.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv18, theCard);
            }
        });
    }

    private void setImageAndCheck(ImageView iv, int card){
        //set images to imageview
        if(cardsArray[card] == 101) {
            iv.setImageResource(image10);
        }
        else if(cardsArray[card] == 102){
            iv.setImageResource(image11);
        }
        else if(cardsArray[card] == 103){
            iv.setImageResource(image12);
        }
        else if(cardsArray[card] == 104){
            iv.setImageResource(image13);
        }
        else if(cardsArray[card] == 105){
            iv.setImageResource(image14);
        }
        else if(cardsArray[card] == 106){
            iv.setImageResource(image15);
        }
        else if(cardsArray[card] == 107){
            iv.setImageResource(image16);
        }
        else if(cardsArray[card] == 108){
            iv.setImageResource(image17);
        }
        else if(cardsArray[card] == 109){
            iv.setImageResource(image18);
        }
        else if(cardsArray[card] == 201){
            iv.setImageResource(image20);
        }
        else if(cardsArray[card] == 202){
            iv.setImageResource(image21);
        }
        else if(cardsArray[card] == 203){
            iv.setImageResource(image22);
        }
        else if(cardsArray[card] == 204){
            iv.setImageResource(image23);
        }
        else if(cardsArray[card] == 205){
            iv.setImageResource(image24);
        }
        else if(cardsArray[card] == 206){
            iv.setImageResource(image25);
        }
        else if(cardsArray[card] == 207){
            iv.setImageResource(image26);
        }
        else if(cardsArray[card] == 208){
            iv.setImageResource(image27);
        }
        else if(cardsArray[card] == 209){
            iv.setImageResource(image28);
        }
        //check selected image
        if(cardNumber == 1){
            firstCard = cardsArray[card];
            if(firstCard > 200){
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;

            iv.setEnabled(false);
        }else if(cardNumber == 2) {
            secondCard = cardsArray[card];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            iv1.setEnabled(false);
            iv2.setEnabled(false);
            iv3.setEnabled(false);
            iv4.setEnabled(false);
            iv5.setEnabled(false);
            iv6.setEnabled(false);
            iv7.setEnabled(false);
            iv8.setEnabled(false);
            iv9.setEnabled(false);
            iv10.setEnabled(false);
            iv11.setEnabled(false);
            iv12.setEnabled(false);
            iv13.setEnabled(false);
            iv14.setEnabled(false);
            iv15.setEnabled(false);
            iv16.setEnabled(false);
            iv17.setEnabled(false);
            iv18.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    check();
                }
            }, 1000);
        }
    }

    private void check() {
        if (firstCard == secondCard) {
            if (clickedFirst == 0) {
                iv1.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 1) {
                iv2.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 2) {
                iv3.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 3) {
                iv4.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 4) {
                iv5.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 5) {
                iv6.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 6) {
                iv7.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 7) {
                iv8.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 8) {
                iv9.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 9) {
                iv10.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 10) {
                iv11.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 11) {
                iv12.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 12) {
                iv13.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 13) {
                iv14.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 14) {
                iv15.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 15) {
                iv16.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 16) {
                iv17.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 17) {
                iv18.setVisibility(View.INVISIBLE);
            }

            if (clickedSecond == 0) {
                iv1.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 1) {
                iv2.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 2) {
                iv3.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 3) {
                iv4.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 4) {
                iv5.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 5) {
                iv6.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 6) {
                iv7.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 7) {
                iv8.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 8) {
                iv9.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 9) {
                iv10.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 10) {
                iv11.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 11) {
                iv12.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 12) {
                iv13.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 13) {
                iv14.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 14) {
                iv15.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 15) {
                iv16.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 16) {
                iv17.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 17) {
                iv18.setVisibility(View.INVISIBLE);
            }

            playerPoints++;
            p1Text.setText("Player points: " + playerPoints);
            iv1.setEnabled(true);
            iv2.setEnabled(true);
            iv3.setEnabled(true);
            iv4.setEnabled(true);
            iv5.setEnabled(true);
            iv6.setEnabled(true);
            iv7.setEnabled(true);
            iv8.setEnabled(true);
            iv9.setEnabled(true);
            iv10.setEnabled(true);
            iv11.setEnabled(true);
            iv12.setEnabled(true);
            iv13.setEnabled(true);
            iv14.setEnabled(true);
            iv15.setEnabled(true);
            iv16.setEnabled(true);
            iv17.setEnabled(true);
            iv18.setEnabled(true);

        }
        gameOver();
    }

    private void gameOver () {
        if (iv1.getVisibility() == View.INVISIBLE &&
                iv2.getVisibility() == View.INVISIBLE &&
                iv3.getVisibility() == View.INVISIBLE &&
                iv4.getVisibility() == View.INVISIBLE &&
                iv5.getVisibility() == View.INVISIBLE &&
                iv6.getVisibility() == View.INVISIBLE &&
                iv7.getVisibility() == View.INVISIBLE &&
                iv8.getVisibility() == View.INVISIBLE &&
                iv9.getVisibility() == View.INVISIBLE &&
                iv10.getVisibility() == View.INVISIBLE &&
                iv11.getVisibility() == View.INVISIBLE &&
                iv12.getVisibility() == View.INVISIBLE &&
                iv13.getVisibility() == View.INVISIBLE &&
                iv14.getVisibility() == View.INVISIBLE &&
                iv15.getVisibility() == View.INVISIBLE &&
                iv16.getVisibility() == View.INVISIBLE &&
                iv17.getVisibility() == View.INVISIBLE &&
                iv18.getVisibility() == View.INVISIBLE) {
            loadHighScores();
            if (checkHighScore() == false) {
                //Toast.makeText(getActivity(), "Game Over", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game18Screen.this);
                alertDialogBuilder
                        .setMessage("GAME OVER!\nPlayer Points: " + playerPoints)
                        .setCancelable(false)
                        .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(), GameScreen.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game18Screen.this);
                alertDialogBuilder
                        .setTitle("NEW HIGH SCORE!\nPlayer Points: " + playerPoints)
                        .setMessage("Enter Name to save High Score:")
                        .setCancelable(false);

                final EditText nameInput = new EditText(Game18Screen.this);
                int maxLength = 8;
                nameInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                alertDialogBuilder.setView(nameInput);

                alertDialogBuilder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        saveHighScore(nameInput.getText().toString());
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
    }

    private void frontOfCards(){
        image10 = R.drawable.snow;
        image11 = R.drawable.sun;
        image12 = R.drawable.cloudy;
        image13 = R.drawable.lightning;
        image14 = R.drawable.night;
        image15 = R.drawable.rain;
        image16 = R.drawable.storm;
        image17 = R.drawable.cloudy2;
        image18 = R.drawable.cloudy3;
        image20 = R.drawable.twosnow;
        image21 = R.drawable.twosun;
        image22 = R.drawable.twocloudy;
        image23 = R.drawable.twolightning;
        image24 = R.drawable.twonight;
        image25 = R.drawable.tworain;
        image26 = R.drawable.twostorm;
        image27 = R.drawable.twocloudy2;
        image28 = R.drawable.twocloudy3;
    }

    //reads file and copies the scores to 2d array. creates file if it doesn't exist
    public void loadHighScores(){
        File file = getApplicationContext().getFileStreamPath("Scores.txt");
        String line;


        //if file exists, read file
        if (file.exists()){
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("Scores.txt")));

                for(int i = 0; i < 9; i++){
                    for (int j = 0; j < 6; j +=2){
                        line = reader.readLine();
                        StringTokenizer tokens = new StringTokenizer(line, " ");
                        scores[i][j] = tokens.nextToken();
                        scores[i][j + 1] = tokens.nextToken();
                    }
                }

                reader.close();
            }
            catch (IOException e) {
                Toast.makeText(Game18Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        //if no file exists, create one and call method again
        else{
            try{
                FileOutputStream file2 = openFileOutput("Scores.txt", MODE_PRIVATE);
                OutputStreamWriter outputFile = new OutputStreamWriter(file2);

                //set all names to be empty
                for(int i = 0; i < 9; i++){
                    for (int j = 0; j < 6; j += 2 ){
                        scores[i][j] = "empty";
                    }
                }

                //set all scores to 0
                for(int i = 0; i < 9; i++){
                    for (int j = 1; j < 6; j += 2 ){
                        scores[i][j] = "0";
                    }
                }

                for(int i = 0; i < 9; i++){
                    for (int j = 0; j < 6; j += 2 ){
                        outputFile.write(scores[i][j] + " " + scores[i][j + 1] + "\n");
                    }
                }
                outputFile.flush();
                outputFile.close();
                loadHighScores();
            }
            catch (IOException e){
                Toast.makeText(Game18Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    //converts amount of cards to position number in 2d array
    public int convertChoice(int choice){
        int newChoice = 0;

        switch (choice){
            case 4:
                newChoice  = 0;
                break;
            case 6:
                newChoice  = 1;
                break;
            case 8:
                newChoice  = 2;
                break;
            case 10:
                newChoice  = 3;
                break;
            case 12:
                newChoice  = 4;
                break;
            case 14:
                newChoice  = 5;
                break;
            case 16:
                newChoice  = 6;
                break;
            case 18:
                newChoice  = 7;
                break;
            case 20:
                newChoice  = 8;
                break;
        }

        return newChoice;
    }

    public boolean checkHighScore(){
        int scoreType = convertChoice(4);

        int scoreA = Integer.parseInt(scores[scoreType][1]);
        int scoreB = Integer.parseInt(scores[scoreType][3]);
        int scoreC = Integer.parseInt(scores[scoreType][5]);

        if (playerPoints <= scoreC){
            //no new high score
            return false;
        }
        else{
            return true;
        }
    }

    //checks and saves high scores. call this method everytime a game is ended.
    public void saveHighScore(String playerName){
        int scoreType = convertChoice(4);

        int scoreA = Integer.parseInt(scores[scoreType][1]);
        int scoreB = Integer.parseInt(scores[scoreType][3]);
        int scoreC = Integer.parseInt(scores[scoreType][5]);

        if (playerPoints > scoreC && playerPoints <= scoreB){
            //high score is placed in rank 3
            scores[scoreType][4] = playerName;
            scores[scoreType][5] = Integer.toString(playerPoints);

            writeHighScores();
        }
        else if (playerPoints > scoreB && playerPoints <= scoreA){
            //move rank 2 scores to rank 3
            scores[scoreType][4] = scores[scoreType][2];
            scores[scoreType][5] = scores[scoreType][3];

            //high score is placed in rank 2
            scores[scoreType][2] = playerName;
            scores[scoreType][3] = Integer.toString(playerPoints);

            writeHighScores();
        }
        else if (playerPoints > scoreA){
            //move rank 2 scores to rank 3
            scores[scoreType][4] = scores[scoreType][2];
            scores[scoreType][5] = scores[scoreType][3];

            //move rank 1 scores to rank 2
            scores[scoreType][2] = scores[scoreType][0];
            scores[scoreType][3] = scores[scoreType][1];

            //high score is placed in rank 1
            scores[scoreType][0] = playerName;
            scores[scoreType][1] = Integer.toString(playerPoints);

            writeHighScores();
        }
    }

    //writes new high scores to Scores.txt
    public void writeHighScores(){
        try{
            FileOutputStream file2 = openFileOutput("Scores.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file2);

            for(int i = 0; i < 9; i++){
                for (int j = 0; j < 6; j += 2 ){
                    outputFile.write(scores[i][j] + " " + scores[i][j + 1] + "\n");
                }
            }
            outputFile.flush();
            outputFile.close();
        }
        catch (IOException e){
            Toast.makeText(Game18Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // go up the activity hierarchy
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(this) != null) {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

