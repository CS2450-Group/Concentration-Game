package cs2450.TeamStorm.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class HighScoreScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Audio player object to play background music
    private static MediaPlayer player;

    // Text fields
    private TextView highScore1;
    private TextView highScore2;
    private TextView highScore3;
    private Spinner tiles;

    private String[][] scores = new String[9][6]; //each row contains an array for the tile amount. Ex: 4 tiles would be String[4][x]
                                          //for each array, even numbers are names and odd numbers are scores

    // Player's choice
    private int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_screen);

        // set MediaPlayer
        player = MainActivity.getPlayer();

        // initialize text fields
        highScore1 = (TextView) findViewById(R.id.highScore1);
        highScore2 = (TextView) findViewById(R.id.highScore2);
        highScore3 = (TextView) findViewById(R.id.highScore3);
        tiles = (Spinner) findViewById(R.id.tileSelect);

        //reads scores.txt
        loadHighScores();

        //testScores();

        // create adapter to use custom spinner/drop down menu
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.dropMenuOptions,
                R.layout.highscore_spinner_layout);
        adapter.setDropDownViewResource(R.layout.highscore_dropdown_layout);
        tiles.setAdapter(adapter);

        // set actions when item is selected from spinner/drop down menu
        tiles.setOnItemSelectedListener(this);

        // resume audio after mute
        ImageButton resume = (ImageButton) findViewById(R.id.highScoresMusicButton);
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
        ImageButton stop = (ImageButton) findViewById(R.id.highscoreUnmuteButton);
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(player != null){
                    player.pause();
                }
            }
        });

        Button resetButton = (Button)findViewById(R.id.resetButtton);

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HighScoreScreen.this);
                alertDialogBuilder
                        .setMessage("Are you sure you want to reset scores?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                resetHighScores();
                                Toast.makeText(HighScoreScreen.this, "Scores Reset!", Toast.LENGTH_SHORT).show();
                                setHighScores(0);
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    //reads scores.txt
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

                Toast.makeText(HighScoreScreen.this, "Scores Loaded", Toast.LENGTH_SHORT).show();
            }
            catch (IOException e) {
                Toast.makeText(HighScoreScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        //if no file exists, create one
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

                Toast.makeText(HighScoreScreen.this, "Score File Created", Toast.LENGTH_SHORT).show();
            }
            catch (IOException e){
                Toast.makeText(HighScoreScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //displays the scores on the textfields
    public void setHighScores(int choice){
        String string1 = scores[choice][0] + " - " + scores[choice][1];
        String string2 = scores[choice][2] + " - " + scores[choice][3];
        String string3 = scores[choice][4] + " - " + scores[choice][5];
        highScore1.setVisibility(View.VISIBLE);
        highScore2.setVisibility(View.VISIBLE);
        highScore3.setVisibility(View.VISIBLE);
        highScore1.setText(string1);
        highScore2.setText(string2);
        highScore3.setText(string3);
        //Toast.makeText(HighScoreScreen.this, "Scores Set", Toast.LENGTH_SHORT).show();
    }

    //converts the user choice into the given row in the 2d array Scores
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

    public void resetHighScores(){
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

            Toast.makeText(HighScoreScreen.this, "Scores Reset!", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Toast.makeText(HighScoreScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void testScores(){
        scores[0][0] = "ABC";
        scores[0][1] = "4";
        scores[0][2] = "DEF";
        scores[0][3] = "3";
        scores[0][4] = "GHI";
        scores[0][5] = "2";

        scores[4][0] = "CBA";
        scores[4][1] = "12";
        scores[4][2] = "FDE";
        scores[4][3] = "11";
        scores[4][4] = "IHG";
        scores[4][5] = "10";

        scores[8][0] = "JKL";
        scores[8][1] = "20";
        scores[8][2] = "MNO";
        scores[8][3] = "19";
        scores[8][4] = "PQR";
        scores[8][5] = "18";
    }

    // load high scores based on selected item from spinner/drop down menu
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String listItem = parent.getItemAtPosition(position).toString();
        choice = Integer.parseInt(listItem.substring(0,listItem.indexOf(" ")));
        setHighScores(convertChoice(choice));
    }

    // load highscores for smallest tile set - 4
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        setHighScores(4);
    }
}