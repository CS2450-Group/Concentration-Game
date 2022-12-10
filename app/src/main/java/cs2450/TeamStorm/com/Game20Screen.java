package cs2450.TeamStorm.com;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Game20Screen extends AppCompatActivity {
    // Audio player object to play background music
    private static MediaPlayer player;

    ImageView iv11, iv12, iv13, iv14, iv21, iv22, iv23, iv24, iv31, iv32, iv33, iv34, iv41, iv42, iv43, iv44, iv51, iv52, iv53, iv54;

    TextView p1Text;

    Integer[] cardsArray = {100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 200 , 201, 202, 203, 204, 205 , 206, 207, 208, 209};
    String[][] scores = new String[9][6];

    int image10, image11, image12, image13, image14, image15, image16, image17, image18, image19, image20, image21, image22, image23, image24, image25, image26, image27, image28,
            image29;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    int playerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game20_screen);

        // set MediaPlayer
        player = MainActivity.getPlayer();

        // resume audio after mute
        ImageButton resume = (ImageButton) findViewById(R.id.gameunmutebutton);
        resume.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (player != null) {
                    if(!player.isPlaying()){
                        player.start();
                    }
                }
            }
        });

        // mute audio
        ImageButton stop = (ImageButton) findViewById(R.id.gameMusicButton);
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(player != null){
                    player.pause();
                }
            }
        });

        Button tryAgain = (Button)findViewById(R.id.tryAgainButton);

        tryAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                iv11.setImageResource(R.drawable.card);
                iv12.setImageResource(R.drawable.card);
                iv13.setImageResource(R.drawable.card);
                iv14.setImageResource(R.drawable.card);
                iv21.setImageResource(R.drawable.card);
                iv22.setImageResource(R.drawable.card);
                iv23.setImageResource(R.drawable.card);
                iv24.setImageResource(R.drawable.card);
                iv31.setImageResource(R.drawable.card);
                iv32.setImageResource(R.drawable.card);
                iv33.setImageResource(R.drawable.card);
                iv34.setImageResource(R.drawable.card);
                iv41.setImageResource(R.drawable.card);
                iv42.setImageResource(R.drawable.card);
                iv43.setImageResource(R.drawable.card);
                iv44.setImageResource(R.drawable.card);
                iv51.setImageResource(R.drawable.card);
                iv52.setImageResource(R.drawable.card);
                iv53.setImageResource(R.drawable.card);
                iv54.setImageResource(R.drawable.card);

                iv11.setEnabled(true);
                iv12.setEnabled(true);
                iv13.setEnabled(true);
                iv14.setEnabled(true);
                iv21.setEnabled(true);
                iv22.setEnabled(true);
                iv23.setEnabled(true);
                iv24.setEnabled(true);
                iv31.setEnabled(true);
                iv32.setEnabled(true);
                iv33.setEnabled(true);
                iv34.setEnabled(true);
                iv41.setEnabled(true);
                iv42.setEnabled(true);
                iv43.setEnabled(true);
                iv44.setEnabled(true);
                iv51.setEnabled(true);
                iv52.setEnabled(true);
                iv53.setEnabled(true);
                iv54.setEnabled(true);

                if(playerPoints > 0){
                    playerPoints--;
                    p1Text.setText("Player points: " + playerPoints);
                }
            }
        });

        p1Text = (TextView) findViewById(R.id.pointsText);

        iv11 = (ImageView) findViewById(R.id.imageView);
        iv12 = (ImageView) findViewById(R.id.imageView2);
        iv13 = (ImageView) findViewById(R.id.imageView3);
        iv14 = (ImageView) findViewById(R.id.imageView4);
        iv21 = (ImageView) findViewById(R.id.imageView5);
        iv22 = (ImageView) findViewById(R.id.imageView6);
        iv23 = (ImageView) findViewById(R.id.imageView7);
        iv24 = (ImageView) findViewById(R.id.imageView8);
        iv31 = (ImageView) findViewById(R.id.imageView9);
        iv32 = (ImageView) findViewById(R.id.imageView10);
        iv33 = (ImageView) findViewById(R.id.imageView11);
        iv34 = (ImageView) findViewById(R.id.imageView12);
        iv41 = (ImageView) findViewById(R.id.imageView13);
        iv42 = (ImageView) findViewById(R.id.imageView14);
        iv43 = (ImageView) findViewById(R.id.imageView15);
        iv44 = (ImageView) findViewById(R.id.imageView16);
        iv51 = (ImageView) findViewById(R.id.imageView17);
        iv52 = (ImageView) findViewById(R.id.imageView18);
        iv53 = (ImageView) findViewById(R.id.imageView19);
        iv54 = (ImageView) findViewById(R.id.imageView20);


        iv11.setTag("0");
        iv12.setTag("1");
        iv13.setTag("2");
        iv14.setTag("3");
        iv21.setTag("4");
        iv22.setTag("5");
        iv23.setTag("6");
        iv24.setTag("7");
        iv31.setTag("8");
        iv32.setTag("9");
        iv33.setTag("10");
        iv34.setTag("11");
        iv41.setTag("12");
        iv42.setTag("13");
        iv43.setTag("14");
        iv44.setTag("15");
        iv51.setTag("16");
        iv52.setTag("17");
        iv53.setTag("18");
        iv54.setTag("19");

        //set images to image variables
        frontOfCards();

        Collections.shuffle(Arrays.asList(cardsArray));

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

        iv21.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv21, theCard);
            }
        });

        iv22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv22, theCard);
            }
        });

        iv23.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv23, theCard);
            }
        });

        iv24.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv24, theCard);
            }
        });

        iv31.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv31, theCard);
            }
        });

        iv32.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv32, theCard);
            }
        });

        iv33.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv33, theCard);
            }
        });

        iv34.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv34, theCard);
            }
        });

        iv41.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv41, theCard);
            }
        });

        iv42.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv42, theCard);
            }
        });

        iv43.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv43, theCard);
            }
        });

        iv44.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv44, theCard);
            }
        });

        iv51.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv51, theCard);
            }
        });

        iv52.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv52, theCard);
            }
        });

        iv53.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv53, theCard);
            }
        });

        iv54.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv54, theCard);
            }
        });
    }

    private void setImageAndCheck(ImageView iv, int card){
        //set images to imageview
        if(cardsArray[card] == 100) {
            iv.setImageResource(image10);
        }
        else if(cardsArray[card] == 101) {
            iv.setImageResource(image11);
        }
        else if(cardsArray[card] == 102){
            iv.setImageResource(image12);
        }
        else if(cardsArray[card] == 103){
            iv.setImageResource(image13);
        }
        else if(cardsArray[card] == 104){
            iv.setImageResource(image14);
        }
        else if(cardsArray[card] == 105) {
            iv.setImageResource(image15);
        }
        else if(cardsArray[card] == 106){
            iv.setImageResource(image16);
        }
        else if(cardsArray[card] == 107){
            iv.setImageResource(image17);
        }
        else if(cardsArray[card] == 108){
            iv.setImageResource(image18);
        }
        else if(cardsArray[card] == 109){
            iv.setImageResource(image19);
        }
        else if(cardsArray[card] == 200) {
            iv.setImageResource(image20);
        }
        else if(cardsArray[card] == 201){
            iv.setImageResource(image21);
        }
        else if(cardsArray[card] == 202){
            iv.setImageResource(image22);
        }
        else if(cardsArray[card] == 203){
            iv.setImageResource(image23);
        }
        else if(cardsArray[card] == 204) {
            iv.setImageResource(image24);
        }
        else if(cardsArray[card] == 205){
            iv.setImageResource(image25);
        }
        else if(cardsArray[card] == 206){
            iv.setImageResource(image26);
        }
        else if(cardsArray[card] == 207){
            iv.setImageResource(image27);
        }
        else if(cardsArray[card] == 208){
            iv.setImageResource(image28);
        }
        else if(cardsArray[card] == 209){
            iv.setImageResource(image29);
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

            iv11.setEnabled(false);
            iv12.setEnabled(false);
            iv13.setEnabled(false);
            iv14.setEnabled(false);
            iv21.setEnabled(false);
            iv22.setEnabled(false);
            iv23.setEnabled(false);
            iv24.setEnabled(false);
            iv31.setEnabled(false);
            iv32.setEnabled(false);
            iv33.setEnabled(false);
            iv34.setEnabled(false);
            iv41.setEnabled(false);
            iv42.setEnabled(false);
            iv43.setEnabled(false);
            iv44.setEnabled(false);
            iv51.setEnabled(false);
            iv52.setEnabled(false);
            iv53.setEnabled(false);
            iv54.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    check();
                }
            }, 1000);
        }
    }

    private void check(){
        if(firstCard == secondCard){
            if(clickedFirst == 0){
                iv11.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 1){
                iv12.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 2){
                iv13.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 3){
                iv14.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 4){
                iv21.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 5){
                iv22.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 6){
                iv23.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 7){
                iv24.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 8){
                iv31.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 9){
                iv32.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 10){
                iv33.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 11){
                iv34.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 12){
                iv41.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 13){
                iv42.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 14){
                iv43.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 15){
                iv44.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 16){
                iv51.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 17){
                iv52.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 18){
                iv53.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 19){
                iv54.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond == 0){
                iv11.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 1){
                iv12.setVisibility(View.INVISIBLE);
            }else if(clickedSecond == 2){
                iv13.setVisibility(View.INVISIBLE);
            }else if(clickedSecond == 3){
                iv14.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 4){
                iv21.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 5){
                iv22.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 6){
                iv23.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 7){
                iv24.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 8){
                iv31.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 9){
                iv32.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 10){
                iv33.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 11){
                iv34.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 12){
                iv41.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 13){
                iv42.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 14){
                iv43.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 15){
                iv44.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 16){
                iv51.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 17){
                iv52.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 18){
                iv53.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 19){
                iv54.setVisibility(View.INVISIBLE);
            }

            playerPoints++;
            p1Text.setText("Player points: " + playerPoints);
            iv11.setEnabled(true);
            iv12.setEnabled(true);
            iv13.setEnabled(true);
            iv14.setEnabled(true);
            iv21.setEnabled(true);
            iv22.setEnabled(true);
            iv23.setEnabled(true);
            iv24.setEnabled(true);
            iv31.setEnabled(true);
            iv32.setEnabled(true);
            iv33.setEnabled(true);
            iv34.setEnabled(true);
            iv41.setEnabled(true);
            iv42.setEnabled(true);
            iv43.setEnabled(true);
            iv44.setEnabled(true);
            iv51.setEnabled(true);
            iv52.setEnabled(true);
            iv53.setEnabled(true);
            iv54.setEnabled(true);
        }
        gameOver();
    }

    private void gameOver(){
        if(iv11.getVisibility() == View.INVISIBLE &&
                iv12.getVisibility() == View.INVISIBLE &&
                iv13.getVisibility() == View.INVISIBLE &&
                iv14.getVisibility() == View.INVISIBLE &&
                iv21.getVisibility() == View.INVISIBLE &&
                iv22.getVisibility() == View.INVISIBLE &&
                iv23.getVisibility() == View.INVISIBLE &&
                iv24.getVisibility() == View.INVISIBLE &&
                iv31.getVisibility() == View.INVISIBLE &&
                iv32.getVisibility() == View.INVISIBLE &&
                iv33.getVisibility() == View.INVISIBLE &&
                iv34.getVisibility() == View.INVISIBLE &&
                iv41.getVisibility() == View.INVISIBLE &&
                iv42.getVisibility() == View.INVISIBLE &&
                iv43.getVisibility() == View.INVISIBLE &&
                iv44.getVisibility() == View.INVISIBLE &&
                iv51.getVisibility() == View.INVISIBLE &&
                iv52.getVisibility() == View.INVISIBLE &&
                iv53.getVisibility() == View.INVISIBLE &&
                iv54.getVisibility() == View.INVISIBLE) {
            loadHighScores();
            if(checkHighScore() == false) {
                //Toast.makeText(getActivity(), "Game Over", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game20Screen.this);
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
            }
            else{
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game20Screen.this);
                alertDialogBuilder
                        .setTitle("NEW HIGH SCORE!\nPlayer Points: " + playerPoints)
                        .setMessage("Enter Name to save High Score:")
                        .setCancelable(false);

                final EditText nameInput = new EditText(Game20Screen.this);
                int maxLength = 8;
                nameInput.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
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
        image10 = R.drawable.rain;
        image11 = R.drawable.sun;
        image12 = R.drawable.cloudy;
        image13 = R.drawable.cloudy2;
        image14 = R.drawable.cloudy3;
        image15 = R.drawable.lightning;
        image16 = R.drawable.snow;
        image17 = R.drawable.storm;
        image18 = R.drawable.night;
        image19 = R.drawable.rain2;
        image20 = R.drawable.tworain;
        image21 = R.drawable.twosun;
        image22 = R.drawable.twocloudy;
        image23 = R.drawable.twocloudy2;
        image24 = R.drawable.twocloudy3;
        image25 = R.drawable.twolightning;
        image26 = R.drawable.twosnow;
        image27 = R.drawable.twostorm;
        image28 = R.drawable.twonight;
        image29 = R.drawable.tworain2;
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
                Toast.makeText(Game20Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Game20Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(Game20Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
