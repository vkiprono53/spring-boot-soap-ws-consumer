package com.vkiprono.studentwsclient.configs;

import com.vkiprono.studentwsclient.constants.StudentClientConstant;
import com.vkiprono.studentwsclient.services.StudentClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;


import java.util.*;

/**
 * @author vkiprono
 * @created 3/28/23
 */
@Configuration
public class StudentWSConfigClient {

    public ResourceBundle resourceBundle = ResourceBundle.getBundle("studentconfig");

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath(StudentClientConstant.CONTEXT_PATH);
        return jaxb2Marshaller;
    }

    @Bean
    public StudentClient studentClient(Jaxb2Marshaller marshaller) {
        System.out.println(":::::studentClient():::::");

        StudentClient studentClient = new StudentClient();
        studentClient.setDefaultUri(resourceBundle.getString("StudentClient.WSDL_URI"));
        studentClient.setMarshaller(marshaller);
        studentClient.setUnmarshaller(marshaller);
        studentClient.setInterceptors(new ClientInterceptor[]{ securityInterceptor() });

        return studentClient;
    }


    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(){
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementActions("UsernameToken");
        interceptor.setSecurementUsername(resourceBundle.getString("StudentClient.USERNAME"));
        interceptor.setSecurementPassword(resourceBundle.getString("StudentClient.PASSWORD"));
        interceptor.setSecurementPasswordType("PasswordText");

        return interceptor;
    }


}
