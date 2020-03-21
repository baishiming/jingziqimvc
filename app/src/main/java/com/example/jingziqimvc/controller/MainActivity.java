package com.example.jingziqimvc.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jingziqimvc.R;
import com.example.jingziqimvc.model.Board;
import com.example.jingziqimvc.model.Cell;
import com.example.jingziqimvc.model.GameState;
import com.example.jingziqimvc.model.Player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getName();

    private ViewGroup buttonGroup;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;

    private Board board ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winnerPlayerLabel = (TextView)findViewById(R.id.winnerPlayerLabel);
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        buttonGroup = (ViewGroup)findViewById(R.id.buttonGroup);
        board = new Board();
        board.restart();
        reStartView();
    }

    public void reStartView(){
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");

        for (int i = 0 ; i < buttonGroup.getChildCount();  i++){
            ((Button)buttonGroup.getChildAt(i)).setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_jingziqi,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_reset){
            board.restart();
            reStartView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onCellClicked(View v){
        Button button = (Button) v;
        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));
        Log.e(TAG,"click row =="+row + ", col == "+ col);
        Player playerThatMoved = board.mark(row,col);
        if(playerThatMoved !=null){
            button.setText(playerThatMoved.toString());
            if(board.getWiner()!=null){
                winnerPlayerLabel.setText(playerThatMoved.toString());
                winnerPlayerViewGroup.setVisibility(View.VISIBLE);
            }
        }
    }



}
