package khnu.mizhfac.game;

import java.util.*;
import java.util.function.Supplier;

public class Army implements Iterable<Warrior> {

    private static int idCounter = 0;
    private int id = ++idCounter;

    private Deque<WarriorInArmyImpl> troops = new ArrayDeque<>();

    interface Command {
    }

    interface ChampionDealsHit extends Command{
        ChampionDealsHit INSTANCE =  new ChampionDealsHit(){};
    }

    private class WarriorInArmyImpl implements WarriorInArmy{
        // TODO
        final Warrior warrior;
        WarriorInArmy warriorBehind;


        public WarriorInArmyImpl(Warrior warrior) {
            this.warrior = Objects.requireNonNull(warrior);
        }

        public void setWarriorBehind(WarriorInArmy warriorBehind) {
            this.warriorBehind = Objects.requireNonNull(warriorBehind);
        }

        @Override
        public void acceptDamage(int damage) {
            warrior.acceptDamage(damage);
        }

        Warrior unwrap(){
            return warrior;
        }

        void passCommand(Command command, WarriorInArmy passer){
            if(passer != this){
                if (command instanceof ChampionDealsHit &&
                    warrior instanceof CanHeal healer){
                    healer.heal(passer);
                }
            }
            getWarriorBehind().ifPresent(
                    w -> ((WarriorInArmyImpl) w).passCommand(command, this)
            );
        }

        @Override
        public void hit(CanAcceptDamage opponent) {
            warrior.hit(opponent);
            passCommand(ChampionDealsHit.INSTANCE, this);
        }

        @Override
        public int getAttack() {
            return warrior.getAttack();
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }

        @Override
        public boolean isAlive() {
            return warrior.isAlive();
        }

        @Override
        public Optional<WarriorInArmy> getWarriorBehind() {
            return Optional.ofNullable(warriorBehind);
        }

        @Override
        public String toString() {
            return warrior.toString();
        }
    }

    public Army addUnits(WarriorClasses warrior, int quantity) {
        return addUnits(warrior::make, quantity);
    }

    public Army addUnits(Supplier<Warrior> warriorFactory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Warrior novice = warriorFactory.get();
            var noviceInArmy = new WarriorInArmyImpl(novice);
            // todo: binding
            // this
            /*var last = troops.peekLast();
            if(last != null){
                last.setWarriorBehind(noviceInArmy);
            }*/
            // or
            Optional.ofNullable(troops.peekLast())
                            .ifPresent(w -> w.setWarriorBehind(noviceInArmy));

            troops.add(noviceInArmy);
        }
        return this;
    }

    public boolean isEmpty(){
        return (new FirstAliveIterator().hasNext());
    }

    @Override
    public Iterator<Warrior> iterator() {
        return new AllAliveIterator();
    }

    public Iterator<Warrior> firstAliveIterator() {
        return new FirstAliveIterator();
    }

    private class FirstAliveIterator implements Iterator<Warrior>{
        @Override
        public boolean hasNext() {
            while (!troops.isEmpty() && !troops.peek().isAlive()){
                troops.poll();
            }
            return !troops.isEmpty();
        }
        @Override
        public Warrior next() {
            if(!hasNext()) throw new NoSuchElementException();

            return troops.peek();
        }
    }

    private class AllAliveIterator implements Iterator<Warrior>{

        Iterator<WarriorInArmyImpl> iterator = troops.iterator();
        WarriorInArmyImpl warrior;

        @Override
        public boolean hasNext() {
            while(iterator.hasNext()){
                warrior = iterator.next();
                if (warrior.isAlive()) return true;
            }
            return false;
        }
        @Override
        public Warrior next() {
            if(!hasNext()) throw new NoSuchElementException();
            return warrior.unwrap();
        }
    }


    @Override
    public String toString() {
        return "Army#" + id + "{" +
                "troops=" + troops +
                '}';
    }

}
