package br.com.acl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.acl.pedidos.dto.PedidoInputDTO;
import br.com.acl.service.PedidoProducerService;

@Service
public class KafkaProducerServiceImpl implements PedidoProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topicName;

    // Injeção de dependências via construtor (Boa prática do SOLID / Clean Code)
    public KafkaProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate, @Value("${app.kafka.topic.pedido-recebido}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @Override
    public void enviarPedido(String idempotencyKey, PedidoInputDTO pedidoInput) {
        log.info("Publicando pedido no Kafka. Chave Idempotência: {}, Tópico: {}", idempotencyKey, topicName);

        // Passamos a idempotencyKey como 'Key' da mensagem no Kafka para garantir a ordenação das partições
        kafkaTemplate.send(topicName, idempotencyKey, pedidoInput)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Pedido publicado no Kafka com sucesso! Partição: {}, Offset: {}",
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    } else {
                        log.error("Falha ao publicar pedido no Kafka. IdempotencyKey: {}", idempotencyKey, ex);
                    }
                });
    }
}
