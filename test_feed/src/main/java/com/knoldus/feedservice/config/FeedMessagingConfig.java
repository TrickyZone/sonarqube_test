package com.knoldus.feedservice.config;

import com.knoldus.feedservice.config.db.DbConfig;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * General configuration of the application.
 *
 * @author Navdeep parash
 */
@ComponentScan(basePackages = {
        "com.knoldus.feedservice",
})
@Import({
        DbConfig.class,
        SwaggerConfig.class
})

@Configuration
public class FeedMessagingConfig {


    @Value("${rabbitmq.queue}")
    private String queue;
    @Bean
    public Queue feedqueue() {
        return new Queue(queue);
    }
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Bean
    public TopicExchange feedexchange() {
        return new TopicExchange(exchange );
    }

    @Value("${rabbitmq.routingkey}")
    private String routingkey;
    @Bean
    public Binding feedbinding(Queue feedqueue, TopicExchange feedexchange) {
        return BindingBuilder.bind(feedqueue).to(feedexchange).with(routingkey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}

