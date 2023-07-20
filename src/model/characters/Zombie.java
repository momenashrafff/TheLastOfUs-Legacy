package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

import java.awt.*;

public class Zombie extends Character {

    static int ZOMBIES_COUNT;

    public Zombie() {
        super("Zombie " + ++ZOMBIES_COUNT, 40, 10);
    }

    public void attack() throws NotEnoughActionsException, InvalidTargetException {
        Point p = getLocation();
        for (int i = -1; i <= 1; i++) {
            int cx = p.x + i;
            if (cx >= 0 && cx <= 14) {
                for (int j = -1; j <= 1; j++) {
                    int cy = p.y + j;
                    if (cy >= 0 && cy <= 14) {
                        if (!(i == 0 && j == 0) && Game.map[cx][cy] instanceof CharacterCell
                                && ((CharacterCell) Game.map[cx][cy]).getCharacter() instanceof Hero) {
                            setTarget(((CharacterCell) Game.map[cx][cy]).getCharacter());
                            super.attack();
                            return;
                        }
                    }
                }
            }
        }
    }
}
