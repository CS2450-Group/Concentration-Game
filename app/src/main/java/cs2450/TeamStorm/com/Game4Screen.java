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

public class Game4Screen extends AppCompatActivity {
    // Audio player object to play background music
    private static MediaPlayer player;

    ImageView iv1, iv2, iv3, iv4;

    TextView p1Text;

    Integer[] cardsArray = {101, 102, 201, 202};
    String[][] scores = new String[9][6];

    int image1, image2, image3, image4;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    int playerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4_screen);

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
                startActivity(new Intent(Game4Screen.this, GameScreen.class));
            }
        });

        Button endGame = (Button)findViewById(R.id.endGameButton);
        endGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                iv1.setImageResource(image1);
                iv2.setImageResource(image2);
                iv3.setImageResource(image3);
                iv4.setImageResource(image4);

                iv1.setEnabled(false);
                iv2.setEnabled(false);
                iv3.setEnabled(false);
                iv4.setEnabled(false);
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

                iv1.setEnabled(true);
                iv2.setEnabled(true);
                iv3.setEnabled(true);
                iv4.setEnabled(true);

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

        iv1.setTag("0");
        iv2.setTag("1");
        iv3.setTag("2");
        iv4.setTag("3");

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

    }

    private void setImageAndCheck(ImageView iv, int card){
        //set images to imageview
        if(cardsArray[card] == 101) {
            iv.setImageResource(image1);
        }
        else if(cardsArray[card] == 102){
            iv.setImageResource(image2);
        }
        else if(cardsArray[card] == 201){
            iv.setImageResource(image3);
        }
        else if(cardsArray[card] == 202){
            iv.setImageResource(image4);
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
                iv1.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 1){
                iv2.setVisibility(View.INVISIBLE);
            }else if(clickedFirst == 2){
                iv3.setVisibility(View.INVISIBLE);
            }else if(clickedFirst == 3){
                iv4.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond == 0){
                iv1.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 1){
                iv2.setVisibility(View.INVISIBLE);
            }else if(clickedSecond == 2){
                iv3.setVisibility(View.INVISIBLE);
            }else if(clickedSecond == 3){
                iv4.setVisibility(View.INVISIBLE);
            }

            playerPoints++;
            p1Text.setText("Player points: " + playerPoints);
            iv1.setEnabled(true);
            iv2.setEnabled(true);
            iv3.setEnabled(true);
            iv4.setEnabled(true);
        }
        gameOver();
    }

    private void gameOver(){
        if(iv1.getVisibility() == View.INVISIBLE &&
                iv2.getVisibility() == View.INVISIBLE &&
                iv3.getVisibility() == View.INVISIBLE &&
                iv4.getVisibility() == View.INVISIBLE) {
            loadHighScores();
            if(checkHighScore() == false) {
                //Toast.makeText(getActivity(), "Game Over", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game4Screen.this);
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game4Screen.this);
                alertDialogBuilder
                        .setTitle("NEW HIGH SCORE!\nPlayer Points: " + playerPoints)
                        .setMessage("Enter Name to save High Score:")
                        .setCancelable(false);

                final EditText nameInput = new EditText(Game4Screen.this);
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
        image1 = R.drawable.rain;
        image2 = R.drawable.sun;
        image3 = R.drawable.tworain;
        image4 = R.drawable.twosun;
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
                Toast.makeText(Game4Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Game4Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(Game4Screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
