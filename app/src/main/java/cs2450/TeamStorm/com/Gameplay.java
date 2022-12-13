package cs2450.TeamStorm.com;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Gameplay {

    // number of cards to play (value is always even)
    private int numOfCards;

    // identification of all possible cards
    private int[] cardsArray;
    private String[][] scores = new String[9][6];

    // all possible faces of every card
    private int[] allImages = {R.drawable.rain, R.drawable.rain2, R.drawable.sun, R.drawable.cloudy, R.drawable.cloudy2,
            R.drawable.cloudy3, R.drawable.lightning, R.drawable.snow, R.drawable.storm, R.drawable.night};

    // all card pairs
    private int[][] cardPairs;

    // images assigned for each pair of cards, assigned by row
    private int[] images;

    // clicked cards' identification and position in cardsArray
    private int firstCard, secondCard;
    private int clickedFirst, clickedSecond;

    // number of cards player flipped, resets to 1 after each pair
    private int cardNumber = 1;

    // amount of points
    private int playerPoints = 0;

    // constructor
    public Gameplay(int cards) {
        numOfCards = cards;
        cardsArray = new int[numOfCards];
        intiCardArray();
        cardPairs = new int[numOfCards/2][2];
        images = new int[numOfCards/2];
        setImages();
        setPairs();
    }

    // initialize cardArray
    private void intiCardArray() {
        int id = 100;
        for (int i = 0; i < numOfCards/2; i++) {
            id++;
            cardsArray[i] = id;
        }
        id = 200;
        for (int j = numOfCards/2; j > 0; j--) {
            id++;
            cardsArray[j] = id;
        }
    }

    // create pairs of cards
    private void setPairs(){
        Random rand = new Random();
        int count = 0;
        int row = rand.nextInt(cardPairs.length);
        int column = rand.nextInt(cardPairs[0].length);
        while (count < numOfCards) {
            if (cardPairs[row][column] == 0) {
                cardPairs[row][column] = cardsArray[count];
                count++;
            }
            row = rand.nextInt(cardPairs.length);
            column = rand.nextInt(cardPairs[0].length);
        }
    }

    // select images for cards
    private void setImages() {
        Random rand = new Random();
        int count = 0;
        do {
            int imageChoice = rand.nextInt(allImages.length);
            if (images[count] == 0) {
                if (getPositionInIntArray(allImages[imageChoice], images) == -1) {
                    images[count] = allImages[imageChoice];
                    count++;
                }
            }
        } while (count < numOfCards/2);
    }

    // flip cards over
    public void setImageAndCheck(Activity gameScreen, ImageView iv, int card, TextView printLabel,
                                 ImageView[] cardDeck){
        //set image to imageview button
        int index = getRowIn2DArray(cardsArray[card], cardPairs);
        iv.setImageResource(images[index]);

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

            for (int i = 0; i < cardDeck.length; i++) {
                cardDeck[i].setEnabled(false);
            }

            //set one second delay to disappear after cards match
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    check(gameScreen, printLabel, cardDeck);
                }
            }, 1000);
        }
    }

    // check to see if cards match
    private void check(Activity gameScreen, TextView pointLabel, ImageView[] cardDeck){
        if(firstCard == secondCard){
            cardDeck[clickedFirst].setVisibility(View.INVISIBLE);
            cardDeck[clickedSecond].setVisibility(View.INVISIBLE);

            // increase player points
            playerPoints = playerPoints + 2;
            pointLabel.setText("Player points: " + playerPoints);
            for (int i = 0; i < cardDeck.length; i++) {
                cardDeck[i].setEnabled(true);
            }
        }
        gameOver(gameScreen, cardDeck);
    }

    // end the game
    private void gameOver(Activity gameScreen, ImageView[] cardDeck){
        if(checkCardVisibility(cardDeck)) {
            loadHighScores(gameScreen);
            if(checkHighScore(numOfCards) == false) {
                //Toast.makeText(getActivity(), "Game Over", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(gameScreen);
                alertDialogBuilder
                        .setMessage("GAME OVER!\nPlayer Points: " + playerPoints)
                        .setCancelable(false)
                        .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(gameScreen.getApplicationContext(), GameScreen.class);
                                gameScreen.startActivity(intent);
                                gameScreen.finish();
                            }
                        })
                        .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                gameScreen.finish();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
            else{
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(gameScreen);
                alertDialogBuilder
                        .setTitle("NEW HIGH SCORE!\nPlayer Points: " + playerPoints)
                        .setMessage("Enter Name to save High Score:")
                        .setCancelable(false);

                final EditText nameInput = new EditText(gameScreen);
                int maxLength = 8;
                nameInput.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                alertDialogBuilder.setView(nameInput);

                alertDialogBuilder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userName = nameInput.getText().toString();
                        if(TextUtils.isEmpty(userName)) {
                            saveHighScore("...", numOfCards, gameScreen);
                            gameScreen.finish();
                        }
                        else {
                            saveHighScore(userName, numOfCards, gameScreen);
                            gameScreen.finish();
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gameScreen.finish();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
    }

    //reads file and copies the scores to 2d array. creates file if it doesn't exist
    public void loadHighScores(Activity gameScreen){
        File file = gameScreen.getApplicationContext().getFileStreamPath("Scores.txt");
        String line;

        //if file exists, read file
        if (file.exists()){
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(gameScreen.openFileInput("Scores.txt")));

                for(int i = 0; i < 9; i++){
                    for (int j = 0; j < 6; j +=2){
                        line = reader.readLine();
                        StringTokenizer tokens = new StringTokenizer(line, " ");
                        scores[i][j] = tokens.nextToken();
                        scores[i][j + 1] = tokens.nextToken();
                    }
                }

                reader.close();
            }
            catch (IOException e) {
                Toast.makeText(gameScreen, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        //if no file exists, create one and call method again
        else{
            try{
                FileOutputStream file2 = gameScreen.openFileOutput("Scores.txt", gameScreen.MODE_PRIVATE);
                OutputStreamWriter outputFile = new OutputStreamWriter(file2);

                //set all names to be empty
                for(int i = 0; i < 9; i++){
                    for (int j = 0; j < 6; j += 2 ){
                        scores[i][j] = "empty";
                    }
                }

                //set all scores to 0
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
                loadHighScores(gameScreen);
            }
            catch (IOException e){
                Toast.makeText(gameScreen, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //
    private boolean checkHighScore(int cardNumber){
        int scoreType = convertChoice(cardNumber);

        int scoreA = Integer.parseInt(scores[scoreType][1]);
        int scoreB = Integer.parseInt(scores[scoreType][3]);
        int scoreC = Integer.parseInt(scores[scoreType][5]);

        if (playerPoints <= scoreC){
            //no new high score
            return false;
        }
        else{
            return true;
        }
    }

    //checks and saves high scores. call this method everytime a game is ended.
    private void saveHighScore(String playerName, int cardNumber, Activity gameScreen){
        int scoreType = convertChoice(cardNumber);

        int scoreA = Integer.parseInt(scores[scoreType][1]);
        int scoreB = Integer.parseInt(scores[scoreType][3]);
        int scoreC = Integer.parseInt(scores[scoreType][5]);

        if (playerPoints > scoreC && playerPoints <= scoreB){
            //high score is placed in rank 3
            scores[scoreType][4] = playerName;
            scores[scoreType][5] = Integer.toString(playerPoints);

            writeHighScores(gameScreen);
        }
        else if (playerPoints > scoreB && playerPoints <= scoreA){
            //move rank 2 scores to rank 3
            scores[scoreType][4] = scores[scoreType][2];
            scores[scoreType][5] = scores[scoreType][3];

            //high score is placed in rank 2
            scores[scoreType][2] = playerName;
            scores[scoreType][3] = Integer.toString(playerPoints);

            writeHighScores(gameScreen);
        }
        else if (playerPoints > scoreA){
            //move rank 2 scores to rank 3
            scores[scoreType][4] = scores[scoreType][2];
            scores[scoreType][5] = scores[scoreType][3];

            //move rank 1 scores to rank 2
            scores[scoreType][2] = scores[scoreType][0];
            scores[scoreType][3] = scores[scoreType][1];

            //high score is placed in rank 1
            scores[scoreType][0] = playerName;
            scores[scoreType][1] = Integer.toString(playerPoints);

            writeHighScores(gameScreen);
        }
    }

    //writes new high scores to Scores.txt
    private void writeHighScores(Activity gameScreen){
        try{
            FileOutputStream file2 = gameScreen.openFileOutput("Scores.txt", gameScreen.MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file2);

            for(int i = 0; i < 9; i++){
                for (int j = 0; j < 6; j += 2 ){
                    outputFile.write(scores[i][j] + " " + scores[i][j + 1] + "\n");
                }
            }
            outputFile.flush();
            outputFile.close();
        }
        catch (IOException e){
            Toast.makeText(gameScreen, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // show card answers
    public void showCardAnswers(ImageView[] cardDeck) {
        for (int i = 0; i < cardDeck.length; i++) {
            int index = getRowIn2DArray(cardsArray[i], cardPairs);
            cardDeck[i].setImageResource(images[index]);
            cardDeck[i].setEnabled(false);
        }
    }

    // cover up cards and subtract a point from total
    public void reset(ImageView[] cardDeck, TextView pointLabel) {
        for (int i = 0; i < cardDeck.length; i++) {
            int index = getRowIn2DArray(cardsArray[i], cardPairs);
            cardDeck[i].setImageResource(R.drawable.card);;
            cardDeck[i].setEnabled(true);
        }
        if(playerPoints > 0){
            playerPoints--;
            pointLabel.setText("Player points: " + playerPoints);
        }
    }

    // check int array for a value; -1 if not there, position otherwise
    private int getPositionInIntArray(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i])
                return i;
        }
        return -1;
    }

    // find row a value is on in a 2D array; -1 if not there, position otherwise
    private int getRowIn2DArray(int value, int[][] array) {
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < 2; col++) {
                if (array[row][col] == value)
                    return row;
            }
        }
        return -1;
    }

    //converts amount of cards to position number in 2d array
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

    // check if all cards were selected;
    private boolean checkCardVisibility(ImageView[] cardDeck) {
        for (int i = 0; i < cardDeck.length; i++) {
            if (cardDeck[i].getVisibility() != View.INVISIBLE)
                return false;
        }
        return true;
    }

    // get player points
    public int getPlayerPoints() {
        return playerPoints;
    }
}
