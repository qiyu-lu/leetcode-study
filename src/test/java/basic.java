import com.leetcode.hot100.other._31_NextPermutation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class basic {

    @Test
    public void _31_NextPermutation() {
        int[] nums = {1,2,3};
        System.out.println(Arrays.toString(nums));
        int len = nums.length;
        int[] temp = new int[len];
        System.arraycopy(nums, 0, temp, 0, len);
        System.out.println(Arrays.toString(temp));
    }
}
