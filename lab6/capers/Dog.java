package capers;

import java.io.File;
import java.io.Serializable;
import static capers.Utils.*;

/** Represents a dog that can be serialized.
 * @author Ahemd Mousa
*/
public class Dog implements Serializable{

    /** Folder that dogs live in. */
    static final File DOG_FOLDER = Utils.join(".capers", "dog");
                                         //      function in Utils)

    /** Age of dog. */
    private int age;
    /** Breed of dog. */
    private String breed;
    /** Name of dog. */
    private String name;
    /** Name of the file. */
    private String File_Name;

    /**
     * Creates a dog object with the specified parameters.
     * @param name Name of dog
     * @param breed Breed of dog
     * @param age Age of dog
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
        File_Name = name;
    }

    /**
     * Reads in and deserializes a dog from a file with name NAME in DOG_FOLDER.
     *
     * @param name Name of dog to load
     * @return Dog read from file
     */
    public static Dog fromFile(String name) {
        File file = new File(DOG_FOLDER, name);
        if (!file.exists()){
            return null;
        }
        Dog d = readObject(file, Dog.class);
         return d;
    }

    /**
     * Increases a dog's age and celebrates!
     */
    public void haveBirthday() {
        age += 1;
        System.out.println(toString());
        System.out.println("Happy birthday! Woof! Woof!");
        File file = new File(DOG_FOLDER, File_Name);
        writeObject(file, this);
    }

    /**
     * Saves a dog to a file for future use.
     */
    public void saveDog() {
        File file = new File(DOG_FOLDER, File_Name);
        writeObject(file, this);
    }

    @Override
    public String toString() {
        return String.format(
            "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
            name, breed, age);
    }

}
