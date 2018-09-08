/*
 *@author Yakovlev Alexandr
 */
public class Tests {
    @BeforeSuite
    public void prepare(){
        System.out.println("making preparations");
    }

    @Test(prioritry = Test.Priority.MAX_PRIORITY)
    public void test1(){
        System.out.println("in Test 1");
    }

    @Test(prioritry = Test.Priority.HIGHEST)
    public void test2(){
        System.out.println("in Test 2");
    }

    @Test
    public void test3(){
        System.out.println("in Test 3");
    }

    @Test(prioritry = Test.Priority.LOW)
    public void test4(){
        System.out.println("in Test 4");
    }

    @Test(prioritry = Test.Priority.VERY_LOW)
    public void test5(){
        System.out.println("in Test 5");
    }

    @AfterSuite
    public void cleanUp(){
        System.out.println("making cleanup");
    }
}
