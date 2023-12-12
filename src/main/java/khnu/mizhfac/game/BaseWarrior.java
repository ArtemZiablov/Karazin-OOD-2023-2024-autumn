package khnu.mizhfac.game;

public class BaseWarrior implements Warrior, CanHealSelf{

    protected Warrior wrapeedWarrior;

    public BaseWarrior(Warrior warrior){
        this.wrapeedWarrior = warrior;
    }

    @Override
    public int getAttack() {
        return wrapeedWarrior.getAttack();
    }

    @Override
    public void hit(CanAcceptDamage opponent) {
        wrapeedWarrior.hit(opponent);
    }

    @Override
    public void acceptDamage(int damage) {
        wrapeedWarrior.acceptDamage(damage);
    }

    @Override
    public int getHealth() {
        return wrapeedWarrior.getHealth();
    }

   /* public void healSelf(){
        wrapeedWarrior
    }*/

    @Override
    public String toString() {
        return wrapeedWarrior.toString();
    }

    @Override
    public void healSelf(int heal) {
        if(wrapeedWarrior instanceof CanHealSelf warriorCanHealSelf){
            warriorCanHealSelf.healSelf(heal);
        }
    }
}
