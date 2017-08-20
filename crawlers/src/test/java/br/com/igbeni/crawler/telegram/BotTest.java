package br.com.igbeni.crawler.telegram;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Test suite for {@link Bot}.
 *
 * @author Iggor Alves
 */
public class BotTest {

    private DefaultBotSession session;
    private Bot bot;

    @Before
    public void setUp() throws IOException {
        bot = new Bot();

        session = getDefaultBotSession();
    }

    @After
    public void tearDown() {
        if (session != null && session.isRunning()) {
            session.stop();
        }

        bot = null;
    }

    @Test
    public void testDefaultBotSessionIsNotRunningWhenCreated() throws Exception {
        assertFalse(session.isRunning());
    }

    @Test
    public void testDefaultBotSessionCanBeStartedAfterCreation() throws Exception {
        session = getDefaultBotSession();
        session.start();
        assertTrue(session.isRunning());
    }

    @Test(expected = IllegalStateException.class)
    public void testDefaultBotSessionCanNotBeStoppedAfterCreation() throws Exception {
        session = getDefaultBotSession();
        session.stop();
    }

    @Test(expected = IllegalStateException.class)
    public void testDefaultBotSessionCanNotBeStartedIfAlreadyStarted() throws Exception {
        session = getDefaultBotSession();
        session.start();
        session.start();
    }

    @Test
    public void testDefaultBotSessionCanBeStoppedIfStarted() throws Exception {
        session = getDefaultBotSession();
        session.start();
        session.stop();
        assertFalse(session.isRunning());
    }

    @Test(expected = IllegalStateException.class)
    public void testDefaultBotSessionCanNotBeStoppedIfAlreadyStopped() throws Exception {
        session = getDefaultBotSession();
        session.start();
        session.stop();
        session.stop();
    }

    @Test
    public void testOnUpdateReceived() {
    }

    @Test
    public void testGetBotUsername() {
        assertNotNull(bot.getBotUsername());
        assertEquals(Bot.BOT_USERNAME, bot.getBotUsername());
    }

    @Test
    public void testGetBotToken() {
        assertNotNull(bot.getBotToken());
        assertEquals(Bot.BOT_TOKEN, bot.getBotToken());
    }

    private DefaultBotSession getDefaultBotSession() throws IOException {
        HttpResponse response = new BasicHttpResponse(new BasicStatusLine(
                new ProtocolVersion("HTTP", 1, 1), 200, ""));
        response.setStatusCode(200);
        response.setEntity(new StringEntity("{}"));

        HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
        Mockito.when(mockHttpClient.execute(Mockito.any(HttpPost.class)))
                .thenReturn(response);
        DefaultBotSession session = new DefaultBotSession();
        session.setToken(bot.getBotToken());
        session.setCallback(bot);
        session.setOptions(new DefaultBotOptions());
        return session;
    }
}