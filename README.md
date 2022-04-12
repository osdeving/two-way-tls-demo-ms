# Two Way TLS API Server Demo

Exemplo de conexão Two Way TLS

Leia mais sobre TLS aqui: https://hpbn.co/transport-layer-security-tls/#optimizing-for-tls

Veja mais detalhes sobre implementação em Java aqui: https://github.com/Hakky54/mutual-tls-ssl

## Preparando o Ambiente

Os arquivos .jks e .cer devem ficar no classpath.

client.cer e server.cer são certicados do cliente e servidor, respectivamente.


identity.jks e truststore.jks são arquivos com keys para certificados do cliente ou certificados do servidor


Em uma comunicação Two Way TLS, o cliente e o servidor precisam trocar certificados,<br>
então ambos devem criar um certificado e enviar para sua contra-parte que irá incluí-lo no truststore.

### Criar public/private key para o servidor

```
keytool -v -genkeypair -dname "CN=Willams,OU=Brasil,O=Dev,C=BR" -keystore ./identity.jks -storepass secret -keypass secret -keyalg RSA -keysize 2048 -alias server -validity 3650 -deststoretype pkcs12 -ext KeyUsage=digitalSignature,dataEncipherment,keyEncipherment,keyAgreement -ext ExtendedKeyUsage=serverAuth,clientAuth -ext SubjectAlternativeName:c=DNS:localhost,DNS:raspberrypi.local,IP:127.0.0.1
```

### Criar certificado

server.cer será enviado para o cliente

```
keytool -v -exportcert -file ./server.cer -alias server -keystore ./identity.jks -storepass secret -rfc
```

### Incluir cerficado do cliente no truststore

client.cer será obtido do cliente

```
keytool -v -importcert -file ./client.cer -alias client -keystore ./truststore.jks -storepass secret -noprompt
```
