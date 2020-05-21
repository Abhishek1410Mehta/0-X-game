package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
var PLAYER=true
var TURN_COUNT=0
var boardStatus=Array(3){IntArray(3)}
lateinit var board:Array<Array<Button>>
class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for(i in board)
        {
            for(button in i)
            {
                button.setOnClickListener(this);
            }
        }
        resetBtn.setOnClickListener {
        intializeBoardStatus()
        PLAYER=true
        TURN_COUNT=0
        }
        intializeBoardStatus()
    }

    private fun intializeBoardStatus() {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                boardStatus[i][j]=-1;
            }
        }
        for(i in board)
        {
            for(button in i){
                button.isEnabled=true
                button.text=""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id)
        {
            R.id.button1->{
            updatevalue(row=0,col=0,player=PLAYER)
            }
            R.id.button2->{
                updatevalue(row=0,col=1,player=PLAYER)
            }
            R.id.button3->{
                updatevalue(row=0,col=2,player=PLAYER)
            }
            R.id.button4->{
                updatevalue(row=1,col=0,player=PLAYER)
            }
            R.id.button5->{
                updatevalue(row=1,col=1,player=PLAYER)
            }
            R.id.button6->{
                updatevalue(row=1,col=2,player=PLAYER)
            }
            R.id.button7->{
                updatevalue(row=2,col=0,player=PLAYER)
            }
            R.id.button8->{
                updatevalue(row=2,col=1,player=PLAYER)
            }
            R.id.button9->{
                updatevalue(row=2,col=2,player=PLAYER)
            }
        }
        PLAYER=!PLAYER
        TURN_COUNT++
        if(PLAYER)
        {
            updateDisplay("PLAYER X TURN")
        }
        else{
            updateDisplay("PLAYER 0 TURN")
        }
        if(TURN_COUNT==9)
        {
            updateDisplay("GAME DRAW")
        }
        checkWinner()
    }

    private fun disableButton(){
        for(i in board){
            for(button in i){
                button.isEnabled=false
            }
        }
    }
    private fun checkWinner() {
        // horizontal row
        for(i in 0..2)
        {
            if(boardStatus[i][0]== boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                    updateDisplay("PLAYER X IS WINNER")
                    break}else if(boardStatus[i][0]==0){
                    updateDisplay("PLAYER 0 IS WINNER")
                    break
                }
            }
        }
        //vertical column
        for(i in 0..2)
        {
            if(boardStatus[0][i]== boardStatus[1][i] && boardStatus[1][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updateDisplay("PLAYER X IS WINNER")
                    break}else if(boardStatus[0][i]==0){
                    updateDisplay("PLAYER 0 IS WINNER")
                    break
                }
            }
        }
        //first diagonal
        if(boardStatus[0][0]== boardStatus[1][1] && boardStatus[1][1]== boardStatus[2][2])
        {
            if(boardStatus[0][0]==1){
                updateDisplay("PLAYER X IS WINNER")
                }else if(boardStatus[0][0]==0){
                updateDisplay("PLAYER 0 IS WINNER")
            }
        }
        //second disgonal
        if(boardStatus[0][2]== boardStatus[1][1] && boardStatus[0][2]== boardStatus[2][0])
        {
            if(boardStatus[0][2]==1){
                updateDisplay("PLAYER X IS WINNER")
                }else if(boardStatus[0][2]==0){
                updateDisplay("PLAYER 0 IS WINNER")
            }
        }
    }

    private fun updateDisplay(text: String) {
    displayTv.text=text
        if(text.contains("WINNER"))
        {
            disableButton()
        }
    }

    private fun updatevalue(row: Int, col: Int, player: Boolean) {
        val text:String=if(player) "X" else "0"
        val value:Int=if(player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        boardStatus[row][col]=value;
    }
}
