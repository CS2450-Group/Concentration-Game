package cs2450.TeamStorm.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class GameScreen extends AppCompatActivity {
    // Audio player object to play background music
    private static MediaPlayer player= null;

    int cardAmount;

    String[][] scores = new String[9][6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        // set MediaPlayer
        player = MainActivity.getPlayer();

        EditText tileAmountBtn = (EditText) findViewById(R.id.tileAmountBtn);



        Button newGameButton = (Button) findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (tileAmountBtn.getText().toString().length() == 0){
                    Toast.makeText(GameScreen.this, "Enter an even number 4-20", Toast.LENGTH_SHORT).show();
                }
                else{
                    cardAmount = Integer.parseInt(tileAmountBtn.getText().toString());
                    if (cardAmount < 4 || cardAmount > 20){
                        Toast.makeText(GameScreen.this, "Enter an even number 4-20", Toast.LENGTH_SHORT).show();
                    }
                    else if ((cardAmount % 2) != 0){
                        Toast.makeText(GameScreen.this, "Enter an even number 4-20", Toast.LENGTH_SHORT).show();
                    }
                    else if (cardAmount == 4){
                        Button newGameButton2 = (Button) findViewById(R.id.newGameButton2);
                        newGameButton2.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                startActivity(new Intent(GameScreen.this, Game4Screen.class));
                            }
                        });
                    }
                    else if (cardAmount == 6){
                        setContentView(R.layout.activity_game6_screen);
                        Button newGameButton2 = (Button) findViewById(R.id.newGameButton2);
                        newGameButton2.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                startActivity(new Intent(GameScreen.this, GameScreen.class));
                            }
                        });
                    }
                    else if (cardAmount == 8){
                        setContentView(R.layout.activity_game8_screen);
                        Button newGameButton2 = (Button) findViewById(R.id.newGameButton2);
                        newGameButton2.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                startActivity(new Intent(GameScreen.this, GameScreen.class));
                            }
                        });
                    }
                    else if (cardAmount == 10){
                        setContentView(R.layout.activity_game10_screen);
                        Button newGameButton2 = (Button) findViewById(R.id.newGameButton2);
                        newGameButton2.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                startActivity(new Intent(GameScreen.this, GameScreen.class));
                            }
                        });
                    }
                    else if (cardAmount == 12){
                        setContentView(R.layout.activity_game12_screen);
                        Button newGameButton2 = (Button) findViewById(R.id.newGameButton2);
                        newGameButton2.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                startActivity(new Intent(GameScreen.this, GameScreen.class));
                            }
                        });
                    }
                    else if (cardAmount == 14){
                        setContentView(R.layout.activity_game14_screen);
                        Button newGameButton2 = (Button) findViewById(R.id.newGameButton2);
                        newGameButton2.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                startActivity(new Intent(GameScreen.this, GameScreen.class));
                            }
                        });
                    }
                    else if (cardAmount == 16){
                        setContentView(R.layout.activity_game16_screen);
                        Button newGameButton2 = (Button) findViewById(R.id.newGameButton2);
                        newGameButton2.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                startActivity(new Intent(GameScreen.this, GameScreen.class));
                            }
                        });
                    }
                    else if (cardAmount == 18){
                        setContentView(R.layout.activity_game18_screen);
                        Button newGameButton2 = (Button) findViewById(R.id.newGameButton2);
                        newGameButton2.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                startActivity(new Intent(GameScreen.this, GameScreen.class));
                            }
                        });
                    }
                    else if (cardAmount == 20){
                        setContentView(R.layout.activity_game20_screen);
                        Button newGameButton2 = (Button) findViewById(R.id.newGameButton2);
                        newGameButton2.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                startActivity(new Intent(GameScreen.this, GameScreen.class));
                            }
                        });
                    }

                }
            }
        });

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


    }

    //checks and saves high scores. call this method everytime a game is ended.
    public void saveHighScore(String playerName, int playerPoints, int cardAmount){
        loadHighScores();
        int scoreType = convertChoice(cardAmount);

        int scoreA = Integer.parseInt(scores[scoreType][1]);
        int scoreB = Integer.parseInt(scores[scoreType][3]);
        int scoreC = Integer.parseInt(scores[scoreType][5]);

        if (playerPoints <= scoreC){
            //no new high score
            Toast.makeText(GameScreen.this, "No new high score.", Toast.LENGTH_SHORT).show();
        }
        else if (playerPoints > scoreC && playerPoints <= scoreB){
            //high score is placed in rank 3
            scores[scoreType][4] = playerName;
            scores[scoreType][5] = Integer.toString(playerPoints);

            writeHighScores();
            Toast.makeText(GameScreen.this, "New high score saved!", Toast.LENGTH_SHORT).show();
        }
        else if (playerPoints > scoreB && playerPoints <= scoreA){
            //move rank 2 scores to rank 3
            scores[scoreType][4] = scores[scoreType][2];
            scores[scoreType][5] = scores[scoreType][3];

            //high score is placed in rank 2
            scores[scoreType][2] = playerName;
            scores[scoreType][3] = Integer.toString(playerPoints);

            writeHighScores();
            Toast.makeText(GameScreen.this, "New high score saved!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(GameScreen.this, "New high score saved!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(GameScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
                Toast.makeText(GameScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(GameScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
}