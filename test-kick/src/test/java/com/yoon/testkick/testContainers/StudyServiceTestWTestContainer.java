package com.yoon.testkick.testContainers;

import com.yoon.testkick.mockito.domain.Member;
import com.yoon.testkick.mockito.domain.Study;
import com.yoon.testkick.mockito.member.MemberService;
import com.yoon.testkick.mockito.study.StudyRepository;
import com.yoon.testkick.mockito.study.StudyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
@ContextConfiguration(initializers = StudyServiceTestWTestContainer.ContainerPropertyInitializer.class )
class StudyServiceTestWTestContainer {

    @Mock
    MemberService memberService;

    @Autowired
    StudyRepository studyRepository;

    @Autowired
    Environment environment;

    @Value("${container.port}") int port;

    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres")
            .withDatabaseName("studytest");

    @BeforeEach
    void setUp() {
        System.out.println(environment.getProperty("container.port"));
        System.out.println(port);
        studyRepository.deleteAll();
    }


    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log);
        postgreSQLContainer.followOutput(logConsumer);
    }


/*    @AfterAll
    static void afterAll(){
        postgreSQLContainer.stop();
    }*/

    @Test
    void createNewStudy() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("jin@mail.com");
        Study study = new Study(10, "jpa");

        given(memberService.findById(1L)).willReturn(Optional.of(member));

        studyService.createNewStudy(1L, study);

        assertEquals(1L, study.getOwnerId());
        then(memberService).should(times(1)).notify(study);
    }

    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("container.port=" + postgreSQLContainer.getMappedPort(5432))
                    .applyTo(applicationContext.getEnvironment());
        }
    }
}
