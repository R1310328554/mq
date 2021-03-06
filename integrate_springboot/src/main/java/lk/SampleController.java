package lk;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuzh
 * @since 2017/6/17.
 */
@Controller
@EnableAutoConfiguration
public class SampleController {

    public static Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    @RequestMapping("/send")
    @ResponseBody
    String send(String topic, String key, String data) {
        template.send(topic, key, data);
        return "success";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }

    @KafkaListener(id = "t1", topics = "t1")
    public void listenT1(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("{} - {} : {}", cr.topic(), cr.key(), cr.value());
    }

    @KafkaListener(id = "t2", topics = "t2" , containerGroup = "kafka-group5")
    public void listenT2(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("cr = " + cr);
        logger.info("AA {} - {} : {}", cr.topic(), cr.key(), cr.value());
    }

    @KafkaListener(id = "t3", topics = "t2", containerGroup = "kafka-group0")
    public void listenT3(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("cr = " + cr);
        logger.info("BB {} - {} : {}", cr.topic(), cr.key(), cr.value());
    }

    @KafkaListener(id = "t4", topics = "t2", containerGroup = "kafka-group1")
    public void listenT53(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("cr = " + cr);
        logger.info("CC {} - {} : {}", cr.topic(), cr.key(), cr.value());
    }


    @KafkaListener(id = "t8", topics = "t2", containerGroup = "kafka-group2")
    public void listenT454(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("cr = " + cr);
        logger.info("{} - {} : {}", cr.topic(), cr.key(), cr.value());
    }

}