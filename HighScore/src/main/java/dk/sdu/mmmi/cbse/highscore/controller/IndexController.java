package dk.sdu.mmmi.cbse.highscore.controller;

import dk.sdu.mmmi.cbse.highscore.objects.CreatedTable;
import dk.sdu.mmmi.cbse.highscore.objects.Score;
import dk.sdu.mmmi.cbse.highscore.objects.TableCol;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String showReports(Model model) {
        List<CreatedTable> createdTables = showTable();
        model.addAttribute("createdTables", createdTables);
        return "/index";
    }

    public List<CreatedTable> showTable() {
        List<Score> highScore = ScoreReader();
        List<String> headers = new ArrayList<>();
        headers.add("Date");
        headers.add("Player");
        headers.add("Score");
        List<CreatedTable> createdTables = createTableList(headers, highScore);
        if (!highScore.isEmpty()) {
            System.out.println("Created Tables Size: " + createdTables.size());
            return createdTables;
        } else {
            System.out.println("High score is empty. Returning an empty list.");
            return new ArrayList<>();
        }
    }

    public List<CreatedTable> createTableList(List<String> headers, List<Score> highScore) {
        List<CreatedTable> tableList = new ArrayList<>();

        int rows = 0;
        if (highScore.isEmpty()) {
            // Add backup test data
            for (int i = 1; i <= 10; i++) {
                CreatedTable createdTable = new CreatedTable();
                createdTable.setRow(i);
                List<TableCol> tableColList = new ArrayList<>();

                TableCol tableCol1 = new TableCol();
                tableCol1.setColName("test1");
                tableCol1.setValue("testval1row" + i);
                tableColList.add(tableCol1);

                TableCol tableCol2 = new TableCol();
                tableCol2.setColName("test2");
                tableCol2.setValue("testval2row" + i);
                tableColList.add(tableCol2);

                createdTable.setTableCols(tableColList);
                tableList.add(createdTable);
            }
        } else {
            // Populate table with data from highScore
            for (Score score : highScore) {
                rows++;
                CreatedTable createdTable = new CreatedTable();
                createdTable.setRow(rows);
                List<TableCol> colList = new ArrayList<>();

                for (String BRow : headers) {
                    TableCol tableCol = new TableCol();
                    tableCol.setColName(BRow);
                    if (BRow.equals("Date"))
                        tableCol.setValue(String.valueOf(score.getDate()));
                    if (BRow.equals("Player"))
                        tableCol.setValue(score.getPlayerName());
                    if (BRow.equals("Score"))
                        tableCol.setValue(String.valueOf(score.getScore()));
                    colList.add(tableCol);
                }
                createdTable.setTableCols(colList);
                tableList.add(createdTable);
            }
        }

        return tableList;
    }

    private List<Score> ScoreReader () {
        List<Score> scores = new ArrayList<>();
        File csvFile = new File(System.getProperty("user.dir"), "/DataResource/Score.csv");
        boolean success = false;
        if(csvFile.exists()){
            try(BufferedReader file = new BufferedReader(new FileReader(csvFile))) {
                String line = "";
                while ((line = file.readLine()) != null) {
                    success = true;
                    String[] tokens = line.split(",");
                    Score score = new Score();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    score.setDate(dateFormat.parse(tokens[0]));
                    score.setPlayerName(tokens[1]);
                    score.setScore(Integer.parseInt(tokens[2].replace("\"","")));
                    scores.add(score);
                }
            } catch (ParseException | IOException e) {
                System.out.println("ScoreReader Error: "+e.getMessage());
            }
        }
        else {
            System.out.println("ScoreReader Error: CSV file is not initialized");
        }
        if(!success) {
            Score score = new Score();
            score.setDate(new Date());
            score.setPlayerName("error");
            score.setScore(0);
        }
        System.out.println(scores);
        return scores;
    }
}
