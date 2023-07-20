package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

import java.awt.*;

public abstract class Character {

    private String name;
    private int maxHp;
    private int currentHp;
    private Point location;
    private int attackDmg;
    private Character target;

    public Character(String name, int maxHp, int attackDamage) {
        this.name = name;
        this.maxHp = maxHp;
        this.attackDmg = attackDamage;
        this.currentHp = maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        if (currentHp <= 0) {
            this.currentHp = 0;
            onCharacterDeath();

        } else if (currentHp > maxHp) {
            this.currentHp = maxHp;
        } else
            this.currentHp = currentHp;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Character getTarget() {
        return target;
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public void attack() throws NotEnoughActionsException,
            InvalidTargetException {
        getTarget().setCurrentHp(getTarget().getCurrentHp() - getAttackDmg());
        getTarget().defend(this);
    }

    public void defend(Character c) {
        c.setCurrentHp(c.getCurrentHp() - getAttackDmg() / 2);
    }

    public void onCharacterDeath() {
        Point p = this.getLocation();

        if (this instanceof Zombie) {
            Game.zombies.remove(this);
            Game.spawnNewZombie();
        } else if (this instanceof Hero) {
            Game.heroes.remove(this);
        }
        ((CharacterCell) Game.map[p.x][p.y]).setCharacter(null);
    }

}
