/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.student.mialb21.common.data.entityparts;

import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.Entity;

/**
 *
 * @author Someone
 */
public class LifePart implements EntityPart {

    private int life;
    private boolean isHit, dead;
    private float expiration;

    public LifePart(int life, float expiration) {
        this.life = life;
        this.expiration = expiration;
        this.isHit = false;
        this.dead = false;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public float getExpiration() {
        return expiration;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setExpiration(float expiration) {
        this.expiration = expiration;
    }  
    
    public void reduceExpiration(float delta){
        this.expiration -= delta;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
        if (this.isHit) {
            this.life -= 1;
            this.isHit = false;
        }
        if (this.life <= 0) {
            this.dead = true;
        }
    }
}
