package cs2450.TeamStorm.com;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {
    // Audio player object to play background music
    private MediaPlayer player;

    androidx.appcompat.widget.AppCompatButton cardButton1, cardButton2, cardButton3, cardButton4, cardButton5,
            cardButton6, cardButton7, cardButton8, cardButton9, cardButton10,
            cardButton11, cardButton12, cardButton13, cardButton14, cardButton15,
            cardButton16, cardButton17, cardButton18, cardButton19, cardButton20;

    String words[] = {"BAT", "HIPPO", "PANDA", "CAT", "DOG", "ELEPHANT", "TIGER", "LION", "BEAR", "DOLPHIN"};

    int cardArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    int card1, card2, card3, card4, card5, card6, card7, card8, card9, card10,
            card11, card12, card13, card14, card15, card16, card17, card18, card19, card20;

    int firstCard, secondCard;
    int firstClick, secondClick;
    int cardNumber = 1;

    int playerPoints = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        cardButton1 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton1);
        cardButton2 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton2);
        cardButton3 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton3);
        cardButton4 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton4);
        cardButton5 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton5);
        cardButton6 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton6);
        cardButton7 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton7);
        cardButton8 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton8);
        cardButton9 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton9);
        cardButton10 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton10);
        cardButton11 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton11);
        cardButton12 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton12);
        cardButton13 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton13);
        cardButton14 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton14);
        cardButton15 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton15);
        cardButton16 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton16);
        cardButton17 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton17);
        cardButton18 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton18);
        cardButton19 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton19);
        cardButton20 = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.cardButton20);

        cardButton1.setTag("1");
        cardButton2.setTag("2");
        cardButton3.setTag("3");
        cardButton4.setTag("4");
        cardButton5.setTag("5");
        cardButton6.setTag("6");
        cardButton7.setTag("7");
        cardButton8.setTag("8");
        cardButton9.setTag("9");
        cardButton10.setTag("10");
        cardButton11.setTag("11");
        cardButton12.setTag("12");
        cardButton13.setTag("13");
        cardButton14.setTag("14");
        cardButton15.setTag("15");
        cardButton16.setTag("16");
        cardButton17.setTag("17");
        cardButton18.setTag("18");
        cardButton19.setTag("19");
        cardButton20.setTag("20");

        TextView word1 = (TextView)findViewById(R.id.backCard1);
        TextView word2 = (TextView)findViewById(R.id.backCard2);
        TextView word3 = (TextView)findViewById(R.id.backCard30);
        TextView word4 = (TextView)findViewById(R.id.backCard31);
        TextView word5 = (TextView)findViewById(R.id.backCard32);
        TextView word6 = (TextView)findViewById(R.id.backCard33);
        TextView word7 = (TextView)findViewById(R.id.backCard34);
        TextView word8 = (TextView)findViewById(R.id.backCard35);
        TextView word9 = (TextView)findViewById(R.id.backCard36);
        TextView word10 = (TextView)findViewById(R.id.backCard37);
        TextView word11 = (TextView)findViewById(R.id.backCard20);
        TextView word12 = (TextView)findViewById(R.id.backCard21);
        TextView word13 = (TextView)findViewById(R.id.backCard22);
        TextView word14 = (TextView)findViewById(R.id.backCard23);
        TextView word15 = (TextView)findViewById(R.id.backCard24);
        TextView word16 = (TextView)findViewById(R.id.backCard25);
        TextView word17 = (TextView)findViewById(R.id.backCard26);
        TextView word18 = (TextView)findViewById(R.id.backCard27);
        TextView word19 = (TextView)findViewById(R.id.backCard28);
        TextView word20 = (TextView)findViewById(R.id.backCard29);

        word1.setText("DOG");
        word2.setText("BAT");
        word3.setText("HIPPO");
        word4.setText("CAT");
        word5.setText("CAT");
        word6.setText("BAT");
        word7.setText("HIPPO");
        word8.setText("LION");
        word9.setText("PANDA");
        word10.setText("ELEPHANT");
        word11.setText("DOLPHIN");
        word12.setText("TIGER");
        word13.setText("PANDA");
        word14.setText("DOG");
        word15.setText("ELEPHANT");
        word16.setText("BEAR");
        word17.setText("TIGER");
        word18.setText("DOLPHIN");
        word19.setText("LION");
        word20.setText("BEAR");





        ImageButton stop = (ImageButton) findViewById(R.id.gameMusicButton);
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!player.isPlaying()){
                    player.start();
                }
            }
        });

        ImageButton resume = (ImageButton) findViewById(R.id.gameunmutebutton);
        resume.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(player != null){
                    player.pause();
                }
            }
        });


    }

    private void showWord(){

    }
}