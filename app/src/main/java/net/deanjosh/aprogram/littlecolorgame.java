package net.deanjosh.aprogram;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Random;

public class littlecolorgame extends AppCompatActivity {

    int currentDifficulty = 1;
    int numberOfRows = 4, numberOfColumns = 4;
    int r,g,b;
    Button buttonArray[][] = new Button[4][4];
    final Random rand = new Random();
    int differentButton[] = new int[2];
    int buttonClicked[] = new int[2];
    boolean paused = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threecolor);

        Log.i("littlecolorgame", "oncreate method");
//        LinearLayout row2 = (LinearLayout) this.findViewById(R.id.row2);
//        row2.setVisibility(View.GONE);

//        LinearLayout row3 = (LinearLayout) this.findViewById(R.id.row3);
//        row3.setVisibility(View.GONE);

        createButtonArray();
        nextRound();

        createAlert("Tap the color that is different");

//        Button b11 = (Button) findViewById(R.id.but11);
//        b11.setBackgroundColor(Color.parseColor(createHexFromRGB(250,77,77)));


    }

    public void nextRound(){
        Log.i("littlecolorgame", "level of difficulty is " + currentDifficulty);
        createRandomColor();
        setButtonColors(createHexFromRGB(r, g, b));
        clearAllButtonText();
        paused = false;

    }

    public void createButtonArray(){
        Log.i("littlecolorgame", "createbuttonarray method");

        buttonArray[0][0] = (Button) findViewById(R.id.but11);
        buttonArray[0][1] = (Button) findViewById(R.id.but12);
        buttonArray[0][2] = (Button) findViewById(R.id.but13);
        buttonArray[0][3] = (Button) findViewById(R.id.but14);

        buttonArray[1][0] = (Button) findViewById(R.id.but21);
        buttonArray[1][1] = (Button) findViewById(R.id.but22);
        buttonArray[1][2] = (Button) findViewById(R.id.but23);
        buttonArray[1][3] = (Button) findViewById(R.id.but24);

        buttonArray[2][0] = (Button) findViewById(R.id.but31);
        buttonArray[2][1] = (Button) findViewById(R.id.but32);
        buttonArray[2][2] = (Button) findViewById(R.id.but33);
        buttonArray[2][3] = (Button) findViewById(R.id.but34);

        buttonArray[3][0] = (Button) findViewById(R.id.but41);
        buttonArray[3][1] = (Button) findViewById(R.id.but42);
        buttonArray[3][2] = (Button) findViewById(R.id.but43);
        buttonArray[3][3] = (Button) findViewById(R.id.but44);


        createButtonListeners();

    }




    public void buttonClicked(){

        Log.i("littlecolorgame", "button clicked was row " + (buttonClicked[0]+1) +
                ", column " + (buttonClicked[1]+1));

        Log.i("littlecolorgame", "different button was row " + (differentButton[0] + 1) +
                ", column " + (differentButton[1] + 1));

        if(buttonClicked[0] == differentButton[0] && buttonClicked[1] == differentButton[1] ){
            Log.i("littlecolorgame", "you are right");



            if (paused == true) {  //this tests to see if this is second try on paused game

            } else {

                currentDifficulty++;

                if(currentDifficulty%5 == 0) {
                    Toast.makeText(getApplicationContext(), "Correct - Level: " + currentDifficulty,
                            Toast.LENGTH_SHORT).show();
                }

            }  //current difficulty should increase if its first click


            nextRound();

        } else {
            Log.i("littlecolorgame", "you are wrong");

            buttonArray[buttonClicked[0]][buttonClicked[1]].setText("not this one");
            buttonArray[differentButton[0]][differentButton[1]].setText("this one");
            if(paused == true){

            }else {
                currentDifficulty--;
                if(currentDifficulty == 0) {currentDifficulty = 1;}
                paused = true;
                Toast.makeText(getApplicationContext(), "Nope - Level: " + currentDifficulty,
                        Toast.LENGTH_SHORT).show();
            }
        }



    }

    public void createRandomColor(){
        Log.i("littlecolorgame", "createrandomcolor method");

        r = rand.nextInt(255) + 0;
        g = rand.nextInt(255) + 0;
        b = rand.nextInt(255) + 0;

        Log.i("littlecolorgame", "r is " + r + " g is " + g + " b is " + b);

    }

    public void clearAllButtonText(){

        int columnCounter = numberOfColumns-1;
        int rowCounter = numberOfRows-1;

        while (columnCounter >= 0) {
            while (rowCounter >= 0) {

                buttonArray[rowCounter][columnCounter].setText("");

// buttonArray[rowCounter][columnCounter].setBackgroundColor(Color.parseColor(hexBackground));
                rowCounter--;
            }
            rowCounter = 3;
            columnCounter--;
        }
    }

    public String createDifferentColor(){

        if(currentDifficulty == 0) {currentDifficulty = 1;}

        Log.i("littlecolorgame", "create different color method");

        if((255-r >= r)){
            Log.i("littlecolorgame", "255 - r is bigger than r");
            //if the number has more space above it
            r = ((255-r)/currentDifficulty)+r;

        } else {
            Log.i("littlecolorgame", "r is bigger than half");
            r = (r-(r/currentDifficulty));
        }

        if((255-g >= g)){
            Log.i("littlecologgame", "255 - g is bigger than g");
            //if the numbeg has moge space above it
            g = ((255-g)/currentDifficulty)+g;

        } else {
            Log.i("littlecologgame", "g is biggeg than half");
            g = (g-(g/currentDifficulty));
        }

        if((255-b >= b)){
            Log.i("littlecolorgame", "255 - b is bigger than r");
            //if the number has more space above it
            b = ((255-b)/currentDifficulty)+b;

        } else {
            Log.i("littlecolorgame", "b is bigger than half");
            b = (b-(b/currentDifficulty));
        }

        Log.i("littlecolorgame", "different r is " + r + " g is " + g + " b is " + b);

        return createHexFromRGB(r,g,b);
    }

    public String createHexFromRGB(int red, int green, int blue){

        Log.i("littlecolorgame", "createhexfromrgb");
        String hexColor = String.format( "#%02x%02x%02x", red, green, blue );
        return hexColor;
    }

    public void createButtonListeners(){

        Log.i("littlecolorgame", "create button listeners");

        int columnCounter = numberOfColumns-1;
        int rowCounter = numberOfRows-1;

        while (columnCounter >= 0) {
            while (rowCounter >= 0) {

                final int rowCounter2 = rowCounter;
                final int columnCounter2= columnCounter;

                buttonArray[rowCounter][columnCounter].setOnClickListener(new View.OnClickListener() {

                                        @Override
                    public void onClick(View arg0) {
                        buttonClicked [0] = rowCounter2;
                        buttonClicked [1] = columnCounter2;
                        buttonClicked();
                    }
                });


//  buttonArray[rowCounter][columnCounter].setBackgroundColor(Color.parseColor(hexBackground));
                rowCounter--;
            }
            rowCounter = 3;
            columnCounter--;
        }



    }

    public void setButtonColors(String hexBackground) {
        Log.i("littlecolorgame", "setbuttoncolors");
        int columnCounter = numberOfColumns-1;
        int rowCounter = numberOfRows-1;

        while (columnCounter >= 0) {
            while (rowCounter >= 0) {
                buttonArray[rowCounter][columnCounter].setBackgroundColor(Color.parseColor(hexBackground));
                rowCounter--;
            }
            rowCounter = 3;
            columnCounter--;
        }
        setDifferentButton();
    }

    public void setDifferentButton(){
        Log.i("littlecolorgame", "setdifferent button color");

        differentButton[0] = rand.nextInt(numberOfRows) + 0;
        differentButton[1] = rand.nextInt(numberOfRows) + 0;

        buttonArray[differentButton[0]][differentButton[1]].
                setBackgroundColor(Color.parseColor(createDifferentColor()));
    }

    // BEGIN_INCLUDE(create_menu)
    /**
     * Use this method to instantiate your menu, and add your items to it. You
     * should return true if you have added items to it and want the menu to be displayed.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate our menu from the resources by using the menu inflater.
        getMenuInflater().inflate(R.menu.main, menu);

        // It is also possible add items here. Use a generated id from
        // resources (ids.xml) to ensure that all menu ids are distinct.
        //  MenuItem locationItem = menu.add(0, R.id.menu_location, 0, R.string.menu_location);
        // locationItem.setIcon(R.drawable.ic_action_location);

        // Need to use MenuItemCompat methods to call any action item related methods
        // MenuItemCompat.setShowAsAction(locationItem, MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    public void createAlert(String alertMessage){

//todo scale app to start with only 3 buttons
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("An App by Josh Dean");

        // set dialog message
        alertDialogBuilder
                .setMessage(alertMessage)
                .setCancelable(true)
                .setPositiveButton("Return", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        dialog.cancel();
                    }
                })
                /*.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                })*/;

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }
    // END_INCLUDE(create_menu)

    // BEGIN_INCLUDE(menu_item_selected)
    /**
     * This method is called when one of the menu items to selected. These items
     * can be on the Action Bar, the overflow menu, or the standard options menu. You
     * should return true if you handle the selection.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //   case R.id.menu_refresh:
            // Here we might start a background refresh task
            //     return true;

            case R.id.menu_refresh:

                currentDifficulty = 1;
                nextRound();

                return true;
            case R.id.menu_about:

                createAlert("This app is my process in learning more about app design and creation. " +
                        "Enjoy.  Dec-2015");
                // Here we would open up our settings activity
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // END_INCLUDE(menu_item_selected)
}
