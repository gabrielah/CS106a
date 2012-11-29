/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;


public class Hangman extends ConsoleProgram {
/**get instance variable for HangmanLexicon class**/
private HangmanLexicon wordsList;
/**get instance variable for HangmanCanvas class**/
private RandomGenerator rgen=RandomGenerator.getInstance();
/**number of trials the player has before losing**/
private int trialsCounter=8;
private HangmanCanvas canvas;
/**word that is going to get guessed**/
private String unguessedWord;
/** word randomly chosen that is going to get guessed**/
private String randomWord=selectWord();
/**Characters entered by player**/
private char ch;
/**wrong guesses**/
private String incorrectLetters;

public void init() {
	canvas=new HangmanCanvas();
	add(canvas);
}

    public void run() {
    	setUpHangman();
    	playHangman();
	}
	private void setUpHangman(){
		canvas.reset();
		unguessedWord=showLettersSpaces();
	}
/** select word from Lexicon list**/    
    private String selectWord(){
    	wordsList=new HangmanLexicon();
		int index=rgen.nextInt(0,(wordsList.getWordCount() - 1));
		String selectedWord=wordsList.getWord(index);
		return selectedWord;
	}
/**shows the empty spaces of the word selected**/	
	private String showLettersSpaces() {
		String result = "";
		for(int i = 0 ; i < randomWord.length(); i++) {
			result=result+"-";
		}
			return result;
	}
	private void playHangman(){
		println("Welcome to Hangman!");
		println("The word now looks like this:"+unguessedWord);
		for (int i=trialsCounter;i>0;){ 
		String getChar=readLine("Your guess is:");
		while (true){
			if(getChar.length()>1){
			getChar=readLine("You can only type one letter at a time, try again:");
			}
			if(getChar.length() == 1) 
			break;
		}
		ch=getChar.charAt(0);
		if(Character.isLowerCase(ch)){
			ch=Character.toUpperCase(ch);
		}
		checkLetter();
		returnLetter();
			if (unguessedWord.equals(randomWord)){
				println("You guessed the word: "+randomWord);
				println("you win!");
				break;
			}
		println("You have "+ trialsCounter +" guesses left");
		println("The word now looks like this "+unguessedWord);
		
		/**if person loses the game**/
		if (trialsCounter==0){
			println("you are completely hung");
			println("the word was "+randomWord);
			println("you lose!");
			break;
		}
		}
	}

/**checks if letter is in random word**/
	private void checkLetter(){
		if (randomWord.indexOf(ch)!=-1) {
			println("The guess is correct");
		}
		if(randomWord.indexOf(ch)==-1){
			trialsCounter--;
			println("there are no "+ch+"'s in the word");
			incorrectLetters = incorrectLetters + ch;
            canvas.noteIncorrectGuess(incorrectLetters);
		}
		
	}
	/**returns if a letter is in the unguessed word**/
	private void returnLetter(){
		for (int j=0;j<randomWord.length();j++){
			if (ch==randomWord.charAt(j)){
				if(j>0){
					unguessedWord=unguessedWord.substring(0,j)+ch+unguessedWord.substring(j+1);
				}
				if (j == 0) {
					unguessedWord = ch + unguessedWord.substring(1);
				}
				canvas.displayWord(unguessedWord);
			}
		}
	}
}
