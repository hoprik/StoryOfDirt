package ru.hoprik.story.scripts;

import net.minecraft.entity.player.PlayerEntity;
import ru.hoprik.story.hero.Executer;
import ru.hoprik.story.hero.StoryFunction;
public class Script_two {
    static PlayerEntity playerTriger;
    public static void HeroSay(){
        Executer executer = new Executer();
        executer.addS(() -> {StoryFunction.SayPlayerAll(playerTriger, "Земля", "Я вспонил рецепт!"); StoryFunction.ItemSay(playerTriger); },1);
        executer.addS(() -> {StoryFunction.SayPlayerAll(playerTriger, "Земля", "И крафтиться из..."); },1);
        executer.addS(() -> {StoryFunction.SayPlayerAll(playerTriger, "Земля", "Одного алмаза"); },1);
        executer.addS(() -> {StoryFunction.SayPlayerAll(playerTriger, "Земля", "Одного обсидиана"); },1);
        executer.addS(() -> {StoryFunction.SayPlayerAll(playerTriger, "Земля", "Одного железного блока"); },1);
        executer.addS(() -> {StoryFunction.SayPlayerAll(playerTriger, "Земля", "Одного золотого слитка"); },1);
        executer.addS(() -> {StoryFunction.SayPlayerAll(playerTriger, "Земля", "И одного угольного блока"); },1);
        executer.addS(() -> {StoryFunction.SayPlayerAll(playerTriger, "Земля", "Вот добавил крафт в твою записную книгу крафтов");StoryFunction.unlockRecipe(playerTriger, "half_god_igot");StoryFunction.ItemDisbleSay(playerTriger); },1);
        executer.Exec();
    }
}