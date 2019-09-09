import java.util.*;

public class ScoreUtil {

    private final String[] initialscore = {"0", "1", "2"};
    List<String> player1Score = new ArrayList<>();
    List<String> player2Score = new ArrayList<>();
    int gameScoreIndex = 0;
    Map<String, String> scoreMap = new HashMap<>();
    Map<String, String> scoreRef = new HashMap<>();
    List<String> initialScoreList = Arrays.asList(initialscore);
    List<Integer> player1Games = new ArrayList<>();
    List<Integer> player2Games = new ArrayList<>();

    ScoreUtil() {
        player1Score.add("LOVE");
        player2Score.add("LOVE");
        scoreMap.put("LOVE", "0");
        scoreMap.put("15", "1");
        scoreMap.put("30", "2");
        scoreMap.put("40", "3");
        scoreMap.put("Adv", "4");
        scoreMap.put("Game", "5");
        scoreMap.put("Deuce", "4");
        scoreRef.put("1", "15");
        scoreRef.put("2", "30");
        scoreRef.put("3", "40");
        scoreRef.put("4", "Adv");
        scoreRef.put("5", "Game");
        player1Games.add(0);
        player2Games.add(0);
    }

    public void pointWonBy(String player) {
        String currentPlayer1 = player1Score.get(gameScoreIndex);
        String currentPlayer2 = player2Score.get(gameScoreIndex);
        if ("1".equals(player)) {
            if (initialScoreList.contains(scoreMap.get(currentPlayer1))) {
                Integer score = Integer.parseInt(scoreMap.get(currentPlayer1)) + 1;
                player1Score.add(scoreRef.get(score.toString()));
                player2Score.add(currentPlayer2);
            } else if ("3".equals(scoreMap.get(currentPlayer1))) {
                if (initialScoreList.contains(scoreMap.get(currentPlayer2))) {
                    Integer score = Integer.parseInt(scoreMap.get(currentPlayer1)) + 2;
                    player1Score.add(scoreRef.get(score.toString()));
                    player2Score.add(currentPlayer2);
                } else if ("3".equals(scoreMap.get(currentPlayer2))) {
                    Integer score = Integer.parseInt(scoreMap.get(currentPlayer1)) + 1;
                    System.out.println("score after 40:  " + score);
                    player1Score.add(scoreRef.get(score.toString()));
                    player2Score.add(currentPlayer2);
                } else {
                    Integer score = Integer.parseInt(scoreMap.get(currentPlayer1));
                    player1Score.add(scoreRef.get(score.toString()));
                    Integer score2 = Integer.parseInt(scoreMap.get(currentPlayer2)) - 1;
                    player2Score.add(scoreRef.get(score2.toString()));
                }
            } else if ("4".equals(scoreMap.get(currentPlayer1))) {
                Integer score = Integer.parseInt(scoreMap.get(currentPlayer1)) + 1;
                player1Score.add(scoreRef.get(score.toString()));
                player2Score.add(currentPlayer2);
            }

        } else if ("2".equals(player)) {
            if (initialScoreList.contains(scoreMap.get(currentPlayer2))) {
                Integer score = Integer.parseInt(scoreMap.get(currentPlayer2)) + 1;
                player2Score.add(scoreRef.get(score.toString()));
                player1Score.add(currentPlayer1);
            } else if ("3".equals(scoreMap.get(currentPlayer2))) {
                if (initialScoreList.contains(scoreMap.get(currentPlayer1))) {
                    Integer score = Integer.parseInt(scoreMap.get(currentPlayer2)) + 2;
                    player2Score.add(scoreRef.get(score.toString()));
                    player1Score.add(currentPlayer1);
                } else if ("3".equals(scoreMap.get(currentPlayer1))) {
                    Integer score = Integer.parseInt(scoreMap.get(currentPlayer2)) + 1;
                    player2Score.add(scoreRef.get(score.toString()));
                    player1Score.add(currentPlayer1);
                } else {
                    Integer score = Integer.parseInt(scoreMap.get(currentPlayer2));
                    player2Score.add(scoreRef.get(score.toString()));
                    Integer score2 = Integer.parseInt(scoreMap.get(currentPlayer1)) - 1;
                    player1Score.add(scoreRef.get(score2.toString()));
                }
            } else if ("4".equals(scoreMap.get(currentPlayer2))) {
                Integer score = Integer.parseInt(scoreMap.get(currentPlayer2)) + 1;
                player2Score.add(scoreRef.get(score.toString()));
                player1Score.add(currentPlayer1);
            }
        }
        gameScoreIndex++;
    }

    private void printScore() {
        Integer player1Game = player1Games.get(player1Games.size() - 1);
        Integer player2Game = player2Games.get(player2Games.size() - 1);
        if ("40".equals(player1Score.get(gameScoreIndex)) && "40".equals(player2Score.get(gameScoreIndex))) {
            System.out.println("Set Scores by the players player1-player2: Deuce");
            System.out.println("Match Scores: " + player1Game + " - " + player2Game);
        } else {
            System.out.println("Set Scores by the players player1-player2: " + player1Score.get(gameScoreIndex) + " - " + player2Score.get(gameScoreIndex));
            System.out.println("Match Scores: " + player1Game + " - " + player2Game);
        }
    }

    private void addGameForPlayer(String player) {
        Integer currentPlayer1Points = player1Games.get(player1Games.size() - 1);
        Integer currentPlayer2Points = player2Games.get(player2Games.size() - 1);
        if ("1".equals(player)) {
            player1Games.add(currentPlayer1Points + 1);
        } else {
            player2Games.add(currentPlayer2Points + 1);
        }
    }

    public boolean checkGame() {
        if ("GAME".equalsIgnoreCase(player1Score.get(gameScoreIndex))) {
            addGameForPlayer("1");
            printScore();
            resetGamePoints();
            gameScoreIndex = 0;
            return true;
        } else if ("GAME".equalsIgnoreCase(player2Score.get(gameScoreIndex))) {
            addGameForPlayer("2");
            printScore();
            resetGamePoints();
            gameScoreIndex = 0;
            return true;
        }
        printScore();
        return false;
    }

    private void resetGamePoints() {
        player1Score = new ArrayList<>();
        player1Score.add("LOVE");
        player2Score = new ArrayList<>();
        player2Score.add("LOVE");
        gameScoreIndex = 0;
    }

    public boolean isMatchComplete() {
        String player = "";
        checkGame();
        boolean isMatchComplete = false;
        if (player1Games.get(player1Games.size() - 1) == 6) {
            player = "Player 1 Wins";
            System.out.println(player);
            isMatchComplete = true;
        } else if (player2Games.get(player2Games.size() - 1) == 6) {
            player = "Player 2 Wins";
            System.out.println(player);
            isMatchComplete = true;
        }
        return isMatchComplete;
    }
}
