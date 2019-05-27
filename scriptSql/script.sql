create database lab03wesleysistematransportadora;

use lab03wesleysistematransportadora;

create table cliente(

  idCliente int primary key auto_increment, 
  nomeCliente VARCHAR(70), 
  enderecoCliente VARCHAR(100), 
  contatoCliente VARCHAR(30)
  
);

update cliente set contatoCliente = '99547859' where idCLiente = 8;

insert into Cliente (nomeCliente, enderecoCliente, contatoCliente)
     values ('Wesley Eduardo', 'São Luís', 981650805);
      
desc cliente;     
select * from cliente;

create table cidade(

  idCidade int primary key auto_increment, 
  nomeCidade VARCHAR(50), 
  ufCidade VARCHAR(30), 
  taxaCidade double
  
);


insert into cidade (nomeCidade, ufCidade, taxaCidade)
     values ('Belém', 'Pará', 50.0);
  
desc cidade;  
select * from cidade;

update cidade set taxaCidade = 1 where idCidade = 1;

create table frete(

  idFrete int primary key auto_increment, 
  id_Cidade int not null,
  id_Cliente int not null,
  descricaoFrete VARCHAR(50), 
  pesoFrete double, 
  valorFrete double,
  
  constraint  foreign key (id_Cidade)
  references cidade (idCidade),
  
  constraint  foreign key (id_Cliente)
  references cliente (idCliente)
  
);

insert into frete (id_Cidade, id_Cliente, descricaoFrete, pesoFrete, valorFrete)
     values ('1','1','Ligar Antes de Entregar', '6.8', 144.0);
       
 desc frete;
select * from frete;

/*
Recuperar o valor do frete, que deve ser calculado através do peso multiplicado por
um valor fixo (por exemplo R$ 10,00), acrescido da taxa de entrega associada a cada
cidade de destino.*/
select frete.idFrete, frete.descricaoFrete, frete.valorFrete from frete frete where idFrete = 1;


/*Recuperar uma lista com todos os fretes de um determinado cliente.*/
select idFrete, id_Cidade, id_Cliente, descricaoFrete, pesoFrete, valorFrete from frete where id_Cliente=4;


/*Recuperar o frete de maior valor (custo)*/
select idFrete , descricaoFrete, valorFrete from frete where valorFrete = ( select max(valorFrete) from frete );


select * from frete;

select * from cidade;

/*Recuperar a cidade que é destinatária da maior quantidade de fretes*/
select id_Cidade, count(frete.idFrete) as qtdFretesAssociados
from frete frete
group by id_Cidade
order by qtdFretesAssociados desc
limit 1;

/*Recuperar Quantidade de Fretes da Cidade Com Mais Fretes*/
select count(frete.idFrete) as qtdFretesAssociados
from frete frete
group by id_Cidade
order by qtdFretesAssociados desc
limit 1;