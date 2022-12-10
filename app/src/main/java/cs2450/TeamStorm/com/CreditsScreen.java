package cs2450.TeamStorm.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class CreditsScreen extends AppCompatActivity {
    // Audio player object to play background music
    private static MediaPlayer player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_screen);

        // create ancestral navigation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (NavUtils.getParentActivityName(this) != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        // set MediaPlayer
        player = MainActivity.getPlayer();

        // resume audio after mute
        ImageButton resume = (ImageButton) findViewById(R.id.creditsMusicButton);
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
        ImageButton stop = (ImageButton) findViewById(R.id.creditsUnmuteButton);
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(player != null){
                    player.pause();
                }
            }
        });
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