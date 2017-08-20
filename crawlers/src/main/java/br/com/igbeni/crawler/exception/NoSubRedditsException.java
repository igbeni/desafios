package br.com.igbeni.crawler.exception;

/**
 * Wrapper around {@link NullPointerException} for when there is no line on the text.
 *
 * @author Iggor Alves
 */
public class NoSubRedditsException extends NullPointerException {
    public NoSubRedditsException() {
        super("No subreddits on the input data.");
    }
}
