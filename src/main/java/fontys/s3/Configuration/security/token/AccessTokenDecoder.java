package fontys.s3.Configuration.security.token;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}