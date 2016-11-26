package all.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import concurrency.shared_memory.monitor.GapBufferTest;
import concurrency.shared_memory.monitor.SimpleBufferTest;
import concurrency.synchronize.SharedArrayTestTest;
import concurrency.synchronize.SimpleArrayTest;
import recursion.datatypes.ImListTest;


@RunWith(Suite.class)
@SuiteClasses({ SharedArrayTestTest.class, SimpleArrayTest.class, 
    GapBufferTest.class, SimpleBufferTest.class, ImListTest.class })
public class AllTests {

}

