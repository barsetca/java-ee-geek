package com.cherniak;

import com.cherniak.service.ProductServiceRemote;
import java.io.IOException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EjbClient {

  public static void main(String[] args) throws IOException, NamingException {
    Context context = createInitialContext();

    String jndiServiceName = "ejb:/first-jsf-app/ProductServiceImpl!com.cherniak.service.ProductServiceRemote";
    ProductServiceRemote productService = (ProductServiceRemote) context.lookup(jndiServiceName);

    productService.findAllRemote()
        .forEach(prod -> System.out.println(prod.getId() + "\t" + prod.getName() + "\t" + prod.getCategoryId()));
  }

  public static Context createInitialContext() throws IOException, NamingException {
    final Properties env = new Properties();
    env.load(EjbClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
    return new InitialContext(env);
  }
}
