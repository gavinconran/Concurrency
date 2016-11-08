package all.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import concurrency.synchronize.SharedArrayTestTest;
import concurrency.synchronize.SimpleArrayTest;


@RunWith(Suite.class)
@SuiteClasses({ SharedArrayTestTest.class, SimpleArrayTest.class })
public class AllTests {

}

