package cs2450.TeamStorm.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class GameScreen extends AppCompatActivity {
    // Audio player object to play background music
    private static MediaPlayer player= null;

    int cardAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        // create ancestral navigation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (NavUtils.getParentActivityName(this) != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        // set MediaPlayer
        player = MainActivity.getPlayer();

        EditText tileAmountBtn = (EditText) findViewById(R.id.tileAmountBtn);

        // start new game with selected number of cards
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
                        startActivity(new Intent(GameScreen.this, Game4Screen.class));
                    }
                    else if (cardAmount == 6){
                        startActivity(new Intent(GameScreen.this, Game6Screen.class));
                    }
                    else if (cardAmount == 8){
                        startActivity(new Intent(GameScreen.this, Game8Screen.class));
                    }
                    else if (cardAmount == 10){
                        startActivity(new Intent(GameScreen.this, Game10Screen.class));
                    }
                    else if (cardAmount == 12){
                        startActivity(new Intent(GameScreen.this, Game12Screen.class));
                    }
                    else if (cardAmount == 14) {
                        startActivity(new Intent(GameScreen.this, Game14Screen.class));
                    }
                    else if (cardAmount == 16){
                        startActivity(new Intent(GameScreen.this, Game16Screen.class));
                    }
                    else if (cardAmount == 18){
                        startActivity(new Intent(GameScreen.this, Game18Screen.class));
                    }
                    else if (cardAmount == 20){
                        startActivity(new Intent(GameScreen.this, Game20Screen.class));
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