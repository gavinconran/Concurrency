# Multithreading with GUI
This repository contains code examples from **Java: How To Program** [JHTP](https://www.pearsonhighered.com/product/Deitel-Java-How-to-Program-early-objects-9th-Edition/9780132575669.html).

All the examples were written using Eclipse and Java 8.

#### Code Examples:
1. Class **FibonacciNumbers** 
   * Displays a window containing two sets of GUI components:
      * One set to compute a Fibonacci number in a worker thread
      * Another to get the next Fibonacci number in response to the user’s clicking a JButton 
2. Class **BackgroundCalculator** is a SwingWorker subclass for calculating Fibonacci numbers in a background thread
   * class extends SwingWorker, overriding the methods doInBackground and done
   * Method doInBackground computes the nth Fibonacci number in a worker thread and returns the result
   * Method done displays the result in a JLabel


