# votingEduardoBrusch
Votação de assuntos por: Eduardo Brusch

Contato: eduardobrusch@hotmail.com

## Pré requisitos:
* Java openJDK 11+
* Gradle 6+
* Git
* Mysql 5.7
* tomcat 9+
* Postman (para teste)

## Instalação
No atual momento a instalação é feita com a build no gradle com o comando `glradle build` e colocando o arquivo no 
diretório de deploy do Tomcat. Para fins de verificação, pode ser executado no servidor embutido no eclipse ou Intellij.
Não existe um profile configurado.

É necessário configurar o seu usuário do banco de dados no arquivo `application.properties` .

* Para realizar os testes, basta importar a coleção no postman, menu Import (canto superiot esqurdo)
* Para importar a coleção, clicar no botão de configuração dos environments e clicar no botão import, na parte inferior.

## Observações:
Na pasta raiz do projeto, está disponível a pasta postman com a coleção e o environment para teste.
API de validação de CPF apresenta comportamento inconsistente. 
Ela retorna *"ABLE_TO_VOTE"* e *"UNABLE_TO_VOTE"* utilizando o mesmo cpf, com reprodução intermitente.

Exemplos de CPF que podem reproduzir o problema:
* 69785331067
* 79324986007

## Decisões de Desenvovimento
O meu processo padrão é usar o [Spring Initializr](https://start.spring.io/), na última release, com web, 
 teste, jpa, hibenate e lombok.  
 
 Para tentar promover o entendimento das decisões de desenvolvimento,
 eu criei uma lista de possíveis melhorias que eu mesmo implementaria: 

* Terminar implementação do DockerFile para facil deploy (excluido da versão final)
* Criar validador customizado retirando os métodos de validação do serviço
* Colocar todas as mensagems como Strings internacionalizáveis EN-US e PT-BR
* Criar excessões de negócio, não apenas jogar runtime
* OU adiconar um padrão de criação de erros, com detalhes do erro de negócio, de vez de excessões.
* Melhorar o *errorHandler* para cercar melhor os cenários do cliente de validação de CPF e outros. Atualmente todos os erros tem a mesma resposta genérica.
* Aumentar a quantidade de detalhes nas respostas da api.
Exemplo: 
    * tempo restande na sessão
    * data de criação e encerramento
    
* Autenticação com OAuth2 (apesar de não ser um requisito)
* Adicionar variáveis de ambiente para facilitar pipeline
