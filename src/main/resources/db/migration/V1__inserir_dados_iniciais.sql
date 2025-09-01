-- Inserir Contatos
INSERT INTO contato (telefone, celular, email)
VALUES ('1133224455', '1199887766', 'joao@email.com');

INSERT INTO contato (telefone, celular, email)
VALUES ('1144335566', '1199776655', 'maria@email.com');

-- Inserir Endereços
INSERT INTO endereco (logradouro, numero, cidade, uf, cep)
VALUES ('Rua das Flores', '123', 'São Paulo', 'SP', '01000-000');

INSERT INTO endereco (logradouro, numero, cidade, uf, cep)
VALUES ('Av. Paulista', '1000', 'São Paulo', 'SP', '01310-000');

-- Inserir Clientes vinculando contato e endereço
INSERT INTO cliente (nome, documento, nascimento, sexo, contato_id, endereco_id)
VALUES ('João da Silva', '12345678900', '1985-10-12', 'M', 1, 1);

INSERT INTO cliente (nome, documento, nascimento, sexo, contato_id, endereco_id)
VALUES ('Maria Oliveira', '98765432100', '1990-05-25', 'F', 2, 2);