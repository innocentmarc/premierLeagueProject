import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PremierLeague {
    public static void main(String[] args) {
        String line = "";

        HashMap<String, String> test = new HashMap<>();
        List<String> list = new ArrayList<>();
        LinkedHashSet<Team> results = new LinkedHashSet<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.csv"));
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }

            for (int x = 0; x < list.size(); x++) {
                String name = list.get(x).substring(0, list.get(x).indexOf(","));
                String matchResults = list.get(x).substring(list.get(x).indexOf(","));
                test.put(name, matchResults);
            }

            for (String s : test.keySet()) {
                Team team = new Team();
                int gPlayed = 0,gWon = 0,gDrawn = 0,gLost = 0,goalsFor =0,goalsAgaints=0,goalDiff=0,points=0;
                results.add(getResults(team,s, test.get(s).split(","), gPlayed, gWon,
                        gDrawn, gLost,points,goalsFor,goalsAgaints,goalDiff));

            }

            //sorting the ranking
            ArrayList<Team> rankingList = new ArrayList<Team>(results);
            Collections.sort(rankingList);


            System.out.println("table results");
            int i = 0;
            for (Team result : rankingList) {
                i++;
                int goalDiff = result.getGoalsFor() - result.getGoalsAgainst();
                System.out.println("("+ i +") " + result.getTeamName() +" GamesPlayed: "+result.getgPlayed() +" GamesWon: " +
                        +result.getgWon() +" GamesDrawn: "+result.getgDrawn()
                        +" GamesLost: "+result.getgLost()
                        +" Goals For: "+result.getGoalsFor() + " Goals Against " +result.getGoalsAgainst()
                        +" Goal Difference: "+ goalDiff
                        +" Points: "+ result.getPoints()
                );
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Team getResults(Team team,String teamName, String[] score,
                                  int gPlayed,int gWon,int gDrawn,int gLost,
                                  int points,int goalsFor ,int goalsAgaints,int goalDiff) {

        //Looping the score results by team
        for (String s : score) {
            if (!s.isEmpty() && !s.equals("")) {

                //Getting the individual scores for the current game
                int scoreA = Integer.parseInt(s.substring(0, 1).trim());
                int scoreB = Integer.parseInt(s.substring(3).trim());

                //Count games played
                gPlayed++;
                goalsFor +=scoreA;
                goalsAgaints +=scoreB;

                if (scoreA > scoreB) {
                    gWon++;
                    points = points + 3;
                } else if (scoreA == scoreB) {
                    gDrawn++;
                    points = points + 1;
                } else {
                    gLost++;
                }

                team.setTeamName(teamName);
                team.setgPlayed(gPlayed);
                team.setgWon(gWon);
                team.setgDrawn(gDrawn);
                team.setgLost(gLost);
                team.setGoalsFor(goalsFor);
                team.setGoalsAgainst(goalsAgaints);
                team.setPoints(points);

            }

        }
            return team;
    }
}
