package ru.hoprik.story.hero.Goals;

public class CalcultorTime {

    public static int TickToSecond(int time){
        return time*20;
    }
    public static int MiliSecondToSecond(int time){
        return time/1000;
    }
    public static int SecondToMiliSecond(int time){
        return time*1000;
    }
}
