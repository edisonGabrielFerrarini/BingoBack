CREATE TABLE cliente (
    id              BIGSERIAL   NOT NULL,
    id_usuario      INTEGER     NOT NULL,
    id_agente       INTEGER     NOT NULL,
    nome            VARCHAR(60) NOT NULL,
    id_endereco     INTEGER,
    id_contato      INTEGER     NOT NULL,
    telefone        VARCHAR(30),
    celular         VARCHAR(30),
    cpf             VARCHAR(20) NOT NULL,
    saldo           DECIMAL,
    ganhos          DECIMAL,
    cidade          VARCHAR(150),
    bairro          VARCHAR(150),
    cep             VARCHAR(20),
    estado          VARCHAR(100),
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE agente (
    id              BIGSERIAL   NOT NULL,
    id_usuario          INTEGER     NOT NULL,
    id_gerente          INTEGER     NOT NULL,
    nome                VARCHAR(60) NOT NULL,
    id_endereco         INTEGER,
    id_contato          INTEGER     NOT NULL,
    cpf                 VARCHAR(20) NOT NULL,
    telefone            VARCHAR(30),
    celular             VARCHAR(30),
    porcentual_venda    DECIMAL,
    cidade              VARCHAR(150),
    bairro              VARCHAR(150),
    cep                 VARCHAR(20),
    estado              VARCHAR(100),
    created_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE gerente (
    id              BIGSERIAL   NOT NULL,
    id_usuario      INTEGER     NOT NULL,
    nome            VARCHAR(60) NOT NULL,
    id_endereco     INTEGER,
    id_contato      INTEGER     NOT NULL,
    cpf             VARCHAR(20) NOT NULL,
    telefone        VARCHAR(30),
    celular         VARCHAR(30),
    cidade          VARCHAR(150),
    bairro          VARCHAR(150),
    cep             VARCHAR(20),
    estado          VARCHAR(100),
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE usuario (
    id              BIGSERIAL       NOT NULL,
    tipo_cadastro   INTEGER         NOT NULL,
    senha           VARCHAR(60)     NOT NULL,
    email           VARCHAR(120)    NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE ganhador (
    id              BIGSERIAL   NOT NULL,
    id_ticket       INTEGER     NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE ticket (
    id              BIGSERIAL       NOT NULL,
    id_cliente      INTEGER         NOT NULL,
    id_cartela      INTEGER         NOT NULL,
    numeros         varchar(100)    NOT NULL,
    valor           DECIMAL         NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE cartela (
    id                  BIGSERIAL       NOT NULL,
    ativa               BOOLEAN         NOT NULL,
    id_cartela          INTEGER         NOT NULL,
    numeros_sorteados   TEXT        NOT NULL,
    valor               DECIMAL         NOT NULL,
    valor_numero        DECIMAL         NOT NULL,
    created_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

