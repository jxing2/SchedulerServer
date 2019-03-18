package persagy.utils;
/*
 * Author: Jxing
 * Create Time: 2019/3/12
 */

import persagy.pojo.Command;

import java.util.HashSet;
import java.util.Set;

public class CommonUtil {
    public static Set<Command> createSet(Command... elements) {
        Set<Command> set = new HashSet<>();
        for (Command e : elements) {
            set.add(e);
        }
        return set;
    }
}
