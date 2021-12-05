import org.omg.CORBA.INTERNAL;

import java.util.Comparator;
import java.util.Objects;

public class Team implements Comparable <Team>{

    private String TeamName;
    //g stands for games
    private int gPlayed;
    private int gWon;
    private int gDrawn;
    private int gLost;
    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;
    private int points;

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public int getgPlayed() {
        return gPlayed;
    }

    public void setgPlayed(int gPlayed) {
        this.gPlayed = gPlayed;
    }

    public int getgWon() {
        return gWon;
    }

    public void setgWon(int gWon) {
        this.gWon = gWon;
    }

    public int getgDrawn() {
        return gDrawn;
    }

    public void setgDrawn(int gDrawn) {
        this.gDrawn = gDrawn;
    }

    public int getgLost() {
        return gLost;
    }

    public void setgLost(int gLost) {
        this.gLost = gLost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return gPlayed == team.gPlayed && gWon == team.gWon && gDrawn == team.gDrawn && gLost == team.gLost && goalsFor == team.goalsFor && goalsAgainst == team.goalsAgainst && goalDifference == team.goalDifference && points == team.points && Objects.equals(TeamName, team.TeamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TeamName, gPlayed, gWon, gDrawn, gLost, goalsFor, goalsAgainst, goalDifference, points);
    }

    @Override
    public int compareTo(Team o) {
        return Comparators.POINTS_GOALDIFF_GOALSCORED.compare(this,o);
    }


    public static  class Comparators {
        public static final Comparator<Team> POINTS = (Team o1,Team o2) -> Integer.compare(o1.getPoints(),o2.getPoints());
        public static final Comparator<Team> GOALDIFF = (Team o1,Team o2) -> Integer.compare(o1.getGoalDifference(),o2.getGoalDifference());
        public static final Comparator<Team> GOALSCORED = (Team o1,Team o2) -> Integer.compare(o1.getGoalsFor(),o2.getGoalsFor());
        public static final Comparator<Team> POINTS_GOALDIFF_GOALSCORED= (Team o1,Team o2) -> POINTS.reversed().thenComparing(GOALDIFF).reversed().thenComparing(GOALSCORED).reversed().compare(o1,o2);

    }

}
