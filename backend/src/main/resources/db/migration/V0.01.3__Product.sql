CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE product
(
  id BIGSERIAL PRIMARY KEY,
  uuid  UUID  NOT NULL,
  product_id UUID NOT NULL,
  version INTEGER NOT NULL DEFAULT 0,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  name VARCHAR(255) UNIQUE NOT NULL,
  image_url VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE product_history
(
  rev BIGINT,
  revtype SMALLINT,
  id BIGINT,
  uuid UUID,
  product_id UUID,
  version INTEGER,
  updated_at TIMESTAMP WITH TIME ZONE,
  created_at TIMESTAMP WITH TIME ZONE,
  name VARCHAR(255),
  image_url VARCHAR(255),
  PRIMARY KEY (rev, id)
);
