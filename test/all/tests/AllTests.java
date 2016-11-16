package all.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import concurrency.synchronize.SharedArrayTestTest;
import concurrency.synchronize.SimpleArrayTest;
import mit6005.fa16.reading23.editor.GapBufferTest;
import mit6005.fa16.reading23.editor.SimpleBufferTest;


@RunWith(Suite.class)
@SuiteClasses({ SharedArrayTestTest.class, SimpleArrayTest.class, GapBufferTest.class, SimpleBufferTest.class })
public class AllTests {

}

