package com.chen.dynamic;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ProxyPerformance
 *
 * @Author LeifChen
 * @Date 2018-09-27
 */
public class ProxyPerformance {

    public static void main(String[] args) {
        // 创建测试对象
        Target nativeTest = new TargetImpl();
        Target dynamicProxy = JdkDynamicProxy.newProxyInstance(nativeTest);
        Target cglibProxy = CglibProxy.newProxyInstance(TargetImpl.class);

        // 执行测试
        Map<String, Target> tests = new LinkedHashMap<>();
        tests.put("Native       ", nativeTest);
        tests.put("JDK Dynamic  ", dynamicProxy);
        tests.put("CGLib Dynamic", cglibProxy);
        int repeatCount = 3;
        int runCount = 1000000;
        runTest(repeatCount, runCount, tests);
        runCount = 50000000;
        runTest(repeatCount, runCount, tests);
    }

    private static void runTest(int repeatCount, int runCount, Map<String, Target> tests) {
        System.out.println(
                String.format("=== run test : [repeatCount=%s] [runCount=%s] [java.version=%s] === ",
                        repeatCount, runCount, System.getProperty("java.version"))
        );
        for (int i = 0; i < repeatCount; i++) {
            System.out.println(
                    String.format("--- test: [%s] ---", i + 1)
            );
            for (String key : tests.keySet()) {
                runWithMonitor(tests.get(key), runCount, key);
            }
        }
    }

    private static void runWithMonitor(Target target, int runCount, String tag) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < runCount; i++) {
            target.test(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("[" + tag + "] Total Time: " + (end - start) + "ms");
    }
}
