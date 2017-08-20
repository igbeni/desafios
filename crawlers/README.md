# Desafio 2: Crawlers

Parte do trabalho na IDwall inclui desenvolver *crawlers/scrapers* para coletar dados de websites.
Como nós nos divertimos trabalhando, às vezes trabalhamos para nos divertir!

O Reddit é quase como um fórum com milhares de categorias diferentes. Com a sua conta, você pode navegar por assuntos técnicos, ver fotos de gatinhos, discutir questões de filosofia, aprender alguns life hacks e ficar por dentro das notícias do mundo todo!

Subreddits são como fóruns dentro do Reddit e as postagens são chamadas *threads*.

Para quem gosta de gatos, há o subreddit ["/r/cats"](https://www.reddit.com/r/cats) com threads contendo fotos de gatos fofinhos.
Para *threads* sobre o Brasil, vale a pena visitar ["/r/brazil"](https://www.reddit.com/r/brazil) ou ainda ["/r/worldnews"](https://www.reddit.com/r/worldnews/).
Um dos maiores subreddits é o "/r/AskReddit".

Cada *thread* possui uma pontuação que, simplificando, aumenta com "up votes" (tipo um like) e é reduzida com "down votes".

Sua missão é encontrar e listar as *threads* que estão bombando no Reddit naquele momento!
Consideramos como bombando *threads* com 5000 pontos ou mais.

## Entrada
- Lista com nomes de subreddits separados por ponto-e-vírgula (`;`). Ex: "askreddit;worldnews;cats"

### Parte 1
Gerar e imprimir uma lista contendo número de upvotes, subreddit, título da thread, link para os comentários da thread, link da thread.
Essa parte pode ser um CLI simples, desde que a formatação da impressão fique legível.

### Parte 2
Construir um robô que nos envie essa lista via Telegram sempre que receber o comando `/NadaPraFazer [+ Lista de subrredits]` (ex.: `/NadaPraFazer programming;dogs;brazil`)


Qualquer método para coletar os dados é válido. Caso não saiba por onde começar, procure por SeleniumHQ (Java), PhantomJS (Javascript) e Scrapy (Python).

### Execução do código

Compile o código com o comando abaixo.
```
./gradlew clean build
```

Para a parte 1, execute a classe App, entrando com uma lista de subreddits separados por ponto-e-vírgula (`;`). A saída será uma lista com as top *threads* do momento no Reddit.

Para a parte 2, execute a classe Bot e abra o Telegram. Pesquise pelo bot `@QuimeraBot` e, dentro do chat com o mesmo, digite `/NadaPraFazer` seguido de uma lista de subreddits separados por ponto-e-vírgula (`;`). (ex.: `/NadaPraFazer programming;dogs;brazil`). O bot lhe enviará uma lista com as top *threads* do momento no Reddit.
