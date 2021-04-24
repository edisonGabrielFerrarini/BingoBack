
--=========================================================
-- AGENTE
--=========================================================

ALTER TABLE agente ADD CONSTRAINT agente_pk PRIMARY KEY(id);

ALTER TABLE agente
ADD CONSTRAINT agente__usuario_fk
FOREIGN KEY (id_usuario) REFERENCES usuario (id);

ALTER TABLE agente
ADD CONSTRAINT agente__gerente_fk
FOREIGN KEY (id_gerente) REFERENCES gerente (id);

--=========================================================
-- GERENTE
--=========================================================

ALTER TABLE gerente ADD CONSTRAINT gerente_pk PRIMARY KEY(id);

ALTER TABLE gerente
ADD CONSTRAINT gerente__usuario_fk
FOREIGN KEY (id_usuario) REFERENCES usuario (id);

--=========================================================
-- GANHADOR
--=========================================================

ALTER TABLE ganhador ADD CONSTRAINT ganhador_pk PRIMARY KEY(id);

ALTER TABLE ganhador
ADD CONSTRAINT ganhador__ticket_fk
FOREIGN KEY (id_ticket) REFERENCES ticket (id);


--=========================================================
-- TICKET
--=========================================================

ALTER TABLE ticket ADD CONSTRAINT `ticket_pk` PRIMARY KEY(id);

ALTER TABLE ticket
ADD CONSTRAINT ticket__usuario_fk
FOREIGN KEY (id_cliente) REFERENCES cliente (id);

ALTER TABLE ticket
ADD CONSTRAINT ganhador__cartela_fk
FOREIGN KEY (id_cartela) REFERENCES cartela (id);

--=========================================================
-- CLIENTE
--=========================================================

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY(id);

--=========================================================
-- CLIENTE_AGENTE
--=========================================================

ALTER TABLE cliente_agente ADD CONSTRAINT teste_pk PRIMARY KEY(id);

ALTER TABLE cliente_agente
ADD CONSTRAINT cliente_agente__cliente_fk
FOREIGN KEY (id_cliente) REFERENCES cliente (id);

ALTER TABLE cliente_agente
ADD CONSTRAINT cliente_agente_fk
FOREIGN KEY (id_agente) REFERENCES agente (id);