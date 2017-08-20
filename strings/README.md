# Desafio 1: Strings

Após ler o coding style do kernel Linux, você descobre a mágica que é 
ter linhas de código com no máximo 80 caracteres cada uma.

Assim, você decide que de hoje em diante seus e-mails enviados também 
seguirão um padrão parecido e resolve desenvolver um plugin para te ajudar
com isso. Contudo, seu plugin aceitará no máximo 40 caracteres por linha.

## Execução

Para executar o código é preciso ter Docker instalado em seu computador. O download pode ser realizado 
[aqui](https://www.docker.com).

Com Docker instalado, execute os comandos abaixo para criar uma máquina virtual, iniciá-la e utilizá-la para executar o 
código.

```
sudo apt-get install virtualbox
docker-machine create --driver virtualbox default
docker-machine start default
eval "$(docker-machine env default)"
```

Faça download ou clone este projeto e, na pasta raíz, execute o comando abaixo para compilar o projeto.
```
./gradlew clean build
```

Navegue até `src/main/docker/` e execute os comandos abaixo para criar a imagem e um container.

```
docker build -t qaware-oss-docker-registry.bintray.io/base/debian8-jre8 .
docker run -p 2890:2890 qaware-oss-docker-registry.bintray.io/base/debian8-jre8 .
```

Para formatar um texto é preciso fazer uma requisição do tipo `POST` em 
[http://192.168.99.100:2890/strings](http://192.168.99.100:2890/strings) com `Content-Type: text/plain` no cabeçalho. 
A tabela abaixo mostra os parâmetros da requisição.

| Parâmetro | Significado                                                  | Valor default |               |
|-----------|--------------------------------------------------------------|---------------|:-------------:|
| text      | Texto a ser formatado.                                       |       -       | (obrigatório) |
| max       | Número máximo de caracteres por linha.                       |       40      |   (opcional)  |
| justify   | Booleano indicando se as linhas do texto serão justificadas. |     false     |   (opcional)  |

## Exemplo input

```json
{
	"text": "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\nAnd God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.",
	"max": 40,
	"justify": false
}
```

## Exemplo output

```json
{
    "status": "OK",
    "data": "In the beginning God created the heavens\nand the earth. Now the earth was\nformless and empty, darkness was over\nthe surface of the deep, and the Spirit\nof God was hovering over the waters.\nAnd God said, \"Let there be light,\" and\nthere was light. God saw that the light\nwas good, and he separated the light\nfrom the darkness. God called the light\n\"day,\" and the darkness he called\n\"night.\" And there was evening, and\nthere was morning - the first day."
}
```