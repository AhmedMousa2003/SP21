package capers;

import java.io.File;
import static capers.Utils.*;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = new File (".capers"); // TODO Hint: look at the `join`
                                            //      function in Utils

    /** Main Dog folder. */
    static final File DOG_FOLDER = new File (Utils.join(".capers"), "dog");

    /** Main story folder. */
    static final File STORY_FOLDER = new File(Utils.join(".capers"), "story");

    /** Main dog file */
    static final File dog = new File(Utils.join(".capers", "dog"), "dog.txt");

    /** Main story file*/
    static final File story = new File(Utils.join(".capers", "story"), "story.txt");

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        if (!DOG_FOLDER.exists()) {
            DOG_FOLDER.mkdirs();
        }
        if (!STORY_FOLDER.exists()){
            STORY_FOLDER.mkdirs();
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        String current_Final_story = new String();
        if (story.exists()) {
            current_Final_story = readContentsAsString(story);
        }
        current_Final_story += text + "\n";
        Utils.writeContents(story, current_Final_story);
        System.out.println(current_Final_story);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog current_dog = new Dog(name, breed, age);
        current_dog.saveDog();
        System.out.println(current_dog.toString());
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog current_dog = Dog.fromFile(name);
        if (current_dog == null){
            exitWithError(String.format("No such dog is found in data base"));
        }
        current_dog.haveBirthday();
    }
}
