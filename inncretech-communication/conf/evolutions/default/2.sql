# Template schema

# --- !Ups

CREATE TABLE template (
    id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    subject varchar(255) NOT NULL,
    template_text text NOT NULL,
    created_at DateTime default current_timestamp,
    last_modified_at DateTime
);

# --- !Downs

DROP TABLE template;