create table employee (
    id int(10) not null Auto_INCREMENT,
    first_name varchar(255),
    last_name varchar(255),
    phone varchar(255),
    email varchar(255),
    date_of_birth date,
    created_by varchar(255),
    created_date timestamp,
    updated_by varchar(255),
    updated_date timestamp,
    Primary key (id)
    );

create table address (
    id int(10) not null Auto_INCREMENT,
    employee_id int(10),
    add_type varchar(255),
    full_add varchar(255),
    city varchar(255),
    state varchar(255),
    country varchar(255),
    pincode int(10),
    Primary key (id),
    foreign key (employee_id) references employee(id)
    );