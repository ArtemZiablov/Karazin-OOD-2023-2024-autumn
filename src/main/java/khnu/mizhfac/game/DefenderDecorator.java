package khnu.mizhfac.game;

public class DefenderDecorator extends BaseWarrior implements Warrior, CanAcceptDamage{
    static final int DEFENCE = 2;

    public DefenderDecorator(Warrior warrior) {
        super(warrior);
    }

    public int getDefence() {
        return DEFENCE;
    }

    public void acceptDamage(final int damage) {
        int reducedDamage = Math.max(0, damage - getDefence());
        super.acceptDamage(reducedDamage);
    }

    @Override
    public String toString() {
        return "Defender" + wrapeedWarrior.toString();
    }
}
