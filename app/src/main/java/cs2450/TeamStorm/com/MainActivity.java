package cs2450.TeamStorm.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    // Audio player object to play background music
    private static MediaPlayer player = null;

    // Creates activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // start music
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.lofirain);
            player.setLooping(true);
            player.start();
        }

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
        // set click listener for credits button
        Button credits = (Button) findViewById(R.id.creditsButton);
        credits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreditsScreen.class));
            }
        });

        // mute audio
        ImageButton stop = (ImageButton) findViewById(R.id.mainMusicButton);
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
        ImageButton resume = (ImageButton) findViewById(R.id.mainUnmuteButton);
        resume.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (player != null) {
                    player.pause();
                }
            }
        });
    }

    // share MediaPlayer data with other activities
    public static MediaPlayer getPlayer() {
        return player;
    }
}