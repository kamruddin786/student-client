package com.kamruddin.studentclient;

import com.kamruddin.studentclient.components.StudentClient;
import com.kamruddin.studentclient.models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest
@AutoConfigureWireMock
public class StudentClientTest {

    @Autowired
    private StudentClient studentClient;

    @Test
    void getStudent_forGivenStudent_isReturned(){
        Long id = 1L;

        stubFor(get("/students/" + id).willReturn(okJson("""
                {
                    "id" : 1,
                    "studentName" : "Kamruddin",
                    "grade" : 10
                }
                """)));
        Student student = studentClient.getStudent(id);
        then(student.getId()).isNotNull();
        then(student.getStudentName()).isEqualTo("Kamruddin");
        then(student.getGrade()).isEqualTo(10);
    }
}
