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
 * @author Alexander
 */
public interface EntityPart {
    void process(GameData gameData, Entity entity);
}
