// https://leetcode.com/problems/implement-strstr/#/description

/**
    Implement strStr().
    Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
 
 public class StrStr_ii {
    public int strStr(String source, String target) {
        if(target == null) {
            return -1;
        }

        int m = target.length();
        if(m == 0) {
            return 0;
        }
        
        if(source == null) {
            return -1;
        }
        int n = source.length();
        if(n == 0) {
            return -1;
        }
        
        // mod could be any big integer
        // just make sure mod * 33 wont exceed max value of int.
        int mod = Integer.MAX_VALUE / 33;
        int hash_target = 0;

        // 33 could be something else like 26 or whatever you want
        for (int i = 0; i < m; ++i) {
            hash_target = (hash_target * 33 + target.charAt(i) - 'a') % mod;
            if (hash_target < 0) {
                hash_target += mod;
            }
        }

        int m33 = 1;
        for (int i = 0; i < m - 1; ++i) {
            m33 = m33 * 33 % mod;
        }

        int value = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= m) {
                value = (value - m33 * (source.charAt(i - m) - 'a')) % mod;
            }

            value = (value * 33 + source.charAt(i) - 'a') % mod;
            // JAVA STUPID: You want to do this because in JAVA (-ve #) % (+ve #) gives 
            // you a negative number, Egs (-4 % 3) = -1 
            //  But you want the compliment of that so you add it with
            // *mod* value. That is -4%3 = -1 + 3 = 2 which is the actual reminder
            // THis is not required in Python.
            if (value < 0) {
                value += mod;
            }

            if (i >= m - 1 && value == hash_target) {
                // you have to double check by directly compare the string
                if (target.equals(source.substring(i - m + 1, i + 1))) {
                    return i - m + 1;
                }
            }
        }
        return -1;
    }
    }
}
