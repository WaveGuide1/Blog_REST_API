package io.waveguide.social_media.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = PostController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private final PostService postService;

    @Test
    public void testUpdateFailedValidation() throws Exception {

        UpdatePostRequest postRequest = new UpdatePostRequest();
                postRequest.setPostId("");
                postRequest.setTitle("Spring boot");
                postRequest.setBody("Spring boot is awesome");
                postRequest.setUserId("101");
                postRequest.setPublished(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/posts/")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postRequest))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), response.getStatus());
        Assertions.assertEquals("{\"message\":\"PostId is required.\"}", response.getContentAsString());
    }

    @Test
    public void testUpdatePostNotFound() throws Exception {
        UpdatePostRequest postRequest = new UpdatePostRequest();
        postRequest.setPostId("1001");
        postRequest.setTitle("Spring boot");
        postRequest.setBody("Spring boot is awesome");
        postRequest.setUserId("101");
        postRequest.setPublished(true);

        when(postService.updatePost(any(UpdatePostRequest.class))).thenReturn(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/posts/")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postRequest))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        Assertions.assertEquals("{\"message\":\"PostId is required.\"}", response.getContentAsString());
    }
}
