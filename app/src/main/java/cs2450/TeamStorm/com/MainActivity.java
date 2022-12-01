package cs2450.TeamStorm.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set click listener for start button
        Button start = (Button) findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameScreen.class));
            }
        });
        // set click listener for highscore button
        Button highscore = (Button) findViewById(R.id.highscoreButton);
        highscore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HighScoreScreen.class));
            }
        });
        // set click listener for settings button
        Button settings = (Button) findViewById(R.id.settingsButton1);
        settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsScreen.class));
            }
        });
        // set click listener for credits button
        Button credits = (Button) findViewById(R.id.creditsButton);
        credits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreditsScreen.class));
            }
        });
    }
}