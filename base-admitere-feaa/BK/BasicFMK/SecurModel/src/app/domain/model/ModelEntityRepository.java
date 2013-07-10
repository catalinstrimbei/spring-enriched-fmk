/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.model;

import app.domain.patterns.impl.EntityRepository;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author catalin
 */
public class ModelEntityRepository extends EntityRepository {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public ModelEntityRepository(EntityManager em) {
        super(em, app.domain.model.ModelEntity.class);
    }
}
