package com.topeventos;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/*
-> Criar database 'topeventos':
    - create dadabase topeventos;
    - use topeventos;
*/

/*
                        TRANSACTIONS
                        ------------

1 -> O springboot possui a possibilidade de gerenciar totalmente nosso banco de dados, desde criar schemas, tabelas etc
2 -> Algumas vezes principalmente se estivermos usando windows, o spring cria tabelas usando a ENGINE 'myIZAM' e não o 'innodb'
3 -> Quando criarmos uma tabela como 'myIZAM', não temos controle sobre a tabela, por isso usamos 'InnoDB'

4 -> Definindo a ENGINE do mysql que o spring-boot vai utilizar:
    - Path: /src/resources/application.properties
    - "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect"
        > Dessa forma o spring que vai escolher a ENGINE do mysql
    - Adicionando ENGINE especifica:
        > "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect"

*/

@Configuration
public class DataConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/topeventos");
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        return (DataSource) dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }

}