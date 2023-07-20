package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

public class Explorer extends Hero {

    public Explorer(String name, int maxHp, int attackDamage, int maxActions) {
        super(name, maxHp, attackDamage, maxActions);
    }

    public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
        super.useSpecial();
        for (int i = 0; i < Game.map.length; i++) {
            for (int j = 0; j < Game.map[i].length; j++) {
                Game.map[i][j].setVisible(true);
            }
        }
    }

}
