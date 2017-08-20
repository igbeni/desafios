package br.com.igbeni.crawler;

import br.com.igbeni.crawler.exception.NoSubRedditsException;
import br.com.igbeni.crawler.model.SubReddit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Crawler for getting reddit top thread listing.
 *
 * @author Iggor Alves
 */
public class Crawler {
    private static final String BASE_URL = "https://www.reddit.com/r/";
    private static final int MINIMUM_UP_VOTES = 5000;
    private static final By BY_CLASS_NAME_THING = By.className("thing");
    private static final By BY_CLASS_NAME_SCORE_UNVOTED = By.className("score unvoted");
    private static final By BY_CLASS_NAME_TITLE = By.className("title may-blank");
    private static final By BY_CLASS_NAME_AUTHOR = By.className("author may-blank");

    @NotNull
    private static Crawler instance = new Crawler();

    /**
     * Function to get the Crawler instance.
     *
     * @return Crawler instance
     */
    @NotNull
    public static Crawler getInstance() {
        return instance;
    }

    /**
     * Empty constructor.
     */
    private Crawler() {
    }

    /**
     * Function for getting information from an array of subreddits.
     *
     * @param subredditsArray An array of subreddits.
     * @return List of subreddits infos.
     */
    @NotNull
    public List<SubReddit> getSubRedditsInfo(@Nullable String[] subredditsArray) {
        if (subredditsArray == null) {
            throw new NoSubRedditsException();
        }

        List<SubReddit> subReddits = new ArrayList<>();

        if (subredditsArray.length > 0) {
            WebDriver webDriver = new HtmlUnitDriver();

            for (String subreddit : subredditsArray) {
                getSubRedditInfo(webDriver, subReddits, subreddit);
            }
        }

        return subReddits;
    }

    /**
     * Function for getting information of a subreddit.
     *
     * @param webDriver  {@link WebDriver} instance.
     * @param subReddits List of subreddits.
     * @param subreddit  Subreddit info.
     */
    private void getSubRedditInfo(WebDriver webDriver, List<SubReddit> subReddits, String subreddit) {
        webDriver.get(BASE_URL + subreddit);
        List<WebElement> elements = webDriver.findElements(BY_CLASS_NAME_THING);

        for (WebElement webElement : elements) {
            long upVotes = parseUpVotes(webElement.findElement(BY_CLASS_NAME_SCORE_UNVOTED).getText());

            if (upVotes >= MINIMUM_UP_VOTES) {
                SubReddit subReddit = SubReddit.SubRedditBuilder.create()
                        .withSubreddit(subreddit)
                        .withThreadTitle(webElement.findElement(BY_CLASS_NAME_TITLE).getText())
                        .withThreadLink(webElement.findElement(BY_CLASS_NAME_TITLE).getAttribute("href"))
                        .withThreadAuthor(webElement.findElement(BY_CLASS_NAME_AUTHOR).getText())
                        .withThreadAuthorLink(webElement.findElement(BY_CLASS_NAME_AUTHOR).getAttribute("href"))
                        .withUpVotes(upVotes)
                        .build();

                subReddits.add(subReddit);
            }
        }
    }

    /**
     * Function to parse up votes number from a string.
     *
     * @param upVotesString Up votes number as a string.
     * @return Up votes number as a long.
     */
    private long parseUpVotes(@NotNull String upVotesString) {
        long upVotes = 0;

        switch ((int) upVotesString.charAt(upVotesString.length() - 1)) {
            //Thousands of upvotes
            case 75:
            case 107:
                upVotes = (long) (Double.valueOf(upVotesString.substring(0, upVotesString.length() - 1)) * Math.pow(10, 3));
                break;

            //Millions of upvotes
            case 77:
            case 155:
                upVotes = (long) (Double.valueOf(upVotesString.substring(0, upVotesString.length() - 1)) * Math.pow(10, 6));
                break;

            //Billions of upvotes
            case 66:
            case 98:
                upVotes = (long) (Double.valueOf(upVotesString.substring(0, upVotesString.length() - 1)) * Math.pow(10, 8));
                break;

            //Less than a thousand upvotes
            default:
                try {
                    upVotes = Long.valueOf(upVotesString);
                } catch (NumberFormatException ignored) {

                }
                break;
        }

        return upVotes;
    }
}
