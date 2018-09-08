
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/*
 *@author Yakovlev Alexandr
 */
public class AppTestCase {
    private App dz;

    @Before
    public void prepare() {
        dz = new App();
    }

    @Test(expected = RuntimeException.class)
    public void test_task01_empty_array() {
        dz.task01(new int[0]);
    }

    @Test(expected = RuntimeException.class)
    public void test_task01_withput_4() {
        dz.task01(new int[]{1, 1, 1});
    }

    @Test
    public void test_task01_where_4_is_not_last() {
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7};
        Assert.assertArrayEquals(new int[]{5, 6, 7}, dz.task01(data));
    }

    @Test
    public void test_task01_where_4_last() {
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7};
        Assert.assertArrayEquals(new int[]{5, 6, 7}, dz.task01(data));
    }

    @Test
    public void test_task02_only_1_nad4() {
        Assert.assertThat(dz.task02(new int[]{1, 4, 4, 1}), Is.is(true));
    }

    @Test
    public void test_task02_1_and_4_adn_others() {
        Assert.assertThat(dz.task02(new int[]{1, 4, 3, 1}), Is.is(false));
    }

    @Test
    public void test_task02_without_1_and_4() {
        Assert.assertThat(dz.task02(new int[]{2, 3}), Is.is(false));
    }

    @Test
    public void test_task02_only_1() {
        Assert.assertThat(dz.task02(new int[]{1, 1, 1}), Is.is(false));
    }

    @Test
    public void test_task02_only_4() {
        Assert.assertThat(dz.task02(new int[]{4, 4, 4}), Is.is(false));
    }
}
