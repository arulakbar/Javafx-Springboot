package com.example.JavafxSpringboot.application;

import com.example.JavafxSpringboot.JavafxSpringbootApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JavaFxApplication extends Application {

    private static ConfigurableApplicationContext context;
//    private ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) throws Exception {
        context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() throws Exception {
        ApplicationContextInitializer<GenericApplicationContext> initializer =
                context -> {
                        context.registerBean(Application.class, () -> JavaFxApplication.this);
                };
        context = new SpringApplicationBuilder()
                .sources(JavafxSpringbootApplication.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));
    }


    @Override
    public void stop() throws Exception {
        context.close();
        Platform.exit();
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return context;
    }
}
