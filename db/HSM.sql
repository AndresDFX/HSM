--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-04-18 18:49:16

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
-- TOC entry 202 (class 1259 OID 33108)
-- Name: areas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.areas (
    codigo character varying(50) NOT NULL,
    nombre character varying(50),
    descripcion character varying
);


ALTER TABLE public.areas OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 33114)
-- Name: camas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.camas (
    codigo character varying(50) NOT NULL,
    descripcion character varying(500),
    estado character varying,
    codigo_area character varying(50) NOT NULL
);


ALTER TABLE public.camas OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 33120)
-- Name: camas_paciente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.camas_paciente (
    codigo_cama character varying(50) NOT NULL,
    cedula_paciente character varying(50) NOT NULL,
    fecha character varying(50) NOT NULL
);


ALTER TABLE public.camas_paciente OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 33123)
-- Name: campania; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.campania (
    codigo character varying(50) NOT NULL,
    nombre character varying(50),
    objetivo character varying(50),
    fecha character varying(50),
    cedula character varying(50) NOT NULL
);


ALTER TABLE public.campania OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 33126)
-- Name: campanias_pacientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.campanias_pacientes (
    codigo_campania character varying(50) NOT NULL,
    cedula_paciente character varying(50) NOT NULL
);


ALTER TABLE public.campanias_pacientes OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 33129)
-- Name: causas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.causas (
    codigo character varying(50) NOT NULL,
    nombre character varying(50),
    descripcion character varying(300)
);


ALTER TABLE public.causas OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 33132)
-- Name: causas_registro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.causas_registro (
    codigo_registro character varying(50) NOT NULL,
    codigo_causa character varying(50) NOT NULL
);


ALTER TABLE public.causas_registro OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 33135)
-- Name: cita; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cita (
    codigo character varying(50) NOT NULL,
    fecha character varying(50),
    hora character varying(50),
    costo integer,
    descuento character varying(50),
    cedula character varying(50) NOT NULL,
    cedula_medico character varying(50) NOT NULL
);


ALTER TABLE public.cita OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 33138)
-- Name: empleados; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empleados (
    cargo character varying(50),
    salario double precision,
    email character varying(50),
    cedula character varying(50) NOT NULL,
    codigo_area character varying(50) NOT NULL,
    cedula_jefe character varying
);


ALTER TABLE public.empleados OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 33144)
-- Name: enfermeras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.enfermeras (
    "años_experiencia" character varying(50),
    cedula character varying(50) NOT NULL
);


ALTER TABLE public.enfermeras OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 33147)
-- Name: habilidades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.habilidades (
    codigo character varying(50) NOT NULL,
    nombre character varying(50)
);


ALTER TABLE public.habilidades OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 33150)
-- Name: habilidades_enfermeras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.habilidades_enfermeras (
    cedula character varying(50) NOT NULL,
    codigo_habilidad character varying(50) NOT NULL
);


ALTER TABLE public.habilidades_enfermeras OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 33153)
-- Name: historias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historias (
    codigo character varying NOT NULL,
    fecha_apertura character varying(50),
    cedula character varying(50) NOT NULL
);


ALTER TABLE public.historias OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 33159)
-- Name: medicamentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medicamentos (
    codigo character varying(50) NOT NULL,
    nombre character varying(50),
    descripcion character varying(50),
    costo integer
);


ALTER TABLE public.medicamentos OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 33162)
-- Name: medicos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medicos (
    numero_licencia character varying(50) NOT NULL,
    especialidad character varying(50),
    universidad character varying(50),
    cedula character varying(50) NOT NULL
);


ALTER TABLE public.medicos OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 33165)
-- Name: numero_cita; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.numero_cita
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.numero_cita OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 33167)
-- Name: numero_historia; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.numero_historia
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.numero_historia OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 33169)
-- Name: numero_registro; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.numero_registro
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.numero_registro OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 33171)
-- Name: pacientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pacientes (
    seguridad_social character varying(50) NOT NULL,
    fecha_nacimiento character varying(50),
    ocupacion character varying(50),
    cedula character varying(50) NOT NULL,
    campanias integer
);


ALTER TABLE public.pacientes OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 33174)
-- Name: personas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.personas (
    cedula character varying(50) NOT NULL,
    nombre character varying(50),
    direccion character varying(50),
    telefono character varying(50),
    contrasena character varying(50),
    tipo_usuario character varying(50)
);


ALTER TABLE public.personas OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 33177)
-- Name: registro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.registro (
    codigo character varying(50) NOT NULL,
    codigo_cita character varying(50) NOT NULL,
    codigo_historia character varying(50) NOT NULL
);


ALTER TABLE public.registro OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 33180)
-- Name: registros_medicamentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.registros_medicamentos (
    codigo_registro character varying(50),
    codigo_medicamento character varying(50)
);


ALTER TABLE public.registros_medicamentos OWNER TO postgres;

--
-- TOC entry 2929 (class 0 OID 33108)
-- Dependencies: 202
-- Data for Name: areas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.areas VALUES ('001', 'Pediatria', 'Estudia al niño y sus enfermedades');
INSERT INTO public.areas VALUES ('002', 'General', 'constituye el primer nivel de atención médica. ');


--
-- TOC entry 2930 (class 0 OID 33114)
-- Dependencies: 203
-- Data for Name: camas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.camas VALUES ('1', 'Cama Nueva', 'true', '001');
INSERT INTO public.camas VALUES ('2', 'Cama Reparada', 'true', '001');
INSERT INTO public.camas VALUES ('3', 'Cama traida desde el HUV', 'true', '002');
INSERT INTO public.camas VALUES ('4', 'Cama traida desde el centro', 'true', '002');


--
-- TOC entry 2931 (class 0 OID 33120)
-- Dependencies: 204
-- Data for Name: camas_paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.camas_paciente VALUES ('1', '123123123', '2018-05-14 00:00:00.0');
INSERT INTO public.camas_paciente VALUES ('3', '456456456', '2018-05-14 00:00:00.0');


--
-- TOC entry 2932 (class 0 OID 33123)
-- Dependencies: 205
-- Data for Name: campania; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.campania VALUES ('001', 'Cuidando Infantes', 'Promotion', '2018-05-01 00:00:00.0', '321321321');
INSERT INTO public.campania VALUES ('002', 'Cuidando Gente', 'Prevention', '2017-05-01 00:00:00.0', '321321321');


--
-- TOC entry 2933 (class 0 OID 33126)
-- Dependencies: 206
-- Data for Name: campanias_pacientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.campanias_pacientes VALUES ('001', '123123123');
INSERT INTO public.campanias_pacientes VALUES ('002', '123123123');
INSERT INTO public.campanias_pacientes VALUES ('002', '456456456');
INSERT INTO public.campanias_pacientes VALUES ('001', '456456456');


--
-- TOC entry 2934 (class 0 OID 33129)
-- Dependencies: 207
-- Data for Name: causas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.causas VALUES ('001', 'Diarrea', 'Secrecion');
INSERT INTO public.causas VALUES ('002', 'Vomito', 'Secrecion');


--
-- TOC entry 2935 (class 0 OID 33132)
-- Dependencies: 208
-- Data for Name: causas_registro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.causas_registro VALUES ('22', '001');
INSERT INTO public.causas_registro VALUES ('22', '002');


--
-- TOC entry 2936 (class 0 OID 33135)
-- Dependencies: 209
-- Data for Name: cita; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cita VALUES ('16', '2018-05-07 00:00:00.0', '16:54:51', 25000, NULL, '123123123', '321321321');
INSERT INTO public.cita VALUES ('17', '2018-05-01 00:00:00.0', '16:54:51', 25000, NULL, '456456456', '113113113');


--
-- TOC entry 2937 (class 0 OID 33138)
-- Dependencies: 210
-- Data for Name: empleados; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.empleados VALUES ('Enfermera', 500000, 'perez@gmail.com', '987987987', '001', '12121212');
INSERT INTO public.empleados VALUES ('Enfermera', 800000, 'diana@gmail.com', '765765765', '001', '12121212');
INSERT INTO public.empleados VALUES ('Medico', 1200000, 'martinez@gmail.com', '321321321', '002', '12121212');
INSERT INTO public.empleados VALUES ('Medico', 900000, 'castro@hotmail.com', '113113113', '002', '12121212');


--
-- TOC entry 2938 (class 0 OID 33144)
-- Dependencies: 211
-- Data for Name: enfermeras; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.enfermeras VALUES ('5', '987987987');
INSERT INTO public.enfermeras VALUES ('5', '765765765');


--
-- TOC entry 2939 (class 0 OID 33147)
-- Dependencies: 212
-- Data for Name: habilidades; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.habilidades VALUES ('001', 'Aplicar Inyecciones');
INSERT INTO public.habilidades VALUES ('002', 'Cuidar Niños');


--
-- TOC entry 2940 (class 0 OID 33150)
-- Dependencies: 213
-- Data for Name: habilidades_enfermeras; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.habilidades_enfermeras VALUES ('987987987', '001');
INSERT INTO public.habilidades_enfermeras VALUES ('765765765', '002');


--
-- TOC entry 2941 (class 0 OID 33153)
-- Dependencies: 214
-- Data for Name: historias; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.historias VALUES ('14', '2018-05-21', '123123123');
INSERT INTO public.historias VALUES ('15', '2018-05-21', '456456456');


--
-- TOC entry 2942 (class 0 OID 33159)
-- Dependencies: 215
-- Data for Name: medicamentos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.medicamentos VALUES ('12', 'Acetaminofen', 'utilizado principalmente para tratar la fiebre', 600);
INSERT INTO public.medicamentos VALUES ('13', 'Advil', 'utilizado principalmente para tratar la fiebre ', 1000);


--
-- TOC entry 2943 (class 0 OID 33162)
-- Dependencies: 216
-- Data for Name: medicos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.medicos VALUES ('321321321', 'Neurologia', 'Univalle', '321321321');
INSERT INTO public.medicos VALUES ('113113113', 'Cirugia', 'Universidad Autonoma', '113113113');


--
-- TOC entry 2947 (class 0 OID 33171)
-- Dependencies: 220
-- Data for Name: pacientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pacientes VALUES ('123123123', '1996-11-25 00:00:00.0', 'Ingeniero Sistemas', '123123123', 2);
INSERT INTO public.pacientes VALUES ('456456456', '1994-11-12 00:00:00.0', 'Ingeniero Electronico', '456456456', 2);


--
-- TOC entry 2948 (class 0 OID 33174)
-- Dependencies: 221
-- Data for Name: personas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.personas VALUES ('123123123', 'Diana Marcela Perez', 'Cra 5#12-15', '323460789', '123123123', 'Paciente');
INSERT INTO public.personas VALUES ('456456456', 'Jaime Perez', 'Cra 41 #2-21', '321213123', '456456456', 'Paciente');
INSERT INTO public.personas VALUES ('987987987', 'Daniela Perez', 'Cra 5 #14-80', '4323223', '987987987', 'Enfermera');
INSERT INTO public.personas VALUES ('765765765', 'Diana Castaño', 'Cra 45#23-1', '345345', '765765765', 'Enfermera');
INSERT INTO public.personas VALUES ('321321321', 'Daniel Martinez', 'Calle 12#25-10', '567567', '321321321', 'Medico');
INSERT INTO public.personas VALUES ('113113113', 'Mateo Castro', 'Diagona 24 # 1-23', '678456', '113113113', 'Medico');
INSERT INTO public.personas VALUES ('1144194156', 'Julian Castaño
', 'Calle 10 # 25-11', '780199', 'admin123', 'Administrador');


--
-- TOC entry 2949 (class 0 OID 33177)
-- Dependencies: 222
-- Data for Name: registro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.registro VALUES ('22', '17', '14');


--
-- TOC entry 2950 (class 0 OID 33180)
-- Dependencies: 223
-- Data for Name: registros_medicamentos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.registros_medicamentos VALUES ('22', '12');


--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 217
-- Name: numero_cita; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.numero_cita', 17, true);


--
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 218
-- Name: numero_historia; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.numero_historia', 15, true);


--
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 219
-- Name: numero_registro; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.numero_registro', 22, true);


--
-- TOC entry 2768 (class 2606 OID 33184)
-- Name: areas areas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.areas
    ADD CONSTRAINT areas_pkey PRIMARY KEY (codigo);


--
-- TOC entry 2772 (class 2606 OID 33186)
-- Name: camas_paciente camas_paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.camas_paciente
    ADD CONSTRAINT camas_paciente_pkey PRIMARY KEY (codigo_cama, cedula_paciente, fecha);


--
-- TOC entry 2770 (class 2606 OID 33188)
-- Name: camas camas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.camas
    ADD CONSTRAINT camas_pkey PRIMARY KEY (codigo);


--
-- TOC entry 2774 (class 2606 OID 33190)
-- Name: campania campania_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.campania
    ADD CONSTRAINT campania_pkey PRIMARY KEY (codigo);


--
-- TOC entry 2776 (class 2606 OID 33192)
-- Name: campanias_pacientes campanias_pacientes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.campanias_pacientes
    ADD CONSTRAINT campanias_pacientes_pkey PRIMARY KEY (codigo_campania, cedula_paciente);


--
-- TOC entry 2778 (class 2606 OID 33194)
-- Name: causas causas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.causas
    ADD CONSTRAINT causas_pkey PRIMARY KEY (codigo);


--
-- TOC entry 2780 (class 2606 OID 33196)
-- Name: causas_registro causas_registro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.causas_registro
    ADD CONSTRAINT causas_registro_pkey PRIMARY KEY (codigo_registro, codigo_causa);


--
-- TOC entry 2782 (class 2606 OID 33198)
-- Name: cita cita_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cita
    ADD CONSTRAINT cita_pkey PRIMARY KEY (codigo);


--
-- TOC entry 2784 (class 2606 OID 33200)
-- Name: empleados empleados_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleados_pkey PRIMARY KEY (cedula);


--
-- TOC entry 2786 (class 2606 OID 33202)
-- Name: enfermeras enfermeras_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enfermeras
    ADD CONSTRAINT enfermeras_pkey PRIMARY KEY (cedula);


--
-- TOC entry 2790 (class 2606 OID 33204)
-- Name: habilidades_enfermeras habilidades_enfermeras_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.habilidades_enfermeras
    ADD CONSTRAINT habilidades_enfermeras_pkey PRIMARY KEY (cedula, codigo_habilidad);


--
-- TOC entry 2788 (class 2606 OID 33206)
-- Name: habilidades habilidades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.habilidades
    ADD CONSTRAINT habilidades_pkey PRIMARY KEY (codigo);


--
-- TOC entry 2792 (class 2606 OID 33208)
-- Name: historias historias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historias
    ADD CONSTRAINT historias_pkey PRIMARY KEY (codigo);


--
-- TOC entry 2794 (class 2606 OID 33210)
-- Name: medicamentos medicamentos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medicamentos
    ADD CONSTRAINT medicamentos_pkey PRIMARY KEY (codigo);


--
-- TOC entry 2796 (class 2606 OID 33212)
-- Name: medicos medicos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medicos
    ADD CONSTRAINT medicos_pkey PRIMARY KEY (numero_licencia);


--
-- TOC entry 2798 (class 2606 OID 33214)
-- Name: pacientes pacientes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pacientes
    ADD CONSTRAINT pacientes_pkey PRIMARY KEY (seguridad_social);


--
-- TOC entry 2800 (class 2606 OID 33216)
-- Name: personas personas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personas
    ADD CONSTRAINT personas_pkey PRIMARY KEY (cedula);


--
-- TOC entry 2802 (class 2606 OID 33218)
-- Name: registro registro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registro
    ADD CONSTRAINT registro_pkey PRIMARY KEY (codigo);


-- Completed on 2020-04-18 18:49:17

--
-- PostgreSQL database dump complete
--

