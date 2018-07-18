package com.zhy.test;

import com.zhy.test.v1.V1AllTests;
import com.zhy.test.v2.V2AllTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({V1AllTests.class, V2AllTests.class})
public class AllTest {
}
