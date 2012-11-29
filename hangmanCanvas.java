/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class HangmanCanvas extends GCanvas {

/** the game begins displaying the scaffold only**/ 
	public void reset() {
		double x=(getWidth()/2);
		double y=(getHeight()/2)-(HEAD_RADIUS*2)-ARM_OFFSET_FROM_HEAD-ROPE_LENGTH;
		GLine rope= new GLine (x,y,x,y+ROPE_LENGTH);
		add(rope);
		GLine beam= new GLine (x,y,x-BEAM_LENGTH,y);
		add(beam);
		GLine scalffold= new GLine (x-BEAM_LENGTH,y,x-BEAM_LENGTH,y+SCAFFOLD_HEIGHT);
		add(scalffold);
	}

/**
 Updates the hidden word when there is a correct guess. 
 */
	public void displayWord(String randomWord) {
        //creates a label with the hidden word and the correct letters
        double x = getWidth()/4;
        double y = getHeight()/6;
        GLabel hiddenWord = new GLabel("The word looks like this:"+randomWord, x, y);
       /**removes the latest hidden word and replaces it with the most updates version that includes the
        * very last guessed letter**/
        if (getElementAt(x,y) != null){
            remove(getElementAt(x,y));
        }
        add(hiddenWord);
	}

/**
 Displays the incorrect letters and, every time there is a missed guess, a new hangman's part
 appears on the screen
 */
	public void noteIncorrectGuess(String incorrectGuesses) {
		/**label with the incorrect letters**/
        double x = getWidth()/4;
        double y = getHeight()/6+(HEAD_RADIUS/2);
        GLabel incorrectLetters = new GLabel("Incorrect Guesses:"+incorrectGuesses, x, y);
        add(incorrectLetters);
     /** every incorrect guess shows a new part of the hangman**/
        if(incorrectGuesses.length() == 5) {
            drawFace();
        }
        if(incorrectGuesses.length() == 6) {
            drawBody();
        }
        if(incorrectGuesses.length() == 7) {
            drawLeftArm();
        }
        if(incorrectGuesses.length() == 8) {
            drawRightArm();
        }
        if(incorrectGuesses.length() == 9) {
            drawLeftLeg();
        }
        if(incorrectGuesses.length() == 10) {
            drawRightLeg();
        }
        if(incorrectGuesses.length() == 11) {
            drawLeftFoot();
        }
        if(incorrectGuesses.length() == 12) {
            drawRightFoot();
            /**displays a game over label when the last body part appears**/
            GLabel gameOver=new GLabel("GAME OVER");
            double gameX=(getWidth()/2-gameOver.getWidth());
            double gameY=(getHeight()/2-gameOver.getAscent());
            gameOver.setColor(Color.red);
            gameOver.setFont("Helvetica-30");
            add(gameOver,gameX,gameY);  
        }
	}
/**creation of each of hangman's body parts**/
	
	private void drawFace(){
		double x=(getWidth()/2)-(HEAD_RADIUS);
		double y=(getHeight()/2)-(HEAD_RADIUS*2)-ARM_OFFSET_FROM_HEAD;
		GOval face= new GOval (x,y,(HEAD_RADIUS*2),(HEAD_RADIUS*2));
		add(face);
	}
	private void drawBody(){
		double x=(getWidth()/2);
		double y=(getHeight()/2);
		GLine neck= new GLine (x,y-ARM_OFFSET_FROM_HEAD,x,y);
		add(neck);
		GLine body= new GLine (x,y,x,y+BODY_LENGTH);
		add(body);
	}
	private void drawRightArm(){
		double x=(getWidth()/2);
		double y=(getHeight()/2);
		GLine upperRightArm= new GLine (x,y,x+UPPER_ARM_LENGTH,y);
		add(upperRightArm);
		GLine lowerRightArm= new GLine (x+UPPER_ARM_LENGTH,y,x+UPPER_ARM_LENGTH,y-LOWER_ARM_LENGTH);
		add(lowerRightArm);
	}
	private void drawLeftArm(){
		double x=(getWidth()/2);
		double y=(getHeight()/2);
		GLine upperLeftArm= new GLine (x,y,x-UPPER_ARM_LENGTH,y);
		add(upperLeftArm);
		GLine lowerLeftArm= new GLine (x-UPPER_ARM_LENGTH,y,x-UPPER_ARM_LENGTH,y-LOWER_ARM_LENGTH);
		add(lowerLeftArm);
	}
	private void drawRightLeg(){
		double x=(getWidth()/2);
		double y=(getHeight()/2)+BODY_LENGTH;
		GLine hip= new GLine (x,y,x+HIP_WIDTH,y);
		add(hip);
		GLine rightLeg= new GLine (x+HIP_WIDTH,y,x+HIP_WIDTH,y+LEG_LENGTH);
		add(rightLeg);
	}
	private void drawLeftLeg(){
		double x=(getWidth()/2);
		double y=(getHeight()/2)+BODY_LENGTH;
		GLine hip= new GLine (x,y,x-HIP_WIDTH,y);
		add(hip);
		GLine leftLeg= new GLine (x-HIP_WIDTH,y,x-HIP_WIDTH,y+LEG_LENGTH);
		add(leftLeg);
	}
	private void drawRightFoot(){
		double x=(getWidth()/2);
		double y=(getHeight()/2)+BODY_LENGTH;
		GLine rightFoot= new GLine (x+HIP_WIDTH,y+LEG_LENGTH,x+HIP_WIDTH+FOOT_LENGTH,y+LEG_LENGTH);
		add(rightFoot);
	}
	private void drawLeftFoot(){
		double x=(getWidth()/2);
		double y=(getHeight()/2)+BODY_LENGTH;
		GLine leftFoot= new GLine (x-HIP_WIDTH,y+LEG_LENGTH,x-HIP_WIDTH-FOOT_LENGTH,y+LEG_LENGTH);
		add(leftFoot);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
