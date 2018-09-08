package lesson05hw;

/*
 *@author Yakovlev Alexandr
 */
public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c) throws InterruptedException;
}
