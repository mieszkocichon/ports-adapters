CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE user_role
(
    id         BIGSERIAL PRIMARY KEY,
    uuid       UUID                     NOT NULL,
    version    INTEGER                  NOT NULL DEFAULT 0,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    role_name  VARCHAR(255)             NOT NULL UNIQUE
);

CREATE TABLE user_role_history
(
    rev        BIGINT,
    revtype    SMALLINT,
    id         BIGSERIAL PRIMARY KEY,
    uuid       UUID,
    version    INTEGER,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE,
    role_name  VARCHAR(255)
);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES user_role (id) ON DELETE CASCADE
);

CREATE TABLE user_roles_history
(
    rev        BIGINT,
    revtype    SMALLINT,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id, rev)
);

INSERT INTO user_role (uuid, version, updated_at, created_at, role_name)
VALUES ('e1dee183-8533-46db-916a-8d72cc11a9e4', 1, now(), now(), 'ROLE_ADMIN'),
       ('1de0ab0e-2914-44c1-ac36-6bcbfe69a6e3', 1, now(), now(), 'ROLE_MODERATOR'),
       ('ba972fdd-f6e1-4886-8ac1-93269965e63e', 1, now(), now(), 'ROLE_USER')