# communication schema

# --- !Ups

CREATE TABLE communication (
    id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    contact_info varchar(255) NOT NULL,
    comm_type tinyint NOT NULL,
    comm_data varchar(255) NOT NULL,
    comm_method tinyint NOT NULL,
    sent boolean not null,
    sent_at DateTime
);

# --- !Downs

DROP TABLE communication;