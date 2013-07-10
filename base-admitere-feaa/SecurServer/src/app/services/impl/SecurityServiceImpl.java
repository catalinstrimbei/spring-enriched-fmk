/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services.impl;

import app.domain.model.AppRole;
import app.domain.model.AppRolesRepository;
import app.domain.model.AppUser;
import app.domain.model.AppUsersRepository;
import app.domain.model.RepositoryFactory;
import app.domain.model.RoleAttachment;
import app.services.SecurityService;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.management.relation.Role;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author catalin
 */
public class SecurityServiceImpl implements SecurityService {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    public List<AppUser> getUsers() {
        AppUsersRepository repository = RepositoryFactory.getRepositoryOf(AppUser.class);
        List<AppUser> users = new ArrayList(repository.toCollection());
        for (AppUser user : users) {
            //repository.refresh(user);
            user.setAttachedRolesDTO();
        }
        return users;
    }
    public AppUser getUser(AppUser user) {
        AppUsersRepository repository = RepositoryFactory.getRepositoryOf(AppUser.class);
        user = repository.refresh(user);
        user.setAttachedRolesDTO();
        return user;
    }
    public List<AppRole> getRoles() {
        AppRolesRepository repository = RepositoryFactory.getRepositoryOf(AppRole.class);
        return new ArrayList(repository.toCollection());
    }

    public List<Role> getEnabledRoleOf(AppUser user) {
        AppUsersRepository repository = RepositoryFactory.getRepositoryOf(AppUser.class);
        return new ArrayList(repository.getEnabledRoleOf(user));
    }

    public AppUser addUserInApp(AppUser user) throws Exception {
        AppUser newUser = null;
        // make JDBC call
        Connection conn = getDBConnection();
        CallableStatement call = null;
        try{
            call =  conn.prepareCall(
                "{call " + "admitere.pac_gest_secur.generate_user_standard(?)}");
            call.setString(1, user.getDbUser());
            call.execute();
            // make JPA call
            AppUsersRepository repository = RepositoryFactory.getRepositoryOf(AppUser.class);
            newUser = (AppUser)
                new ArrayList(repository.get(new AppUser(null, user.getDbUser()))).get(0);
         }catch(Exception e){
            e.printStackTrace();
            if (!call.isClosed())
                call.close();
            throw e;
        }
        return newUser;
    }

    public AppUser forceRevalidateUser(AppUser user) throws Exception {
        AppUser validatedUser = null;
        // make JDBC call
        Connection conn = getDBConnection();
        CallableStatement call = null;
        try{
            call = conn.prepareCall(
                "{call " + "admitere.pac_gest_secur.force_revalidate_user(?)}");
            call.setString(1, user.getDbUser());
            call.execute();
            // make JPA call
            AppUsersRepository repository = RepositoryFactory.getRepositoryOf(AppUser.class);
            validatedUser = (AppUser)
                    new ArrayList(repository.get(new AppUser(null, user.getDbUser()))).get(0);
        }catch(Exception e){
            e.printStackTrace();
            if (!call.isClosed())
                call.close();
            throw e;
        }
        return validatedUser;
    }

    public AppUser setUserRole(AppUser user, AppRole role, boolean enabled) throws Exception {
        AppUser validatedUser = null;
        CallableStatement call = null;
        try{
            // make JDBC call
            Connection conn = getDBConnection();
            call =
                    conn.prepareCall(
                    "{call " + "admitere.pac_gest_secur.set_user_role(?, ?, ?)}");
            call.setString(1, user.getDbUser());
            logger.info(" [1] = " + user.getDbUser());
            call.setString(2, role.getRoleName());
            logger.info(" [2] = " + role.getRoleName());
            call.setInt(3, (enabled) ? 1 : 0);
            logger.info(" [3] = " + enabled);
            call.execute();
            
            // make JPA call
            AppUsersRepository repository = RepositoryFactory.getRepositoryOf(AppUser.class);
            validatedUser = (AppUser)
                    new ArrayList(repository.get(new AppUser(null, user.getDbUser()))).get(0);
            repository.refresh(validatedUser);
            for(RoleAttachment r: validatedUser.getAttachedRoles()){
                repository.refresh(r);
            }
            validatedUser.setAttachedRolesDTO();
        }catch(Exception e){
            e.printStackTrace();
            if (!call.isClosed())
                call.close();
            throw e;
        }
        return validatedUser;
    }

    public AppUser setUserState(AppUser user) throws Exception {
        if (user == null || user.getStatus() == null)
            throw new Exception("Utilizator invalid, sau utilizator cu status incorect !");

        AppUser validatedUser = null;
        CallableStatement call = null;
        try{
            // make JDBC call
            Connection conn = getDBConnection();
            call =
                    conn.prepareCall(
                    "{call " + "admitere.pac_gest_secur.set_user_state(?, ?, ?)}");
            call.setString(1, user.getDbUser());
            logger.info(" [1] = " + user.getDbUser());
            call.setString(2, user.isEnabled() == true ? "Y" : "N");
            logger.info(" [2] = " + user.isEnabled());
            call.setString(3, user.getStatus());
            logger.info(" [3] = " + user.getStatus());
            call.execute();

            call =
                    conn.prepareCall(
                    "{call " + "admitere.pac_gest_secur.set_user_role(?, ?, ?)}");

            if (user.getAttachedRoles()  != null && user.getAttachedRoles().size() > 0){
                for (RoleAttachment attachedrole : user.getAttachedRoles()){
                    call.setString(1, user.getDbUser());
                    logger.info(" [1] = " + user.getDbUser());
                    call.setString(2, attachedrole.getRole().getRoleName());
                    logger.info(" [2] = " + attachedrole.getRole().getRoleName());
                    call.setInt(3, (attachedrole.isEnabled()) ? 1 : 0);
                    logger.info(" [3] = " + attachedrole.isEnabled());
                    call.execute();
                }
            }


            // make JPA call
            AppUsersRepository repository = RepositoryFactory.getRepositoryOf(AppUser.class);
            validatedUser = (AppUser)
                    new ArrayList(repository.get(new AppUser(null, user.getDbUser()))).get(0);
            repository.refresh(validatedUser);
            for(RoleAttachment r: validatedUser.getAttachedRoles()){
                repository.refresh(r);
            }
            validatedUser.setAttachedRolesDTO();
        }catch(Exception e){
            e.printStackTrace();
//            if (!call.isClosed())
//                call.close();
            throw e;
        }
        return validatedUser;
    }

    public AppUser forceRevalidateUserFor(AppUser user, AppRole role) throws Exception {
        AppUser validatedUser = null;
        // make JDBC call
        Connection conn = getDBConnection();
        CallableStatement call = null;
        try{
            call = conn.prepareCall(
                "{call " + "admitere.pac_gest_secur.force_revalidate_user_for(?, ?)}");
            call.setString(1, user.getDbUser());
            call.setString(2, role.getRoleName());
            call.execute();
            // make JPA call
            AppUsersRepository repository = RepositoryFactory.getRepositoryOf(AppUser.class);
            validatedUser = (AppUser)
                    new ArrayList(repository.get(new AppUser(null, user.getDbUser()))).get(0);
        }catch(Exception e){
            e.printStackTrace();
            if (!call.isClosed())
                call.close();
            throw e;
        }
        return validatedUser;
    }

    // infrastructure-plumbing methods. TODO: need to be reconsidered
    private static Map<String, String> emfConfigProps;


    static {
        EntityManagerFactory emf;
        emfConfigProps = new HashMap<String, String>();
        // TopLink persistence provider
        emfConfigProps.put("toplink.jdbc.user", "admitere");
        emfConfigProps.put("toplink.jdbc.password", "adm10");
        emfConfigProps.put("toplink.jdbc.url", "jdbc:oracle:oci:@85.122.22.1:1521:ORCL");
        emfConfigProps.put("toplink.jdbc.driver", "oracle.jdbc.driver.OracleDriver");
        
        // Hibernate persistence provider
//        emfConfigProps.put("hibernate.connection.username", "admitere");
//        emfConfigProps.put("hibernate.connection.password", "revadm04");
//        emfConfigProps.put("hibernate.connection.url", "jdbc:oracle:oci:@85.122.22.1:1521:ORCL");
//        emfConfigProps.put("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
//        emfConfigProps.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        emf = Persistence.createEntityManagerFactory("SecurPU", emfConfigProps);
        
        RepositoryFactory.setEntityManagerFactory(emf);
    }
    private static List<Connection> jdbcConnections = new ArrayList<Connection>();

    private static Connection getDBConnection() throws Exception {
        for (Connection conn : jdbcConnections) {
            if (!conn.isClosed()) {
                return conn;
            }
        }

        Connection conn = null;
        Class.forName(emfConfigProps.get("toplink.jdbc.driver"));

//        Class.forName(emfConfigProps.get("hibernate.connection.driver_class"));
        conn = DriverManager.getConnection(
                emfConfigProps.get("toplink.jdbc.url"),
                emfConfigProps.get("toplink.jdbc.user"),
                emfConfigProps.get("toplink.jdbc.password"));

//        conn = DriverManager.getConnection(
//                emfConfigProps.get("hibernate.connection.url"),
//                emfConfigProps.get("hibernate.connection.username"),
//                emfConfigProps.get("hibernate.connection.password"));
        
        jdbcConnections.add(conn);
        return conn;

    }

    public List<AppUser> getRoleUsers(AppRole role) {
        AppUsersRepository repository = RepositoryFactory.getRepositoryOf(AppUser.class);
        List<AppUser> users = repository.getRoleUsers(role);
        logger.info("::: roleUser.size = " + users.size());
        return users;
    }

    public List<AppUser> batchSetUsersRole(List<AppUser> users, AppRole role,
            boolean enabled) throws Exception {
        List<AppUser> refreshedUsers = new ArrayList<AppUser>();
        for (AppUser user : users){
            refreshedUsers.add(setUserRole(user, role, enabled));
        }
        return refreshedUsers;
    }

    public Boolean login(AppUser user , String password) throws Exception{
        // Check user login
        Connection conn = null;
        Class.forName(emfConfigProps.get("toplink.jdbc.driver"));
        conn = DriverManager.getConnection(
                emfConfigProps.get("toplink.jdbc.url"),
                user.getDbUser(),
                password);
        
//        Class.forName(emfConfigProps.get("hibernate.connection.driver_class"));
//        conn = DriverManager.getConnection(
//                emfConfigProps.get("hibernate.connection.url"),
//                user.getDbUser(),
//                password);
        
        conn.close();
        // Check user security role
        logger.info("user : " + user.getDbUser());
        logger.info("password : " + password);
        
        AppUsersRepository usersRepository = RepositoryFactory.getRepositoryOf(AppUser.class);
        AppRolesRepository rolesRepository = RepositoryFactory.getRepositoryOf(AppRole.class);
        AppUser appUser = (AppUser)
                new ArrayList(usersRepository.get(new AppUser(null, user.getDbUser()))).get(0);
        List<AppRole> userRoles = usersRepository.getEnabledRoleOf(appUser);
        AppRole securAdminRole = (AppRole)
                new ArrayList(rolesRepository.get(new AppRole(null, "SECURADMIN"))).get(0);
        if (userRoles != null && !userRoles.isEmpty() && userRoles.contains(securAdminRole))
           return true;
        else
           throw new RuntimeException("Nu aveti drepturile necesare !!!");
    }

    public AppUser forceRevalidateUserPassword(AppUser user) throws Exception {
        AppUser validatedUser = null;
        // make JDBC call
        Connection conn = getDBConnection();
        CallableStatement call = null;
        try{
            call = conn.prepareCall(
                "{call " + "admitere.pac_gest_secur.force_revalidate_user_password(?)}");
            call.setString(1, user.getDbUser());
            call.execute();
            // make JPA call
            AppUsersRepository repository = RepositoryFactory.getRepositoryOf(AppUser.class);
            validatedUser = (AppUser)
                    new ArrayList(repository.get(new AppUser(null, user.getDbUser()))).get(0);
        }catch(Exception e){
            e.printStackTrace();
            if (!call.isClosed())
                call.close();
            throw e;
        }
        return validatedUser;
    }
}