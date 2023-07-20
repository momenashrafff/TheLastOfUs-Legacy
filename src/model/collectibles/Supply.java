package model.collectibles;

import model.characters.Hero;

public class Supply implements Collectible {

    @Override
    public void pickUp(Hero h) {
        h.getSupplyInventory().add(this);
    }

    @Override
    public void use(Hero h) {
        h.getSupplyInventory().remove(this);
    }

}
