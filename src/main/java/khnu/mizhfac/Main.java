package khnu.mizhfac;

import khnu.mizhfac.game.*;
import lombok.extern.slf4j.Slf4j;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/import static khnu.mizhfac.game.Game.fight;
import static khnu.mizhfac.game.WarriorClasses.*;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.trace("log trace"); // arguments, results of methods etc.
        log.debug("log debug");
        log.info("log info"); // рівень за замовчуванням(ініціалізація завершена, надійшов запит з такої-то адреси і т.п.)
        log.warn("log warn"); // клієнт спробував залогувати, але ввів неправильний пароль
        log.error("log error");

        System.out.println("Hello world!");


    }
    public static int add(int a, int b){
        return a + b;
    }
}