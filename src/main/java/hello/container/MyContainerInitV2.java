package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes(AppInit.class)
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("MyContainerInitV2 c = " + set);
        System.out.println("MyContainerInitV2 container = " + servletContext);

        for (Class<?> appInitClass : set) {
            try {
                AppInit appInit = (AppInit) appInitClass.getDeclaredConstructor().newInstance();
                appInit.startup(servletContext);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
