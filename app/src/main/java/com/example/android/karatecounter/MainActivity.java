package com.example.android.karatecounter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

/**
 * the following import is needed to read the color from the colors.xml
 * import android.content.Context;
 * import android.support.v4.content.ContextCompat;
 */

public class MainActivity extends AppCompatActivity {

    int scoreContestant1, scoreContestant2, penaltiesContestant1, penaltiesContestant2;
    String penaltyYellowColor = "#FFAE18";
    String penaltyRedColor = "#FF2600";
    String penaltyClearColor = "#e7e7e7";
    final static String SCORE_CONTESTANT_1 = "SavedStateOfContestant1Score";
    final static String SCORE_CONTESTANT_2 = "SavedStateOfContestant2Score";
    final static String PENALTIES_CONTESTANT_1 = "SavedStateOfContestant1Penalties";
    final static String PENALTIES_CONTESTANT_2 = "SavedStateOfContestant2Penalties";
    final static String CONTESTANT_1_NAME = "SavedStateOfContestant1Name";
    final static String CONTESTANT_2_NAME = "SavedStateOfContestant2Name";
    String contestant1Name = "name1";
    String contestant2Name = "name2";

    /**
     * The following lines get the colors from the colors.xml file and store their integer
     * but it crashes the app
    Context context = getApplicationContext();
    int yellowColor = ContextCompat.getColor(context, R.color.penaltyYellow);
    int clearColor = ContextCompat.getColor(context, R.color.penaltyClear);
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** recovering the instance state */
        if (savedInstanceState != null) {
            scoreContestant1 = Integer.valueOf(savedInstanceState.getString(SCORE_CONTESTANT_1));
            scoreContestant2 = Integer.valueOf(savedInstanceState.getString(SCORE_CONTESTANT_2));
            penaltiesContestant1 = Integer.valueOf(savedInstanceState.getString(PENALTIES_CONTESTANT_1));
            penaltiesContestant2 = Integer.valueOf(savedInstanceState.getString(PENALTIES_CONTESTANT_2));
            contestant1Name = savedInstanceState.getString(CONTESTANT_1_NAME);
            contestant2Name = savedInstanceState.getString(CONTESTANT_2_NAME);
        } else {
            contestant1Name = getIntent().getExtras().getString("nameCont1");
            contestant2Name = getIntent().getExtras().getString("nameCont2");
        }
        setContentView(R.layout.activity_main);
        displayScoreFor1(scoreContestant1);
        displayScoreFor2(scoreContestant2);
        displayPenaltiesFor1(penaltiesContestant1);
        displayPenaltiesFor2(penaltiesContestant2);
        reloadPenaltyCardsForContestantOne();
        reloadPenaltyCardsForContestantTwo();
        setContestantNames(contestant1Name, contestant2Name);
    }

    /**
     * Save state for later callback
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(SCORE_CONTESTANT_1, Integer.valueOf(scoreContestant1).toString());
        outState.putString(SCORE_CONTESTANT_2, Integer.valueOf(scoreContestant2).toString());
        outState.putString(PENALTIES_CONTESTANT_1, Integer.valueOf(penaltiesContestant1).toString());
        outState.putString(PENALTIES_CONTESTANT_2, Integer.valueOf(penaltiesContestant2).toString());
        outState.putString(CONTESTANT_1_NAME, contestant1Name);
        outState.putString(CONTESTANT_2_NAME, contestant2Name);

        super.onSaveInstanceState(outState);
    }

    /**
     * This callback is called only when there is a saved instance previously saved using
     * onSaveInstanceState(). We restore some state in onCreate() while we can optionally restore
     * other state here, possibly usable after onStart() has completed.
     * The savedInstanceState Bundle is same as the one used in onCreate().
     */

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        scoreContestant1 = Integer.valueOf(savedInstanceState.getString(SCORE_CONTESTANT_1));
        scoreContestant2 = Integer.valueOf(savedInstanceState.getString(SCORE_CONTESTANT_2));
        penaltiesContestant1 = Integer.valueOf(savedInstanceState.getString(PENALTIES_CONTESTANT_1));
        penaltiesContestant2 = Integer.valueOf(savedInstanceState.getString(PENALTIES_CONTESTANT_2));
        displayScoreFor1(scoreContestant1);
        displayScoreFor2(scoreContestant2);
        displayPenaltiesFor1(penaltiesContestant1);
        displayPenaltiesFor2(penaltiesContestant2);
        contestant1Name = (String) savedInstanceState.getString(CONTESTANT_1_NAME);
        contestant2Name = (String) savedInstanceState.getString(CONTESTANT_2_NAME);
    }
    /**
     * Method to display score for contestant 1
     * @param score
     */
    public void displayScoreFor1 (int score) {
        TextView scoreView = (TextView) findViewById(R.id.scoreContestant1);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Method to display score for contestant 2
     * @param score
     */
    public void displayScoreFor2 (int score) {
        TextView scoreView = (TextView) findViewById(R.id.scoreContestant2);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Method to display penalties for contestant 1
     * @param penalties
     */
    public void displayPenaltiesFor1 (int penalties) {
        TextView penaltiesView = (TextView) findViewById(R.id.penaltiesContestant1);
        penaltiesView.setText(String.valueOf(penalties));
    }

    /**
     * Method to display penalties for contestant 2
     * @param penalties
     */
    public void displayPenaltiesFor2 (int penalties) {
        TextView penaltiesView = (TextView) findViewById(R.id.penaltiesContestant2);
        penaltiesView.setText(String.valueOf(penalties));
    }

    /**
     * Add one point (YUKO) to the score of contestant 1 and display new score
     * @param view
     */
    public void addOneForContestantOne_YUKO (View view) {
        scoreContestant1 = scoreContestant1 + 1;
        displayScoreFor1(scoreContestant1);
    }

    /**
     * Add two points (WAZA-ARI) to the score of contestant 1 and display new score
     * @param view
     */
    public void addTwoForContestantOne_WAZAARI (View view) {
        scoreContestant1 = scoreContestant1 + 2;
        displayScoreFor1(scoreContestant1);
    }

    /**
     * Add three points (NIPPON) to the score of contestant 1 and display new score
     * @param view
     */
    public void addThreeForContestantOne_NIPPON (View view) {
        scoreContestant1 = scoreContestant1 + 3;
        displayScoreFor1(scoreContestant1);
    }

    /**
     * Add one penalty point for contestant 1 and display penalty points
     * @param view
     */
    public void addPenaltyForContestantOne (View view) {
        penaltiesContestant1 = penaltiesContestant1 + 1;
        displayPenaltiesFor1(penaltiesContestant1);
        if (penaltiesContestant1 == 1) {
            /** the following line makes use of the color.xml file but crashes the app
             * setPenaltyCard1Contestant1Color(yellowColor);
             */
            setPenaltyCard1Contestant1Color(penaltyYellowColor);
        } else if (penaltiesContestant1 == 2) {
            setPenaltyCard2Contestant1Color(penaltyYellowColor);
        } else if (penaltiesContestant1 == 3) {
            setPenaltyCard3Contestant1Color(penaltyYellowColor);
        } else {
            setPenaltyCard4Contestant1Color(penaltyRedColor);
            //AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            //dlgAlert.setMessage("Contestant 1 has been automatically disqualified after getting 4 penalties");
            //dlgAlert.setTitle("Disqualified");
            //dlgAlert.setPositiveButton("OK", null);
            //dlgAlert.setCancelable(true);
            //dlgAlert.create().show();
            Context context = this;
            Toast disqMessage = Toast.makeText(context, contestant1Name+getText(R.string.disqualified_toast_message), Toast.LENGTH_LONG);
            disqMessage.show();
        }
    }

    /**
     * This method is called to reload the card colors onCreate or onRestore
     */
    private void reloadPenaltyCardsForContestantOne () {
        if (penaltiesContestant1 == 0) {
            return;
        } else if (penaltiesContestant1 == 1) {
            setPenaltyCard1Contestant1Color(penaltyYellowColor);
        } else if (penaltiesContestant1 == 2) {
            setPenaltyCard2Contestant1Color(penaltyYellowColor);
            setPenaltyCard1Contestant1Color(penaltyYellowColor);
        } else if (penaltiesContestant1 == 3) {
            setPenaltyCard3Contestant1Color(penaltyYellowColor);
            setPenaltyCard2Contestant1Color(penaltyYellowColor);
            setPenaltyCard1Contestant1Color(penaltyYellowColor);
        } else {
            setPenaltyCard4Contestant1Color(penaltyRedColor);
            setPenaltyCard3Contestant1Color(penaltyYellowColor);
            setPenaltyCard2Contestant1Color(penaltyYellowColor);
            setPenaltyCard1Contestant1Color(penaltyYellowColor);
        }
    }

    /**
     * Add one point (YUKO) to the score of contestant 2 and display new score
     * @param view
     */
    public void addOneForContestantTwo_YUKO (View view) {
        scoreContestant2 = scoreContestant2 + 1;
        displayScoreFor2(scoreContestant2);
    }

    /**
     * Add two points (WAZA-ARI) to the score of contestant 2 and display new score
     * @param view
     */
    public void addTwoForContestantTwo_WAZAARI (View view) {
        scoreContestant2 = scoreContestant2 + 2;
        displayScoreFor2(scoreContestant2);
    }

    /**
     * Add three points (NIPPON) to the score of contestant 2 and display new score
     * @param view
     */
    public void addThreeForContestantTwo_NIPPON (View view) {
        scoreContestant2 = scoreContestant2 + 3;
        displayScoreFor2(scoreContestant2);
    }

    /**
     * Add one penalty point for contestant 2 and display penalty points
     * @param view
     */
    public void addPenaltyForContestantTwo (View view) {
        penaltiesContestant2 = penaltiesContestant2 + 1;
        displayPenaltiesFor2(penaltiesContestant2);
        if (penaltiesContestant2 == 1) {
            setPenaltyCard1Contestant2Color(penaltyYellowColor);
        } else if (penaltiesContestant2 == 2) {
            setPenaltyCard2Contestant2Color(penaltyYellowColor);
        } else if (penaltiesContestant2 == 3) {
            setPenaltyCard3Contestant2Color(penaltyYellowColor);
        } else {
            setPenaltyCard4Contestant2Color(penaltyRedColor);
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage(contestant2Name + getText(R.string.disqualified_dialogue));
            dlgAlert.setTitle(getText(R.string.disqualified_title));
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }

    }

    /**
     * This method is called to reload the card colors onCreate or onRestore
     */
    private void reloadPenaltyCardsForContestantTwo () {
        if (penaltiesContestant2 == 0) {
            return;
        } else if (penaltiesContestant2 == 1) {
            setPenaltyCard1Contestant2Color(penaltyYellowColor);
        } else if (penaltiesContestant2 == 2) {
            setPenaltyCard2Contestant2Color(penaltyYellowColor);
            setPenaltyCard1Contestant2Color(penaltyYellowColor);
        } else if (penaltiesContestant2 == 3) {
            setPenaltyCard3Contestant2Color(penaltyYellowColor);
            setPenaltyCard2Contestant2Color(penaltyYellowColor);
            setPenaltyCard1Contestant2Color(penaltyYellowColor);
        } else {
            setPenaltyCard3Contestant2Color(penaltyYellowColor);
            setPenaltyCard2Contestant2Color(penaltyYellowColor);
            setPenaltyCard1Contestant2Color(penaltyYellowColor);
            setPenaltyCard4Contestant2Color(penaltyRedColor);
        }
    }

    /** the following method is the variant that uses the color from the xml,
     * but crashes the app
     public void setPenaltyCard1Contestant1Color (int color) {
     View penaltyView = (View) findViewById(R.id.card1cont1);
     penaltyView.setBackgroundColor(color);
     }
     */
    public void setPenaltyCard1Contestant1Color (String color) {
        View penaltyView = (View) findViewById(R.id.card1cont1);
        penaltyView.setBackgroundColor(Color.parseColor(color));
    }
    public void setPenaltyCard2Contestant1Color (String color) {
        View penaltyView = (View) findViewById(R.id.card2cont1);
        penaltyView.setBackgroundColor(Color.parseColor(color));
    }
    public void setPenaltyCard3Contestant1Color (String color) {
        View penaltyView = (View) findViewById(R.id.card3cont1);
        penaltyView.setBackgroundColor(Color.parseColor(color));
    }
    public void setPenaltyCard4Contestant1Color (String color) {
        View penaltyView = (View) findViewById(R.id.card4cont1);
        penaltyView.setBackgroundColor(Color.parseColor(color));
    }
    public void setPenaltyCard1Contestant2Color (String color) {
        View penaltyView = (View) findViewById(R.id.card1cont2);
        penaltyView.setBackgroundColor(Color.parseColor(color));
    }
    public void setPenaltyCard2Contestant2Color (String color) {
        View penaltyView = (View) findViewById(R.id.card2cont2);
        penaltyView.setBackgroundColor(Color.parseColor(color));
    }
    public void setPenaltyCard3Contestant2Color (String color) {
        View penaltyView = (View) findViewById(R.id.card3cont2);
        penaltyView.setBackgroundColor(Color.parseColor(color));
    }
    public void setPenaltyCard4Contestant2Color (String color) {
        View penaltyView = (View) findViewById(R.id.card4cont2);
        penaltyView.setBackgroundColor(Color.parseColor(color));
    }


    /**
     * resets all scores and starts new match, display 0
     * @param view
     */
    public void newMatch (View view) {
        scoreContestant1 = 0;
        scoreContestant2 = 0;
        penaltiesContestant1 = 0;
        penaltiesContestant2 = 0;
        displayScoreFor1(scoreContestant1);
        displayScoreFor2(scoreContestant2);
        displayPenaltiesFor1(penaltiesContestant1);
        displayPenaltiesFor2(penaltiesContestant2);
        /**
         * the following line makes use of the color from the xml file
         * setPenaltyCard1Contestant1Color(clearColor);
         */
        setPenaltyCard1Contestant1Color(penaltyClearColor);
        setPenaltyCard1Contestant2Color(penaltyClearColor);
        setPenaltyCard2Contestant1Color(penaltyClearColor);
        setPenaltyCard2Contestant2Color(penaltyClearColor);
        setPenaltyCard3Contestant1Color(penaltyClearColor);
        setPenaltyCard3Contestant2Color(penaltyClearColor);
        setPenaltyCard4Contestant1Color(penaltyClearColor);
        setPenaltyCard4Contestant2Color(penaltyClearColor);
        Intent nameActivity = new Intent(this, CounterActivity.class);
        startActivity(nameActivity);
        finish();

    }

    /**
     * set names to textviews
     * @param name1 1st Contestant's name
     * @param name2 2nd Contestant's name
     */
    public void setContestantNames (String name1, String name2) {
        TextView contestant1 = (TextView) findViewById(R.id.text_contestant_1);
        TextView contestant2 = (TextView) findViewById(R.id.text_contestant_2);
        contestant1.setText(name1);
        contestant2.setText(name2);
    }
}
