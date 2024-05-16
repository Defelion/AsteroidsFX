package dk.sdu.mmmi.cbse.commonweb;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.Score;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScoreSaverProcess implements IPostEntityProcessingService {

    private static File csvFile;

    /**
     * @param gameData the game data
     * @param world    the world
     */
    @Override
    public void process(GameData gameData, World world) {
        try {
            csvFile = new File(System.getProperty("user.dir"), "/DataResource/Score.csv");
            csvFile.getParentFile().mkdirs();
            URL csvURL = csvFile.toURI().toURL();
            if(gameData.getHighScore().isEmpty()){
                try { gameData.setHighScore(ScoreReader()); }
                catch (IOException e) { System.out.println("getHighScore Error: "+e.getMessage()); }
            }
            if(gameData.isGameOver())
                try { ScoreWriter(gameData.getHighScore()); }
                catch (IOException e)
                { System.out.println("ScoreWriter Error: "+e.getMessage()); }
        } catch (MalformedURLException e) {
            System.out.println("URL Error: "+e.getMessage());
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private List<Score> ScoreReader () throws IOException {
        List<Score> scores = new ArrayList<>();
        if(csvFile.exists()){
            try(BufferedReader file = new BufferedReader(new FileReader(csvFile))) {
                String line = "";
                while ((line = file.readLine()) != null) {
                    String[] tokens = line.split(",");
                    Score score = new Score();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    score.setDate(dateFormat.parse(tokens[0]));
                    score.setPlayerName(tokens[1]);
                    score.setScore(Integer.parseInt(tokens[2].replace("\"","")));
                    scores.add(score);
                }
            } catch (ParseException e) {
                System.out.println("ScoreReader Error: "+e.getMessage());
            }
        }
        else {
            System.out.println("ScoreReader Error: CSV file is not initialized");
        }
        return scores;
    }

    private void ScoreWriter (List<Score> highScore) throws IOException {
        List<String[]> data = new ArrayList<String[]>();
        for (Score score : highScore) {
            System.out.println("player score date: " + score.getDate());
            data.add(new String[] {
                    score.getDate().toString(),
                    score.getPlayerName(),
                    String.format("%.2f", score.getScore())
            });
        }

        try(PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {
            data.stream().map(this::convertToCSV).forEach(pw::println);
        }
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
