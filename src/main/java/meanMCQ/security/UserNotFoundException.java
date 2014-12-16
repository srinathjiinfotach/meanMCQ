package meanMCQ.security;

/**
 * Created by red on 12/1/14.
 */
@SuppressWarnings("serial")
class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super("Could not find user '" + id + "'.");
    }
}
