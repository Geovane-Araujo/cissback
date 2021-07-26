# Instruções

Neste arqquivo contém um resumo de como o projeto funciona
***********************************************************************************************************

Classes Dynamic, DynamicController, DynamicResource

Por se tratar de um registro que só envolve 1 grid e uma tabela
este método seria dispensável, entretanto não quis mudar a forma como faço em meus projetos

Estad classes são responsáves por fazer a montagem dinamica dos grids de acordo com as rotas passadas
são 2 parametros:

Dynamic que nos interessa a propriedade route, filters, orders e paging
e outro é um Objeto do tipo Connecions com a conexão ja aberta

Na verdade teria um 3º parametro que seria o token
Como trabalho com arquitetura Multitenancy(aprendi esse termo semana passada rsrsrs), cada cliente tem seu proprio bancoo
de dados nesse token contém o id do banco de dados, posso explicar melhor em uma oportunidade cso seja do interesse

ai cria um componente unico no front-end e conforme o usuário vai navegando pelos menus  aqui faz a montagem das tabelas
pois retorna os headers e os dados, assim como a quantidade de dados e a pagina

ta meio feinho a logica mas aos poucos vamos melhorando rsrsrsrs

**************************************************************************************************************************
tabela do banco de dados dynamicQuery

é com ela que o método dynamicObject faz a montagem, pois nela está contida a rota que é um atributo unico, quis deixar
como string pra melhor entendimento, pois mnu_funcionario seria melhor que id 3(exemplo),
contém a sql da rota e a tabela base com este ultimo fazemos a contagem dos registros

são 3 consultas que é feito 1 no Banco de dados master pra pegar a sql, e duas no banco do locatário
fiz alguns testes de performance mas o ganho de tempo de execução comparado ao método manual não tras grandes problemas
visto que geralmente trabalho com 20 registros por tabela

************************************************************************************************************************
Lib Atom Framework

Esta é uma biblioteca que eu mesmo fiz pra agilizar o desenvolvimento, é similar ao hibernate
só que o hibernate faz o mapeamento do objeto para o banco, o atom faz o contrario
faz o mapeamento do banco para o objeto.

Criei esta lib porque no hibernate trabalhar com arquitetura Multitenancy é um parto, da pra fazer mas é
muito dificil a configuração.

Com o Atom eu mesmo controlo a conexão com o banco e posso agir como quiser podendo conectar com qual eu quiser em tempo de
execução.

ainda está em desenvolvimento, há muito o que melhorar ainda, mas para operações simples de crud já está estavel

Fiz alguns testes de performance ele se encontra no repositório
https://github.com/Geovane-Araujo/databasecomparation

o Atom Framework está no:
https://github.com/Geovane-Araujo/atom-framework

Obs: para o projeto funcionar é necessário adicionala, não consegui deixar ainda em algum repositorio maven
