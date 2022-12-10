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

public class Game6Screen extends AppCompatActivity {
    // Audio player object to play background music
    private static MediaPlayer player;

    ImageView iv11, iv12, iv21, iv22, iv31, iv32;

    TextView p1Text;

    Integer[] cardsArray = {100, 101, 102, 200, 201, 202};
    String[][] scores = new String[9][6];

    int image10, image11, image12, image20, image21, image22;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    int playerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6_screen);

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
                iv21.setImageResource(R.drawable.card);
                iv22.setImageResource(R.drawable.card);
                iv31.setImageResource(R.drawable.card);
                iv32.setImageResource(R.drawable.card);

                iv11.setEnabled(true);
                iv12.setEnabled(true);
                iv21.setEnabled(true);
                iv22.setEnabled(true);
                iv31.setEnabled(true);
                iv32.setEnabled(true);

                if(playerPoints > 0){
                    playerPoints--;
                    p1Text.setText("Player points: " + playerPoints);
                }
            }
        });

        p1Text = (TextView) findViewById(R.id.pointsText);

        iv11 = (ImageView) findViewById(R.id.imageView);
        iv12 = (ImageView) findViewById(R.id.imageView2);
        iv21 = (ImageView) findViewById(R.id.imageView3);
        iv22 = (ImageView) findViewById(R.id.imageView4);
        iv31 = (ImageView) findViewById(R.id.imageView5);
        iv32 = (ImageView) findViewById(R.id.imageView6);


        iv11.setTag("0");
        iv12.setTag("1");
        iv21.setTag("2");
        iv22.setTag("3");
        iv31.setTag("4");
        iv32.setTag("5");

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
    }

    private void setImageAndCheck(ImageView iv, int card){
        //set images to imageview
        if(cardsArray[card] == 100) {
            iv.setImageResource(image10);
        }
        else if(cardsArray[card] == 101){
            iv.setImageResource(image11);
        }
        else if(cardsArray[card] == 102){
            iv.setImageResource(image12);
        }
        else if(cardsArray[card] == 200){
            iv.setImageResource(image20);
        }
        else if(cardsArray[card] == 201){
            iv.setImageResource(image21);
        }
        else if(cardsArray[card] == 202){
            iv.setImageResource(image22);
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
            iv21.setEnabled(false);
            iv22.setEnabled(false);
            iv31.setEnabled(false);
            iv32.setEnabled(false);

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
                iv11.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 1) {
                iv12.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 2) {
                iv21.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 3) {
                iv22.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 4) {
                iv31.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 5) {
                iv32.setVisibility(View.INVISIBLE);
            }

            if (clickedSecond == 0) {
                iv11.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 1) {
                iv12.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 2) {
                iv21.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 3) {
                iv22.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 4) {
                iv31.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 5) {
                iv32.setVisibility(View.INVISIBLE);
            }

            playerPoints++;
            p1Text.setText("Player points: " + playerPoints);
            iv11.setEnabled(true);
            iv12.setEnabled(true);
            iv21.setEnabled(true);
            iv22.setEnabled(true);
            iv31.setEnabled(true);
            iv32.setEnabled(true);


        }
        gameOver();
    }

    private void gameOver () {
        if (iv11.getVisibility() == View.INVISIBLE &&
                iv12.getVisibility() == View.INVISIBLE &&
                iv21.getVisibility() == View.INVISIBLE &&
                iv22.getVisibility() == View.INVISIBLE &&
                iv31.getVisibility() == View.INVISIBLE &&
                iv32.getVisibility() == View.INVISIBLE) {
            loadHighScores();
            if (checkHighScore() == false) {
                //Toast.makeText(getActivity(), "Game Over", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game6Screen.this);
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game6Screen.this);
                alertDialogBuilder
                        .setTitle("NEW HIGH SCORE!\nPlayer Points: " + playerPoints)
                        .setMessage("Enter Name to save High Score:")
                        .setCancelable(false);

                final EditText nameInput = new EditText(Game6Screen.this);
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
        image10 = R.drawable.rain;
        image11 = R.drawable.sun;
        image12 = R.drawable.cloudy;
        image20 = R.drawable.tworain;
        image21 = R.drawable.twosun;
        image22 = R.drawable.twocloudy;
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
                Toast.makeText(Game6Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Game6Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(Game6Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

