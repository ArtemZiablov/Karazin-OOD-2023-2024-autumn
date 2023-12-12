package khnu.mizhfac.game;

public class HealerImpl extends AbstractWarrior implements CanHeal{
    private static final int HEAL_POWER = 2;
    private static final int INITIAL_HEALTH = 50;

    public HealerImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return 0;
    }

    int getHealPower(){
        return HEAL_POWER;
    }

    @Override
    public void heal(HasHealth patient) {
        if(patient instanceof AbstractWarrior abstractWarrior){
            abstractWarrior.setHealth(abstractWarrior.getHealth() + getHealPower());
        }
    }
}
