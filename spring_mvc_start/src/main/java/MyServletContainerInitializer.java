import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * @author chenguang.lin
 * @date 2022-08-08
 */
//容器启动的时候会将@HandlesTypes指定的这个类型下面的子类（实现类，子接口等）传递过来；
//@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {

    /**
     * 应用启动的时候，会运行onStartup方法；
     * <p>
     * Set<Class<?>> c：感兴趣的类型的所有子类型；
     * ServletContext ctx:代表当前Web应用的ServletContext；一个Web应用一个ServletContext；
     * <p>
     */
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        //这里的c会把所有我们感兴趣的类型都拿到
        System.out.println("感兴趣的类型：");
        for (Class<?> clazz : c) {
            System.out.println(clazz);
        }

        //==========================编码形式注册三大组件============================
        //注册组件  ServletRegistration
//        ServletRegistration.Dynamic servlet = ctx.addServlet("userServlet", new UserServlet());
//        //配置servlet的映射信息
//        servlet.addMapping("/user");
//        //注册Listener
//        ctx.addListener(UserListener.class);
//        //注册Filter  FilterRegistration
//        FilterRegistration.Dynamic filter = ctx.addFilter("userFilter", UserFilter.class);
//        //配置Filter的映射信息
//        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }

}
