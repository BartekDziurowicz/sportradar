package org.example.sportradar.service;

import static org.junit.jupiter.api.Assertions.*;

import org.example.sportradar.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.List;

class ScoreBoardTest {

    private ScoreBoard scoreBoard;
    private final String teamName1 = "Mexico";
    private final String teamName2 = "Canada";

    @BeforeEach
    public void setUp() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    void testStartGame() {
        //given
        //when
        scoreBoard.startGame(teamName1, teamName2);
        List<Game> games = scoreBoard.getGames();
        //then
        assertEquals(1, games.size());
        assertEquals(teamName1, games.getFirst().getHomeTeam());
        assertEquals(teamName2, games.getFirst().getAwayTeam());
    }

    @Test
    void testFinishGame() {
        //given
        //when
        scoreBoard.startGame(teamName1, teamName2);
        scoreBoard.finishGame(teamName1, teamName2);
        List<Game> games = scoreBoard.getGames();
        //then
        assertEquals(0, games.size());
    }

    @Test
    void testUpdateScore() {
        //given
        //when
        scoreBoard.startGame(teamName1, teamName2);
        scoreBoard.updateScore(teamName1, teamName2, 1, 2);
        List<Game> games = scoreBoard.getGames();
        //then
        assertEquals(1, games.getFirst().getHomeScore());
        assertEquals(2, games.getFirst().getAwayScore());
    }

    @Test
    void testGetSummary() {
        //given
        //when
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.startGame("Germany", "France");
        scoreBoard.startGame("Uruguay", "Italy");
        scoreBoard.startGame("Argentina", "Australia");

        scoreBoard.updateScore("Mexico", "Canada", 0, 5);
        scoreBoard.updateScore("Spain", "Brazil", 10, 2);
        scoreBoard.updateScore("Germany", "France", 2, 2);
        scoreBoard.updateScore("Uruguay", "Italy", 6, 6);
        scoreBoard.updateScore("Argentina", "Australia", 3, 1);

        List<Game> summary = scoreBoard.getSummary();

        //then
        assertEquals("Spain", summary.getFirst().getHomeTeam());
        assertEquals("Brazil", summary.getFirst().getAwayTeam());
        assertEquals(10, summary.get(0).getHomeScore());
        assertEquals(2, summary.get(0).getAwayScore());

        assertEquals("Uruguay", summary.get(1).getHomeTeam());
        assertEquals("Italy", summary.get(1).getAwayTeam());
        assertEquals(6, summary.get(1).getHomeScore());
        assertEquals(6, summary.get(1).getAwayScore());
    }

}
