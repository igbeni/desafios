package br.com.igbeni.strings.model;

import br.com.igbeni.strings.exception.NoLinesException;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test suite for {@link Text}.
 *
 * @author Iggor Alves
 */
public class TextTest {
    @Nullable
    private String input;

    /**
     * Function to assign value to {@link TextTest#input} before starting the tests.
     */
    @Before
    public void setUp() {
        input = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, " +
                "darkness was over the surface of the deep, and the Spirit of God was hovering over the waters." +
                "\n" +
                "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he " +
                "separated the light from the darkness. God called the light \"day,\" and the darkness he called " +
                "\"night.\" And there was evening, and there was morning - the first day.";
    }

    /**
     * Function to clear the value of {@link TextTest#input} after the tests are finished.
     */
    @After
    public void tearDown() {
        input = null;
    }

    /**
     * Tests the creating a {@link Text}.
     */
    @Test
    public void createText() {
        Text.TextBuilder.create().build();
    }

    /**
     * Tests the creating a {@link Text} with a list of lines.
     */
    @Test
    public void createTextWithLines() {
        Text.TextBuilder.create()
                .withLines(new ArrayList<String>())
                .build();
    }

    /**
     * Tests the creating a {@link Text} from a {@link String}.
     */
    @Test
    public void createTextFromString() {
        Text.TextBuilder.create()
                .fromString(input)
                .build();
    }

    /**
     * Tests the creating a {@link Text} with a value for the maximum characters number.
     */
    @Test
    public void createTextWithMaximumCharactersNumber() {
        Text.TextBuilder.create()
                .withMaximumCharactersNumber(80)
                .build();
    }

    /**
     * Tests the creating a {@link Text} without justification.
     */
    @Test
    public void createTextWithoutJustification() {
        Text.TextBuilder.create()
                .withJustification(false)
                .build();
    }

    /**
     * Tests the creating a {@link Text} with justification.
     */
    @Test
    public void createTextWithJustification() {
        Text.TextBuilder.create()
                .withJustification(true)
                .build();
    }

    /**
     * Tests the creating a {@link Text} with a value for the maximum characters number, a list of lines and justifying
     * each line.
     */
    @Test
    public void createTextWithMaximumCharactersNumberAndJustificationWithLines() {
        Text.TextBuilder.create()
                .withJustification(true)
                .withMaximumCharactersNumber(80)
                .withLines(new ArrayList<String>())
                .build();
    }

    /**
     * Tests the creating a {@link Text} with a value for the maximum characters number, with a list of lines and without
     * justifying the lines.
     */
    @Test
    public void createTextWithMaximumCharactersNumberAndWithoutJustificationWithLines() {
        Text.TextBuilder.create()
                .withJustification(false)
                .withMaximumCharactersNumber(80)
                .withLines(new ArrayList<>())
                .build();
    }

    /**
     * Tests whether a {@link Text} created with a value for the maximum characters number, with a list of lines and
     * without justifying the lines contains lines that respect the maximum number of characters.
     */
    @Test
    public void withMaximumCharactersNumberAndWithoutJustificationWithLines() {
        int maximumCharactersNumber = 80;

        Text text = Text.TextBuilder.create()
                .withMaximumCharactersNumber(maximumCharactersNumber)
                .withJustification(false)
                .withLines(new ArrayList<String>())
                .build();

        for (String line : text.getLines()) {
            assertTrue(line.length() <= maximumCharactersNumber);
        }

    }

    /**
     * Tests whether a {@link Text} created with a value for the maximum characters number, with a list of lines and
     * without justifying the lines contains lines that respect the maximum number of characters and are justified.
     */
    @Test
    public void withMaximumCharactersNumberAndJustificationWithLines() {
        int maximumCharactersNumber = 80;

        Text text = Text.TextBuilder.create()
                .withMaximumCharactersNumber(maximumCharactersNumber)
                .withJustification(true)
                .withLines(new ArrayList<String>())
                .build();

        for (String line : text.getLines()) {
            assertEquals(line.length(), maximumCharactersNumber);
        }
    }

    /**
     * Tests the creating a {@link Text} from a {@link String}, with a value for the maximum characters number and with
     * justifying the lines.
     */
    @Test
    public void createTextWithMaximumCharactersNumberAndJustificationFromString() {
        Text.TextBuilder.create()
                .withJustification(true)
                .withMaximumCharactersNumber(80)
                .fromString(input)
                .build();
    }

    /**
     * Tests the creating a {@link Text} from a {@link String}, with a value for the maximum characters number and without
     * justifying the lines.
     */
    @Test
    public void createTextWithMaximumCharactersNumberAndWithoutJustificationFromString() {
        Text.TextBuilder.create()
                .withJustification(false)
                .withMaximumCharactersNumber(80)
                .fromString(input)
                .build();
    }

    /**
     * Tests whether a {@link Text} created from a {@link String}, with a value for the maximum characters number and
     * without justifying the lines contains lines that respect the maximum number of characters.
     */
    @Test
    public void withMaximumCharactersNumberAndWithoutJustificationFromString() {
        int maximumCharactersNumber = 80;

        Text text = Text.TextBuilder.create()
                .withMaximumCharactersNumber(maximumCharactersNumber)
                .withJustification(false)
                .fromString(input)
                .build();

        for (String line : text.getLines()) {
            assertTrue(line.length() <= maximumCharactersNumber);
        }

    }

    /**
     * Tests whether a {@link Text} created from a {@link String}, with a value for the maximum characters number and
     * without justifying the lines contains lines that respect the maximum number of characters and are justified.
     */
    @Test
    public void withMaximumCharactersNumberAndJustificationFromString() {
        int maximumCharactersNumber = 80;

        Text text = Text.TextBuilder.create()
                .withMaximumCharactersNumber(maximumCharactersNumber)
                .withJustification(true)
                .fromString(input)
                .build();

        for (String line : text.getLines()) {
            assertEquals(line.length(), maximumCharactersNumber);
        }
    }

    /**
     * Tests whether a {@link NoLinesException} is thrown or not.
     */
    @Test(expected = NoLinesException.class)
    public void linesAsStringWithNoLines() {
        Text text = Text.TextBuilder.create()
                .build();

        text.linesAsString();
    }

    /**
     * Tests whether the return of {@link Text#linesAsString()} is not null for a {@link Text} created with a list of
     * lines or not.
     */
    @Test
    public void linesAsStringWithLines() {
        Text text = Text.TextBuilder.create()
                .withLines(new ArrayList<String>())
                .build();

        assertTrue(text.linesAsString() != null);
    }

    /**
     * Tests whether the return of {@link Text#linesAsString()} is not null for a {@link Text} created from a {@link String}
     * or not.
     */
    @Test
    public void linesAsStringFromString() {
        Text text = Text.TextBuilder.create()
                .fromString(input)
                .build();

        assertTrue(text.linesAsString() != null);
    }
}