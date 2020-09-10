drop table if exists present_type;
drop table if exists person;
drop table if exists present_to_person;

create table present_type (
    type_id INT primary key,
    type_name varchar(255) not null
);

create table present (
    present_id INT AUTO_INCREMENT primary key,
    present_type_id INT not null,
    foreign key (present_type_id) references present_type (type_id)
)

create table person (
    person_id INT AUTO_INCREMENT primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null
);

create table present_to_person (
    id int auto_increment primary key,
    person_id int not null,
    present_type_id int not null,
    foreign key (person_id) references person (person_id),
    foreign key (present_type_id) references present_type (type_id)
);

create index ind_ptp_person_id on present_to_person(person_id);
create index ind_ptp_present_type_id on present_to_person(present_type_id);
create index ind_pr_present_type_id on present(present_type_id);