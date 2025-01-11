package org.example.sportradar.service;

import lombok.Getter;
import org.example.sportradar.model.Game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Getter
public class ScoreBoard {
    private List<Game> games = new ArrayList<>();

    public void startGame(String homeTeam, String awayTeam) {
        games.add(new Game(homeTeam, awayTeam));
    }

    public void finishGame(String homeTeam, String awayTeam) {
        games.removeIf(game -> game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        games.stream()
                .filter(game -> game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam))
                .findFirst()
                .ifPresent(game -> game.updateScore(homeScore, awayScore));
    }

    public List<Game> getSummary() {
        games.sort(Comparator.comparingInt(Game::getTotalScore).reversed()
                .thenComparing(Comparator.comparingLong(Game::getTimestamp).reversed()));
        return games;
    }

    public void printSummary() {
        getSummary().forEach(System.out::println);
    }
}
