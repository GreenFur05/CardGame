package client;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.GameFrame;

public class Client
{
   public static void main(String args[])
   {
      final GameEngine gameEngine = new GameEngineImpl();
      
      SwingUtilities.invokeLater(new Runnable() {
    	  @Override
    	  public void run() {
    		  gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
    		  gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(new GameFrame(gameEngine)));
    	  }
      });
      
      
   }
}
