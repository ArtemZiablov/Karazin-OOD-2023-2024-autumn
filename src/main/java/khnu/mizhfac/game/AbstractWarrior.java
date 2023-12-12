package khnu.mizhfac.game;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractWarrior implements Warrior, CanHealSelf  {

    private static int idCounter = 0;
    private int health;
    private int initialHealth;
    private int id = ++idCounter;

    public AbstractWarrior(int health) {
        this.health = health;
        this.initialHealth = health;
    }

    public void acceptDamage(int damage){
        setHealth(getHealth() - damage);
    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = Math.min(initialHealth, health);
    }

    public void healSelf(int heal){
        setHealth(heal);
    }

    public abstract int getAttack();

    @Override
    public String toString() {
        String name = getClass().getSimpleName();
        name = name.replaceAll("Impl", "");
        name = name.toUpperCase();
        return name + "#" + id + "{" +
                "h=" + health +
                ", a=" + getAttack() +
                '}';
    }

}
