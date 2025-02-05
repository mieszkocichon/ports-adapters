CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE car
(
    id            BIGSERIAL PRIMARY KEY,
    uuid          UUID                     NOT NULL,
    car_id        UUID                     NOT NULL,
    version       INTEGER                  NOT NULL DEFAULT 0,
    updated_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    name          VARCHAR(255) UNIQUE      NOT NULL,
    owner         VARCHAR(255) UNIQUE      NOT NULL,
    amount        BIGINT                   NOT NULL,
    warranty_date TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE car_history
(
    rev           BIGINT,
    revtype       SMALLINT,
    id            BIGINT,
    uuid          UUID,
    car_id        UUID,
    version       INTEGER,
    updated_at    TIMESTAMP WITH TIME ZONE,
    created_at    TIMESTAMP WITH TIME ZONE,
    name          VARCHAR(255),
    owner         VARCHAR(255),
    amount        BIGINT,
    warranty_date TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (rev, id)
);

CREATE TABLE REVINFO
(
    rev      BIGSERIAL PRIMARY KEY,
    REVTSTMP BIGINT
);
