package com.example.reviews;

import com.example.reviews.controller.CourseController;
import com.example.reviews.controller.UserController;
import com.example.reviews.model.Course;
import com.example.reviews.service.CourseService;
import com.example.reviews.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CourseController.class)
@WithMockUser
public class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    Course mockCourse = new Course(1, "Learn React", "https://www.codecademy.com/learn/react-101", "Codecademy", "Web development");

    String exampleCourseJson = "{\"title\":\"Learn React\",\"url\":\"https://www.codecademy.com/learn/react-101\",\"provider\":[\"Codecademy\",\"subject\":[\"Web development\"]}";

    @Test
    public void retrieveDetails() throws Exception {
        Mockito.when(courseService.getCourseById(Mockito.anyInt())).thenReturn(mockCourse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/courses/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"id\":\"1\",\"title\":\"Learn React\",\"url\":\"https://www.codecademy.com/learn/react-101\",\"provider\":[\"Codecademy\",\"subject\":[\"Web development\" }";



        // {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

}
