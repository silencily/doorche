package org.silencer.doorche.uitls;

import org.junit.Test;

import java.util.*;

/**
 * @author gejb
 * @since 2016/3/21
 */
public class SilencilyTest {
    @Test
    public void testMain() {
        String name = "a.b.c";
        StringTokenizer tokenizer = new StringTokenizer(name, ".", false);
        String lastAlias = null;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (tokenizer.hasMoreTokens()) {
                lastAlias = token;
                System.out.println(token);

            }

        }
        String propertyName = name;
        if (lastAlias != null) {
            propertyName = lastAlias + name.substring(name.lastIndexOf("."));
        }
        System.out.println(propertyName);
    }

    @Test
    public void testMain2() {
        Set<String> aSet = new HashSet<String>();
        aSet.add("222");
        aSet.add("222");
        System.out.println(aSet.size());
    }
    @Test
    public void testMain3() {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(a, "a");
        map.put(b, "b");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());

        }
    }
}
