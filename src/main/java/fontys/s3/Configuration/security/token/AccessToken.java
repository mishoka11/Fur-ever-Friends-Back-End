package fontys.s3.Configuration.security.token;

import java.util.Set;

public interface AccessToken {
    String getSubject();
    Set<String> getRoles();
    boolean hasRole(String roleName);
}