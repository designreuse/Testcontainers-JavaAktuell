package com.anderscore.technology.testcontainers.resolver;

import static java.lang.Integer.parseInt;

import com.anderscore.technology.testcontainers.util.ServletContainerContext;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class ServletContainerContextParameterResolver implements ParameterResolver {

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		
		Class<?> type = parameterContext.getParameter().getType();
		return ServletContainerContext.class.isAssignableFrom(type);
	}

    // tag::resolver[]
	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		
		String serverPort = SpringExtension.getApplicationContext(extensionContext).getEnvironment().getProperty("local.server.port");
		ServletContainerContext context = new ServletContainerContext("host.testcontainers.internal", parseInt(serverPort));
		
		return context;
	}
    // end::resolver[]
}
