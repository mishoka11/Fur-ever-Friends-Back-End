package fontys.s3.Configuration.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}