# Concurrency in Java
This repository contains code examples from **Software Construction in Java** [SCiJ](http://web.mit.edu/6.005/www/fa16/) and **Java: How To Program** [JHTP](https://www.pearsonhighered.com/product/Deitel-Java-How-to-Program-early-objects-9th-Edition/9780132575669.html).

All the examples were written using Eclipse and Java 8.

### Table of Contents
#### There are four basic types of Concurrency found in Java:
1. **Containment**
   * Example: Graphical User Interfaces, e.g. Java Swing and Android
   * Most GUI applications have a single thread, called the **_event dispatch thread_**, to handle interactions with the application's GUI components
   * Here multi-thtreading is achieved by using **_thread confinement_**
   * There are three important **_Design Patterns_** used in GUI development:
      * **_View Tree_**: central feature in the architecture of most GUI toolkits
      * **_Model-View-Controller_**: Separates input, output, and data
      * **_Listener_**: Decouples the model from the View and Controller
2. **Immutability**
   * Example: Local Search which uses Recursive Data Types
   * Recursive Data Types in Java 8 are similar to the same feature in Scala
3. **Thread Safe Data Types**
   * Example: Library Data Structures which use the **_Shared Memory Model_** in the form of the **_Monitor Pattern_**
   * List, Set, & HashMap are Sequential Data Types but thay all have Concurrent versions
   * Example: Map<Integer, Boolean> cache = Collections.synchronizedMap( new HashMap<Integer, Boolean>());
4. **Synchronisation** (syntatic sugar for **Locks and Conditions**)
   * An example of Synchronisation is the **_Message Passing Model_** in the form of the **_Producer / Consumer Pattern_**
   * Another example of the **_Message Passing Model_** is the **_Client / Server Pattern_** found in Networking
   * Programmers can apply a **_Monitor_** directly to a data Type by using the keyword Synchronize or by "going under the hood" and using the Lock and Condition Interfaces
   * Example: Mutable Data Structures use **_Coarse-grain locking_**
   * Example: Operating Systems use **_Fine-grain locks_** for high-performance and **_Lock Ordering_** to deal with Deadlocks

#### Code Examples:
1. Creating and Executing **Threads**
   * Code example: package "concurrency.runnable"
2. Thread Management with the **Executor Framework**
   * Code example: package "concurrency.executor"
3. **Containment**
   * Code example: package "concurrency.GUI.backgroundCalculator
4. **Synchronisation: Message Passing Model**
   * Example: **_Producer / Consumer Pattern_**
   * Code example: package "concurrency.producerconsumer" which contains two main examples:
      * SynchronizedBuffer.java       (Synchronization using keyword Synchronize)
      * SynchronizedBufferLocks.java  (Synchronization using Locks and Conditions) 
   * Another example of a Message passing Model is the Client / Server Pattern with two code examples:
      * **_Stream Socket Connections (TCP)_**: package "sequential.message_passing.client_server.TCP"
      * **_Connectionless Datagrams  (UDP)_**: package "sequential.message_passing.client_server.UDP"
5. **Synchronisation: Shared Memory Model**
   * An example of Synchronisation in the form of the **_Monitor Pattern_**
   * Code example: package "concurrency.shared_memory.monitor"
      * Contains the method findReplace which is an example of **_Serialisability_**. Found in Editor.java



