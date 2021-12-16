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
public class Java8JavaLookupTester {

    @Rule
    public TestName testName = new TestName();

    private String getName(final OutputStream out) {
        return out.getClass().getSimpleName() + "." + testName.getMethodName();
    }

    /**
     * 表达式在低版本执行
     * java:   1.8.0_131-b11
     * log4j2: 2.10.0
     */
    @Test
    public void log4j2Crisis() {
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
    }

    /**
     * 表达式在15版本，带表达式执行
     * java:   1.8.0_131-b11
     * log4j2: 2.15.0
     * with no lookup exp xml -> not exe
     */
    @Test
    public void log4j2NoLookup() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStream os = new BufferedOutputStream(out);
        String name = getName(out);
        ConfigurationTestUtils.addAppender(os, name, "log4j2.xml");
        Logger logger = LogManager.getLogger(name);

        // java lookup
        String exp = "${java:runtime} - ${java:vm} - ${java:os}";
        logger.info("hello {}", exp);
        String actual = out.toString();
        Assert.assertTrue(actual, actual.contains(exp));
    }

    /**
     * 表达式在15版本，无表达式不执行
     * java:   1.8.0_131-b11
     * log4j2: 2.15.0
     * with lookup exp xml -> exe
     */
    @Test
    public void log4j2WithLookup() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStream os = new BufferedOutputStream(out);
        String name = getName(out);
        ConfigurationTestUtils.addAppender(os, name, "log4j2-lookup.xml");
        Logger logger = LogManager.getLogger(name);

        // java lookup
        String exp = "${java:runtime} - ${java:vm} - ${java:os}";
        logger.info("hello {}", exp);
        String actual = out.toString();
        Assert.assertFalse(actual, actual.contains(exp));
    }

    /**
     * 表达式在16版本不执行
     * java:   1.8.0_131-b11
     * log4j2: 2.16.0
     * with lookup exp xml -> not exe
     */
    @Test
    public void log4j2WithLookupRemove() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStream os = new BufferedOutputStream(out);
        String name = getName(out);
        ConfigurationTestUtils.addAppender(os, name, "log4j2-lookup.xml");
        Logger logger = LogManager.getLogger(name);

        // java lookup
        String exp = "${java:runtime} - ${java:vm} - ${java:os}";
        logger.info("hello {}", exp);
        String actual = out.toString();
        Assert.assertTrue(actual, actual.contains(exp));
    }

}
