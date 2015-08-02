package com.example.sheraz.lib;

import java.util.ArrayList;
import java.util.Random;

public class JokeGenerator {

    private static JokeGenerator _instance = null;

    private static ArrayList<String> jokes = new ArrayList<String>();

    static {
        jokes.add("Bob: \"I just got a new set of golf clubs for my wife.\"\n" +
                "Jim: \"Great trade!\"");
        jokes.add("Two brooms are getting married. Before the ceremony, the bride broom says to the groom broom, \"I think I'm going to have a whisk.\"\n" +
                "The groom broom says, \"How can that be? We haven't even swept together!\"");
        jokes.add("A blonde goes to the doctor with burns on both of her ears and her right hand. \"Sit down and tell me how it happened,\" says the doctor.\n" +
                "\"I was ironing my clothes when I received a call. Instead of picking up the phone, I picked up the iron and burned my ear.\"\n" +
                "\"'What about the other ear and your hand?\" the doctor asked.\n" +
                "She replied, \"I tried to call for an ambulance.\"");
        jokes.add("A man cheats on his girlfriend Lorraine with a woman named Clearly.\n" +
                "Lorraine dies suddenly.\n" +
                "At the funeral, the man stands up and sings, \"I can see Clearly now, Lorraine is gone.\"");
        jokes.add("During a funeral, the pallbearers accidentally bump into a wall and hear a faint moan. They open the casket and find out that the woman is actually alive.\n" +
                "She lives for 10 more years and then dies. There is another funeral for her. At the end of the service, the pallbearers carry out the casket.\n" +
                "As they are walking out, the husband cries out, \"Watch out for the wall!\"");
        jokes.add("Q: What's the difference between a girlfriend and a wife?\n" +
                "\n" +
                "A: 45 lbs.\n" +
                "\n" +
                "Q: What's the difference between a boyfriend and a husband?\n" +
                "\n" +
                "A: 45 minutes.");
        jokes.add("A newlywed couple on their honeymoon prepares to see each other naked for the first time.\n" +
                "The husband exposes his knotted and twisted feet. He explains, \"I had tolio as a child.\"\n" +
                "The wife asks if he means polio. He says, \"No, it only affects the toes.\"\n" +
                "He removes his pants and reveals deformed knees. He admits, \"I had kneesles, too.\"\n" +
                "Finally, he pulls off his boxers. In shock, the woman gasps, \"Oh no -- smallcox, too!\"");
        jokes.add("Q: What's the difference between a divorce and a circumcision?\n" +
                "A: In a divorce, you get rid of the whole schmuck.");
        jokes.add("Q: What comes with the new Divorced Barbie?\n" +
                "A: All of Ken's stuff.");

        jokes.add("Q: If your wife is shouting at the front door and your dog is barking at the back door, who do you let in first?\n" +
                "A: The dog -- at least he'll quiet down after you let him in.");
        jokes.add("Q: Why did Frosty the Snowman want a divorce?\n" +
                "A: He thought his wife was a flake.");
    }

    private JokeGenerator() {}

    public static JokeGenerator getInstance () {
        if (null == _instance) {
           _instance = new JokeGenerator();
        }

        return _instance;
    }

    public String getJoke() {
        int START = 1;
        int END = jokes.size();
        Random random = new Random();

        int jokeNumber = Utility.generateRandomInteger(START, END, random);

        return jokes.get(jokeNumber);
    }


}
