package org.litespring.test.v5;

import org.junit.Test;
import org.litespring.service.v5.GoodService;
import org.litespring.tx.TransactionManager;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/3/13
 **/
public class CglibTest {

    @Test
    public void testCglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(GoodService.class);
        enhancer.setCallback(new TransactionInterceptor());
        GoodService service = (GoodService) enhancer.create();
        service.placeOrder();

    }

    @Test
    public void testFilter() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(GoodService.class);
        enhancer.setInterceptDuringConstruction(false);

        Callback[] callbacks = new Callback[]{new TransactionInterceptor(), NoOp.INSTANCE};
        Class<?>[] types = new Class<?>[callbacks.length];
        for (int i = 0; i < callbacks.length; i++) {
            types[i] = callbacks[i].getClass();
        }
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackTypes(types);
        enhancer.setCallbackFilter(new ProxyCallbackFilter());

        GoodService service = (GoodService) enhancer.create();
        service.placeOrder();
        System.out.println(service.toString());
    }

    private static class TransactionInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            TransactionManager tx = new TransactionManager();
            tx.start();
            Object result = methodProxy.invokeSuper(o, objects);
            tx.commit();
            return result;
        }
    }


    private static class ProxyCallbackFilter implements CallbackFilter {

        public ProxyCallbackFilter() {

        }

        public int accept(Method method) {
            if(method.getName().startsWith("place")){
                return 0;
            } else{
                return 1;
            }

        }

    }
}
