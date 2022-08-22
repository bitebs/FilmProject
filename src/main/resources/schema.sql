create table film(
                      film_id LONG  NOT NULL,
                      name varchar(100) NOT NULL,
                      genre varchar(20) NOT NULL,
                      country_id LONG,
                      PRIMARY KEY  (film_id)
);

create table country(
      country_id LONG NOT NULL AUTO_INCREMENT,
      country_name varchar(100) NOT NULL,
      PRIMARY KEY (country_id) );