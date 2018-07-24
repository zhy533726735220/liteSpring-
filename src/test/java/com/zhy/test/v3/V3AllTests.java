package com.zhy.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV3.class, BeanDefinitonTestV3.class, ConstructorResolverTest.class
})
public class V3AllTests {
}
