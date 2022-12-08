package cs2450.TeamStorm.com;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Game4Screen extends AppCompatActivity {
    // Audio player object to play background music
    private static MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4_screen);

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
    }
}
