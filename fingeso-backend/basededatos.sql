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
-- Name: actividades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actividades (
    id integer NOT NULL,
    text character varying,
    input boolean,
    id_grupo_actividades integer,
    puntaje_maximo integer
);


ALTER TABLE public.actividades OWNER TO postgres;

--
-- Name: categorias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categorias (
    text character varying,
    puntaje_maximo integer,
    input boolean,
    id integer NOT NULL,
    img character varying
);


ALTER TABLE public.categorias OWNER TO postgres;

--
-- Name: comites; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comites (
    id integer NOT NULL,
    name character varying
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
-- Name: documentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.documentos (
    id integer NOT NULL,
    archivo character varying,
    tipo_respaldo character varying,
    id_subcategoria integer,
    id_grupoactividades integer,
    id_actividad integer,
    id_solicitud integer,
    puntaje integer,
    valido boolean
);


ALTER TABLE public.documentos OWNER TO postgres;

--
-- Name: documentos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.documentos ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.documentos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: grupos_actividades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grupos_actividades (
    text character varying,
    id integer NOT NULL,
    input boolean,
    puntaje_maximo integer,
    id_subcategoria integer
);


ALTER TABLE public.grupos_actividades OWNER TO postgres;

--
-- Name: solicitudes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.solicitudes (
    id_usuario integer NOT NULL,
    fecha_envio date,
    id integer NOT NULL,
    puntuacion integer,
    estado character varying,
    resultado_puntuacion character varying,
    resultado_apelacion character varying,
    resultado_evaluacion character varying,
    resultado_final character varying,
    id_comite integer,
    periodo date
);


ALTER TABLE public.solicitudes OWNER TO postgres;

--
-- Name: subcategorias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subcategorias (
    text character varying,
    puntaje_maximo integer,
    input boolean,
    id_categoria integer,
    id integer NOT NULL
);


ALTER TABLE public.subcategorias OWNER TO postgres;

--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    nombre character varying,
    username character varying,
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
-- Data for Name: actividades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actividades (id, text, input, id_grupo_actividades, puntaje_maximo) FROM stdin;
4	Simposios y/o congresos sin publicación de acta	t	1	2
1	Publicaciones en revistas de nivel A	t	1	12
3	Simposios y/o congresos con publicación de acta	t	1	4
5	Charla invitada a congreso nacional, si es que no ha sido considerada en los items anteriores	t	1	4
6	Charla invitada a congreso nacional, si es que no ha sido considerada en los ítemes anteriores. 	t	1	3
7	Monografías en la especialidad	t	2	70
8	Capítulo de libro de la especialidad	t	2	70
9	Editor de libro con la especialidad	t	2	70
10	Evaluación de proyectos de investigación y árbitro de publicaciones.	t	2	70
11	Trabajos de desarrollo y aplicación.	t	2	70
12	Cursos de pregrado	t	3	300
13	Cursos de Post-Grado	t	3	300
14	Guía de Tesis de Doctorado	t	4	300
15	Guía de Tesis de Magíster	t	4	300
16	Guía de Tesis de Pregrado o Titulación	t	4	300
17	Corrección de Tesis de Doctorado	t	4	300
18	Corrección Guía de Tesis de Magíster	t	4	300
19	Corrección de Tesis de Pregrado o Titulación	t	4	300
20	Guía de práctica profesional	t	4	300
21	Autor de libros editados por editorial internacional	t	5	140
22	Autor de libros editados por editorial nacional	t	5	140
23	Autor de apuntes originales de curso completo	t	5	140
24	Autor de manuales originales de laboratorio de cursos completos	t	5	140
25	Colaborador de apuntes originales de curso completo	t	5	140
26	Colaborador de apuntes originales de laboratorio de cursos completos	t	5	140
27	Revisión en libros editados por editorial internacional	t	5	140
28	Revisión en libros editados por editorial nacional	t	5	140
29	Revisión en apuntes originales de curso completo	t	5	140
30	Revisión en manuales originales de laboratorio de cursos completos 	t	5	140
31	Dirección de Departamentos Académicos o cargos superiores	t	6	100
32	Sub-Dirección de Departamentos Académicos o cargos superiores	t	6	100
33	Jefatura de Área, Laboratorio, Comité, Carrera y similares, dentro de un Departamento Académico	t	6	100
34	Coordinación de Nivel, teoría o laboratorio	t	6	100
35	A nivel superior de la Universidad	t	7	20
36	A nivel de Facultad	t	7	20
37	A nivel Departamental	t	7	20
38	De nivel internacional	t	12	250
39	De nivel nacional	t	12	250
40	Interno de la Universidad	t	12	250
41	Interno del Departamento	t	12	250
42	Nivel Internacional	t	16	250
43	Nivel Nacional	t	16	250
44	A nivel universitario	t	17	250
45	A nivel no universitario	t	17	250
2	Publicaciones en revistas de nivel B	t	1	4
\.


--
-- Data for Name: categorias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categorias (text, puntaje_maximo, input, id, img) FROM stdin;
Obra Realizada	600	f	2	obra.svg
Formación	400	f	1	formacion.svg
\.


--
-- Data for Name: comites; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comites (id, name) FROM stdin;
2	Comité de Puntuación del Departamento
3	Comité de Apelación de la Facultad
4	Comité de Evaluación de la Facultad
5	Finalizada
1	Habilitada
\.


--
-- Data for Name: documentos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.documentos (id, archivo, tipo_respaldo, id_subcategoria, id_grupoactividades, id_actividad, id_solicitud, puntaje, valido) FROM stdin;
15	hector@gmail.com/actividad/7/prosa.txt	actividad	\N	\N	7	8	0	f
18	hector@gmail.com/grupoActividades/10/prosa.txt	grupoActividades	\N	10	\N	8	0	f
19	hector@gmail.com/grupoActividades/10/prosa.txt	grupoActividades	\N	10	\N	8	0	f
22	hector@gmail.com/actividad/32/prosa.txt	actividad	\N	\N	32	8	0	f
23	hector@gmail.com/actividad/32/prosa.txt	actividad	\N	\N	32	8	0	f
24	hector@gmail.com/subCategoria/2/tareafingeso.txt	subCategoria	2	\N	\N	8	0	f
25	hector@gmail.com/grupoActividades/14/tareafingeso.txt	grupoActividades	\N	14	\N	8	0	f
26	hector@gmail.com/grupoActividades/13/prosa.txt	grupoActividades	\N	13	\N	8	0	f
20	hector@gmail.com/grupoActividades/10/9 Nivele Aplicacion Formacion Ingenieros (1).pdf	grupoActividades	\N	10	\N	8	0	f
17	hector@gmail.com/actividad/13/9 Nivele Aplicacion Formacion Ingenieros (1).pdf	actividad	\N	\N	13	8	30	t
27	hector@gmail.com/actividad/25/9 Nivele Aplicacion Formacion Ingenieros (1).pdf	actividad	\N	\N	25	8	140	t
28	hector@gmail.com/actividad/35/lab2Dobble.txt	actividad	\N	\N	35	8	20	t
31	hector@gmail.com/actividad/4/9 Nivele Aplicacion Formacion Ingenieros (1).pdf	actividad	\N	\N	4	4	2	t
14	felipe@gmail.com/grupoActividades/9/tareafingeso.txt	grupoActividades	\N	9	\N	9	250	t
16	hector@gmail.com/actividad/8/9 Nivele Aplicacion Formacion Ingenieros (1) (1).pdf	actividad	\N	\N	8	8	0	f
32	hector@gmail.com/actividad/11/9 Nivele Aplicacion Formacion Ingenieros (1) (1).pdf	actividad	\N	\N	11	8	0	f
33	hector@gmail.com/actividad/34/lab2Dobble.txt	actividad	\N	\N	34	8	0	f
29	hector@gmail.com/actividad/1/lab2Dobble.txt	actividad	\N	\N	1	4	12	t
30	hector@gmail.com/actividad/2/9 Nivele Aplicacion Formacion Ingenieros (1).pdf	actividad	\N	\N	2	4	4	t
21	hector@gmail.com/actividad/45/prosa.txt	actividad	\N	\N	45	8	250	t
\.


--
-- Data for Name: grupos_actividades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.grupos_actividades (text, id, input, puntaje_maximo, id_subcategoria) FROM stdin;
Otras actividades de Investigación	2	f	70	3
Publicaciones, Congresos, Simposios, Jornadas, etc.	1	f	380	3
Clases realizadas	3	f	300	4
Trabajos de graduación	4	f	100	4
Confección y Revisión de Material Docente	5	f	140	4
Jefaturas, Coordinaciones, etc.	6	f	100	5
Participación como miembro de Comisiones, Comités, etc.	7	f	20	5
Prestación de servicios a Institución Externa como miembro de la USACH 	13	t	250	6
Direccion de programas o proyectos de extensión de la universidad	10	t	250	6
Organización de eventos	12	f	250	6
Capacitación dada en la propia disciplina	17	f	250	6
Publicaciones en revistas de difusión	9	t	250	6
Director alterno de proyectos institucionales con financiamiento externo y contraparte	15	t	250	6
Director de proyectos institucionales con financiamiento externo y contraparte	14	t	250	6
Participación en Comités Editoriales	16	f	250	6
Charlas y/o conferencias	8	t	250	6
Colaborador de programas o proyectos de extensión de la universidad	11	t	250	6
\.


--
-- Data for Name: solicitudes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.solicitudes (id_usuario, fecha_envio, id, puntuacion, estado, resultado_puntuacion, resultado_apelacion, resultado_evaluacion, resultado_final, id_comite, periodo) FROM stdin;
5	2022-11-28	8	500	Comité de Puntuación del Departamento	null	\N	\N	Pendiente	2	2021-03-24
5	2022-11-28	4	500	Comité de Puntuación del Departamento	null	\N	\N	Pendiente	2	2022-11-23
7	2022-11-28	9	500	Finalizada	Ayudante	\N	\N	Ayudante	5	2021-03-24
\.


--
-- Data for Name: subcategorias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subcategorias (text, puntaje_maximo, input, id_categoria, id) FROM stdin;
Nivel de Formación	320	t	1	1
Puntajes Adicionales	80	t	1	2
Investigación	450	f	2	3
Docencia	450	f	2	4
Administración y Planificación Docente	120	f	2	5
Extensión y Asistencia Técnica	250	f	2	6
\.


--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (nombre, username, rut, departamento, facultad, desempenio, cargo, jerarquia, antiguedad, ultima_jerarquia, id, password, id_comite) FROM stdin;
Hector Gallardo	hector@gmail.com	20.285.942-9	DIINF	Ingenieria	3	Planta	Ayudante	2022-11-28	2022-11-28	5	$2a$10$.iYx7f6AmEjit2/ds4P5yu35AsHhwZVCryyGeijDbI3ggzQd02RHW	2
Felipe Aravena	felipe@gmail.com	20.285.942-9	DIINF	Ingenieria	3	Planta	Ayudante	2022-11-28	2022-11-28	7	$2a$10$.iYx7f6AmEjit2/ds4P5yu35AsHhwZVCryyGeijDbI3ggzQd02RHW	2
\.


--
-- Name: comites_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comites_id_seq', 5, true);


--
-- Name: documentos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.documentos_id_seq', 33, true);


--
-- Name: actividades actividades_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT actividades_pk PRIMARY KEY (id);


--
-- Name: categorias categorias_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categorias
    ADD CONSTRAINT categorias_pk PRIMARY KEY (id);


--
-- Name: comites comites_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comites
    ADD CONSTRAINT comites_pk PRIMARY KEY (id);


--
-- Name: documentos documentos_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documentos
    ADD CONSTRAINT documentos_pk PRIMARY KEY (id);


--
-- Name: grupos_actividades grupos_actividades_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grupos_actividades
    ADD CONSTRAINT grupos_actividades_pk PRIMARY KEY (id);


--
-- Name: solicitudes solicitudes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitudes
    ADD CONSTRAINT solicitudes_pk PRIMARY KEY (id);


--
-- Name: subcategorias subbcategorias_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subcategorias
    ADD CONSTRAINT subbcategorias_pk PRIMARY KEY (id);


--
-- Name: usuarios usuarios_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pk PRIMARY KEY (id);


--
-- Name: documentos id_actividad_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documentos
    ADD CONSTRAINT id_actividad_fk FOREIGN KEY (id_actividad) REFERENCES public.actividades(id);


--
-- Name: usuarios id_comite_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT id_comite_fk FOREIGN KEY (id_comite) REFERENCES public.comites(id);


--
-- Name: actividades id_grupo_actividades_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT id_grupo_actividades_fk FOREIGN KEY (id_grupo_actividades) REFERENCES public.grupos_actividades(id);


--
-- Name: documentos id_grupoactividades_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documentos
    ADD CONSTRAINT id_grupoactividades_fk FOREIGN KEY (id_grupoactividades) REFERENCES public.grupos_actividades(id);


--
-- Name: documentos id_solicitud_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documentos
    ADD CONSTRAINT id_solicitud_fk FOREIGN KEY (id_solicitud) REFERENCES public.solicitudes(id);


--
-- Name: grupos_actividades id_subcategoria_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grupos_actividades
    ADD CONSTRAINT id_subcategoria_fk FOREIGN KEY (id_subcategoria) REFERENCES public.subcategorias(id);


--
-- Name: documentos id_subcategoria_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documentos
    ADD CONSTRAINT id_subcategoria_fk FOREIGN KEY (id_subcategoria) REFERENCES public.subcategorias(id);


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
-- Name: subcategorias subbcategorias_categorias_null_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subcategorias
    ADD CONSTRAINT subbcategorias_categorias_null_fk FOREIGN KEY (id_categoria) REFERENCES public.categorias(id);


--
-- PostgreSQL database dump complete
--

