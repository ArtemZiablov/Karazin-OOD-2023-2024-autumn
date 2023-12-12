package khnu.mizhfac.game;

public class VampireImpl
        extends AbstractWarrior
        implements CanHitAndReportMixin {

    static final int ATTACK = 4;
    static final int INITIAL_HEALTH = 40;
    static final int VAMPIRISM = 50;


    public VampireImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    public static int getVampirism() {
        return VAMPIRISM;
    }

    @Override
    public void hit(CanAcceptDamage opponent) {
        int damageDealt = hitAndReportDealtDamage(opponent);  // mixin
        var healing = damageDealt * getVampirism() / 100;
        healSelf(getHealth() + healing);
    }


}
