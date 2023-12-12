package khnu.mizhfac.game;

public class LancerImpl
        extends AbstractWarrior
        implements CanHitAndReportMixin{

    static final int INITIAL_HEALTH = 60;
    static final int ATTACK = 6;
    static final int PENETRATION = 50; // 50%

    public LancerImpl(){
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public void hit(CanAcceptDamage opponent) {
        // вдарити опонента, спитати чи є хтось позаду, якщо є - вдарити і його на половину від атаки
        int damageDealt = hitAndReportDealtDamage(opponent);  // mixin pattern
        if (opponent instanceof WarriorInArmy warriorInArmy){
            var nextBehind = warriorInArmy.getWarriorBehind();
            if(nextBehind.isPresent()) { // перевіряємо чи коробочка не порожня
                int secondDamage = damageDealt * PENETRATION / 100;
                //nextBehind.get().acceptDamage(secondDamage); // так, або через proxy
                CanHit proxySecondHitLancer = () -> secondDamage;
                proxySecondHitLancer.hit(nextBehind.get());
            }
        }
    }
}
