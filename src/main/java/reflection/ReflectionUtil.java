package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ReflectionUtil {
	
	public static <T,U> U convert(T from, U to ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		Map<String, Method> fromMethodNameWithMethod	= Arrays.asList(from.getClass().getMethods()).stream()
				.filter(classMethod -> (
						classMethod.getName().startsWith("get") && 
						(classMethod.getParameterCount() == 0)	&&
						(!classMethod.getName().equals("getClass")) 
						))
				.collect(Collectors.toMap(
						classMethod -> classMethod.getName().substring(classMethod.getName().lastIndexOf("get")+3), classMethod ->classMethod));
		
		Arrays.asList(to.getClass().getMethods()).stream()
				.filter(classMethod -> (
						classMethod.getName().startsWith("set") && 
						(classMethod.getParameterCount() == 1) 
						))
				.forEach(toMethod -> {
					
					String toMethodName = toMethod.getName().substring(toMethod.getName().lastIndexOf("set")+3);
					Method fromMethod = fromMethodNameWithMethod.get(toMethodName);
					try {
						toMethod.invoke(to, fromMethod.invoke(from,null));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				});
		
		return to;
	}

}
