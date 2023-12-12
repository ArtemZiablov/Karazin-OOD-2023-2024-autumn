package khnu.mizhfac.game;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static khnu.mizhfac.game.Game.straightFight;
import static org.junit.jupiter.api.Assertions.*;
import static khnu.mizhfac.game.Game.fight;
import static khnu.mizhfac.game.WarriorClasses.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
class GameTest {

    @ParameterizedTest
    @MethodSource("warriorsPairsFirstWin")
    @DisplayName("first should win")
    void fightTestFirsWin(Warrior first, Warrior second) {
        assertTrue(fight(first, second));
    }

    @ParameterizedTest
    @MethodSource("warriorsPairsSecondWin")
    @DisplayName("second should win")
    void fightTestSecondWin(Warrior first, Warrior second) {
        assertFalse(fight(first, second));
    }

    static Stream<Arguments> warriorsPairsFirstWin(){
        return Stream.of(
                arguments(WARRIOR.make(), WARRIOR.make()),
                arguments(KNIGHT.make(), WARRIOR.make()),
                arguments(KNIGHT.make(), KNIGHT.make())
                );
    }

    static Stream<Arguments> warriorsPairsSecondWin(){
        return Stream.of(
                arguments(WARRIOR.make(), KNIGHT.make())
        );
    }


    @Test
    @DisplayName("when warrior fights against warrior the first should win")
    void test01() {
        log.info("test01 is running");
        // given
        Warrior chuck = WARRIOR.make();
        Warrior bruce = WARRIOR.make();
        log.debug("chuck = {}", chuck);
        log.debug("bruce = {}", bruce);
        // when
        boolean res = fight(chuck, bruce);
        // then
        assertTrue(res);
    }
    @Test
    @DisplayName("when warrior fights against knight he should lose")
    void test02() {
        // given
        Warrior chuck = WARRIOR.make();
        Warrior bruce = KNIGHT.make();
        // when
        boolean res = fight(chuck, bruce);
        // then
        assertFalse(res);
    }

    /*"4. Fight": [
        prepare_test(middle_code='''zeus = Knight()
godkiller = Warrior()
fight(zeus, godkiller)''',
                     test="zeus.is_alive",
                     answer=True)
                ],*/
    @Test
    @DisplayName("when knight fights against warrior he should stay alive")
    void test4() {
        // given
        Warrior zeus = KNIGHT.make();
        Warrior godkiller = WARRIOR.make();
        // when
        fight(godkiller, zeus);
        // then
        assertTrue(zeus.isAlive());
    }

    /*"5. Fight": [
        prepare_test(middle_code='''husband = Warrior()
wife = Warrior()
fight(husband, wife)''',
                     test="wife.is_alive",
                     answer=False)
                ],*/
    @Test
    @DisplayName("when warrior fights against warrior, second should die")
    void test5() {
        // given
        Warrior husband = WARRIOR.make();
        Warrior wife = WARRIOR.make();
        // when
        fight(husband, wife);
        // then
        assertFalse(wife.isAlive());
    }

    /*"6. Fight": [
        prepare_test(middle_code='''dragon = Warrior()
knight = Knight()
fight(dragon, knight)''',
                     test="knight.is_alive",
                     answer=True)
                ],*/

    @Test
    @DisplayName("when warrior fights against knight, second should stay alive")
    void test6() {
        // given
        Warrior dragon = WARRIOR.make();
        Warrior knight = KNIGHT.make();
        // when
        fight(dragon, knight);
        // then
        assertTrue(knight.isAlive());
    }

    /*"7. Fight": [
        prepare_test(middle_code='''unit_1 = Warrior()
unit_2 = Knight()
unit_3 = Warrior()
fight(unit_1, unit_2)''',
                     test="fight(unit_2, unit_3)",
                     answer=False)
                ]*/

    @Test
    @DisplayName("when warrior fights against knight, warrior should die")
    void test7() {
        // given
        Warrior unit_1 = WARRIOR.make();
        Warrior unit_2 = KNIGHT.make();
        Warrior unit_3 = WARRIOR.make();
        // when
        fight(unit_1, unit_2);
        fight(unit_2, unit_3);
        // then
        assertFalse(unit_2.isAlive());

    }

    @Test
    void WarriorVsDefender() {
        log.info("Executing test WarriorVsDefender");
        var warrior = WARRIOR.make();
        var defender = DEFENDER.make();

        var res = fight(warrior, defender);

        assertAll(
                () -> assertFalse(res),
                () -> assertEquals(-1, ((AbstractWarrior)warrior).getHealth()),
                () -> assertEquals(9, ((AbstractWarrior)defender).getHealth())
        );
    }

    @Test
    void DefenderVsVampire() {
        var defender = DEFENDER.make();
        var vampire = VAMPIRE.make();

        var res = fight(defender, vampire);

        assertAll(
                () -> assertTrue(res),
                () -> assertEquals(-1, ((AbstractWarrior)vampire).getHealth()),
                () -> assertEquals(22, ((AbstractWarrior)defender).getHealth())
        );
    }

    @Test
    void defenderSmokeTest(){
        var chuck = WARRIOR.make();
        var bruce = WARRIOR.make();
        var carl = KNIGHT.make();
        var dave = WARRIOR.make();
        var mark = WARRIOR.make();
        var bob = DEFENDER.make();
        var mike = KNIGHT.make();
        var rog = WARRIOR.make();
        var lancelot = DEFENDER.make();

        assertTrue(fight(chuck, bruce));
        assertFalse(fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(fight(carl, mark));
        assertFalse(carl.isAlive());
        assertFalse(fight(bob, mike));
        assertTrue(fight(lancelot, rog));

        var my_army = new Army().addUnits(DEFENDER, 1);

        var enemy_army = new Army()
                .addUnits(WARRIOR, 2);

        var army_3 = new Army()
                .addUnits(WARRIOR, 1)
                .addUnits(DEFENDER, 1);

        var army4 = new Army().addUnits(WARRIOR, 2);

        assertFalse(fight(my_army, enemy_army));
        assertTrue(fight(army_3, army4));

    }

    static class Rookie extends AbstractWarrior{
        public Rookie() {
            super(50);
        }

        @Override
        public int getAttack() {
            return 1;
        }
    }

    @Test
    void test8(){
        var defender = DEFENDER.make();
        var rookie = new Rookie();

        fight(defender, rookie);
        assertEquals(60, ((AbstractWarrior)defender).getHealth());
    }

    @Test
    void test9(){
        var defender = DEFENDER.make();
        var rookie = new Rookie();
        var warrior = WARRIOR.make();

        fight(defender, rookie);
        assertTrue(fight(defender, warrior));
    }

    @DisplayName("Test Straight-Fight")
    @Test
    void fight04() {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(LANCER, 7);
        army1.addUnits(VAMPIRE, 3);
        army1.addUnits(HEALER, 1);
        army1.addUnits(WARRIOR, 4);
        army1.addUnits(HEALER, 1);
        army1.addUnits(DEFENDER, 2);//18
        army2.addUnits(WARRIOR, 4);
        army2.addUnits(DEFENDER, 4);
        army2.addUnits(HEALER, 1);
        army2.addUnits(VAMPIRE, 6);
        army2.addUnits(LANCER, 4);//19
        var res = straightFight(army1, army2);
        assertFalse(res);

    }

    @Test
    void testStraightFight() {

        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(LANCER, 7);
        army1.addUnits(VAMPIRE, 3);
        army1.addUnits(HEALER, 1);
        army1.addUnits(WARRIOR, 4);
        army1.addUnits(HEALER, 1);
        army1.addUnits(DEFENDER, 2);//18

        army2.addUnits(WARRIOR, 4);
        army2.addUnits(DEFENDER, 4);
        army2.addUnits(HEALER, 1);
        army2.addUnits(VAMPIRE, 6);
        army2.addUnits(LANCER, 4);//19
        var res = straightFight(army1, army2);
        assertFalse(res);
    }

    @Test
    @DisplayName("Test for decorated warrior")
    void testDecoratedWarrior() {
        Warrior unit_1 = KNIGHT.make();
        Warrior unit_2 = WARRIOR_WITH_DEFENCE_AND_VAMPIRISM.make();

        Warrior unit_3 = KNIGHT.make();
        Warrior unit_4 = WARRIOR_WITH_VAMPIRISM_AND_DEFENCE.make();
        assertAll(
                () -> assertFalse(fight(unit_1, unit_2)),
                () -> assertFalse(fight(unit_3, unit_4))
        );
    }


}