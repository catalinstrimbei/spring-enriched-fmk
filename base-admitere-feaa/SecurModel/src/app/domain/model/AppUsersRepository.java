/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.model;

import app.domain.patterns.impl.EntityRepository;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author catalin
 */
public class AppUsersRepository extends EntityRepository {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public AppUsersRepository(EntityManager em) {
        super(em, app.domain.model.AppUser.class);
    }

    public List<AppRole> getEnabledRoleOf(AppUser user){
        String sql = "SELECT r FROM RoleAttachment o JOIN o.role r WHERE o.user = :user and o.enabled = 'Y'";
        logger.info("JAPQL: " + sql);

        List<AppRole> roles = em.createQuery(sql).
                setParameter("user", user).getResultList();
        return roles;
    }
    @Override
    public <T> Collection<T> toCollection() {
        logger.info("JPAQL: " + genericSQL);

        return em.createQuery(genericSQL + " ORDER BY o.dbUser").getResultList();
    }

    public List<AppUser> getRoleUsers(AppRole role){
        String sql = "SELECT DISTINCT ra.user FROM RoleAttachment ra" +
                " WHERE ra.role = :role AND ra.enabled = 'Y'";

        logger.info("JPAQL: " + sql);

        return em.createQuery(sql).setParameter("role", role).getResultList();
    }
}
