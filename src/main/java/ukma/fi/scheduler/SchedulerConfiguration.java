package ukma.fi.scheduler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class SchedulerConfiguration {


    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(
            value = "database.condition.test",
            matchIfMissing = true)
    @Bean
    public DataSource getDataSource() {

        System.out.println("DataSauce");
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:test");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

}
