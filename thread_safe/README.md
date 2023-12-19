1. 外层的类是singleton
2. 使用@Autowired注入
这样的话仍然只会有一个实例

[SprintBoot(Sprint)中Controller、Service、DAO线程安全问题](https://blog.csdn.net/zzhongcy/article/details/111880554)
[spring的scope为prototype的bean的正确使用方法](https://zhuanlan.zhihu.com/p/27971569)

---

* `Class01X` 线程不安全
* `Class02X` `ThreadLocal` 维护bean状态，线程安全

原型模式 `@Scope(scopeName = BeanDefinition.SCOPE_PROTOTYPE)`
* `Class03X` `applicationContext.getBean()` 获取 实例，线程安全
* `Class04X` `@Lookup` 注解获取实例，线程安全
* `Class05X` `ObjectProvider` 获取实例，线程安全

[spring注解@Lookup使用原理和注意点以及其他替换实现方案](https://blog.csdn.net/duxd185120/article/details/109125440)