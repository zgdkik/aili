package org.hbhk.aili.core.share.util;

import java.util.Arrays;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
  
public class JavaParamsUtil {  
  
    public static void main(String[] args1) throws Exception {  
        // 匹配静态方法  
        String[] paramNames = getMethodParamNames(JavaParamsUtil.class, "main", String[].class);  
        System.out.println(Arrays.toString(paramNames));  
        // 匹配实例方法  
        paramNames = getMethodParamNames(JavaParamsUtil.class, "foo", String.class);  
        System.out.println(Arrays.toString(paramNames));  
        // 自由匹配任一个重名方法  
        paramNames = getMethodParamNames(JavaParamsUtil.class, "getMethodParamNames");  
        System.out.println(Arrays.toString(paramNames));  
        // 匹配特定签名的方法  
        paramNames = getMethodParamNames(JavaParamsUtil.class, "getMethodParamNames", Class.class, String.class);  
        System.out.println(Arrays.toString(paramNames));  
    }  
  
    /** 
     * 获取方法参数名称，按给定的参数类型匹配方法 
     *  
     * @param clazz 
     * @param method 
     * @param paramTypes 
     * @return 
     * @throws NotFoundException 
     *             如果类或者方法不存在 
     * @throws MissingLVException 
     *             如果最终编译的class文件不包含局部变量表信息 
     */  
    public static String[] getMethodParamNames(Class<?> clazz, String method, Class<?>... paramTypes)  
            throws Exception {  
        ClassPool pool = ClassPool.getDefault();  
        CtClass cc = pool.get(clazz.getName());  
        String[] paramTypeNames = new String[paramTypes.length];  
        for (int i = 0; i < paramTypes.length; i++){
        	 paramTypeNames[i] = paramTypes[i].getName();  
        }
        CtMethod cm = cc.getDeclaredMethod(method, pool.get(paramTypeNames));  
        return getMethodParamNames(cm);  
    }  
  
    /** 
     * 获取方法参数名称，匹配同名的某一个方法 
     *  
     * @param clazz 
     * @param method 
     * @return 
     * @throws NotFoundException 
     *             如果类或者方法不存在 
     * @throws MissingLVException 
     *             如果最终编译的class文件不包含局部变量表信息 
     */  
    public static String[] getMethodParamNames(Class<?> clazz, String method) throws Exception{  
  
        ClassPool pool = ClassPool.getDefault();  
        CtClass cc = pool.get(clazz.getName());  
        CtMethod cm = cc.getDeclaredMethod(method);  
        return getMethodParamNames(cm);  
    }  
  
    /** 
     * 获取方法参数名称 
     *  
     * @param cm 
     * @return 
     * @throws NotFoundException 
     * @throws MissingLVException 
     *             如果最终编译的class文件不包含局部变量表信息 
     */  
    protected static String[] getMethodParamNames(CtMethod cm) throws Exception{  
        CtClass cc = cm.getDeclaringClass();  
        MethodInfo methodInfo = cm.getMethodInfo();  
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();  
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);  
        if (attr == null){
        	throw new RuntimeException(cc.getName());  
        }
        String[] paramNames = new String[cm.getParameterTypes().length];  
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;  
        for (int i = 0; i < paramNames.length; i++){
        	 paramNames[i] = attr.variableName(i + pos);  
        }
        return paramNames;  
    }  
  
  
    static void foo() {  
    }  
  
    void foo(String bar) {  
    }  
}  