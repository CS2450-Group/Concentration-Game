package cs2450.TeamStorm.com;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;

public class Game4Screen extends AppCompatActivity {
    // Audio player object to play background music
    private static MediaPlayer player;

    ImageView iv1, iv2, iv3, iv4;

    TextView p1Text;

    Integer[] cardsArray = {101, 102, 201, 202};

    int image1, image2, image3, image4;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    int playerPoints = 0;

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

        Button tryAgain = (Button)findViewById(R.id.tryAgainButton);

        tryAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                iv1.setImageResource(R.drawable.card);
                iv2.setImageResource(R.drawable.card);
                iv3.setImageResource(R.drawable.card);
                iv4.setImageResource(R.drawable.card);

                iv1.setEnabled(true);
                iv2.setEnabled(true);
                iv3.setEnabled(true);
                iv4.setEnabled(true);
            }
        });

        p1Text = (TextView) findViewById(R.id.pointsText);

        iv1 = (ImageView) findViewById(R.id.imageView);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv3 = (ImageView) findViewById(R.id.imageView3);
        iv4 = (ImageView) findViewById(R.id.imageView4);

        iv1.setTag("0");
        iv2.setTag("1");
        iv3.setTag("2");
        iv4.setTag("3");

        //set images to image variables
        frontOfCards();

        Collections.shuffle(Arrays.asList(cardsArray));

        iv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv1, theCard);
            }
        });

        iv2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv2, theCard);
            }
        });

        iv3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv3, theCard);
            }
        });

        iv4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int theCard = Integer.parseInt((String) view.getTag());
                setImageAndCheck(iv4, theCard);
            }
        });

    }

    private void setImageAndCheck(ImageView iv, int card){
        //set images to imageview
        if(cardsArray[card] == 101) {
            iv.setImageResource(image1);
        }
        else if(cardsArray[card] == 102){
            iv.setImageResource(image2);
        }
        else if(cardsArray[card] == 201){
            iv.setImageResource(image3);
        }
        else if(cardsArray[card] == 202){
            iv.setImageResource(image4);
        }
        //check selected image
        if(cardNumber == 1){
            firstCard = cardsArray[card];
            if(firstCard > 200){
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;

            iv.setEnabled(false);
        }else if(cardNumber == 2) {
            secondCard = cardsArray[card];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            iv1.setEnabled(false);
            iv2.setEnabled(false);
            iv3.setEnabled(false);
            iv4.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    check();
                }
            }, 1000);
        }
    }

    private void check(){
        if(firstCard == secondCard){
            if(clickedFirst == 0){
                iv1.setVisibility(View.INVISIBLE);
            } else if(clickedFirst == 1){
                iv2.setVisibility(View.INVISIBLE);
            }else if(clickedFirst == 2){
                iv3.setVisibility(View.INVISIBLE);
            }else if(clickedFirst == 3){
                iv4.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond == 0){
                iv1.setVisibility(View.INVISIBLE);
            } else if(clickedSecond == 1){
                iv2.setVisibility(View.INVISIBLE);
            }else if(clickedSecond == 2){
                iv3.setVisibility(View.INVISIBLE);
            }else if(clickedSecond == 3){
                iv4.setVisibility(View.INVISIBLE);
            }

            playerPoints++;
            p1Text.setText("Player points: " + playerPoints);
            iv1.setEnabled(true);
            iv2.setEnabled(true);
            iv3.setEnabled(true);
            iv4.setEnabled(true);
        }
        gameOver();
    }

    private void gameOver(){
        if(iv1.getVisibility() == View.INVISIBLE &&
                iv2.getVisibility() == View.INVISIBLE &&
                iv3.getVisibility() == View.INVISIBLE &&
                iv4.getVisibility() == View.INVISIBLE) {
            //Toast.makeText(getActivity(), "Game Over", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Game4Screen.this);
            alertDialogBuilder
                    .setMessage("GAME OVER!\nPlayer Points: " + playerPoints)
                    .setCancelable(false)
                    .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), GameScreen.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void frontOfCards(){
        image1 = R.drawable.rain;
        image2 = R.drawable.sun;
        image3 = R.drawable.tworain;
        image4 = R.drawable.twosun;
    }

}
