CREATE TABLE public.usuario (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	email varchar(255) NULL,
	nome varchar(255) NULL,
	senha varchar(255) NULL,
	CONSTRAINT uk_5171l57faosmj8myawaucatdw UNIQUE (email),
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);


CREATE TABLE public.cidade (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	estado varchar(255) NULL,
	latitude varchar(255) NULL,
	longitude varchar(255) NULL,
	nome varchar(255) NULL,
	CONSTRAINT cidade_pkey PRIMARY KEY (id)
);

CREATE TABLE public.parada (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	horario_chegada timestamp NULL,
	latitude varchar(255) NULL,
	longitude varchar(255) NULL,
	ponto_referencia varchar(255) NULL,
	url varchar(255) NULL,
	CONSTRAINT parada_pkey PRIMARY KEY (id)
);

CREATE TABLE public.perfis (
	usuario_id int8 NOT NULL,
	perfis int4 NULL
);

ALTER TABLE public.perfis ADD CONSTRAINT fkiso72ajmkk36lw7dqjva1h8hl FOREIGN KEY (usuario_id) REFERENCES usuario(id);

CREATE TABLE public.rota (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	horario_chegada timestamp NULL,
	horario_saida timestamp NULL,
	cidade_destino_id int8 NULL,
	cidade_origem_id int8 NULL,
	CONSTRAINT rota_pkey PRIMARY KEY (id)
);

ALTER TABLE public.rota ADD CONSTRAINT fkd8a1lwfhm749cyx3vfg3a5409 FOREIGN KEY (cidade_origem_id) REFERENCES cidade(id);
ALTER TABLE public.rota ADD CONSTRAINT fkxtglbh4o5o665myyste59n87 FOREIGN KEY (cidade_destino_id) REFERENCES cidade(id);

CREATE TABLE public.rota_parada (
	rota_id int8 NOT NULL,
	parada_id int8 NOT NULL
);

ALTER TABLE public.rota_parada ADD CONSTRAINT fk5x92xw56luoxu0n43b07eenqk FOREIGN KEY (parada_id) REFERENCES parada(id);
ALTER TABLE public.rota_parada ADD CONSTRAINT fkpiccie7bbe8vafgm1524abcpl FOREIGN KEY (rota_id) REFERENCES rota(id);

