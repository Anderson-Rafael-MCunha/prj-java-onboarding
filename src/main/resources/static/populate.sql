-- populate initial data
-- table client_type
insert into onboarding.client_type (id, description) values
(1,'PF'),
(2,'PJ');


-- populate initial data
-- table client_type
insert into onboarding.address (id, country, state, city, neighborhood, street, number, complement, zipcode ) values
(1, 'BRAZIL', 'SAO PAULO', 'CAMPINAS', 'VILA NOVA', 'RUA ABC', 10, 'AP 01', '12345-012'),
(2, 'BRAZIL', 'SAO PAULO', 'CAMPINAS', 'VILA NOVA', 'RUA DEF', 20, '', '12345-013'),
(3, 'BRAZIL', 'MINAS GERAIS', 'BELO HORIZONTE', 'VILA VELHA', 'RUA MINAS', 0, 'N/A', '12345-014'),
(4, 'BRAZIL', 'RIO DE JANEIRO', 'RIO DE JANEIRO', 'BARRA', 'RUA IPA', 15, 'AP 14', '12345-015');


-- populate initial data
-- table client
insert into onboarding.client (id, client_adress_id, client_type_id, pep, birth_date, created_at, document, email, name, status) values
(1, 2, 1, false, '1997-08-29', '2025-03-22 16:31:00', '12345678910', 'client@yahoo.com','john oak','active');


-- populate phone
insert into onboarding.phone (id, client_phone_id, area, ddd, number, phone_type, phone_status) values
(1, 1, '55', '12', '999999999','celular','ativo'),
(2, 1, '55', '12', '12345678','residencial','inativo');