package cs2450.TeamStorm.com;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CreditsScreen extends AppCompatActivity {
    // Audio player object to play background music
    //private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_screen);
/*
        ImageButton stop = (ImageButton) findViewById(R.id.creditsMusicButton);
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!player.isPlaying()){
                    player.start();
                }
            }
        });

        ImageButton resume = (ImageButton) findViewById(R.id.creditsUnmuteButton);
        resume.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(player != null){
                    player.pause();
                }
            }
        }); */
    }
}