package org.example.system.impl;

import org.example.exceptions.ResourceNotFoundException;
import org.example.model.PresentQuantity;
import org.example.model.PresentType;
import org.example.repository.PresentQuantityRepository;
import org.example.repository.PresentTypeRepository;
import org.example.system.PresentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PresentServiceTest {

    @MockBean
    private PresentTypeRepository presentTypeRepository;

    @MockBean
    private PresentQuantityRepository presentQuantityRepository;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private PresentService presentService;

    private static final String TYPE_NAME = "typeName";

    @TestConfiguration
    public static class PresentServiceImplTestContextConfiguration {

        @Bean
        public PresentService presentService() {
            return new PresentServiceImpl();
        }

    }

    @Before
    public void setUp() {

        PresentType presentType = new PresentType();
        presentType.setTypeName(TYPE_NAME);

        PresentQuantity presentQuantity = new PresentQuantity();
        presentQuantity.setPresentType(presentType);
        presentQuantity.setQuantity(5);

        when(presentTypeRepository.findByTypeName(TYPE_NAME)).thenReturn(Optional.of(presentType));
        when(presentQuantityRepository.findByPresentType(presentType)).thenReturn(Optional.of(presentQuantity));
        when(presentQuantityRepository.save(presentQuantity)).thenReturn(presentQuantity);
    }

    @Test
    public void testTakePresentSuccess() throws ResourceNotFoundException {
        PresentType presentType = new PresentType();
        presentType.setTypeName(TYPE_NAME);

        Assert.assertEquals(presentType, presentService.getPresentType(TYPE_NAME));
    }

}
