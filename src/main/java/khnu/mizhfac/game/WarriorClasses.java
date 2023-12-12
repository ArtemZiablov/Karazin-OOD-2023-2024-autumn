package khnu.mizhfac.game;

public enum WarriorClasses {

    WARRIOR, KNIGHT, DEFENDER, VAMPIRE, LANCER, HEALER, WARRIOR_WITH_DEFENCE_AND_VAMPIRISM, WARRIOR_WITH_VAMPIRISM_AND_DEFENCE;

    public static Warrior factory(WarriorClasses warriorType){
        return switch (warriorType){
            case WARRIOR -> new WarriorImpl();
            case KNIGHT -> new KnightImpl();
            case DEFENDER -> new DefenderImpl();
            case VAMPIRE -> new VampireImpl();
            case LANCER -> new LancerImpl();
            case HEALER -> new HealerImpl();
            case WARRIOR_WITH_DEFENCE_AND_VAMPIRISM -> new VampireDecorator(new DefenderDecorator(new WarriorImpl()));
            case WARRIOR_WITH_VAMPIRISM_AND_DEFENCE -> new DefenderDecorator(new VampireDecorator(new WarriorImpl()));

        };
    }

    public Warrior make(){
        return factory(this);
    }
}
