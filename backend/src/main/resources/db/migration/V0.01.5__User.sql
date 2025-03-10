CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "user"
(
  id BIGSERIAL PRIMARY KEY,
  uuid UUID NOT NULL,
  user_id UUID NOT NULL,
  version INTEGER NOT NULL DEFAULT 0,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  username VARCHAR(255) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user_history
(
  rev BIGINT,
  revtype SMALLINT,
  id BIGINT,
  uuid UUID,
  user_id UUID,
  version INTEGER,
  updated_at TIMESTAMP WITH TIME ZONE,
  created_at TIMESTAMP WITH TIME ZONE,
  username VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255),
  PRIMARY KEY (rev, id)
);
