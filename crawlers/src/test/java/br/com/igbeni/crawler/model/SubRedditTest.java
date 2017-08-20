package br.com.igbeni.crawler.model;

import org.junit.Test;

import static br.com.igbeni.crawler.model.SubReddit.*;
import static org.junit.Assert.*;

/**
 * Test suite for {@link SubReddit}.
 *
 * @author Iggor Alves
 */
public class SubRedditTest {

    @Test
    public void testCreateEmptySubReddit() {
        SubRedditBuilder.create()
                .build();
    }

    @Test
    public void testCreateWithSubreddit() {
        SubRedditBuilder.create()
                .withSubreddit("askreddit")
                .build();
    }

    @Test
    public void testCreateWithTitle() {
        SubRedditBuilder.create()
                .withThreadTitle("Title")
                .build();
    }

    @Test
    public void testCreateWithThreadLink() {
        SubRedditBuilder.create()
                .withThreadLink("Link")
                .build();
    }

    @Test
    public void testCreateWithThreadAuthorLink() {
        SubRedditBuilder.create()
                .withThreadAuthorLink("Author Link")
                .build();
    }

    @Test
    public void testCreateWithThreadAuthor() {
        SubRedditBuilder.create()
                .withThreadAuthor("Author")
                .build();
    }

    @Test
    public void testCreateWithUpVotes() {
        SubRedditBuilder.create()
                .withUpVotes(10L)
                .build();
    }

    @Test
    public void testToString() {
        assertEquals(true, SubRedditBuilder.create().build().toString().length() > 0);
    }

    @Test
    public void testCompareTo() {
        SubReddit subReddit1 = SubRedditBuilder.create().withUpVotes(10L).build();
        SubReddit subReddit2 = SubRedditBuilder.create().withUpVotes(10L).build();

        assertEquals(-1, subReddit1.compareTo(subReddit2));
    }

}