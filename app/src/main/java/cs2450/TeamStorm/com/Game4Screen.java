package cs2450.TeamStorm.com;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;


public class Game4Screen extends AppCompatActivity {
    // Audio player object to play background music
    private static MediaPlayer player;

    // Gameplay controller
    private static Gameplay game;

    // card variables
    private ImageView[] iv = new ImageView[4];

    // text area to display amount of points
    private TextView p1Text;

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

        // set gameplay
        game = new Gameplay(4);

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

        //set variable to textview for point tracking
        p1Text = (TextView) findViewById(R.id.pointsText);

        //set variables to imageview
        iv[0] = (ImageView) findViewById(R.id.imageView);
        iv[1] = (ImageView) findViewById(R.id.imageView2);
        iv[2] = (ImageView) findViewById(R.id.imageView3);
        iv[3] = (ImageView) findViewById(R.id.imageView4);

        //set tags for the variables
        iv[0].setTag("0");
        iv[1].setTag("1");
        iv[2].setTag("2");
        iv[3].setTag("3");

        //call function on click
        iv[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                game.setImageAndCheck(Game4Screen.this, iv[0], theCard, p1Text, iv);
            }
        });

        iv[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                game.setImageAndCheck(Game4Screen.this, iv[1], theCard, p1Text, iv);
            }
        });

        iv[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                game.setImageAndCheck(Game4Screen.this, iv[2], theCard, p1Text, iv);
            }
        });

        iv[3].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                game.setImageAndCheck(Game4Screen.this, iv[3], theCard, p1Text, iv);
            }
        });

        // create a new game with the option to select a new card count
        Button newGame = (Button)findViewById(R.id.newGameButton2);
        newGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Game4Screen.this, GameScreen.class));
            }
        });

        // end game
        Button endGame = (Button)findViewById(R.id.endGameButton);
        endGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                game.showCardAnswers(iv);
            }
        });

        // play the game again with the same number of cards
        Button tryAgain = (Button)findViewById(R.id.tryAgainButton);
        tryAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                game.reset(iv, p1Text);
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
