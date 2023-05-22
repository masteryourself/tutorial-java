package org.masteryourself.tutorial.designpattern.structual.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>description : Spa
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/3/5 3:00 PM
 */
public class Spa {

    private static final Map<String, Waitress> waitressMap = new HashMap<>();

    static {
        waitressMap.put("4396", new Waitress.WaitressImpl("4396", "小花", 19, true));
        waitressMap.put("9527", new Waitress.WaitressImpl("9527", "小翠", 23, true));
    }

    public Waitress getWaitress() {
        Waitress waitress = waitressMap.values().stream().filter(Waitress::state).findFirst().orElse(null);
        if (waitress != null) {
            return waitress;
        }
        waitress = new Waitress.WaitressImpl("8888", "8888", 23, true);
        waitressMap.put("8888", waitress);
        return waitress;
    }

}
