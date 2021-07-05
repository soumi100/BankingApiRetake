package io.swagger.IT;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:io.swagger",
        glue = "io.swagger.IT.steps",
        plugin = "pretty",
        strict = true
)
public class CucumberIT {
}
