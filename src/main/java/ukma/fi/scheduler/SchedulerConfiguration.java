package ukma.fi.scheduler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

import static java.util.Arrays.asList;


@Configuration
@EnableScheduling
@EnableCaching
@EnableWebMvc
public class SchedulerConfiguration implements WebMvcConfigurer {

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

    @Bean
    public FilterRegistrationBean hiddenHttpMethodFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new HiddenHttpMethodFilter());
        filterRegistrationBean.setUrlPatterns(asList("/*"));
        return filterRegistrationBean;
    }

}
