package khnu.mizhfac.game;

public class VampireDecorator extends BaseWarrior implements Warrior, CanHitAndReportMixin {

    static final int VAMPIRISM = 50;

    public VampireDecorator(Warrior warrior) {
        super(warrior);
    }

    public static int getVampirism() {
        return VAMPIRISM;
    }

    public void hit(CanAcceptDamage opponent) {
        int damageDealt = hitAndReportDealtDamage(opponent);  // mixin
        var healing = damageDealt * getVampirism() / 100;
        //healSelf(getHealth() + healing);
        if (wrapeedWarrior instanceof CanHealSelf abstractWarrior){
            abstractWarrior.healSelf(getHealth() + healing);
        }

    }


    @Override
    public String toString() {
        return "Vampire" + wrapeedWarrior.toString();
    }
}
