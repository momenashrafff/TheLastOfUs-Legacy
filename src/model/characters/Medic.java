package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

public class Medic extends Hero {

    public Medic(String name, int maxHp, int attackDamage, int maxActions) {
        super(name, maxHp, attackDamage, maxActions);
    }

    public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
        if (getTarget() instanceof Zombie)
            throw new InvalidTargetException("You can only cure fellow heroes.");
        if (!checkDistance())
            throw new InvalidTargetException("You are only able to heal adjacent targets.");
        super.useSpecial();
        getTarget().setCurrentHp(getTarget().getMaxHp());
    }
}
