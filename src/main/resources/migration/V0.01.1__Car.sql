CREATE
DATABASE car;

CREATE TABLE car
(
    id            BIGSERIAL PRIMARY KEY,
    uuid          VARCHAR(36)              NOT NULL,
    version       INT                      NOT NULL DEFAULT 0,
    updated_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    name          VARCHAR(255) UNIQUE      NOT NULL,
    owner         VARCHAR(255) UNIQUE      NOT NULL,
    warranty_date TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE car_history
(
    rev           BIGINT,
    revtype       SMALLINT,
    id            BIGINT                   NOT NULL,
    uuid          VARCHAR(36)              NOT NULL,
    version       INT                      NOT NULL DEFAULT 0,
    updated_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    name          VARCHAR(255) UNIQUE      NOT NULL,
    owner         VARCHAR(255) UNIQUE      NOT NULL,
    warranty_date TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY (rev, id)
);

