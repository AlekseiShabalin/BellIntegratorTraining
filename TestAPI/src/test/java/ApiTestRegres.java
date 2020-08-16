import org.testng.annotations.Test;

public class ApiTestRegres {

    @Test()
    public void testAvatar(){
        Steps.checkAvatarUsers();
    }

    @Test()
    public void testAvatarWithSpec(){
        Steps.checkAvatarWithSpec(Specifications.requestSpec(),Specifications.responseSpec());
    }

    @Test
    public void testSuccessfulRegistrationUser(){
        Specifications.installSpec(Specifications.requestSpec());
        Steps.successfulUserRegistration();
    }

    @Test
    public void testUnsuccesfulRegister(){
        Specifications.installSpec(Specifications.requestSpec());
        Steps.registrationUserWithoutPassword();
    }

    @Test
    public void testSortByYear(){
        Specifications.installSpec(Specifications.requestSpec());
        Steps.checkSortByYear();
    }

}
