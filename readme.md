Prova de Conceito Eduardo A. T Borges
=================

Aplicação desenvolvida nos padrões de API Spring boot.
-------------------
Configurações de aplicação:
- As configurações de aplicação estão descritas no arquivo application.properties que pode ser encontrado no path src.main.resources
- A criação da estrutura de banco de dados é feita através do Liquibase, sendo executado os arquivos no path src.main.resources.baseDados.liquibase-changelog.xml.
Caso o apontamento do banco no arquivo application.properties esteja correto, o Liquibase irá se encarregar da criação de toda a estrutura de banco.
- Após criada a estrutura de banco, deve-se executar a classe br.com.poc.MainApplication (Spring Boot) para todar a aplicação.

OBSERVAÇÕES:
- Devido ao tempo de desenvolvimento, algumas funcionalidades como Paginação foram implementadas parcialmente.
- Talvez após criar a estrutura via Liquibase e ocorrer erro ao subir novamente a app, deve-se trocar a propriedadw spring.liquibase.enabled=true no application.properties para false.

