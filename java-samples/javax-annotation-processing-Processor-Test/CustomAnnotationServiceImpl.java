

public class CustomAnnotationServiceImpl {

    @Loggable(message = "User login attempt", logTime = true)
    public void login( String username ) {
        System.out.println ("处理实际业务-login + username: " + username);
    }


    @Loggable(message = "User logout attempt", logTime = true)
    public void logout( String username ) {
        System.out.println ("处理实际业务-logout + username: " + username);
    }
}
