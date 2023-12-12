package khnu.mizhfac.game;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static khnu.mizhfac.game.Game.fight;
import static khnu.mizhfac.game.Game.straightFight;
import static org.junit.jupiter.api.Assertions.*;
import static khnu.mizhfac.game.WarriorClasses.WARRIOR;
import static khnu.mizhfac.game.WarriorClasses.KNIGHT;
import static khnu.mizhfac.game.WarriorClasses.DEFENDER;
import static khnu.mizhfac.game.WarriorClasses.VAMPIRE;
import static khnu.mizhfac.game.WarriorClasses.LANCER;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BattleTest {

    @ParameterizedTest
    @MethodSource("armyFirstWin")
    @DisplayName("first army should win")
    void fightTestFirsArmyWin(Army first, Army second) {
        assertTrue(fight(first, second));
    }

    @ParameterizedTest
    @MethodSource("armySecondWin")
    @DisplayName("second army should win")
    void fightTestSecondArmyWin(Army first, Army second) {
        assertFalse(fight(first, second));
    }

    static Stream<Arguments> armyFirstWin() {
        return Stream.of(
                arguments(new Army().addUnits(WARRIOR, 11), new Army().addUnits(WARRIOR, 7)),
                arguments(new Army().addUnits(WARRIOR, 20), new Army().addUnits(WARRIOR, 21)),
                arguments(new Army().addUnits(WARRIOR, 10), new Army().addUnits(WARRIOR, 11))
        );
    }

    static Stream<Arguments> armySecondWin() {
        return Stream.of(
                arguments(new Army().addUnits(WARRIOR, 1), new Army().addUnits(WARRIOR, 2)),
                arguments(new Army().addUnits(WARRIOR, 2), new Army().addUnits(WARRIOR, 3)),
                arguments(new Army().addUnits(WARRIOR, 5), new Army().addUnits(WARRIOR, 7)),
                arguments(new Army().addUnits(WARRIOR, 20).addUnits(KNIGHT, 5),
                        new Army().addUnits(WARRIOR, 30))
        );
    }


    /*"1. Battle": [
        prepare_test(middle_code='''army_1 = Army()
army_2 = Army()
army_1.add_units(Warrior, 1)
army_2.add_units(Warrior, 2)
battle = Battle()''',
                     test="battle.fight(army_1, army_2)",
                     answer=False)
                ],*/
    @Test
    @DisplayName("1. Battle: 1 W < 2 W")
    void battle01() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(WARRIOR, 1);
        army2.addUnits(WARRIOR, 2);
        boolean res = Game.fight(army1, army2);
        assertFalse(res);
    }

    /*2. Battle": [
        prepare_test(middle_code='''army_1 = Army()
army_2 = Army()
army_1.add_units(Warrior, 2)
army_2.add_units(Warrior, 3)
battle = Battle()''',
                     test="battle.fight(army_1, army_2)",
                     answer=False)
                ],*/
    @Test
    @DisplayName("2. Battle: 2 W < 3 W ")
    void battle02() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(WARRIOR, 2);
        army2.addUnits(WARRIOR, 3);
        boolean res = Game.fight(army1, army2);
        assertFalse(res);
    }

    /*3. Battle": [
        prepare_test(middle_code='''army_1 = Army()
army_2 = Army()
army_1.add_units(Warrior, 5)
army_2.add_units(Warrior, 7)
battle = Battle()''',
                     test="battle.fight(army_1, army_2)",
                     answer=False)
                ],*/
    @Test
    @DisplayName("3. Battle: 5 W < 7 W ")
    void battle03() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(WARRIOR, 5);
        army2.addUnits(WARRIOR, 7);
        boolean res = Game.fight(army1, army2);
        assertFalse(res);
    }

    /*4. Battle": [
        prepare_test(middle_code='''army_1 = Army()
army_2 = Army()
army_1.add_units(Warrior, 20)
army_2.add_units(Warrior, 21)
battle = Battle()''',
                     test="battle.fight(army_1, army_2)",
                     answer=True)
                ],*/
    @Test
    @DisplayName("4. Battle: 20 W > 21 W ")
    void battle04() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(WARRIOR, 20);
        army2.addUnits(WARRIOR, 21);
        boolean res = Game.fight(army1, army2);
        assertTrue(res);
    }

    /*5. Battle": [
        prepare_test(middle_code='''army_1 = Army()
army_2 = Army()
army_1.add_units(Warrior, 10)
army_2.add_units(Warrior, 11)
battle = Battle()''',
                     test="battle.fight(army_1, army_2)",
                     answer=True)
                ],*/
    @Test
    @DisplayName("5. Battle: 10 W > 11 W ")
    void battle05() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(WARRIOR, 10);
        army2.addUnits(WARRIOR, 11);
        boolean res = Game.fight(army1, army2);
        assertTrue(res);
    }

    /*6. Battle": [
        prepare_test(middle_code='''army_1 = Army()
army_2 = Army()
army_1.add_units(Warrior, 11)
army_2.add_units(Warrior, 7)
battle = Battle()''',
                     test="battle.fight(army_1, army_2)",
                     answer=True)
                ]*/
    @Test
    @DisplayName("6. Battle: 11 W > 7 W ")
    void battle06() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(WARRIOR, 11);
        army2.addUnits(WARRIOR, 7);
        boolean res = Game.fight(army1, army2);
        assertTrue(res);
    }

    /*
    7. Battle
    army_3 = Army ()
    army_3.add_units(Warrior, 20)
    army_3.add_units(Knight, 5)
    armv 4 = Army()
    army_4.add_units(Warrior, 30)
    battle = Battle()
    battle.fight (my_army, enemy_army) == True 32 battle.fight (army_3, army_4) == False*/
    @Test
    @DisplayName("7. Battle: 20 W + 5 K < 30 W")
    void battle07() {
        var army1 = new Army()
                .addUnits(WARRIOR, 20)
                .addUnits(KNIGHT, 5);
        var army2 = new Army()
                .addUnits(WARRIOR, 30);

        var res = Game.fight(army1, army2);

        assertFalse(res);
    }


    /*  "15. Battle": [
        prepare_test(middle_code='''army_1 = Army()
army_2 = Army()
army_1.add_units(Lancer, 5)
army_1.add_units(Vampire, 3)
army_1.add_units(Warrior, 4)
army_1.add_units(Defender, 2)
army_2.add_units(Warrior, 4)
army_2.add_units(Defender, 4)
army_2.add_units(Vampire, 6)
army_2.add_units(Lancer, 5)
battle = Battle()''',
                     test="battle.fight(army_1, army_2)",
                     answer=False)
                ],*/

    @Test
    @DisplayName("8. Battle: 5*L 3*V 4*W 2*D < 4*W 4*D 6*V 5*L")
    void battle08() {
        var army1 = new Army()
                .addUnits(LANCER, 5)
                .addUnits(VAMPIRE, 3)
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 2);
        var army2 = new Army()
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 4)
                .addUnits(VAMPIRE, 6)
                .addUnits(LANCER, 5);

        var res = Game.fight(army1, army2);

        assertFalse(res);
    }

    /*"16. Battle": [
        prepare_test(middle_code='''army_1 = Army()
army_2 = Army()
army_1.add_units(Lancer, 7)
army_1.add_units(Vampire, 3)
army_1.add_units(Warrior, 4)
army_1.add_units(Defender, 2)
army_2.add_units(Warrior, 4)
army_2.add_units(Defender, 4)
army_2.add_units(Vampire, 6)
army_2.add_units(Lancer, 4)
battle = Battle()''',
                     test="battle.fight(army_1, army_2)",
                     answer=True)
                ],*/

    @Test
    @DisplayName("9. Battle: 7*L 3*V 4*W 2*D > 4*W 4*D 6*V 4*L")
    void battle09() {
        var army1 = new Army()
                .addUnits(LANCER, 7)
                .addUnits(VAMPIRE, 3)
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 2);
        var army2 = new Army()
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 4)
                .addUnits(VAMPIRE, 6)
                .addUnits(LANCER, 4);

        var res = Game.fight(army1, army2);

        assertTrue(res);
    }

    @Test
    @DisplayName("Battle: 20 -- straight fight")
    void battle20() {
        var army1 = new Army()
                .addUnits(LANCER, 7)
                .addUnits(VAMPIRE, 3)
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 2);
        var army2 = new Army()
                .addUnits(WARRIOR, 4)
                .addUnits(DEFENDER, 4)
                .addUnits(VAMPIRE, 6)
                .addUnits(LANCER, 4);

        var res = straightFight(army1, army2);

        assertFalse(res);
    }
}
