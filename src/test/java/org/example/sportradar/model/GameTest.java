package org.example.sportradar.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game("HomeTeam", "AwayTeam");
    }

    @Test
    public void testInitialScores() {
        //given
        //when
        //then
        assertEquals(0, game.getHomeScore());
        assertEquals(0, game.getAwayScore());
    }

    @Test
    public void testUpdateScore() {
        //given
        //when
        game.updateScore(1, 2);
        //then
        assertEquals(1, game.getHomeScore());
        assertEquals(2, game.getAwayScore());
    }

    @Test
    public void testGetTotalScore() {
        //given
        //when
        game.updateScore(1, 2);
        //then
        assertEquals(3, game.getTotalScore());
    }

    @Test
    public void testToString() {
        //given
        //when
        game.updateScore(1, 2);
        //then
        assertEquals("HomeTeam 1 - AwayTeam 2", game.toString());
    }

    @Test
    public void testConstructor() {
        //given
        //when
        long beforeTimestamp = System.currentTimeMillis();
        Game newGame = new Game("HomeTeam", "AwayTeam");
        long afterTimestamp = System.currentTimeMillis();
        //then
        assertEquals("HomeTeam", newGame.getHomeTeam());
        assertEquals("AwayTeam", newGame.getAwayTeam());
        assertTrue(newGame.getTimestamp() >= beforeTimestamp);
        assertTrue(newGame.getTimestamp() <= afterTimestamp);
    }
}
