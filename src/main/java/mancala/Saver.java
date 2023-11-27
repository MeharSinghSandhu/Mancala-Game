package mancala;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The Saver class provides static methods for saving and loading Serializable objects to and from files.
 */
public class Saver {
    /**
     * Saves a Serializable object to a file in the assets folder.
     * 
     * @param toSave The Serializable object to be saved.
     * @param filename The name of the file to save the object to. This file will be located in the assets folder.
     */

     public static void saveObject(final Serializable toSave, final String filename){
        try{
            final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("assets/"+filename));
            out.writeObject(toSave);
            out.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Loads a Serializable object from a file in the assets folder.
     * 
     * @param filename The name of the file from which to load the object. This file should be located in the assets folder.
     * @return The loaded Serializable object, or null if an error occurs during loading.
     */
    
    public static Serializable loadObject(final String filename){
        Serializable loaded = null;
        try{
            final ObjectInputStream input = new ObjectInputStream(new FileInputStream("assets/"+filename));
            loaded = (Serializable)input.readObject();
            input.close();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return loaded;
    }
}