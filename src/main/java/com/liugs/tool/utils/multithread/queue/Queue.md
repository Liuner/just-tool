[原文链接](https://blog.csdn.net/yinwenjie/article/details/50577325)
### 有限队列
+ SynchronousQueue 有限队列

  + 这是一个内部没有任何容量的阻塞队列，任何一次插入操作的元素都要等待相对的删除/读取操作，否则进行插入操作的线程就要一直等待，反之亦然

  + ```java
    SynchronousQueue<Object> queue = new SynchronousQueue<Object>();
    // 不要使用add，因为这个队列内部没有任何容量，所以会抛出异常“IllegalStateException”
    // queue.add(new Object());
    // 操作线程会在这里被阻塞，直到有其他操作线程取走这个对象
    queue.put(new Object()); 
    ```
+ ArrayBlockingQueue 有界阻塞队列
  + 此队列按 FIFO（先进先出）原则对元素进行排序。
  + 一旦创建了这样的缓存区，就不能再增加其容量。试图向已满队列中放入元素会导致操作受阻塞；试图从空队列中提取元素将导致类似阻塞。
  + ```java
    // 我们创建了一个ArrayBlockingQueue，并且设置队列空间为2
    ArrayBlockingQueue<Object> arrayQueue = new ArrayBlockingQueue<Object>(2);
    // 插入第一个对象
    arrayQueue.put(new Object());
    // 插入第二个对象
    arrayQueue.put(new Object());
    // 插入第三个对象时，这个操作线程就会被阻塞。
    arrayQueue.put(new Object());
    // 请不要使用add操作，和SynchronousQueue的add操作一样，它们都使用了AbstractQueue中的add实现
    ```
### 无限队列
+ LinkedBlockingQueue
  + LinkedBlockingQueue是我们在ThreadPoolExecutor线程池中常应用的等待队列。它可以指定容量也可以不指定容量。由于它具有“无限容量”的特性，所以我还是将它归入了无限队列的范畴（实际上任何无限容量的队列/栈都是有容量的，这个容量就是Integer.MAX_VALUE）。
    
  +  LinkedBlockingQueue的实现是基于链表结构，而不是类似ArrayBlockingQueue那样的数组。但实际使用过程中，您不需要关心它的内部实现，如果您指定了LinkedBlockingQueue的容量大小，那么它反映出来的使用特性就和ArrayBlockingQueue类似了。
    ————————————————
    版权声明：本文为CSDN博主「说好不能打脸」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/yinwenjie/article/details/50577325
  +  ```java
        LinkedBlockingQueue<Object> linkedQueue = new LinkedBlockingQueue<Object>(2);
        linkedQueue.put(new Object());
        // 插入第二个对象
        linkedQueue.put(new Object());
        // 插入第三个对象时，这个操作线程就会被阻塞。
        linkedQueue.put(new Object());
        
        =======================================
        
        // 或者如下使用：
        LinkedBlockingQueue<Object> linkedQueue = new LinkedBlockingQueue<Object>();
        linkedQueue.put(new Object());
        // 插入第二个对象
        linkedQueue.put(new Object());
        // 插入第N个对象时，都不会阻塞
        linkedQueue.put(new Object());
       ``` 
+ LinkedBlockingDeque
  + LinkedBlockingDeque是一个基于链表的双端队列。LinkedBlockingQueue的内部结构决定了它只能从队列尾部插入，从队列头部取出元素；但是LinkedBlockingDeque既可以从尾部插入/取出元素，还可以从头部插入元素/取出元素。
  + ```java
    LinkedBlockingDeque<TempObject> linkedDeque = new LinkedBlockingDeque<TempObject>();
    // push ，可以从队列的头部插入元素
    linkedDeque.push(new TempObject(1));
    linkedDeque.push(new TempObject(2));
    linkedDeque.push(new TempObject(3));
    // poll ， 可以从队列的头部取出元素
    TempObject tempObject = linkedDeque.poll();
    // 这里会打印 tempObject.index = 3
    System.out.println("tempObject.index = " + tempObject.getIndex());
    
    // put ， 可以从队列的尾部插入元素
    linkedDeque.put(new TempObject(4));
    linkedDeque.put(new TempObject(5));
    // pollLast , 可以从队列尾部取出元素
    tempObject = linkedDeque.pollLast();
    // 这里会打印 tempObject.index = 5
    System.out.println("tempObject.index = " + tempObject.getIndex());
    ```
+ PriorityBlockingQueue 
  + PriorityBlockingQueue是一个按照优先级进行内部元素排序的无限队列。存放在PriorityBlockingQueue中的元素必须实现Comparable接口，这样才能通过实现compareTo()方法进行排序。优先级最高的元素将始终排在队列的头部；
  + PriorityBlockingQueue不会保证优先级一样的元素的排序，也不保证当前队列中除了优先级最高的元素以外的元素，随时处于正确排序的位置。
  + 这是什么意思呢？PriorityBlockingQueue并不保证除了队列头部以外的元素排序一定是正确的。
  + 代码实例见 PriorityBlockingQueueDemo.java
  
+ LinkedTransferQueue
  + LinkedTransferQueue也是一个无限队列，它除了具有一般队列的操作特性外（先进先出），还具有一个阻塞特性：LinkedTransferQueue可以由一对生产者/消费者线程进行操作，当消费者将一个新的元素插入队列后，消费者线程将会一直等待，直到某一个消费者线程将这个元素取走，反之亦然。
