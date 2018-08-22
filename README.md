# hello-java

梳理Java的相关知识

## hello-thread (线程)

相关知识，请参考慕课网的课程：

1. [《深入浅出Java多线程》](https://www.imooc.com/learn/202)
2. [《细说多线程之Thread VS Runnable》](https://www.imooc.com/learn/312)

### java.lang.Thread

1.线程的创建

* `Thread()`
* `Thread(String name)`
* `Thread(Runnable target)`
* `Thread(Runnable target, String name)`

2.启动线程

* `void start()`

3.线程休眠

* `static void sleep (long millis)`
* `static void sleep (long millis, int nanos)`

4.使其他线程等待当前线程终止

* `void join ()`
* `void join (long millis)`
* `void join (long millis, int nanos)`

5.当前运行线程释放处理器资源

* `static void yield ()`

6.返回当前运行的线程引用

* `static Thread currentThread ()`

特别注意：线程的终止，应使用退出标志，而不是`interrupt()`

### Thread VS Runnable

1. Runnable 方式可以避免 Thread 方式由于 Java 单继承特性带来的缺陷。
2. Runnable 的代码可以被多个线程共享，适合于多个线程处理同一资源的情况。

## 守护线程

运行在后台，为其他前台线程服务。一旦所有用户线程都结束运行，守护线程会随JVM一起结束工作。
垃圾回收GC就是最常见的守护线程。

设置守护线程 setDaemon(true) 需在 start() 方法之前调用，否则会抛出 IllegalThreadStateException 异常。