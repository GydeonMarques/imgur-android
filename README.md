# Integração simples utilizando a api imgur

A integração da api da imgur a qual foi utilizado nesse projeto, foi utilizado o Retrofit,
é para realizar download/cache das imagens, utilizei o Glide para fazer este trabalho.

Para ouvirmos eventos de desconexão/conexão de internet, utilizei a lib RxNetwork, assim
monitorar esses eventos se tornam bem mais prático e intuitivo.

Para deixar um visual um pouco mais agradável e intuitivo, utilizei a lib Lottie para deixar as
coisas um pouco mais animadas...rsrs

E por fim para deixar o apk um pouco mais seguro e evitar/dificultar possíveis engenharia
reversa, ativamos o ProGuard para ofuscar todo o código do apk é também remover possíveis
arquivos que não são utilizados, assim temos uma compilação resultando em um apk bem
menor.
