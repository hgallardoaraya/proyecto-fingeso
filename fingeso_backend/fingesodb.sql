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

DROP DATABASE app_fingeso;
--
-- Name: app_fingeso; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE app_fingeso WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';


ALTER DATABASE app_fingeso OWNER TO postgres;

\connect app_fingeso

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
-- Name: comites; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comites (
    id integer NOT NULL,
    comite character varying
);


ALTER TABLE public.comites OWNER TO postgres;

--
-- Name: comites_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.comites ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.comites_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: solicitudes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.solicitudes (
    id_usuario integer NOT NULL,
    fecha_envio date,
    id integer NOT NULL,
    puntuacion integer,
    archivo character varying,
    estado character varying,
    resultado_puntuacion character varying,
    resultado_apelacion character varying,
    resultado_evaluacion character varying,
    resultado_final character varying,
    id_comite integer
);


ALTER TABLE public.solicitudes OWNER TO postgres;

--
-- Name: solicitud_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.solicitudes ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.solicitud_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    nombre character varying,
    email character varying,
    rut character varying,
    departamento character varying,
    facultad character varying,
    desempenio integer,
    cargo character varying,
    jerarquia character varying,
    antiguedad date,
    ultima_jerarquia date,
    id integer NOT NULL,
    password character varying NOT NULL,
    id_comite integer
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.usuarios ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: comites; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comites (id, comite) FROM stdin;
1	default
2	Comité de Puntuación del Departamento
3	Comité de Apelación de la Facultad
4	Comité de Evaluación de la Facultad
5	end
\.


--
-- Data for Name: solicitudes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.solicitudes (id_usuario, fecha_envio, id, puntuacion, archivo, estado, resultado_puntuacion, resultado_apelacion, resultado_evaluacion, resultado_final, id_comite) FROM stdin;
5	2022-11-28	6	1000	\N	Habilitado	\N	\N	\N	\N	1
5	2022-11-28	8	500	\N	Comité de Puntuación del Departamento	\N	\N	\N	\N	2
\.


--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (nombre, email, rut, departamento, facultad, desempenio, cargo, jerarquia, antiguedad, ultima_jerarquia, id, password, id_comite) FROM stdin;
Hector Gallardo	hector.gallardo.a@usach.cl	20.285.942-9	DIINF	Ingenieria	3	Planta	Ayudante	2022-11-28	2022-11-28	5	holahola	2
\.


--
-- Name: comites_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comites_id_seq', 5, true);


--
-- Name: solicitud_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.solicitud_id_seq', 8, true);


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 5, true);


--
-- Name: comites comites_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comites
    ADD CONSTRAINT comites_pk PRIMARY KEY (id);


--
-- Name: solicitudes solicitudes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitudes
    ADD CONSTRAINT solicitudes_pk PRIMARY KEY (id);


--
-- Name: usuarios usuarios_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pk PRIMARY KEY (id);


--
-- Name: usuarios id_comite_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT id_comite_fk FOREIGN KEY (id_comite) REFERENCES public.comites(id);


--
-- Name: solicitudes id_usuarios_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitudes
    ADD CONSTRAINT id_usuarios_fk FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id);


--
-- Name: solicitudes solicitudes_comites_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitudes
    ADD CONSTRAINT solicitudes_comites_id_fk FOREIGN KEY (id_comite) REFERENCES public.comites(id);


--
-- PostgreSQL database dump complete
--

