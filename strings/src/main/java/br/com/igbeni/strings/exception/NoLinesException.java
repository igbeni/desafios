package br.com.igbeni.strings.exception;

/**
 * Wrapper around {@link NullPointerException} for when there is no line on the text.
 */
public class NoLinesException extends NullPointerException {
    public NoLinesException() {
        super("Text without lines!");
    }
}
