package com.abin.mallchat.utils;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Optional;

public class SpElUtils {
    private static final ExpressionParser parser = new SpelExpressionParser();
    private static final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    public static String parseSpEl(Method method, Object[] args, String spEl) {
        String[] params = Optional.ofNullable(parameterNameDiscoverer.getParameterNames(method)).orElse(new String[]{});//解析参数名
        EvaluationContext context = new StandardEvaluationContext();//el解析需要的上下文对象
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);//所有参数都作为原材料扔进去
        }
        Expression expression = parser.parseExpression(spEl);
        return expression.getValue(context, String.class);
    }

    public static String getMethodKey(Method method) {
        return method.getDeclaringClass() + "#" + method.getName();
    }

    public static void main(String[] args) throws NoSuchMethodException {
        // 获取一个方法实例
        Method method = SpElUtils.class.getMethod("exampleMethod", String.class, int.class);

        // 测试 SpEL 表达式解析
        String spEl = "'Hello ' + #name + ', your number is ' + #number";
        Object[] aaa = new Object[]{"John", 42};
        String result = SpElUtils.parseSpEl(method, aaa, spEl);
        System.out.println(result);
    }

    public  void exampleMethod(String name, int number) {
        // 方法内容无关紧要，仅用于测试
    }

}
