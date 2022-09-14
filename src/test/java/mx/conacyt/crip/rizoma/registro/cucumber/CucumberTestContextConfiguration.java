package mx.conacyt.crip.rizoma.registro.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import mx.conacyt.crip.rizoma.registro.IntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@IntegrationTest
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
