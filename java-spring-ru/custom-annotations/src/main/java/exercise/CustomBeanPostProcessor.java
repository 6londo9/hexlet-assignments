package exercise;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);
    private final Map<Object, String> beanLoggerPair = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Inspect.class) != null) {
                beanLoggerPair.put(bean, field.getAnnotation(Inspect.class).level());
            }
        }
//        Arrays.stream(bean.getClass().getDeclaredAnnotations())
//                .filter(field -> field.annotationType().isAnnotationPresent(Inspect.class))
//                .map(field -> beanLoggerPair.put(bean, field.annotationType().getAnnotation(Inspect.class).level()));
        LOGGER.info("I called from [postProcessBeforeInitialization] " + beanLoggerPair);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        String message = "Was called method: {} with arguments: {}";
        Calculator proxyInstance = (Calculator) Proxy.newProxyInstance(
                CalculatorImpl.class.getClassLoader(),
                CalculatorImpl.class.getInterfaces(),
                (proxy, method, args) -> {
                    String logLevel = beanLoggerPair.get(bean);
                    switch (logLevel) {
                        case "debug" -> {
                            LOGGER.debug(
                                    "Was called method: " + method.getName()
                                    + " with arguments: " + Arrays.toString(args)
                            );
                            return method.invoke(bean, args);
                        }
                        case "info" -> {
                            LOGGER.info(
                                    "Was called method: " + method.getName()
                                            + " with arguments: " + Arrays.toString(args)
                            );
                            return method.invoke(bean, args);
                        }
                        default -> throw new RuntimeException("Invalid logger level: " + logLevel);
                    }
                }
        );
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
// END
