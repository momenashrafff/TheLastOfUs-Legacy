package model.collectibles;

import model.characters.Hero;

public interface Collectible {

    void pickUp(Hero h);

    void use(Hero h);

}
