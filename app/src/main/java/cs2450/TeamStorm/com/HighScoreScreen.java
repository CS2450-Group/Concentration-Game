package cs2450.TeamStorm.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HighScoreScreen extends AppCompatActivity {

    TextView highScore1;
    TextView highScore2;
    TextView highScore3;
    EditText tileAmount;
    String[][] scores = new String[9][6];
    int choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_screen);

        highScore1 = (TextView) findViewById(R.id.highScore1);
        highScore2 = (TextView) findViewById(R.id.highScore2);
        highScore3 = (TextView) findViewById(R.id.highScore3);
        tileAmount = (EditText) findViewById(R.id.tileAmount);

        Button loadScores = (Button) findViewById(R.id.loadScores);
        loadScores.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choice = Integer.parseInt(tileAmount.getText().toString());
                if (choice < 4 || choice > 20){
                    Toast.makeText(HighScoreScreen.this, "Enter an even number 4-20", Toast.LENGTH_SHORT).show();
                }
                else if ((choice % 2) != 0){
                    Toast.makeText(HighScoreScreen.this, "Enter an even number 4-20", Toast.LENGTH_SHORT).show();
                }
                else{
                    loadHighScores(convertChoice(choice));
                }
            }
        });
    }

    public void loadHighScores(int choice){
        File file = getApplicationContext().getFileStreamPath("Scores.txt");
        String line;



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

                String string1 = scores[choice][0] + " - " + scores[choice][1];
                String string2 = scores[choice][2] + " - " + scores[choice][3];
                String string3 = scores[choice][4] + " - " + scores[choice][5];
                highScore1.setText(string1);
                highScore2.setText(string2);
                highScore3.setText(string3);

                Toast.makeText(HighScoreScreen.this, "Scores Loaded", Toast.LENGTH_SHORT).show();
            }
            catch (IOException e) {
                Toast.makeText(HighScoreScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else{
            try{
                FileOutputStream file2 = openFileOutput("Scores.txt", MODE_PRIVATE);
                OutputStreamWriter outputFile = new OutputStreamWriter(file2);

                for(int i = 0; i < 9; i++){
                    for (int j = 0; j < 6; j += 2 ){
                        scores[i][j] = "empty";
                    }
                }

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