--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS testdb;
--
-- Name: testdb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE testdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';


ALTER DATABASE testdb OWNER TO postgres;

\connect testdb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: tarjeta_bip; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tarjeta_bip (
    saldo integer,
    tipo character varying,
    id integer NOT NULL,
    serie integer
);


ALTER TABLE public.tarjeta_bip OWNER TO postgres;

--
-- Name: tarjeta_bip_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tarjeta_bip ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.tarjeta_bip_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: tarjeta_bip; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tarjeta_bip (saldo, tipo, id, serie) FROM stdin;
1600	estudiante	3	3
1000	normal	1	1
1500	normal	2	2
2000	adulto mayor	4	4
\.


--
-- Name: tarjeta_bip_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tarjeta_bip_id_seq', 4, true);


--
-- Name: tarjeta_bip tarjeta_bip_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tarjeta_bip
    ADD CONSTRAINT tarjeta_bip_pk PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

