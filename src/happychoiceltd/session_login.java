/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happychoiceltd;
    import java.util.HashMap;
    import java.util.Map;


/**
 *
 * @author felix
 */
public class session_login {
 

    private static final Map<String, Boolean> loggedInUsers = new HashMap<>();

    public static void loginUser(String username) {
        loggedInUsers.put(username, true);
    }

    public static void logoutUser(String username) {
        loggedInUsers.remove(username);
    }

    public static boolean isUserLoggedIn(String username) {
        return loggedInUsers.containsKey(username);
    }
}

    
