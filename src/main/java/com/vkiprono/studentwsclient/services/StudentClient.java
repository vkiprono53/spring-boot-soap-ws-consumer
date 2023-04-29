package com.vkiprono.studentwsclient.services;

import com.vkiprono.studentwsclient.constants.StudentClientConstant;
import com.vkiprono.studentwsclient.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.ResourceBundle;

/**
 * @author vkiprono
 * @created 3/28/23
 */

public class StudentClient extends WebServiceGatewaySupport {
    public ResourceBundle resourceBundle = ResourceBundle.getBundle("studentconfig");

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentClient.class);

    /**
     * getStudentByAdm
     *
     * @param admNo
     * @return
     */
    public GetStudentByAdmResponse getStudentByAdm(String admNo) {
        LOGGER.info(":::::Start StudentClient.getStudentByAdm():::::");
        GetStudentByAdmRequest getStudentByAdmRequest = new GetStudentByAdmRequest();
        GetStudentByAdmResponse getStudentByAdmResponse = new GetStudentByAdmResponse();

        LOGGER.info(":::::Adm no is:::::" + admNo);

        getStudentByAdmRequest.setAdmNo(admNo);
        if (admNo != null) {
            getStudentByAdmResponse = (GetStudentByAdmResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(getStudentByAdmRequest,
                    new SoapActionCallback(resourceBundle.getString("StudentClient.GET_STUDENT_BY_ADM_NO_URL")));

        }
        LOGGER.info(":::::Start StudentClient.getStudentByAdm():::::");
        return getStudentByAdmResponse;

    }

    /**
     * getAllStudents
     *
     * @return
     */
    public GetAllStudentsResponse getAllStudents() {
        LOGGER.info(":::::Start StudentClient.getAllStudents():::::");

        GetAllStudentsRequest getAllStudentsRequest = new GetAllStudentsRequest();

        GetAllStudentsResponse getAllStudentsResponse = new GetAllStudentsResponse();
        getAllStudentsResponse = (GetAllStudentsResponse) getWebServiceTemplate().marshalSendAndReceive(getAllStudentsRequest,
                new SoapActionCallback(resourceBundle.getString("StudentClient.GET_ALL_STUDENTS_URL")));

        LOGGER.info(":::::Exit StudentClient.getAllStudents():::::");

        return getAllStudentsResponse;
    }

    /**
     * addStudent
     *
     * @param student
     * @return
     */
    public AddStudentResponse addStudent(Student student) {
        LOGGER.info(":::::Start StudentClient.addStudent():::::");

        AddStudentResponse addStudentResponse = new AddStudentResponse();
        AddStudentRequest addStudentRequest = new AddStudentRequest();

        addStudentRequest.setAdmNo(student.getAdmNo());
        addStudentRequest.setFirstName(student.getFirstName());
        addStudentRequest.setLastName(student.getLastName());
        addStudentRequest.setCourse(student.getCourse());
        addStudentRequest.setAge(student.getAge());
        addStudentRequest.setGender(student.getGender());

        addStudentResponse = (AddStudentResponse) getWebServiceTemplate().marshalSendAndReceive(addStudentRequest,
                new SoapActionCallback(resourceBundle.getString("StudentClient.ADD_STUDENT_URL")));

        LOGGER.info(":::::End StudentClient.addStudent():::::");

        return addStudentResponse;
    }


    public UpdateStudentResponse updateStudent(Student student) {
        LOGGER.info(":::::Start StudentClient.updateStudent():::::");

        UpdateStudentResponse updateStudentResponse = new UpdateStudentResponse();
        UpdateStudentRequest updateStudentRequest = new UpdateStudentRequest();

        updateStudentRequest.setStudent(student);

        updateStudentResponse = (UpdateStudentResponse) getWebServiceTemplate().marshalSendAndReceive(updateStudentRequest,
                new SoapActionCallback(resourceBundle.getString("StudentClient.UPDATE_STUDENT_URL")));
        LOGGER.info(":::::Start StudentClient.updateStudent():::::");

        return updateStudentResponse;
    }

    /**
     * deleteStudentByAdm
     *
     * @param admNo
     * @return
     */
    public DeleteStudentResponse deleteStudentByAdm(String admNo) {
        LOGGER.info(":::::Start StudentClient.deleteStudentByAdm():::::");
        DeleteStudentResponse deleteStudentResponse = new DeleteStudentResponse();
        DeleteStudentRequest deleteStudentRequest = new DeleteStudentRequest();
        deleteStudentRequest.setAdmNo(admNo);

        if (admNo != null) {
            deleteStudentResponse = (DeleteStudentResponse) getWebServiceTemplate().marshalSendAndReceive(deleteStudentRequest,
                    new SoapActionCallback(resourceBundle.getString("StudentClient.DELETE_STUDENT_BY_ADM_NO_URL")));

        }
        LOGGER.info(":::::End StudentClient.deleteStudentByAdm():::::");

        return deleteStudentResponse;
    }
}
