package br.com.igbeni.crawler;

import br.com.igbeni.crawler.exception.NoSubRedditsException;
import br.com.igbeni.crawler.model.SubReddit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test suite for {@link Crawler}.
 *
 * @author Iggor Alves
 */
public class CrawlerTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetInstance() {
    }

    @Test(expected = NoSubRedditsException.class)
    public void testGetSubRedditInfoWithNullArray() {
        Crawler.getInstance()
                .getSubRedditsInfo(null);
    }

    @Test
    public void testGetSubRedditInfoWithEmptyArray() {
        Crawler.getInstance()
                .getSubRedditsInfo(new String[]{});
    }

    @Test
    public void testGetSubRedditInfoWithNonEmptyArray() {
        Crawler.getInstance()
                .getSubRedditsInfo(new String[]{"askreddit", "worldnews", "cats"});
    }
}