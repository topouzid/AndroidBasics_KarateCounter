package com.example.android.karatecounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CounterActivity extends AppCompatActivity {

    String contestant1Name;
    String contestant2Name;
    boolean namesOk = true;
    final static String CONTESTANT_1_NAME = "SavedStateOfContestant1Name";
    final static String CONTESTANT_2_NAME = "SavedStateOfContestant1Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            contestant1Name = (String) savedInstanceState.getString(CONTESTANT_1_NAME);
            contestant2Name = (String) savedInstanceState.getString(CONTESTANT_2_NAME);
        }
        setContentView(R.layout.activity_counter);
    }

    /**
     * Save state for later callback
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(CONTESTANT_1_NAME, contestant1Name);
        outState.putString(CONTESTANT_2_NAME, contestant2Name);
        super.onSaveInstanceState(outState);
    }

    /**
     * save name from edittext1
     */
    private void getContestant1Name () {
        EditText contestant_1 = (EditText) findViewById(R.id.edit_contestant_1);
        contestant1Name = contestant_1.getText().toString();
    }

    /**
     * save name from edittext2
     */
    private void getContestant2Name () {
        EditText contestant_2 = (EditText) findViewById(R.id.edit_contestant_2);
        contestant2Name = contestant_2.getText().toString();
    }

    /**
     * check if there are names in the edittexts
     */
    private void checkNames () {
        if (contestant1Name.equals("")) {
            Toast toastCont1 = Toast.makeText(this, getText(R.string.contestant_1_name_hint), Toast.LENGTH_SHORT);
            toastCont1.show();
            namesOk = false;
        }
        if (contestant2Name.equals("")) {
            Toast toastCont2 = Toast.makeText(this, getText(R.string.contestant_2_name_hint), Toast.LENGTH_SHORT);
            toastCont2.show();
            namesOk = false;
        }
    }

    /**
     * this method is called when the Fight button is pressed
     * @param view
     */
    public void goToMainActivity (View view) {
        getContestant1Name();
        getContestant2Name();
        checkNames();
        if (namesOk) {
            Intent mainActivity = new Intent(this, MainActivity.class);
            mainActivity.putExtra("nameCont1", contestant1Name);
            mainActivity.putExtra("nameCont2", contestant2Name);
            startActivity(mainActivity);
            finish();
        }
        namesOk = true;
    }
}
