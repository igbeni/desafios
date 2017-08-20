package br.com.igbeni.crawler;

import br.com.igbeni.crawler.model.SubReddit;

import java.util.Collections;
import java.util.List;

/**
 * Main entry for the first part of the challenge.
 *
 * @author Iggor Alves
 */
public class App {
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Wrong number of arguments.");
            return;
        }

        final String[] subreddits = args[0].split(";");

        List<SubReddit> subReddits = Crawler.getInstance().getSubRedditsInfo(subreddits);

        Collections.sort(subReddits);

        for (SubReddit subReddit : subReddits) {
            System.out.println(subReddit);
        }
    }
}
