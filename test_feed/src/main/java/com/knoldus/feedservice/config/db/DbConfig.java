package com.knoldus.feedservice.config.db;

import org.springframework.context.annotation.Import;

/**
 * General configuration for the database.
 *
 * @author Gaurav Kumar
 */
@Import({LiquibaseMigrationConfig.class})
public class DbConfig {

}
