package jrichardson.snhu.ingredienttester.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import jrichardson.snhu.myrecipebox.model.Ingredient;
import java.util.Scanner;


/**
 * Application to test the Ingredient class of the MyRecipeBox Application
 * @author jhrichardson
 */
public class SteppingStoneIngredientTester {

    
    /**
     * @param args the command line arguments
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, 
                                                  InterruptedException {
        
        final String batFile = "cls.bat";
        final String tempDir = "C:/StepTestDir";
        File TempDir = new File(tempDir);
        File file = new File(TempDir.getPath()+"/"+batFile);
        
        String answer;
        final String os = System.getProperty("os.name");
        
        //Clear the Screen to begin the session
        clearScreen(os, TempDir, file);
        
        //get a scanner to recieve user input
        Scanner sc = new Scanner(System.in);
        
        
        System.out.print("Would you like to create a new ingredient? Yes or No: ");
        answer = sc.nextLine();
        
        switch (answer) {
            case "Yes":
            case "yes":
            case "Y":
            case "y":
                fillIngredient(sc, os);
                break;
            case "No":
            case "no":
            case "N":
            case "n":
                System.out.println();
                System.out.println("Thank you.............. Have a nice day!"
                        + "Good Bye...");
                break;
            default:
                System.out.println();
                System.out.println("You have supplied an incorrect response, "
                        + "please try again, Good Bye");
                break;
        }
        
        file.delete();
        TempDir.delete();
                
    }
    
    private static void clearScreen(String env, File dir, File file) throws IOException, 
                                                InterruptedException{
        // if the file does not exit on the file system &&
        // we are running on a Windows platform
        if(!file.exists() && env.contains("Windows")){
            //no, then pull it from the jar file and push to the file system
            InputStream is = SteppingStoneIngredientTester.class
                                      .getClassLoader()
                                      .getResourceAsStream("resources/cls.bat");

            byte[] buf = new byte[20];
            int numread = is.read(buf);
            is.close();

            if(numread > 0){
                if(!dir.exists()){
                    dir.mkdir();
                }
                    OutputStream os = new FileOutputStream(file);
                    os.write(buf,0,buf.length);
                    os.close();
                }
        }
          
        //Are we running in a windows environment?
        if(env.contains("Windows")){
            //exec the bat file to clear the screen
            new ProcessBuilder(file.getPath()).inheritIO().start().waitFor();
        }
        //no, then it must be a unix varrient or x86 linux
        //this wont work for all other platforms
        else{
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    }

    private static void fillIngredient(Scanner sc, String env) throws IOException, 
                                                            InterruptedException{
        
        String ingredientName;
        Ingredient ingredient;
        
        //clearScreen(env);
        System.out.println();
        System.out.print("What is the name of your ingredient? ");
        ingredientName = sc.nextLine();
        
        
    }
    
}
