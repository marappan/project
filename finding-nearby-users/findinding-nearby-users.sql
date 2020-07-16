-- Table: public.user_data

-- DROP TABLE public.user_data;

CREATE TABLE public.user_data
(
    id integer NOT NULL,
    user_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_data_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.user_data
    OWNER to postgres;

----------

-- Table: public.location_data

-- DROP TABLE public.location_data;

CREATE TABLE public.location_data
(
    id integer NOT NULL,
    latitude double precision,
    longitude double precision,
    user_id integer NOT NULL,
    userdata_id integer,
    CONSTRAINT location_data_pkey PRIMARY KEY (id),
    CONSTRAINT fkq22w6arqdr0smbj7t4xc0cpa3 FOREIGN KEY (userdata_id)
        REFERENCES public.user_data (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.location_data
    OWNER to postgres;

-----------

-- Table: public.user_data_location_data

-- DROP TABLE public.user_data_location_data;

CREATE TABLE public.user_data_location_data
(
    userentity_id integer NOT NULL,
    locations_id integer NOT NULL,
    CONSTRAINT uk_hts8kcsnkq899nptlhjs6iy6b UNIQUE (locations_id),
    CONSTRAINT fkrc26w1jamwvjd47uxd0r69wt3 FOREIGN KEY (locations_id)
        REFERENCES public.location_data (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkrrvo7unyt5jywtnj4mg7rem00 FOREIGN KEY (userentity_id)
        REFERENCES public.user_data (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.user_data_location_data
    OWNER to postgres;