package com.alvachien.learning;

import java.util.List;

import javax.sql.DataSource;

import com.alvachien.learning.Models.Album;
import com.alvachien.learning.Services.StorageService;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 */
@Configuration
@ComponentScan
@PropertySource("app.properties")
public class AppConfig
{
    @Value("${app.name}")
    String appName;
    @Value("${app.version}")
    String appVersion;

    @Value("${jdbc.connectionurl}")
    String jdbcConnectionUrl;
    @Value("${jdbc.server}")
    String jdbcServer;
    @Value("${jdbc.database}")
    String jdbcDatabase;
    @Value("${jdbc.user}")
    String jdbcUser;
    @Value("${jdbc.password}")
    String jdbcPassword;
    
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!" );
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		AppResource appService = context.getBean(AppResource.class);
        appService.printLogo();

        StorageService storage = context.getBean(StorageService.class);
        List<Album> listAlbums = storage.getAlbums(1);
		System.out.println("Total: " + listAlbums.size());
		for (Album u : listAlbums) {
			System.out.println(u);
		}

        ((AnnotationConfigApplicationContext) context).close();
    }

    @Bean
    JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

	@Bean
	DataSource createDataSource() {
        // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        // String connectionUrl = "jdbc:sqlserver://localhost;database=AdventureWorks;integratedSecurity=true;"  
        // DriverManager.d

        SQLServerDataSource ds = new SQLServerDataSource();
        // ds.setURL("jdbc:sqlserver://STUDYPC_201603\\SQLEXPRESS;database=ACGallery;integratedSecurity=true;");
        // ds.setUser(jdbcUser);  
        // ds.setPassword(jdbcPassword);  
        // ds.setPortNumber(1433);

        ds.setIntegratedSecurity(true);
        ds.setServerName("STUDYPC_201603\\SQLEXPRESS");  
        ds.setDatabaseName(jdbcDatabase);
        
        return ds;
	}
}
