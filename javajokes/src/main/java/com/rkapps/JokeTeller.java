package com.rkapps;

public class JokeTeller {

    String[] jokeList = {
            "Can a kangaroo jump higher than a house? Of course, a house doesnt jump at all.\n",
            "Today a man knocked on my door and asked for a small donation towards the local swimming pool. " +
                    "I gave him a glass of water.",
            "If you think nobody cares whether you're alive, try missing a couple of payments.",
            "Money talks ...but all mine ever says is good-bye.",
            "I'm great at multitasking. I can waste time, be unproductive, and procrastinate all at once.",
    };

    public String getAJoke(String jokeType) {
        return "This is " + jokeType + " joke";
    }

    public String getARandomJoke() {
        int min = 0;
        int max = 4;
        int i = min + (int) (Math.random() * max);
        return jokeList[i];
    }
}
