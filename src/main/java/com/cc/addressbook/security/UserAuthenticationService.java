package com.cc.addressbook.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * @author cclaudiu
 *
 * There are various checks that Shiro offers:
 * --> check based on the exceptions Type the correct cause for the fail-user-authentication
 * --> create roles for users
 * --> set permissions for that roles
 * --> persist some data in subject's session
 * Link to Apache Shiro Trail {@literal http://shiro.apache.org/tutorial.html}
 *
 * Simple work-case:
 *  if ( currentUser.hasRole( "schwartz" ) )
 *        log.info("May the Schwartz be with you!")
 *
 *
 * Permission handling:
 *  if ( currentUser.isPermitted( "lightsaber:weild" ) )
 *       log.info("You may use a lightsaber ring.  Use it wisely.");
 *
 * For treating exceptions waterfall we can do something like:
 * try {
 * * currentUser.login(token);
 *} catch (UnknownAccountException uae) {
 *log.info("There is no user with username of " + token.getPrincipal());
 *} catch (IncorrectCredentialsException ice) {
 *log.info("Password for account " + token.getPrincipal() + " was incorrect!");
 *} catch (LockedAccountException lae) {
 *log.info("The account for username " + token.getPrincipal() + " is locked.  " +
 *"Please contact your administrator to unlock it.");
 *}
 * ... catch more exceptions here (maybe custom ones specific to your application?
 *catch (AuthenticationException ae) {
 *unexpected condition?  error?
 *}
 *}
 * And in the end:
 * currentUser.logout();
 *
 * The database needs to be setup, so that we have the domains with the roles and permissions
 * Shiro uses plain JDBC for establishing the DB Connection
 */
public class UserAuthenticationService {
    private User user;

    public UserAuthenticationService(User user) {
        this.user = user;
    }

    public void doAuthenticate(User user) {
        Subject userSubject = SecurityUtils.getSubject();

        if(!userSubject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getLogin(), user.getPassword().toCharArray());
            token.setRememberMe(user.isRememberMe());

            userSubject.login(token);
        }
    }
}
