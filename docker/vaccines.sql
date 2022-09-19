
create table vaccine
(
    id              serial primary key,
    name            varchar(255) not null unique,
    age_in_days     int,
    mandatory       boolean not null default true,
    booster_in_days int[],
    repeat_booster_in_days int,
    created         timestamp default now(),
);

create table vaccines_document
(
    vaccine_id    int,
    document_id   int,
    vaccinated    boolean,
    vaccinated_at timestamp,
    constraint fk_vaccine
        foreign key (vaccine_id) references vaccine (id),
    constraint fk_document
        foreign key (document_id) references document (id)
);

insert into vaccine(name, age_in_days, mandatory, booster_in_days)
values('Tuberculosis',0,true,ARRAY[3650]);
insert into vaccine(name, age_in_days, mandatory, booster_in_days)
values('Hepatitis',0,true,ARRAY[30,182]);
insert into vaccine(name, age_in_days, mandatory, booster_in_days)
values('DTaP',60,true,ARRAY[120,182,456]);
insert into vaccine(name, age_in_days, mandatory, booster_in_days)
values('Polio',60,true,ARRAY[120,182,456]);
insert into vaccine(name, age_in_days, mandatory, booster_in_days,repeat_booster_in_days)
values('Yellow Fever',182,true,ARRAY[3650],3650);

create view crosstab_vaccines_documents as
select *
from crosstab('select vd.document_id,v.name,vd.document_id is not null as value from vaccine v left join vaccines_document vd on v.id = vd.vaccine_id order by 1,2') as ct ("document_id" integer,
                                                                                                                                                                            "Tuberculosis" boolean,
                                                                                                                                                                            "Hepatitis" boolean,
                                                                                                                                                                            "Yellow Fever" boolean, "Polio" boolean,
                                                                                                                                                                            "DTaP" boolean)
